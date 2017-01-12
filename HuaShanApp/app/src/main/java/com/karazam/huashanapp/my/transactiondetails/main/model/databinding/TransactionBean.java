package com.karazam.huashanapp.my.transactiondetails.main.model.databinding;

import com.karazam.huashanapp.my.transactiondetails.main.view.activity.view.TransactionItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/12.
 */

public class TransactionBean {

    private int pages;

    private ArrayList<TransactionItem> orders;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<TransactionItem> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<TransactionItem> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "TransactionBean{" +
                "pages=" + pages +
                ", orders=" + orders +
                '}';
    }

}
