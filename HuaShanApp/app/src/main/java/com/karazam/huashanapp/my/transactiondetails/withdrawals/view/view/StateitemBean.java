package com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view;

/**
 * Created by Administrator on 2016/12/1.
 */

public class StateitemBean {
    private String state;
    private String time;

    public StateitemBean(String state, String time) {
        this.state = state;
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StateitemBean{" +
                "state='" + state + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
