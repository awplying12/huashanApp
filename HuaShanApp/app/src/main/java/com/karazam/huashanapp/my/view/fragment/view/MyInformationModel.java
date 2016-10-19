package com.karazam.huashanapp.my.view.fragment.view;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MyInformationModel {

    private int icon_id;
    private String name;

    public MyInformationModel(int icon_id, String name) {
        this.icon_id = icon_id;
        this.name = name;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
