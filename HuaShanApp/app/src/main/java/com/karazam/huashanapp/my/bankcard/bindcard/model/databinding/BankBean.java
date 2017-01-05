package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BankBean {
//    "id":1,
//            "createDate":"2016-12-02 13:01:17",
//            "modifyDate":"2016-12-02 13:01:32",
//            "version":0,
//            "deleted":false,
//            "sort":1,
//            "name":"华一银行",
//            "code":"3057",
//            "logo":null,
//            "description":"华一银行",
//            "gateawayMemo":null,
//            "quickPayMemo":"",
//            "empty":false

    private String id;
    private String name;
    private String logo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "BankBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
