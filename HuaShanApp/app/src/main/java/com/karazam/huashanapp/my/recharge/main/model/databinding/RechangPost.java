package com.karazam.huashanapp.my.recharge.main.model.databinding;

/**
 * Created by Administrator on 2017/1/9.
 */

public class RechangPost {
//    userId	用户ID
//    amount	充值金额
//    bankCardId	银行卡ID
//    orderNo	订单号
//    smsCode	验证码


    private String userId,amount,bankCardId,orderNo,smsCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "RechangPost{" +
                "userId='" + userId + '\'' +
                ", amount='" + amount + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
