package com.karazam.huashanapp.my.security.paymentpassword.model.databinding;

/**
 * Created by Administrator on 2017/1/3.
 */

public class PaymentPost {
    private String payPassword;
    private String type;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PaymentPost{" +
                "payPassword='" + payPassword + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
