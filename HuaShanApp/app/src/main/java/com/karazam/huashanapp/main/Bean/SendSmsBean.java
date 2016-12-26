package com.karazam.huashanapp.main.Bean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class SendSmsBean {
    private String mobile;
    private String smsType;

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

    @Override
    public String toString() {
        return "SendSmsBean{" +
                "mobile='" + mobile + '\'' +
                ", smsType='" + smsType + '\'' +
                '}';
    }
}
