package com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding;

import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view.StateitemBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WithdrawaldetailsBean {

//    amount=100
//    bank=中国工商银行
//    card=中国工商银行(44210)邓成博
//    fee=0
//    list
//    logo=/upload/image/201404/f1c9a7c5-849d-401b-bd3e-05059dc26224.jpg
//    memo=用户id[51]提现申请
//    status=NEW_CREATE
//    statusDes=新建

    private String amount,
            bank,
            card,
            fee,
            logo,
            memo,
            status,
            statusDes;

    private ArrayList<StateitemBean> list;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public ArrayList<StateitemBean> getList() {
        return list;
    }

    public void setList(ArrayList<StateitemBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WithdrawaldetailsBean{" +
                "amount='" + amount + '\'' +
                ", bank='" + bank + '\'' +
                ", card='" + card + '\'' +
                ", fee='" + fee + '\'' +
                ", logo='" + logo + '\'' +
                ", memo='" + memo + '\'' +
                ", status='" + status + '\'' +
                ", statusDes='" + statusDes + '\'' +
                ", list=" + list +
                '}';
    }
}
