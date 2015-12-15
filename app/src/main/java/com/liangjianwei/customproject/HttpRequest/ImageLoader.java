package com.liangjianwei.customproject.HttpRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.liangjianwei.customproject.utils.FileUtils;

public final class ImageLoader extends com.nostra13.universalimageloader.core.ImageLoader {
    private final static Md5FileNameGenerator md5Generator = new Md5FileNameGenerator();

    private final static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(null)//加载过程中显示的图片
            .showImageForEmptyUri(null)//加载内容为空显示的图片
            .showImageOnFail(null)//加载失败显示的图片
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888).build();

    /**
     * 加载本地图片, 如果不存在从网络加载
     *
     * @param imgView 显示图片的视图
     * @param imgUrl  图片在服务器的url地址
     */
    public static void loadLocalImage(final ImageView imgView, final String imgUrl) {
        if (imgView == null || imgUrl == null) {
            return;
        }

        final String imgName = md5Generator.generate(imgUrl);

        if (!FileUtils.existFile(FileUtils.IMAGE_PATH + "/" + imgName)) {
            //如果本地不存在, 从网络加载
            getInstance().displayImage(imgUrl, imgView, options, new SimpleImageLoaderListener() {
                @Override
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    //网络加载成功, 保存到本地
                    FileUtils.saveImage(bitmap, imgName);
                }
            });
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("file:///").append(FileUtils.IMAGE_PATH).append("/").append(imgName);

            getInstance().displayImage(buffer.toString(), imgView, options, null);
        }
    }

    /**
     * 加载网络图片
     *
     * @param imgView 显示图片的视图
     * @param imgUrl  图片在服务器的url地址
     */
    public static void loadImage(final ImageView imgView, final String imgUrl) {
        if (imgView == null || imgUrl == null) {
            return;
        }

        getInstance().displayImage(imgUrl, imgView, options, null);
    }

    /**
     * 加载网络图片
     *
     * @param imgView 显示图片的视图
     * @param imgUrl  图片在服务器的url地址
     */
    public static void loadGraphicCodeImage(final ImageView imgView, final String imgUrl) {
        if (imgView == null || imgUrl == null) {
            return;
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                byte[] data = (byte[]) msg.obj;
                imgView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            }
        };

        new Thread() {
            @Override
            public void run() {
                InputStream inputStream = null;
                BufferedInputStream bufferedInputStream = null;

                ByteArrayOutputStream byteArrayOutputStream = null;

                HttpURLConnection conn = null;

                try {
                    URL url = new URL(imgUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(30000);
                    conn.setConnectTimeout(30000);

                    int respCode = conn.getResponseCode();

                    if (respCode == 200) {
                        inputStream = conn.getInputStream();
                        bufferedInputStream = new BufferedInputStream(inputStream);
                        byteArrayOutputStream = new ByteArrayOutputStream();

                        byte[] buffer = new byte[4096];
                        int len = -1;

                        while ((len = bufferedInputStream.read(buffer)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, len);
                        }

                        Message msg = Message.obtain();
                        msg.obj = byteArrayOutputStream.toByteArray();
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                            bufferedInputStream = null;
                        }

                        if (inputStream != null) {
                            inputStream.close();
                            inputStream = null;
                        }

                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                            byteArrayOutputStream = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (conn != null) {
                        conn.disconnect();
                        conn = null;
                    }
                }
            }
        }.start();
    }

    /**
     * 加载网络图片
     *
     * @param imgView      显示图片的视图
     * @param defaultImgId 默认显示的图片ID
     * @param imgUrl       图片在服务器的url地址
     */
    public static void loadImage(final ImageView imgView, int defaultImgId, final String imgUrl) {
        if (imgView == null || imgUrl == null) {
            return;
        }

        DisplayImageOptions aroundDetailOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImgId)            //加载过程中显示的图片
                .showImageForEmptyUri(null)                        //加载内容为空显示的图片
                .showImageOnFail(null)                                //加载失败显示的图片
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888).build();

        getInstance().displayImage(imgUrl, imgView, aroundDetailOptions, null);
    }

    private static class SimpleImageLoaderListener implements ImageLoadingListener {

        @Override
        public void onLoadingCancelled(String str, View view) {
        }

        @Override
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        @Override
        public void onLoadingFailed(String str, View view, FailReason reason) {
        }

        @Override
        public void onLoadingStarted(String str, View view) {
        }
    }
}