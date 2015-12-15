package com.liangjianwei.customproject.bean;

/**
 * Created by Javen on 2015/12/7.
 */
public class BaseBean {


    /**
     * errNum : 0
     * retMsg : success
     * retData :
     */

    private int errNum;
    private String retMsg;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

}
