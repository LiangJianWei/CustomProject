package com.liangjianwei.customproject.Utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;


import java.util.Random;
import java.util.UUID;

import com.liangjianwei.customproject.Base.BaseApplication;

public final class MobileInfoUtils {
    private final static String reg = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";

    public synchronized static String getUUID() {
        String newUUId = "";

        String imeistr = getIMEI();
        String wifimacstr = getWifiMac();
        String androididstr = getAndroidID();
        String productorstr = getMobileProductor();
        String devicestr = getPhoneModel();
        String resolutionstr = getScreenResolution();

        // 只要有一个不为空则通过指定的算法生成
        if (imeistr.length() != 0 || wifimacstr.length() != 0 || androididstr.length() != 0) {
            // MD5(IMEI+WIFI_MAC+Android_ID+mobile_productor+device+屏幕分辨率)
            String srcstr = imeistr + wifimacstr + androididstr + productorstr + devicestr + resolutionstr;
            newUUId = "android" + MD5Util.getMD5Str(srcstr);
        }

        if (newUUId.length() < 8) { // 有可能新的算法无法计算出MD5值
            newUUId = UUID.randomUUID().toString();

            if (!newUUId.matches(reg)) { // 这里用正则表达式匹配是否满足uuid格式要求,如不符合则随机生成一段符合格式的字符串
                StringBuffer buffer = new StringBuffer();
                buffer.append(getRandomString(8)).append("-");
                buffer.append(getRandomString(4)).append("-");
                buffer.append(getRandomString(4)).append("-");
                buffer.append(getRandomString(4)).append("-");
                buffer.append(getRandomString(12));

                newUUId = buffer.toString();
            }
        }

        return newUUId;
    }

    private static String getIMEI() {
        String imei = "";

        try {
            TelephonyManager tm = (TelephonyManager) BaseApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);

            if (tm != null) {
                imei = tm.getDeviceId(); // 原来是指向SIM卡号，现在改正为正确的IMEI号 modify by
            }

            if (imei == null) {
                imei = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imei;
    }

    // 2. WIFI MAC
    private static String getWifiMac() {
        String str = "";

        try {
            WifiManager localWifiManager = (WifiManager) BaseApplication.getContext().getSystemService("wifi");

            if (localWifiManager != null) {
                WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
                str = localWifiInfo.getMacAddress();

                if (str == null) {
                    str = "";
                }
            }
        } catch (Exception e) {
        }

        return str;
    }

    // ANDROID ID
    private static String getAndroidID() {
        String androidId = "";

        try {
            androidId = Settings.System.getString(BaseApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

            if (androidId == null) {
                androidId = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return androidId;
    }

    // 6. SCREEN RESOLUTIOn
    private static String getScreenResolution() {
        String result = "800X480";

        DisplayMetrics metrics = BaseApplication.getContext().getResources().getDisplayMetrics();

        if (metrics != null) {
            result = "" + metrics.widthPixels + "X" + metrics.heightPixels;
        }

        return result;
    }

    private static String getMobileProductor() {
        String mp = "";

        try {
            mp = Build.MANUFACTURER;
            if (mp == null) {
                mp = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mp;
    }

    private static String getPhoneModel() {
        String model = "";

        try {
            model = Build.MODEL;

            if (model == null) {
                model = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    private static String getRandomString(int length) { // length表示生成字符串的长度
        final String base = "abcdefghijklmnopqrstuvwxyz0123456789"; // 生成字符串从此序列中取
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int baseLen = base.length();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(baseLen);
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }
}