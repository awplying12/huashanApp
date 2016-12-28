package com.karazam.huashanapp.manage.details_fragment.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ManageRecordsBean {

    private int pages;
    private ArrayList<RecordsItem> records;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<RecordsItem> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RecordsItem> records) {
        this.records = records;
    }
}
