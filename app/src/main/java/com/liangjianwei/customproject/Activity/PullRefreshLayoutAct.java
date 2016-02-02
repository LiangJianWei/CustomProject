package com.liangjianwei.customproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.liangjianwei.customproject.AsyncHttp.OnHttpTaskListener;
import com.liangjianwei.customproject.BaiduApi.ProjectApi;
import com.liangjianwei.customproject.Bean.ProjectBean;
import com.liangjianwei.customproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class PullRefreshLayoutAct extends AppCompatActivity {

    private PullRefreshLayout mPullRefreshLayout;
    private ListView mlistView;
    private Button mbtn;

    private int type = 1;
    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh_layout);

        mPullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pullRefreshLayout);
        mlistView = (ListView) findViewById(R.id.id_listView);
        mbtn = (Button) findViewById(R.id.changeType_btn);
        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);

        mPullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requstData();
            }
        });




        requstData();


        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type++;

                if (type > 5) {

                    type = 1;
                }

                switch (type) {
                    case 1:
                        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
                        break;
                    case 2:
                        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
                        break;
                    case 3:
                        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
                        break;
                    case 4:
                        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
                        break;
                    case 5:
                        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
                        break;
                }

            }
        });

    }

    private void requstData() {
        ProjectApi.getProjectList(PullRefreshLayoutAct.this, 1, new OnHttpTaskListener<ProjectBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onTaskError(int resposeCode) {

            }

            @Override
            public void onFinishTask(ProjectBean bean) {

                mPullRefreshLayout.setRefreshing(false);
                mList.clear();
                for (com.liangjianwei.customproject.Bean.ProjectBean.ProjectListEntity name : bean.getProjectList()) {

                    mList.add(name.getProjectName());

                }

                ArrayAdapter<String> mAdapter = new ArrayAdapter<>(PullRefreshLayoutAct.this, android.R.layout.simple_list_item_1, mList);

                mlistView.setAdapter(mAdapter);

            }
        });
    }
}
