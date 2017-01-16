package com.karazam.huashanapp.my.message.messagedetails.view.view;

/**
 * Created by Administrator on 2016/12/21.
 */

public class DetailsBean {

//    content=新标通知[2017-0116]
//    noticeTime=1484554448000
//    title=新标通知[2017-0116]

    private String title,noticeTime,content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DetailsBean{" +
                "title='" + title + '\'' +
                ", noticeTime='" + noticeTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
