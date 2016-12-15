package com.karazam.huashanapp.main.Bean;

/**
 * Created by Administrator on 2016/11/16.
 */

public class UserInformation {

    private String headerImg;
    private String userName;
    private String nickname;
    private boolean status;
    private String phonenum;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
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
                ", nickname='" + nickname + '\'' +
                ", status='" + status + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", userbalance='" + userbalance + '\'' +
                ", paymentmod='" + paymentmod + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", cardInformation='" + cardInformation + '\'' +
                '}';
    }
}
