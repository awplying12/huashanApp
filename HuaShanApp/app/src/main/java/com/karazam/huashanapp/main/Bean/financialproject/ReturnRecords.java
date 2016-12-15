package com.karazam.huashanapp.main.Bean.financialproject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReturnRecords {

    private ArrayList<ReturnRecordsItem> returnRecordsItems;

    public ReturnRecords() {
    }

    public ReturnRecords(ArrayList<ReturnRecordsItem> returnRecordsItems) {
        this.returnRecordsItems = returnRecordsItems;
    }

    public ArrayList<ReturnRecordsItem> getReturnRecordsItems() {
        return returnRecordsItems;
    }

    public void setReturnRecordsItems(ArrayList<ReturnRecordsItem> returnRecordsItems) {
        this.returnRecordsItems = returnRecordsItems;
    }

    @Override
    public String toString() {
        return "ReturnRecords{" +
                "returnRecordsItems=" + returnRecordsItems +
                '}';
    }

}
