package com.liangjianwei.customproject.AsyncHttp;

public interface OnHttpTaskListener<T> {
    /**
     * 执行Http Task前的预处理
     */
    void onStart();

    /**
     * Http Task出现错误
     */
    void onTaskError(int resposeCode);

    /**
     * Http Task成功执行结束
     *
     * @param t 返回的数据对象
     */
    void onFinishTask(T bean);
}
