package com.liangjianwei.customproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import com.liangjianwei.customproject.Okhttp.CacheType;
import com.liangjianwei.customproject.Okhttp.HttpUtils;
import com.liangjianwei.customproject.Okhttp.OnHttpTaskListener;
import com.liangjianwei.customproject.bean.BaseBean;

public class TestHttpActivity extends AppCompatActivity {

    private HttpUtils httpUtils;
    private boolean isClick;

    private String postUrl = "https://app.junrongdai.com/appapi/getProjectListInfo";

    private String getUrl = "http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
    private ProgressDialog dialog;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);

        text = (TextView)findViewById(R.id.textView2);
        dialog =  new ProgressDialog(TestHttpActivity.this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUtils = new HttpUtils(TestHttpActivity.this,getUrl, CacheType.ONLY_NETWORK);
                httpUtils.get(BaseBean.class, new OnHttpTaskListener<BaseBean>() {

                    @Override
                    public void onStart() {
                        dialog.show();

                    }

                    @Override
                    public void onError(Request request) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final BaseBean bean, final String content) {
                        dialog.dismiss();

                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(content);
                            }
                        });


                    }
                });

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUtils = new HttpUtils(TestHttpActivity.this,getUrl, CacheType.ONLY_CACHED);
                httpUtils.get(BaseBean.class, new OnHttpTaskListener<BaseBean>() {
                    @Override
                    public void onStart() {

                        dialog.show();
                    }

                    @Override
                    public void onError(Request request) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final BaseBean bean, final String content) {
                        dialog.dismiss();

                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(content);
                            }
                        });


                    }
                });
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUtils = new HttpUtils(TestHttpActivity.this,postUrl, CacheType.ONLY_NETWORK);
                httpUtils.putParam("PageSize","1");
                httpUtils.putParam("PageNo","1");
                httpUtils.putParam("sortType","1");
                httpUtils.post(BaseBean.class, new OnHttpTaskListener<BaseBean>() {
                    @Override
                    public void onStart() {

                        dialog.show();
                    }

                    @Override
                    public void onError(Request request) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final BaseBean bean, final String content) {
                        dialog.dismiss();

                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(content);
                            }
                        });


                    }
                });
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick){
                    httpUtils = new HttpUtils(TestHttpActivity.this,postUrl, CacheType.CACHED_ELSE_NETWORK);
                    httpUtils.putParam("PageSize","1");
                    httpUtils.putParam("PageNo","1");
                    httpUtils.putParam("sortType","1");
                    httpUtils.get(BaseBean.class, new OnHttpTaskListener<BaseBean>() {
                        @Override
                        public void onStart() {
                            dialog.show();

                        }

                        @Override
                        public void onError(Request request) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(final BaseBean bean, final String content) {
                            dialog.dismiss();

                            text.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(content);
                                    isClick = false;
                                }
                            });


                        }
                    });
                }else{
                    httpUtils = new HttpUtils(TestHttpActivity.this,getUrl, CacheType.CACHED_ELSE_NETWORK);
                    httpUtils.get(BaseBean.class, new OnHttpTaskListener<BaseBean>() {
                        @Override
                        public void onStart() {
                            dialog.show();

                        }

                        @Override
                        public void onError(Request request) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(final BaseBean bean, final String content) {
                            dialog.dismiss();

                            text.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(content);
                                    isClick = true;
                                }
                            });


                        }
                    });
                }
            }
        });
    }
}
