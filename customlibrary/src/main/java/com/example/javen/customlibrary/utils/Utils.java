package com.example.javen.customlibrary.utils;

import java.util.regex.Pattern;

public class Utils {
    public static final Pattern Chinese_Pattern = Pattern.compile("^[\u4E00-\u9FFF]+$");
    public static final Pattern Person_name = Pattern.compile("(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{2,10}))");
    public static final Pattern Password_Pattern = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$).{6,16}$");
    public static final Pattern NickName_Pattern = Pattern.compile("^[0-9a-zA-Z_-—\u0391-\uFFE5\u4e00-\u9fa5]+$");
    public static final Pattern Phone_Pattern = Pattern.compile("^((14[0-9])|(13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-3,5-9]))\\d{8}$");
    public static final Pattern Email_Pattern = Pattern.compile("^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+.)+[a-zA-Z0-9]{2,4}$");
    public static final Pattern Character_Pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？] ");

    /**
     * 正则表达式匹配手机号码
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        return Phone_Pattern.matcher(phoneNumber).matches();
    }

    public static boolean isNickname(String nickname) {
        return NickName_Pattern.matcher(nickname).matches();
    }

    /**
     * 正则表达式过滤字符
     */
    public static boolean filterCharacter(String character) {
        return Character_Pattern.matcher(character).matches();
    }

    /**
     * 正则表达式匹配邮箱地址
     */
    public static boolean isEmailAddr(String emailAddr) {
        return Email_Pattern.matcher(emailAddr).matches();
    }

    /**
     * 正则表达式检查密码复杂度
     */
    public static boolean isPwdComplex(String password) {
        return Password_Pattern.matcher(password).matches();
    }

    /**
     * 正则表达式匹配汉字
     */
    public static boolean isChineseOnly(String string) {
        return Chinese_Pattern.matcher(string).matches();
    }

    /**
     * 正则表达匹配汉字和字母
     */
    public static boolean isPersonName(String string) {
        return Person_name.matcher(string).matches();
    }

    /**
     * 正则用户身份证号是否正确
     */
    public static boolean isIDcard(String idCard){
        return IDCardVerify.IDCardValidate(idCard);
    }
}
