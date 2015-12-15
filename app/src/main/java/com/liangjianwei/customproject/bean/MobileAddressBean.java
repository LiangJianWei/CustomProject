package com.liangjianwei.customproject.bean;

/**
 * Created by Javen on 2015/12/15.
 */
public class MobileAddressBean extends BaseBean{

    /**
     * phone : 15210011578
     * prefix : 1521001
     * supplier : 移动
     * province : 北京
     * city : 北京
     * suit : 152卡
     */

    private RetDataEntity retData;

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String phone;
        private String prefix;
        private String supplier;
        private String province;
        private String city;
        private String suit;

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public String getPhone() {
            return phone;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getSupplier() {
            return supplier;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getSuit() {
            return suit;
        }
    }
}
