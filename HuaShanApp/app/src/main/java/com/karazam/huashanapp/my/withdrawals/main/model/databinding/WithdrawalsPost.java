package com.karazam.huashanapp.my.withdrawals.main.model.databinding;

/**
 * Created by Administrator on 2017/1/9.
 */

public class WithdrawalsPost {
//    userId	用户ID
//    amount	金额
//    bankCard	银行卡ID
//    payPassword	支付密码

    private String userId,amount,bankCard,payPassword;

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

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "WithdrawalsPost{" +
                "userId='" + userId + '\'' +
                ", amount='" + amount + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", payPassword='" + payPassword + '\'' +
                '}';
    }
}
