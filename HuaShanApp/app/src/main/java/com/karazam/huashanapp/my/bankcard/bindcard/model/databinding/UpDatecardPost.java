package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

/**
 * Created by Administrator on 2017/1/5.
 */

public class UpDatecardPost {
//    bank	银行ID
//    card	银行卡号
//    payPassword	支付密码

    private String bank;
    private String card;
    private String payPassword;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "UpDatecardPost{" +
                "bank='" + bank + '\'' +
                ", card='" + card + '\'' +
                ", payPassword='" + payPassword + '\'' +
                '}';
    }
}
