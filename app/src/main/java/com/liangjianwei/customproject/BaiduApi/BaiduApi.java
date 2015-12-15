package com.liangjianwei.customproject.BaiduApi;

import android.content.Context;

import com.liangjianwei.customproject.Okhttp.CacheType;
import com.liangjianwei.customproject.Okhttp.HttpUtils;
import com.liangjianwei.customproject.Okhttp.OnHttpTaskListener;
import com.liangjianwei.customproject.bean.IDCardBean;
import com.liangjianwei.customproject.bean.MobileAddressBean;
import com.liangjianwei.customproject.bean.WeatherBean;

/**
 * Created by Javen on 2015/12/15.
 */
public class BaiduApi {

    public static final String BAIDU_API_KRY = "20dc7c243271067f36d943c33de6b399";
    private static final String BASE_URL = "http://apis.baidu.com/apistore/";


    /**
     * 根据身份证号查询个人信息
     */
    private static final String IDCARd_SEARCH = BASE_URL + "idservice/id";

    /**
     * 根据身份证查询个人信息
     */
    public static void searchIDCard(Context context, String id, OnHttpTaskListener<IDCardBean> onHttpTaskListener) {
        HttpUtils httpUtils = new HttpUtils(context, IDCARd_SEARCH + "?id=" + id, CacheType.ONLY_NETWORK);
        httpUtils.get(IDCardBean.class, onHttpTaskListener);

    }

    /**
     * 查询天气预报
     */
    private static final String WEATHER_SEARCH = BASE_URL + "weatherservice/cityname";

    /**
     * 查询天气预报
     */
    public static void searchWeather(Context context, String cityName, OnHttpTaskListener<WeatherBean> onHttpTaskListener) {
        HttpUtils httpUtils = new HttpUtils(context, WEATHER_SEARCH + "?cityname=" + cityName, CacheType.ONLY_NETWORK);
        httpUtils.get(WeatherBean.class, onHttpTaskListener);

    }

    /**
     * 查询手机号归属地
     */
    private static final String MOBILE_ADDRESS_SEARCH = BASE_URL + "mobilenumber/mobilenumber";

    /**
     * 查询手机号归属地
     */
    public static void searchMobile(Context context, String phoneNum, OnHttpTaskListener<MobileAddressBean> onHttpTaskListener) {
        HttpUtils httpUtils = new HttpUtils(context, MOBILE_ADDRESS_SEARCH + "?phone=" + phoneNum, CacheType.ONLY_NETWORK);
        httpUtils.get(MobileAddressBean.class, onHttpTaskListener);
    }
}
