package com.liangjianwei.customproject.Utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

/**
 * @author 倒计时相关
 */
public class CountDownUtil {

    private Button btnSendCode; // 倒计时按钮

    private String getAgainCode; // 获取多少秒以后重新获取验证码的String字符串
    private String getStringCode;// 获取点击获取验证码的String字符串

    /**
     * 启动倒计时
     *
     * @param mHandler    用handler启动线程
     * @param btnSendCode 倒计时按钮
     */
    public CountDownUtil(Handler mHandler, Button btnSendCode) {
        this.btnSendCode = btnSendCode;

        getAgainCode = "%1$d秒后重新获取";
        getStringCode = "重新获取验证码";

        mHandler.post(attemptLockout);
    }

    /**
     * 启动倒计时的线程
     */
    Runnable attemptLockout = new Runnable() {
        @Override
        public void run() {
            int countDownTime = 60000;
            new CountDownTimer(countDownTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int secondsRemaining = (int) (millisUntilFinished / 1000);
                    btnSendCode.setEnabled(false);
                    String codeText = String.format(getAgainCode, secondsRemaining);
                    SpannableStringBuilder style = new SpannableStringBuilder(codeText);
                    style.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    btnSendCode.setText(style);
                }

                @Override
                public void onFinish() {
                    btnSendCode.setText(getStringCode);
                    btnSendCode.setEnabled(true);
                }

            }.start();
        }
    };

}
