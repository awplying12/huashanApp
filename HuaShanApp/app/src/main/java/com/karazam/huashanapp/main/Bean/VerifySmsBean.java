package com.karazam.huashanapp.main.Bean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class VerifySmsBean {

    private String mobile;
    private String smsType;
    private String smsCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "VerifySmsBean{" +
                "mobile='" + mobile + '\'' +
                ", smsType='" + smsType + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }

}
