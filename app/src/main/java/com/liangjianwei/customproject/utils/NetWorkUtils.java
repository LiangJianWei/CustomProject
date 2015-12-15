package com.liangjianwei.customproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.liangjianwei.customproject.BaseApplication;


/**
 * Created by Javen on 2015/12/4.
 * 网络状态工具类
 */
public class NetWorkUtils {
    public static boolean hasMobileNet() {
        ConnectivityManager connectivity = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
