package com.karazam.huashanapp.my.realname.model.databinding;

/**
 * Created by Administrator on 2017/1/3.
 */

public class RealnamePost {
    private String name;
    private String idNo;

    private String userIdKey;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserIdKey() {
        return userIdKey;
    }

    public void setUserIdKey(String userIdKey) {
        this.userIdKey = userIdKey;
    }

    @Override
    public String toString() {
        return "RealnamePost{" +
                "name='" + name + '\'' +
                ", idNo='" + idNo + '\'' +
                ", userIdKey='" + userIdKey + '\'' +
                '}';
    }
}
