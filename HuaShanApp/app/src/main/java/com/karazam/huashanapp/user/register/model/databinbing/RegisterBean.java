package com.karazam.huashanapp.user.register.model.databinbing;

/**
 * Created by Administrator on 2016/12/22.
 */

public class RegisterBean {

    private String mobile;
    private String password;
    private String smsCode;
    private String clientType = "android";

    public String getMobilel() {
        return mobile;
    }

    public void setMobilel(String mobilel) {
        this.mobile = mobilel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "mobilel='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }

}
