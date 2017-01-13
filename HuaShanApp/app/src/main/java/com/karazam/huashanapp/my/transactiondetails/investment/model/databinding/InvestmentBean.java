package com.karazam.huashanapp.my.transactiondetails.investment.model.databinding;

/**
 * Created by Administrator on 2017/1/13.
 */

public class InvestmentBean {
//    "amount":100000,
//            "memo":"按月还款、等额本息",
//            "buyMethod":"余额支付",
//            "title":"测试001",
//            "createDate":"2017-01-09 19:37:33",
//            "status":"WAITING_PAY"
//             statusDes=待支付

    private String amount,memo,buyMethod,title,status,statusDes;

    private long createDate;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBuyMethod() {
        return buyMethod;
    }

    public void setBuyMethod(String buyMethod) {
        this.buyMethod = buyMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    @Override
    public String toString() {
        return "InvestmentBean{" +
                "amount='" + amount + '\'' +
                ", memo='" + memo + '\'' +
                ", buyMethod='" + buyMethod + '\'' +
                ", title='" + title + '\'' +
                ", createDate='" + createDate + '\'' +
                ", status='" + status + '\'' +
                ", statusDes='" + statusDes + '\'' +
                '}';
    }
}
