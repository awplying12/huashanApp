package com.karazam.huashanapp.today.main.view.fragment.view;

/**
 * Created by Administrator on 2016/11/7.
 */

public class NewItem {
    private String text1;
    private String text2;
    private String text3;
    private String imgId;

    public NewItem() {
    }

    public NewItem(String text1, String text2, String text3, String imgId) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.imgId = imgId;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "NewItem{" +
                "text1='" + text1 + '\'' +
                ", text2='" + text2 + '\'' +
                ", text3='" + text3 + '\'' +
                ", imgId='" + imgId + '\'' +
                '}';
    }
}
