package com.example.javen.customlibrary.utils;

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

            StringBuilder buf = new StringBuilder("");

            for (byte aB : b) {
                val = aB;

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