/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Mr.liang on 2015-12-2914:37
 */
public abstract class CommontAdapter<T> extends BaseAdapter {



    private final int mItemLayoutId;
    private Context mContext;
    private List<T> mLists;

    public CommontAdapter(Context mContext, List<T> mLists, int mItemLayoutId) {
        this.mContext = mContext;
        this.mLists = mLists;
        this.mItemLayoutId = mItemLayoutId;
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public T getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = getViewHolder(position, convertView, parent);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }


}
