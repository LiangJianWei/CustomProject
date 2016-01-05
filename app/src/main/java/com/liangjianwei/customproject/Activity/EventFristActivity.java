/*
 * Copyright (c) 2016.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liangjianwei.customproject.EventBus.TestEventBus;
import com.liangjianwei.customproject.R;

import de.greenrobot.event.EventBus;

public class EventFristActivity extends AppCompatActivity {


    private TextView eventmsgtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_frist);
        this.eventmsgtext = (TextView) findViewById(R.id.event_msg_text);
        Button eventnextbtn = (Button) findViewById(R.id.event_next_btn);
        EventBus.getDefault().register(this);

        eventnextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventFristActivity.this, EventSecondActivity.class));
            }
        });
    }


    public void onEventMainThread(TestEventBus event) {

        String msg = "收到了secondActivity传过来的消息" + event.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        this.eventmsgtext.setText(msg);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
