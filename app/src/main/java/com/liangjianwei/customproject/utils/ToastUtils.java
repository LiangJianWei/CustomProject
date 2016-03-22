package com.liangjianwei.customproject.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liangjianwei.customproject.R;

/**
 * Created by Javen on 2015/12/4.
 * Toast工具类
 */
public class ToastUtils {
    private static TextView tvView;
    private static Toast toast = null;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, int message) {

        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);

            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }

        toast.show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }

        toast.show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int message) {
        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }

        toast.show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);
            toast.setDuration(duration);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }

        toast.show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        if (toast == null) {
            toast = new Toast(context);
            tvView = (TextView) View.inflate(context, R.layout.toast_layout, null);
            tvView.setText(message);
            toast.setDuration(duration);
            toast.setView(tvView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            tvView.setText(message);
        }

        toast.show();
    }

}