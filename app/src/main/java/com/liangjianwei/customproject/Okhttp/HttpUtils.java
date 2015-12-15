package com.liangjianwei.customproject.Okhttp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.liangjianwei.customproject.HttpRequest.JsonGenerator;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.liangjianwei.customproject.parser.DefaultParser;
import com.liangjianwei.customproject.parser.Parser;
import com.liangjianwei.customproject.utils.MobileInfoUtils;

/**
 * OKHttp 工具类
 * <p/>
 * HttpUtils.get()  get方法
 * HttpUtils.post() post方法
 * HttpUtils.setEncryptJson()  http数据是否加密
 * HttpUtils.putParam()  http传输时需要传的参数
 */
public class HttpUtils {

    private static final String VERSION = "1.0";
    private Context context;
    private HttpRequest request;
    private String url;
    private Parser parser;
    /**
     * 缓存的类型
     */
    private CacheType cacheType;
    private Map params;
    private boolean useJson;
    private JsonGenerator jsonGenerator;


    public HttpUtils(Context context, String url, CacheType cacheType) {
        this.context = context;
        this.cacheType = cacheType;
        this.params = new HashMap();
        this.url = url;
        //实例化HttpRequest
        request = getRequest();
    }

    public HttpRequest getRequest() {
        return new HttpRequest.Builder(context).build();
    }

    /**
     * 数据是否加密
     */
    public void setEncryptJson(boolean useJson) {
        this.useJson = useJson;
        this.jsonGenerator = JsonGenerator.getJsonGenerator();
    }

    public void putParam(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }

        if (!this.useJson) {
            this.params.put(key, value);
        } else {
            this.jsonGenerator.putParam(key, value);
        }
    }

    public void putParam(String key, boolean value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (!this.useJson) {
            this.params.put(key, value);
        } else {
            this.jsonGenerator.putParam(key, value);
        }
    }

    public void putParam(String key, int value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (!this.useJson) {
            this.params.put(key, value);
        } else {
            this.jsonGenerator.putParam(key, value);
        }
    }

    public void putParam(String key, long value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (!this.useJson) {
            this.params.put(key, value);
        } else {
            this.jsonGenerator.putParam(key, value);
        }
    }

    public void putParam(String key, double value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (!this.useJson) {
            this.params.put(key, value);
        } else {
            this.jsonGenerator.putParam(key, value);
        }
    }

    public void putParam(HashMap<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return;
        }

        Set<Map.Entry<String, String>> setParams = paramsMap.entrySet();

        if (!this.useJson) {
            for (Map.Entry<String, String> entry : setParams) {
                this.params.put(entry.getKey(), entry.getValue());
            }
        } else {
            for (Map.Entry<String, String> entry : setParams) {
                this.jsonGenerator.putParam(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * get 请求
     */
    public <T> void get(Class<T> tClass, OnHttpTaskListener<T> onHttpTaskListener) {
        request.get(url, cacheType, null, getCallback(parser, tClass, onHttpTaskListener));
    }

    /**
     * psot请求
     */
    public <T> void post(Class<T> tClass, OnHttpTaskListener<T> onHttpTaskListener) {
        request.post(url, params, addHeader(),getCallback(parser, tClass, onHttpTaskListener));
    }


    private static <T> Callback getCallback(final Parser<T> parser, final Class<T> tClass, final OnHttpTaskListener onHttpTaskListener) {
        Callback callback;

        if (onHttpTaskListener != null) {
            onHttpTaskListener.onStart();
        }

        callback = new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                if (onHttpTaskListener != null) {
                    onHttpTaskListener.onError(request);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String content = response.body().string();
                Log.d("-----------", "onResponse: " + content);

                T bean;
                if (parser == null) {
                    bean = new DefaultParser<T>().parser(content, tClass);
                } else {
                    bean = parser.parser(content, tClass);
                }

                if (onHttpTaskListener != null) {
                    onHttpTaskListener.onSuccess(bean,content);
                }
            }
        };

        return callback;

    }

    /**
     * headers 所需要传的值
     */
    private static Headers addHeader() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("deviceid", MobileInfoUtils.getUUID());
        hashMap.put("ver", VERSION);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return Headers.of(hashMap);
    }

}
