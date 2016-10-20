package com.karazam.huashanapp.user.login.model.databinding;

/**
 * Created by Administrator on 2016/10/18.
 */

public class LoginEntity {

    private String loginName;
    private String password;

    public LoginEntity(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public LoginEntity() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
