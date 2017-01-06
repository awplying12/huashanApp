package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

/**
 * Created by Administrator on 2017/1/6.
 */

public class DeletecardPost {
    private String id;
    private String payPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "DeletecardPost{" +
                "id='" + id + '\'' +
                ", payPassword='" + payPassword + '\'' +
                '}';
    }
}
