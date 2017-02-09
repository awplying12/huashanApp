package com.karazam.huashanapp.my.myfinancing.main.model.databinding;

/**
 * Created by Administrator on 2017/1/11.
 */

public class RepayingBean {

//      "period":"3月",
//       amount=200
//       dueTime=2017-4-11
//      "buyTime":"2016-12-07",
//       unpaidAmount=230.32
//      "paidAmount":0,
//      "interestRate":"8.0%",
//      "title":"测试1",
//      "repaymentMethod":"按月还款、等额本息",
//      "finishTime":"2016-12-28",
//      "paidCapitaliInterest":0,
//      "investmentId" 投资ID

    private String period,
            investmentId,
            amount,
            dueTime,
            unpaidAmount,
            buyTime,
            paidAmount,
            interestRate,
            title,
            repaymentMethod,
            finishTime,
            paidCapitaliInterest;

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

    @Override
    public String toString() {
        return "RepayingBean{" +
                "period='" + period + '\'' +
                ", investmentId='" + investmentId + '\'' +
                ", amount='" + amount + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", unpaidAmount='" + unpaidAmount + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", paidAmount='" + paidAmount + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", title='" + title + '\'' +
                ", repaymentMethod='" + repaymentMethod + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", paidCapitaliInterest='" + paidCapitaliInterest + '\'' +
                '}';
    }
}
