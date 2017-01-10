package com.karazam.huashanapp.manage.purchase.model.databinding;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchasePost {
//    projectId	项目ID
//    paymentMethod	支付方式（BALANCE_PAY、QUICK_PAY）
//    amount	金额
//    payPassword	支付密码 - 余额支付必填
//    captcha	短信验证码 - 快捷支付必填

    private String projectId;
    private String paymentMethod;
    private String amount;
    private String payPassword;
    private String captcha;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "PurchasePost{" +
                "projectId='" + projectId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount='" + amount + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
