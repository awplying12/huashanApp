package com.karazam.huashanapp.today.main.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */

public class TodayBean {

    private String todayProfits;
    private ArrayList<HotProjects> hotProjects;

    public String getTodayProfits() {
        return todayProfits;
    }

    public void setTodayProfits(String todayProfits) {
        this.todayProfits = todayProfits;
    }

    public ArrayList<HotProjects> getHotProjects() {
        return hotProjects;
    }

    public void setHotProjects(ArrayList<HotProjects> hotProjects) {
        this.hotProjects = hotProjects;
    }

    @Override
    public String toString() {
        return "TodayBean{" +
                "todayProfits='" + todayProfits + '\'' +
                ", hotProjects=" + hotProjects +
                '}';
    }

}
