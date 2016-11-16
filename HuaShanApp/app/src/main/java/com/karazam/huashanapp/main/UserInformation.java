package com.karazam.huashanapp.main;

/**
 * Created by Administrator on 2016/11/16.
 */

public class UserInformation {

    private String headerImg;
    private String userName;
    private String userbalance;
    private String paymentmod;
    private String bankCard;
    private String cardInformation;

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserbalance() {
        return userbalance;
    }

    public void setUserbalance(String userbalance) {
        this.userbalance = userbalance;
    }

    public String getPaymentmod() {
        return paymentmod;
    }

    public void setPaymentmod(String paymentmod) {
        this.paymentmod = paymentmod;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(String cardInformation) {
        this.cardInformation = cardInformation;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "headerImg='" + headerImg + '\'' +
                ", userName='" + userName + '\'' +
                ", userbalance='" + userbalance + '\'' +
                ", paymentmod='" + paymentmod + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", cardInformation='" + cardInformation + '\'' +
                '}';
    }
}
