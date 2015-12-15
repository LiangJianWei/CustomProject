package com.liangjianwei.customproject.Okhttp;

import com.squareup.okhttp.Request;

/**
 * Created by Javen on 2015/12/11.
 */
public interface OnHttpTaskListener<T> {

    /**
     * 开始请求
     */
    void onStart();

    /**
     * 请求失败
     */
    void onError(Request request);

    /**
     * 请求成功
     */
    void onSuccess(T bean, String context);
}
