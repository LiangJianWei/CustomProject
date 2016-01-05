package com.liangjianwei.customproject.AsyncHttp;

import android.content.Context;
import android.util.Log;

import com.liangjianwei.customproject.BaiduApi.BaiduApi;
import com.liangjianwei.customproject.Parser.DefaultParser;
import com.liangjianwei.customproject.Parser.Parser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;


public class HttpRequest {
    private final static String CONTENT_TYPE = "application/x-www-form-urlencoded";

    public final static String VERSION = "1.0";
    private static final int TIME_OUT = 30000;
    private static final String TAG = "HttpRequest";
    private static final AsyncHttpClient httpClient = new AsyncHttpClient();

    static {
        httpClient.setResponseTimeout(TIME_OUT);
        httpClient.setConnectTimeout(TIME_OUT);
        httpClient.setMaxRetriesAndTimeout(0, TIME_OUT);
    }

    /**
     * Get请求
     */
    public static <T> void excuteHttpGet(Context context, Parser<T> parser, String url, final RequestParams params, final Class<T> tClass, final OnHttpTaskListener<T> httpTaskListener) {
        httpClient.get(url, params, getHttpResponseHandler(context, parser, tClass, httpTaskListener));
    }

    /**
     * post请求
     */
    public static <T> void excuteHttpPost(Context context, Parser<T> parser, String url, final RequestParams params, final Class<T> tClass, final OnHttpTaskListener<T> httpTaskListener) {
        httpClient.post(context, url, addHeader(), params, CONTENT_TYPE, getHttpResponseHandler(context, parser, tClass, httpTaskListener));
    }

    /**
     * 取消post请求
     *
     * @param context
     */
    public static void CancelHttp(Context context) {
        httpClient.cancelRequests(context, true);
    }

    private static <T> TextHttpResponseHandler getHttpResponseHandler(final Context context, final Parser<T> parser, final Class<T> tClass, final OnHttpTaskListener<T> httpTaskListener) {
        TextHttpResponseHandler responseHandler = new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                if (httpTaskListener != null) {
                    httpTaskListener.onStart();
                }
            }

            @Override
            public void onSuccess(int code, Header[] header, String content) {
                Log.d(TAG, "---------------- post返回值-->" + content);

                T bean = null;

                if (parser == null) {
                    bean = new DefaultParser<T>().parser(content, tClass);
                } else {
                    bean = parser.parser(content, tClass);
                }
                if (httpTaskListener != null) {
                    httpTaskListener.onFinishTask(bean);
                }
            }

            @Override
            public void onFailure(int code, Header[] header, String content, Throwable throwable) {
                Log.d(TAG, "------------- code = " + code + "   --- content = " + content);

                if (httpTaskListener != null) {
                    httpTaskListener.onTaskError(code);
                }
            }
        };
        return responseHandler;
    }

    private static Header[] addHeader() {
        BasicHeader deviceHeader = new BasicHeader("apikey", BaiduApi.BAIDU_API_KRY);
        return new BasicHeader[]{deviceHeader};
    }
}
