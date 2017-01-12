package com.karazam.huashanapp.my.myreturn.main.model.databinding;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReturnRecordsItem {
//      "amount":1200.00,
//      "period":"1/3",
//      "buyTime":"2016-12-07",
//      "state":"还款中"

    private String amount,
                period,
                buyTime,
                state;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ReturnRecordsItem{" +
                "amount='" + amount + '\'' +
                ", period='" + period + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
