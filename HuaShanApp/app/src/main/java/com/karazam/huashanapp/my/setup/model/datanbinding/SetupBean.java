package com.karazam.huashanapp.my.setup.model.datanbinding;

/**
 * Created by Administrator on 2017/1/10.
 */

public class SetupBean {

//    "imageUrl":"/group1/M00/00/00/wKgCDVh0xdmAdqBpAAC3gF0jB58131.jpg",
//     "nickName":"12423434252"

    private String imageUrl
            ,nickName;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SetupBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
