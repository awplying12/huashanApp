package com.karazam.huashanapp.manage.details_fragment.model.databinding;

/**
 * Created by Administrator on 2016/12/29.
 */

public class OpinionsItem {

    private String t1;
    private String t2;
    private String t3;
    private int flag;

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "OpinionsItem{" +
                "t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", t3='" + t3 + '\'' +
                ", flag=" + flag +
                '}';
    }
}
