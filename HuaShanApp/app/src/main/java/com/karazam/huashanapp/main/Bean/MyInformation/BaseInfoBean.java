package com.karazam.huashanapp.main.Bean.MyInformation;

/**
 * Created by Administrator on 2016/12/29.
 */

public class BaseInfoBean {
//    "mobile":"152 **** 8683",
//            "realname":"李白",
//            "idno":"511502198810106835",
//            "avatar":null,
//            "name":"15228998683",
//            "authentication":true
//             isSetGesPassword=True
//              gesPassword=cc70e9369e9f87a660812e08d76a828e
    private String mobile;
    private String realname;
    private String idno;
    private String name;
    private String avatar;
    private String gesPassword;
    private boolean authentication;
    private boolean isSetGesPassword;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGesPassword() {
        return gesPassword;
    }

    public void setGesPassword(String gesPassword) {
        this.gesPassword = gesPassword;
    }

    public boolean isSetGesPassword() {
        return isSetGesPassword;
    }

    public void setSetGesPassword(boolean setGesPassword) {
        isSetGesPassword = setGesPassword;
    }

    @Override
    public String toString() {
        return "BaseInfoBean{" +
                "mobile='" + mobile + '\'' +
                ", realname='" + realname + '\'' +
                ", idno='" + idno + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gesPassword='" + gesPassword + '\'' +
                ", authentication=" + authentication +
                ", isSetGesPassword=" + isSetGesPassword +
                '}';
    }
}
