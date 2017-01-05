package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/5.
 */

public class BankPost {
    private ArrayList<BankBean> rows = new ArrayList<>();
    private int pages;

    public ArrayList<BankBean> getRows() {
        return rows;
    }

    public void setRows(ArrayList<BankBean> rows) {
        this.rows = rows;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "BankPost{" +
                "rows=" + rows +
                ", pages=" + pages +
                '}';
    }
}
