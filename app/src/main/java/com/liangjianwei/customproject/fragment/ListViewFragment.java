/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.liangjianwei.customproject.Base.CommontAdapter;
import com.liangjianwei.customproject.Base.ViewHolder;
import com.liangjianwei.customproject.R;
import com.liangjianwei.customproject.bean.MobileAddressBean.RetDataEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LIANG JIAN WEI on 2015-12-29 15:22
 */
public class ListViewFragment extends Fragment {

    private ListView mListView;
    private View mView;
    private List<RetDataEntity> mList;

    public ListViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.listview_fragment_layout, container, false);
        mListView = (ListView) mView.findViewById(R.id.id_listView);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RetDataEntity dataEntity = new RetDataEntity();
            dataEntity.setCity("BeiJing" + i);
            dataEntity.setPhone("1342684658" + i);
            dataEntity.setProvince("HaiDian" + i);
            dataEntity.setSuit(i + "");

            mList.add(dataEntity);
        }


        mListView.setAdapter(new CommontAdapter<RetDataEntity>(getActivity(), mList, R.layout.item_layout) {
            @Override
            public void convert(ViewHolder holder, final RetDataEntity item) {
                holder.setText(R.id.id_city_text, item.getCity());
                holder.setText(R.id.id_provnice_text, item.getProvince());
                holder.setText(R.id.id_phone_text, item.getPhone());
                holder.setButton(R.id.id_btn, item.getSuit(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), "手机号：" + item.getPhone(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
