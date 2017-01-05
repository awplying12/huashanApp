package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BindcardPost {

//    isQuick	是否快捷卡
//    bank	银行ID
//    card	银行卡号
//    mobile	预留手机号码(快捷必填)
//    captch	验证码(快捷必填)

    private boolean isQuick;
    private String bank;
    private String card;
    private String mobile;
    private String captch;

    public boolean isQuick() {
        return isQuick;
    }

    public void setQuick(boolean quick) {
        isQuick = quick;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptch() {
        return captch;
    }

    public void setCaptch(String captch) {
        this.captch = captch;
    }

    @Override
    public String toString() {
        return "BindcardPost{" +
                "isQuick='" + isQuick + '\'' +
                ", bank='" + bank + '\'' +
                ", card='" + card + '\'' +
                ", mobile='" + mobile + '\'' +
                ", captch='" + captch + '\'' +
                '}';
    }
}
