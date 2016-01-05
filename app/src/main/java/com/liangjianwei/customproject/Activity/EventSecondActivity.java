/*
 * Copyright (c) 2016.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liangjianwei.customproject.EventBus.TestEventBus;
import com.liangjianwei.customproject.R;

import de.greenrobot.event.EventBus;

public class EventSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_second);
        Button eventsendbtn = (Button) findViewById(R.id.event_send_btn);
        eventsendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestEventBus("SecondActivity 发消息了"));
            }
        });
    }
}
