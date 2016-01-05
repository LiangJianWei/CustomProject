package com.liangjianwei.customproject.Utils;

import android.util.Log;

/**
 * Created by Javen on 2015/12/4.
 * log信息工具类
 */
public class LogUtils {

    // 是否需要打印bug
    private final static boolean isDebug = true;

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

}
