package com.karazam.huashanapp.user.register.model.databinbing;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CheckMobileBean {
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "CheckMobileBean{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
