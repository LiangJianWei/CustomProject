package com.liangjianwei.customproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.liangjianwei.customproject.BaiduApi.BaiduApi;
import com.liangjianwei.customproject.bean.IDCardBean;
import com.liangjianwei.customproject.bean.MobileAddressBean;
import com.liangjianwei.customproject.bean.WeatherBean;
import com.squareup.okhttp.Request;

import com.liangjianwei.customproject.Okhttp.OnHttpTaskListener;

public class TestHttpActivity extends AppCompatActivity {

    private String postUrl = "https://app.junrongdai.com/appapi/getProjectListInfo";
    private ProgressDialog dialog;
    private TextView text;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);

        editText = (EditText) findViewById(R.id.editText);
        text = (TextView)findViewById(R.id.textView2);
        dialog = new ProgressDialog(TestHttpActivity.this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchIDCard(TestHttpActivity.this, editText.getText().toString(), new OnHttpTaskListener<IDCardBean>() {
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
                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(bean.getRetData().getSex() + bean.getRetData().getBirthday() + bean.getRetData().getAddress());
                            }
                        });
                    }
                });

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchWeather(TestHttpActivity.this, editText.getText().toString(), new OnHttpTaskListener<WeatherBean>() {
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
                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(bean.getRetData().getAltitude() + bean.getRetData().getCity() + bean.getRetData().getCitycode() + bean.getRetData().getDate() + bean.getRetData().getH_tmp() + bean.getRetData().getL_tmp() + bean.getRetData().getPostCode() + bean.getRetData().getSunrise() + bean.getRetData().getSunset() + bean.getRetData().getTemp() + bean.getRetData().getTime() + bean.getRetData().getLongitude() + bean.getRetData().getLatitude() + bean.getRetData().getWeather() + bean.getRetData().getWS() + bean.getRetData().getWD());
                            }
                        });
                    }
                });
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("")) {
                    return;
                }
                BaiduApi.searchMobile(TestHttpActivity.this, editText.getText().toString(), new OnHttpTaskListener<MobileAddressBean>() {
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
                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(bean.getRetData().getPhone() + bean.getRetData().getCity() + bean.getRetData().getProvince() + bean.getRetData().getSupplier());
                            }
                        });
                    }
                });
            }
        });

    }
}
