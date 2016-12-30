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
    private String mobile;
    private String realname;
    private String idno;
    private String name;
    private String authentication;

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

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "BaseInfoBean{" +
                "mobile='" + mobile + '\'' +
                ", realname='" + realname + '\'' +
                ", idno='" + idno + '\'' +
                ", name='" + name + '\'' +
                ", authentication='" + authentication + '\'' +
                '}';
    }
}
