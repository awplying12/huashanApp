package com.karazam.huashanapp.home.model.databinding;

/**
 * Created by Administrator on 2016/12/21.
 */

public class CheckloginBean {

    private String userId;
    private String userKey;
    private Boolean corp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Boolean getCorp() {
        return corp;
    }

    public void setCorp(Boolean corp) {
        this.corp = corp;
    }

    @Override
    public String toString() {
        return "CheckloginBean{" +
                "userId='" + userId + '\'' +
                ", userKey='" + userKey + '\'' +
                ", corp='" + corp + '\'' +
                '}';
    }
}
