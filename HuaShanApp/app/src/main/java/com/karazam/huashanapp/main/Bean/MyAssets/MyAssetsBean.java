package com.karazam.huashanapp.main.Bean.MyAssets;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MyAssetsBean {
//    available	可用余额
//    withdrawing	提现中的金额
//    investFrozen	投资冻结金额
//    watingCapital	待收本金
//    watingProfits	待收收益
//    alreadyProfits	已收收益
//    alreadyReferralFees	已收推荐费
//    withdrawFee	提现手续费


    private String watingCapital;
    private String investFrozen;
    private String watingProfits;
    private String withdrawFee;
    private String alreadyProfits;
    private String available;
    private String alreadyReferralFees;
    private String withdrawing;

    public String getWatingCapital() {
        return watingCapital;
    }

    public void setWatingCapital(String watingCapital) {
        this.watingCapital = watingCapital;
    }

    public String getInvestFrozen() {
        return investFrozen;
    }

    public void setInvestFrozen(String investFrozen) {
        this.investFrozen = investFrozen;
    }

    public String getWatingProfits() {
        return watingProfits;
    }

    public void setWatingProfits(String watingProfits) {
        this.watingProfits = watingProfits;
    }

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getAlreadyProfits() {
        return alreadyProfits;
    }

    public void setAlreadyProfits(String alreadyProfits) {
        this.alreadyProfits = alreadyProfits;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAlreadyReferralFees() {
        return alreadyReferralFees;
    }

    public void setAlreadyReferralFees(String alreadyReferralFees) {
        this.alreadyReferralFees = alreadyReferralFees;
    }

    public String getWithdrawing() {
        return withdrawing;
    }

    public void setWithdrawing(String withdrawing) {
        this.withdrawing = withdrawing;
    }

    @Override
    public String toString() {
        return "MyAssetsBean{" +
                "watingCapital='" + watingCapital + '\'' +
                ", investFrozen='" + investFrozen + '\'' +
                ", watingProfits='" + watingProfits + '\'' +
                ", withdrawFee='" + withdrawFee + '\'' +
                ", alreadyProfits='" + alreadyProfits + '\'' +
                ", available='" + available + '\'' +
                ", alreadyReferralFees='" + alreadyReferralFees + '\'' +
                ", withdrawing='" + withdrawing + '\'' +
                '}';
    }
}
