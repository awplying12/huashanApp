package com.karazam.huashanapp.main.Bean;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageBean {
    private int iconId;
    private String title;

    public MessageBean(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "iconId=" + iconId +
                ", title='" + title + '\'' +
                '}';
    }
}
