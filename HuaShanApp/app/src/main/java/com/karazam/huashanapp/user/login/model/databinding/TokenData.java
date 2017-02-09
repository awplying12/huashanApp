package com.karazam.huashanapp.user.login.model.databinding;

/**
 * Created by Administrator on 2016/10/20.
 */

public class TokenData {

//    userKey=74c549b62aa6e7a8a2e60e2cf0dc473f
//    userId=1289
//    sid=2eb8b3b8-930b-44d9-bc29-d7aadd609522

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
