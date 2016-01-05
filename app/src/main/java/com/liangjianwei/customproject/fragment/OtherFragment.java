/*
 * Copyright (c) 2016.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.liangjianwei.customproject.Activity.EventFristActivity;
import com.liangjianwei.customproject.R;

/**
 * Created by LIANG JIAN WEI on 2015-12-29 15:23
 */
public class OtherFragment extends Fragment {


    private Button eventnextbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment_layout, container, false);
        this.eventnextbtn = (Button) view.findViewById(R.id.event_next_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        eventnextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), EventFristActivity.class));

            }
        });
    }
}
