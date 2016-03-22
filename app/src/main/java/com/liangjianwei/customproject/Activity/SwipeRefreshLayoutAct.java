package com.liangjianwei.customproject.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liangjianwei.customproject.AsyncHttp.OnHttpTaskListener;
import com.liangjianwei.customproject.BaiduApi.ProjectApi;
import com.liangjianwei.customproject.Bean.ProjectBean;
import com.liangjianwei.customproject.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutAct extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    private List<String> mList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_demo);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh_layout);
        mListView = (ListView) findViewById(R.id.swipeRefresh_listview);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);


        requstData();


    }

    private void requstData() {
        int pageNo = 1;
        ProjectApi.getProjectList(SwipeRefreshLayoutAct.this, pageNo, new OnHttpTaskListener<ProjectBean>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onTaskError(int resposeCode) {

            }

            @Override
            public void onFinishTask(ProjectBean bean) {

                swipeRefreshLayout.setRefreshing(false);
                mList.clear();
                for (ProjectBean.ProjectListEntity name : bean.getProjectList()) {

                    mList.add(name.getProjectName());

                }

                mAdapter = new ArrayAdapter<>(SwipeRefreshLayoutAct.this, android.R.layout.simple_list_item_1, mList);

                mListView.setAdapter(mAdapter);

            }
        });
    }

    @Override
    public void onRefresh() {
        requstData();
    }
}
