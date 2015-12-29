/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liangjianwei.customproject.HttpRequest.ImageLoader;


public class ViewHolder {

    private SparseArray<View> views;
    private View mConvertView;

    private ViewHolder(Context mContext, ViewGroup parent, int layoutId, int position) {

        this.views = new SparseArray<>();
        mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        //set ViewTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder 对象
     *
     * @param mContext    当前activity
     * @param convertView 当前View
     * @param parent      当前viewGroup
     * @param layoutId    当前的layoutId
     * @param position    当前的position
     * @return 返回实例
     */
    public static ViewHolder get(Context mContext, View convertView, ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new ViewHolder(mContext, parent, layoutId, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 通过控件的Id 获取对应的控件 ，没有则加入views
     *
     * @param viewId 控件id
     */
    public <T extends View> T getView(int viewId) {

        View view = views.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取View实例
     */
    public View getConvertView() {
        return mConvertView;
    }


    /**
     * 设置文本信息
     */
    public ViewHolder setText(int controlId, String text) {
        TextView textView = getView(controlId);
        if (textView != null) {
            textView.setText(text);
        }
        return this;
    }

    /**
     * 设置button信息以及监听事件
     */
    public ViewHolder setButton(int controlId, String text, View.OnClickListener onClickListener) {

        Button btn = getView(controlId);
        if (btn != null) {
            if (text != null) {
                btn.setText(text);
            }
            if (onClickListener != null) {
                btn.setOnClickListener(onClickListener);
            }
        }
        return this;
    }

    /**
     * 设置imageVIew的资源ID
     */
    public ViewHolder setImageResource(int controlId, int resourceId) {
        ImageView imageView = getView(controlId);
        if (imageView != null) {
            imageView.setImageResource(resourceId);
        }
        return this;
    }

    /**
     * 设置url的网络imageView
     */
    public ViewHolder setImageUrl(int controlId, String ImageUrl) {
        ImageView imageView = getView(controlId);
        if (imageView != null) {
            ImageLoader.loadLocalImage(imageView, ImageUrl);
        }
        return this;
    }

    /**
     * 设置url的网络imageView 带默认图片
     */
    public ViewHolder setImageUrl(int controlId, int defaultImage, String ImageUrl) {
        ImageView imageView = getView(controlId);
        if (imageView != null) {
            ImageLoader.loadImage(imageView,defaultImage,ImageUrl);
        }
        return this;
    }



}
