package com.liangjianwei.customproject.Bean;

/**
 * Created by Javen on 2015/12/15.
 */
public class IDCardBean extends BaseBean{

    /**
     * errNum : 0
     * retMsg : success
     * retData : {"sex":"M","birthday":"1987-04-20","address":"湖北省孝感市汉川市"}
     */
    /**
     * sex : M
     * birthday : 1987-04-20
     * address : 湖北省孝感市汉川市
     */

    private RetDataEntity retData;

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String sex;
        private String birthday;
        private String address;

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSex() {
            return sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getAddress() {
            return address;
        }
    }
}
