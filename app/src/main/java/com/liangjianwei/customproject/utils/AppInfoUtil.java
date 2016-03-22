package com.liangjianwei.customproject.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;

import com.liangjianwei.customproject.Base.BaseApplication;


/**
 * 获取应用程序名称, 应用程序版本
 *
 * @author Luke
 */
public class AppInfoUtil {

    private AppInfoUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        String appName = null;

        try {
            Context context = BaseApplication.getContext();

            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;

            appName = context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            appName = null;
            e.printStackTrace();
        }

        return appName;
    }

    /**
     * 获取应用程序包名
     */
    public static String getAppPkgName() {
        String pkgName = null;

        try {
            Context context = BaseApplication.getContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            pkgName = packageInfo.applicationInfo.packageName;
        } catch (NameNotFoundException e) {
            pkgName = null;
            e.printStackTrace();
        }

        return pkgName;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName() {
        String versionName = null;

        try {
            Context context = BaseApplication.getContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            versionName = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            versionName = "";
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * 获取应用程序版本code
     *
     * @return 当前应用的版本code
     */
    public static int getVersionCode() {
        int versionCode = 0;

        try {
            Context context = BaseApplication.getContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            versionCode = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            versionCode = 0;
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * 获取渠道名称
     *
     * @return 渠道名称
     */
    public static String getChannel() {
        ApplicationInfo ai = null;
        String channel = null;

        try {
            Context context = BaseApplication.getContext();
            ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            channel = (String) ai.metaData.get("UMENG_CHANNEL");
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return TextUtils.isEmpty(channel) ? "" : channel;
    }

    /**
     * 调用系统安装程序
     *
     * @return apk文件路径
     */
    public static void installApk(String apkPath) {
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}