package com.karazam.huashanapp.today.main.view.fragment.view;

/**
 * Created by Administrator on 2016/11/4.
 */

public class CommodityItem {
    private String title;
    private String color;

    public CommodityItem() {
    }

    public CommodityItem(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CommodityItem{" +
                "title='" + title + '\'' +
                ", color=" + color +
                '}';
    }
}
