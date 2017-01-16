package com.karazam.huashanapp.my.message.messagedetails.model.databinding;

import com.karazam.huashanapp.my.message.messagedetails.view.view.DetailsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessagedetailsBean {

    private ArrayList<DetailsBean> rows;
    private int pages;

    public ArrayList<DetailsBean> getRows() {
        return rows;
    }

    public void setRows(ArrayList<DetailsBean> rows) {
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
        return "MessagedetailsBean{" +
                "rows=" + rows +
                ", pages='" + pages + '\'' +
                '}';
    }
}
