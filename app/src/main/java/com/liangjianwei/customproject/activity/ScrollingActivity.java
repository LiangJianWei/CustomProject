/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.liangjianwei.customproject.Okhttp.HttpUtils;
import com.liangjianwei.customproject.R;

public class ScrollingActivity extends AppCompatActivity {


    private HttpUtils httpUtils;
    private String url = "https://app.junrongdai.com/appapi/getProjectListInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this, TestHttpActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(ScrollingActivity.this, ViewPagerActivity.class));
            return true;
        } else if (id == R.id.action_suspension) {
            startActivity(new Intent(ScrollingActivity.this, SuspensionActivity.class));
            return true;
        } else if (id == R.id.action_swipeRefresh) {
            startActivity(new Intent(ScrollingActivity.this, SwipeRefreshLayoutAct.class));
            return true;
        } else if (id == R.id.action_pullRefresh) {
            startActivity(new Intent(ScrollingActivity.this, PullRefreshLayoutAct.class));
            return true;
        } else if (id == R.id.action_recyclerview) {
            startActivity(new Intent(ScrollingActivity.this, RecylerViewAct.class));
            return true;

        } else if (id == R.id.action_cardview) {
            startActivity(new Intent(ScrollingActivity.this, CardViewAct.class));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
