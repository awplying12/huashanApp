package com.karazam.huashanapp.my.message.main.model.databinding;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessagelistItem {
//    "noticeTime":"1484276149000",
//            "title":"abcd",
//            "content":"abc"

    public String title,content,noticeTime;
//    public Long noticeTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "MessagelistItem{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noticeTime=" + noticeTime +
                '}';
    }

}
