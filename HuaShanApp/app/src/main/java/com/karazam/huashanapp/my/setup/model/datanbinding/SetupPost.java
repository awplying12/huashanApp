package com.karazam.huashanapp.my.setup.model.datanbinding;

/**
 * Created by Administrator on 2017/1/10.
 */

public class SetupPost {
//    avatar	头像图片经BASE64编码的字符串
//    nickName	昵称

    private String avatar,
            nickName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SetupPost{" +
                "avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
