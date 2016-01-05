package com.liangjianwei.customproject.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
    public static String getMD5Str(String str) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int val;

            StringBuffer buf = new StringBuffer("");
            int len = b.length;

            for (int offset = 0; offset < len; offset++) {
                val = b[offset];

                if (val < 0) {
                    val += 256;
                }

                if (val < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(val));
            }

            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}