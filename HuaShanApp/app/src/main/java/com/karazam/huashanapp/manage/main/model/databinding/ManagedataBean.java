package com.karazam.huashanapp.manage.main.model.databinding;

import com.karazam.huashanapp.main.Bean.HotProjects;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/27.
 */

public class ManagedataBean {

    private ArrayList<HotProjects> rows;

    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<HotProjects> getRows() {
        return rows;
    }

    public void setRows(ArrayList<HotProjects> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ManagedataBean{" +
                "rows=" + rows +
                ", pages=" + pages +
                '}';
    }
}
