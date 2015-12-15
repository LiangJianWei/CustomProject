package com.liangjianwei.customproject.HttpRequest;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpRequest {


    private OkHttpClient client = null;

    private HttpRequest(Context context, int maxCacheSize, File cachedDir, final int maxCacheAge, CacheType cacheType, List<Interceptor> netWorkinterceptors, List<Interceptor> interceptors) {
        client = new OkHttpClient();
        if (cachedDir != null) {
            client.setCache(new Cache(cachedDir, maxCacheSize));
        } else {
            client.setCache(new Cache(context.getCacheDir(), maxCacheSize));
        }
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", String.format("max-age=%d", maxCacheAge))
                        .build();
            }
        };
        client.networkInterceptors().add(cacheInterceptor);
        if (netWorkinterceptors != null && !netWorkinterceptors.isEmpty()) {
            client.networkInterceptors().addAll(netWorkinterceptors);
        }
        if (interceptors != null && !interceptors.isEmpty()) {
            client.interceptors().addAll(interceptors);
        }

        long MAX_TIMEOUT = 10;
        client.setConnectTimeout(MAX_TIMEOUT, TimeUnit.SECONDS); //设置连接超时  以秒为单位
        client.setWriteTimeout(MAX_TIMEOUT, TimeUnit.SECONDS); //设置连接超时  以秒为单位
        client.setReadTimeout(MAX_TIMEOUT, TimeUnit.SECONDS); //设置连接超时  以秒为单位
    }

    public HttpRequest initDefault(Context context) {
        return new Builder(context).build();
    }


    /**
     * get 请求
     */
    public void get(final String url, final CacheType cacheType, final Headers headers, final Callback callback) {
        switch (cacheType) {
            case ONLY_NETWORK://仅网络请求
                getDataFromNetwork(url, headers, callback);
                break;
            case ONLY_CACHED://仅缓存请求
                getDataFromCached(url, headers, callback);
                break;
            case CACHED_ELSE_NETWORK://先请求缓存，没有则走网络
                getDataFromCached(url, headers, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        getDataFromNetwork(url, headers, callback);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.code() == 200) {
                            callback.onResponse(response);
                        } else {
                            getDataFromNetwork(url, headers, callback);
                        }
                    }
                });
                break;
            case NETWORK_ELSE_CACHED://先网络请求，请求失败则走缓存
                getDataFromNetwork(url, headers, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        getDataFromCached(url, headers, callback);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.code() == 200) {
                            callback.onResponse(response);
                        } else {
                            getDataFromCached(url, headers, callback);
                        }
                    }
                });
                break;
        }
    }

    /**
     * post 请求
     */
    public void post(String url,Map<String,String> params,Headers headers,Callback callback){
        post(url,params,headers,null,null,callback);
    }

    /**
     * get 网络请求数据
     */
    private void getDataFromNetwork(final String url, Headers headers, final Callback callback) {
        Log.d("httpUtils", "getDataFromNetwork");
        getData(url, CacheControl.FORCE_NETWORK, headers, callback);
    }

    /**
     * get 缓存数据请求
     */
    private void getDataFromCached(String url, Headers headers, final Callback callback) {
        Log.d("httpUtils", "getDataFromCached");
        getData(url, CacheControl.FORCE_CACHE, headers, callback);
    }


    /**
     * get 获取数据
     */
    private void getData(String url, final CacheControl cacheControl, Headers headers, final Callback callback) {
        final Request.Builder requestBuilder = new Request.Builder().url(url).cacheControl(cacheControl);
        if (headers != null) {
            requestBuilder.headers(headers);
        }
        requestBuilder.tag(url);
        final Request request = requestBuilder.build();
        getData(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() == 504) {
                    if (CacheControl.FORCE_CACHE == cacheControl) {
                        callback.onFailure(request, new IOException("cached not found"));
                        return;
                    }
                }
                callback.onResponse(response);
            }
        });
    }

    /**
     * get
     */
    private void getData(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }

    /**
     * post 请求
     */
    private void post(String url, Map<String, String> params, Headers headers, String encodedKey, String encodedValue, final Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                formEncodingBuilder.add(key, params.get(key));
            }
        }
        if (!TextUtils.isEmpty(encodedKey) && !TextUtils.isEmpty(encodedValue)) {
            formEncodingBuilder.addEncoded(encodedKey, encodedValue);
        }
        final Request.Builder requestBuilder = new Request.Builder().url(url).post(formEncodingBuilder.build());
        if (headers != null) {
            requestBuilder.headers(headers);
        }
        requestBuilder.tag(url);
        post(requestBuilder.build(), callback);
    }

    /**
     * post
     */
    private void post(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }


    /**
     * 通过url来取消一个请求  如果一个请求正在进行IO操作，会抛出异常，如果使用自定义的Request，必须设置request的Tag为url才能有效
     */
    public void cancel(String url) {
        client.cancel(url);
    }


    public static class Builder {
        private int maxCachedSize = 5 * 1024 * 1024;
        private File cachedDir;
        private Context context;
        private List<Interceptor> networkInterceptors;
        private List<Interceptor> interceptors;
        private int maxCacheAge = 3600 * 12;
        private CacheType cacheType = CacheType.NETWORK_ELSE_CACHED;


        public Builder(Context context) {
            this.context = context;
        }

        private Builder() {
        }

        public HttpRequest build() {
            return new HttpRequest(context, maxCachedSize, cachedDir, maxCacheAge, cacheType, networkInterceptors, interceptors);
        }

        public Builder cacheType(CacheType cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder cachedDir(File cachedDir) {
            this.cachedDir = cachedDir;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        /**
         * 拦截器使用可参考这篇文章  <a href="http://www.tuicool.com/articles/Uf6bAnz">http://www.tuicool.com/articles/Uf6bAnz</a>
         */
        public Builder interceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder maxCachedSize(int maxCachedSize) {
            this.maxCachedSize = maxCachedSize;
            return this;
        }

        /**
         * 拦截器使用可参考这篇文章  <a href="http://www.tuicool.com/articles/Uf6bAnz">http://www.tuicool.com/articles/Uf6bAnz</a>
         */
        public Builder networkInterceptors(List<Interceptor> networkInterceptors) {
            this.networkInterceptors = networkInterceptors;
            return this;
        }

        public Builder maxCacheAge(int maxCacheAge) {
            this.maxCacheAge = maxCacheAge;
            return this;
        }
    }


//    client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
    //请求的url链接后面添加数据
//                request = request.newBuilder().url(request.urlString()+"s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=98996590_hao_pg&wd=he&rsv_pq=f4953a2f000ff24d&rsv_t=04cbC9bfdepmyhGqXX6mksqeAGbXZUDDVjdafZ4rpa6%2BPtgbIZHlYi2IqlmTxdO8OxBTvKr3&rsv_enter=1&rsv_sug3=2&rsv_sug1=1&rsv_sug2=0&inputT=680&rsv_sug4=680").build();
//                return chain.proceed(request);
//
//            }
//        });

}
