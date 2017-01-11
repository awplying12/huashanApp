package com.karazam.huashanapp.my.myfinancing.main.model.databinding;

/**
 * Created by Administrator on 2017/1/11.
 */

public class CompletedBean {
//    "period":"3月",
//     "buyTime":"2016-12-07",
//      finishTime=2017-4-11
//      paidCapitaliInterest=266.66
//     "paidAmount":0,
//      amount=4700
//     "interestRate":"8.0%",
//     "title":"测试1",
//      "repaymentMethod":"按月还款、等额本息",
//      "progress":0.0001

    private String period,
            finishTime,
            paidCapitaliInterest,
            buyTime,
            amount,
            interestRate,
            title,
            repaymentMethod,
            progress;

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPaidCapitaliInterest() {
        return paidCapitaliInterest;
    }

    public void setPaidCapitaliInterest(String paidCapitaliInterest) {
        this.paidCapitaliInterest = paidCapitaliInterest;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "CompletedBean{" +
                "period='" + period + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", paidCapitaliInterest='" + paidCapitaliInterest + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", amount='" + amount + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", title='" + title + '\'' +
                ", repaymentMethod='" + repaymentMethod + '\'' +
                ", progress='" + progress + '\'' +
                '}';
    }
}
