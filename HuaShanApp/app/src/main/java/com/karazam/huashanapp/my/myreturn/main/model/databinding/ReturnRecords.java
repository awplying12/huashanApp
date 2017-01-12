package com.karazam.huashanapp.my.myreturn.main.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReturnRecords {

    private String title,
                    amount;

    private ArrayList<ReturnRecordsItem> rpList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ArrayList<ReturnRecordsItem> getRpList() {
        return rpList;
    }

    public void setRpList(ArrayList<ReturnRecordsItem> rpList) {
        this.rpList = rpList;
    }

    @Override
    public String toString() {
        return "ReturnRecords{" +
                "title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", rpList=" + rpList +
                '}';
    }
}
