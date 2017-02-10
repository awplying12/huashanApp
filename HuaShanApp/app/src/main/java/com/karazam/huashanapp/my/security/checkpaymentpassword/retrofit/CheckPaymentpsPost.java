package com.karazam.huashanapp.my.security.checkpaymentpassword.retrofit;

/**
 * Created by Administrator on 2017/2/10.
 */

public class CheckPaymentpsPost {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CheckPaymentpsPost{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
