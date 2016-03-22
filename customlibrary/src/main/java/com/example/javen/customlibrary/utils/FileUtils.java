package com.example.javen.customlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Javen on 2015/12/4.
 * 文件工具类
 */
public class FileUtils {

    // sdcard存储基本路径
    private final static String BASE_PATH = SDCardUtils.getExternalFilePath() + "/jrd";

    // 升级apk时的下载路径
    public final static String UPGRADE_APK_DOWNLOAD_PATH = BASE_PATH + "/apk";

    // 图片保存的路径(用户头像, 银行logo等等)
    public final static String IMAGE_PATH = BASE_PATH + "/images";

    static {
        initFilePath();
    }

    /**
     * 初始化文件保存路径
     */
    private static void initFilePath() {
        if (SDCardUtils.isSDCardEnable()) {// 如果sdcard可用
            createDir(UPGRADE_APK_DOWNLOAD_PATH);
            createDir(IMAGE_PATH);
        }
    }

    /**
     * 文件路径
     */
    private static void createDir(String filePath) {
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 保存图片到本地sdcard
     *
     * @param bitmap         图片bitmap
     * @param savedImageName 保存到本地的图片名称(不需要传入扩展名)
     */
    public static void saveImage(Bitmap bitmap, String savedImageName) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream outputStream = null;

        try {
            fileOutputStream = new FileOutputStream(new File(IMAGE_PATH, savedImageName));
            outputStream = new BufferedOutputStream(fileOutputStream);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存图片到本地sdcard
     *
     * @param imgByte        图片byte
     * @param savedImageName 保存到本地的图片名称(不需要传入扩展名)
     */
    public static void saveImage(byte[] imgByte, String savedImageName) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream outputStream = null;

        try {
            fileOutputStream = new FileOutputStream(new File(IMAGE_PATH, savedImageName));
            outputStream = new BufferedOutputStream(fileOutputStream);
            outputStream.write(imgByte, 0, imgByte.length);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过文件名称获取本地图片
     *
     * @param savedImageName 保存到本地的图片名称(不需要传入扩展名)
     */
    public static Bitmap getImage(String savedImageName) {
        String imgPath = IMAGE_PATH + "/" + savedImageName;
        File file = new File(imgPath);

        if (!file.exists()) {
            return null;
        }

        return BitmapFactory.decodeFile(imgPath);
    }

    public static boolean existFile(String path) {
        return new File(path).exists();
    }

    /**
     * 通过文件名称获取本地图片
     *
     * @param imgFilePath 保存到本地的图片完整路径
     */
    public static byte[] getLocalImage(String imgFilePath) {
        File file = new File(imgFilePath);

        if (!file.exists()) {
            return null;
        }

        FileInputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        byte[] imgByte = null;

        try {
            inputStream = new FileInputStream(imgFilePath);
            bufferedInputStream = new BufferedInputStream(inputStream);

            byteArrayOutputStream = new ByteArrayOutputStream();
            bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);

            byte[] buffer = new byte[4096];
            int len;

            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
            }

            bufferedOutputStream.flush();

            imgByte = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }

                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }

                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgByte;
    }

    public static void deleteImgFile(String fileName) {
        File file = new File(IMAGE_PATH + "/" + fileName);

        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }
}