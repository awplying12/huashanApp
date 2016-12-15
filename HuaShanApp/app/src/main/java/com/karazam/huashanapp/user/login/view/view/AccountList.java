package com.karazam.huashanapp.user.login.view.view;

import com.karazam.huashanapp.main.Bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/15.
 */

public class AccountList extends BaseBean{

    private ArrayList<String> arrayList;

    public AccountList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "AccountList{" +
                "arrayList=" + arrayList +
                '}';
    }
}
