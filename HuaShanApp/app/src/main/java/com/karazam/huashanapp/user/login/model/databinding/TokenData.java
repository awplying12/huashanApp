package com.karazam.huashanapp.user.login.model.databinding;

/**
 * Created by Administrator on 2016/10/20.
 */

public class TokenData {

    private String token;
    private String userId;
    private String userKey;
    private String sid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "TokenData{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", userKey='" + userKey + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
