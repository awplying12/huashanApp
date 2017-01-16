package com.karazam.huashanapp.main.Bean;

import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistItem;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageBean {
    private int iconId;
    private String title;
    private String type;
    private MessagelistItem item;

    public MessageBean(int iconId, String title, String type) {
        this.iconId = iconId;
        this.title = title;
        this.type = type;
    }

    public MessageBean(int iconId, String title, String type, MessagelistItem item) {
        this.iconId = iconId;
        this.title = title;
        this.type = type;
        this.item = item;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MessagelistItem getItem() {
        return item;
    }

    public void setItem(MessagelistItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "iconId=" + iconId +
                ", title='" + title + '\'' +
                ", item=" + item +
                '}';
    }

}
