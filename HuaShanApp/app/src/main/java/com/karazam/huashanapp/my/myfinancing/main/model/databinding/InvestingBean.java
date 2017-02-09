package com.karazam.huashanapp.my.myfinancing.main.model.databinding;

/**
 * Created by Administrator on 2017/1/11.
 */

public class InvestingBean {

//    "period":"3月",
//    "buyTime":"2016-12-07",
//     "paidAmount":0,
//      progress=0.48
//     "interestRate":"8.0%",
//     "title":"测试1",
//     "repaymentMethod":"按月还款、等额本息",
//     "amount":"89000.00",
//     "dueTime":"2017-01-06",
//     "unpaidAmount":"13200.00"
//      investmentId 投资ID

    private String period,
            investmentId,
            buyTime,
            paidAmount,
            interestRate,
            progress,
            title,
            repaymentMethod,
            amount,
            dueTime,
            unpaidAmount;

//    private float progress;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(String unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "InvestingBean{" +
                "period='" + period + '\'' +
                ", investmentId='" + investmentId + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", paidAmount='" + paidAmount + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", progress='" + progress + '\'' +
                ", title='" + title + '\'' +
                ", repaymentMethod='" + repaymentMethod + '\'' +
                ", amount='" + amount + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", unpaidAmount='" + unpaidAmount + '\'' +
                '}';
    }
}
