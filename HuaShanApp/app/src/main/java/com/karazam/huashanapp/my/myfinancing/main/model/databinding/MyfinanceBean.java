package com.karazam.huashanapp.my.myfinancing.main.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MyfinanceBean {

    private int pages;

    private ArrayList<CompletedBean> completed;

    private ArrayList<InvestingBean> investing;

    private ArrayList<RepayingBean> repaying;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<CompletedBean> getCompleted() {
        return completed;
    }

    public void setCompleted(ArrayList<CompletedBean> completed) {
        this.completed = completed;
    }

    public ArrayList<InvestingBean> getInvesting() {
        return investing;
    }

    public void setInvesting(ArrayList<InvestingBean> investing) {
        this.investing = investing;
    }

    public ArrayList<RepayingBean> getRepaying() {
        return repaying;
    }

    public void setRepaying(ArrayList<RepayingBean> repaying) {
        this.repaying = repaying;
    }

    @Override
    public String toString() {
        return "MyfinanceBean{" +
                "pages='" + pages + '\'' +
                ", completed=" + completed +
                ", investing=" + investing +
                ", repaying=" + repaying +
                '}';
    }
}
