package com.karazam.huashanapp.my.recharge.main.model.databinding;

/**
 * Created by Administrator on 2017/1/9.
 */

public class QuickpaySMSPost {
//    userId	用户ID
//    amount	充值金额
//    bankCardId	银行卡ID
    private String userId,amount,bankCardId;

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

    @Override
    public String toString() {
        return "QuickpaySMSPost{" +
                "userId='" + userId + '\'' +
                ", amount='" + amount + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                '}';
    }
}
