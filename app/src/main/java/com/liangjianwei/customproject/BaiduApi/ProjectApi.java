package com.liangjianwei.customproject.BaiduApi;

import android.content.Context;

import com.liangjianwei.customproject.AsyncHttp.HttpTask;
import com.liangjianwei.customproject.AsyncHttp.OnHttpTaskListener;
import com.liangjianwei.customproject.Bean.ProjectBean;

/**
 * Created by LIANGJIANWEI on 2016-1-27
 */
public class ProjectApi {

    public static void getProjectList(Context mContext, int pageNo, OnHttpTaskListener<ProjectBean> onHttpTaskListener) {
        HttpTask httpTask = HttpTask.getHttpTask(mContext, "/getProjectListInfo", HttpTask.METHOD_POST);
        httpTask.putParam("pageNo", pageNo);
        httpTask.putParam("pageSize", 10);
        httpTask.runTask(ProjectBean.class, onHttpTaskListener);
    }

}
