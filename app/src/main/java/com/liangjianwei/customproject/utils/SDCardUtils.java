package com.liangjianwei.customproject.Utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

import com.liangjianwei.customproject.Base.BaseApplication;


/**
 * sdcard是否可用, 获取sdcard路径
 */
public final class SDCardUtils {
    // SD卡的最小剩余容量大小1MB
    private final static long DEFAULT_LIMIT_SIZE = 1;

    private SDCardUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());

            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;

            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();

            return freeBlocks * availableBlocks;
        }

        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }

        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;

        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 判断SD卡是否可用
     *
     */
    public static boolean isSDCardAvaiable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && getSDFreeSize() > DEFAULT_LIMIT_SIZE;
    }

    /**
     * 获取SDCard的剩余大小
     *
     * @return 多少MB
     */
    public static long getSDFreeSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * 获取SD卡的总容量
     *
     */
    public static long getSDAllSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 获取所有数据块数
        long allBlocks = sf.getBlockCount();
        // 返回SD卡大小
        return (allBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * 获取文件存储路径
     *
     */
    public static String getExternalFilePath() {
        //小米4手机取不到dir目录, 存储到sdcard上
        File dir = BaseApplication.getContext().getExternalFilesDir(null);

        if (isSDCardEnable() && dir != null) {//sdcard存在并且可以取到dir目录
            return dir.getAbsolutePath();
        }

        //sdcard不存在，取sdcard根目录
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
