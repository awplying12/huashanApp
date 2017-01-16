package com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding;

/**
 * Created by Administrator on 2017/1/13.
 */

public class RepaymentdetalisBean {
//    amount=1000
//    createDate=1484540831547
//    memo=测试测试测试测试
//    period=1/3
//    status=SUCCESS
//    statusDes=已成功
//    title=测试

    private String amount,
            createDate,
            memo,
            period,
            status,
            statusDes,
            title;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RepaymentdetalisBean{" +
                "amount='" + amount + '\'' +
                ", createDate='" + createDate + '\'' +
                ", memo='" + memo + '\'' +
                ", period='" + period + '\'' +
                ", status='" + status + '\'' +
                ", statusDes='" + statusDes + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
