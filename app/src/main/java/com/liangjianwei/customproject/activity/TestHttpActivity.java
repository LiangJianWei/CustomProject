/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liangjianwei.customproject.BaiduApi.BaiduApi;
import com.liangjianwei.customproject.Base.BaseActivity;
import com.liangjianwei.customproject.Bean.IDCardBean;
import com.liangjianwei.customproject.Bean.MobileAddressBean;
import com.liangjianwei.customproject.Bean.WeatherBean;
import com.liangjianwei.customproject.Okhttp.OnHttpTaskListener;
import com.liangjianwei.customproject.R;
import com.squareup.okhttp.Request;

public class TestHttpActivity extends BaseActivity {

    private String postUrl = "https://app.junrongdai.com/appapi/getProjectListInfo";
    private ProgressDialog dialog;

    private EditText idcontentedit;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);


        this.textView2 = (TextView) findViewById(R.id.textView2);
        Button idaddressbtn = (Button) findViewById(R.id.id_address_btn);
        Button idpostbtn = (Button) findViewById(R.id.id_post_btn);
        Button idweaterbtn = (Button) findViewById(R.id.id_weater_btn);
        Button idcardbtn = (Button) findViewById(R.id.id_card_btn);
        this.idcontentedit = (EditText) findViewById(R.id.id_content_edit);

        idcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idcontentedit.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchIDCard(TestHttpActivity.this, idcontentedit.getText().toString(), new OnHttpTaskListener<IDCardBean>() {
                    @Override
                    public void onStart() {

                        dialog.show();

                    }

                    @Override
                    public void onError(Request request) {

                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final IDCardBean bean, final String context) {

                        dialog.dismiss();
                        textView2.post(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(bean.getRetData().getSex() + bean.getRetData().getBirthday() + bean.getRetData().getAddress());
                            }
                        });
                    }
                });

            }
        });
        idweaterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idcontentedit.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchWeather(TestHttpActivity.this, idcontentedit.getText().toString(), new OnHttpTaskListener<WeatherBean>() {
                    @Override
                    public void onStart() {

                        dialog.show();
                    }

                    @Override
                    public void onError(Request request) {

                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final WeatherBean bean, final String context) {
                        dialog.dismiss();
                        textView2.post(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(bean.getRetData().getAltitude() + bean.getRetData().getCity() + bean.getRetData().getCitycode() + bean.getRetData().getDate() + bean.getRetData().getH_tmp() + bean.getRetData().getL_tmp() + bean.getRetData().getPostCode() + bean.getRetData().getSunrise() + bean.getRetData().getSunset() + bean.getRetData().getTemp() + bean.getRetData().getTime() + bean.getRetData().getLongitude() + bean.getRetData().getLatitude() + bean.getRetData().getWeather() + bean.getRetData().getWS() + bean.getRetData().getWD());
                            }
                        });
                    }
                });
            }
        });
        idpostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        idaddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idcontentedit.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchMobile(TestHttpActivity.this, idcontentedit.getText().toString(), new OnHttpTaskListener<MobileAddressBean>() {
                    @Override
                    public void onStart() {

                        dialog.show();
                    }

                    @Override
                    public void onError(Request request) {

                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(final MobileAddressBean bean, String context) {

                        dialog.dismiss();
                        if (bean.getErrNum() == -1) {
                            return;
                        }
                        textView2.post(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(bean.getRetData().getPhone() + bean.getRetData().getCity() + bean.getRetData().getProvince() + bean.getRetData().getSupplier());
                            }
                        });
                    }
                });
            }
        });

    }
}
