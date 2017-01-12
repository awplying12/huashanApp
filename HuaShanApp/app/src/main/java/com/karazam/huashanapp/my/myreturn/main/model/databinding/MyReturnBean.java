package com.karazam.huashanapp.my.myreturn.main.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MyReturnBean {

    private ArrayList<ReturnRecords> rows;

    public ArrayList<ReturnRecords> getRows() {
        return rows;
    }

    public void setRows(ArrayList<ReturnRecords> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MyReturnBean{" +
                "rows=" + rows +
                '}';
    }
}
