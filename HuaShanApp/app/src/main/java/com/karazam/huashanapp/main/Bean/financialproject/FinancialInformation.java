package com.karazam.huashanapp.main.Bean.financialproject;

/**
 * Created by Administrator on 2016/12/5.
 */

public class FinancialInformation {
    private boolean state = true; //转让状态

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FinancialInformation{" +
                "state=" + state +
                '}';
    }
}
