package com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view;

/**
 * Created by Administrator on 2016/12/1.
 */

public class StateitemBean {
    private String status;
    private String date;
    private boolean pass;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "StateitemBean{" +
                "status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", pass=" + pass +
                '}';
    }
}
