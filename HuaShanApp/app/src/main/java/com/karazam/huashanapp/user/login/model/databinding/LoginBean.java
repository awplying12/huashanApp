package com.karazam.huashanapp.user.login.model.databinding;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LoginBean {
    private String username;
    private String password;
    private Boolean corp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCorp() {
        return corp;
    }

    public void setCorp(Boolean corp) {
        this.corp = corp;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", corp='" + corp + '\'' +
                '}';
    }
}
