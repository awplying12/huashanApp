package com.karazam.huashanapp.my.message.main.model.databinding;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessagelistBean {

//    newProjectNotice 新标
//    repaymentNotice 还款
//    systemNotice 系统
//    myMessage 我的

    private MessagelistItem newProjectNotice,
                            repaymentNotice,
                            systemNotice,
                            myMessage;

    public MessagelistItem getNewProjectNotice() {
        return newProjectNotice;
    }

    public void setNewProjectNotice(MessagelistItem newProjectNotice) {
        this.newProjectNotice = newProjectNotice;
    }

    public MessagelistItem getRepaymentNotice() {
        return repaymentNotice;
    }

    public void setRepaymentNotice(MessagelistItem repaymentNotice) {
        this.repaymentNotice = repaymentNotice;
    }

    public MessagelistItem getSystemNotice() {
        return systemNotice;
    }

    public void setSystemNotice(MessagelistItem systemNotice) {
        this.systemNotice = systemNotice;
    }

    public MessagelistItem getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(MessagelistItem myMessage) {
        this.myMessage = myMessage;
    }

    @Override
    public String toString() {
        return "MessagelistBean{" +
                "newProjectNotice=" + newProjectNotice +
                ", repaymentNotice=" + repaymentNotice +
                ", systemNotice=" + systemNotice +
                ", myMessage=" + myMessage +
                '}';
    }
}
