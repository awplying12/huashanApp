package com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding;

/**
 * Created by Administrator on 2017/1/13.
 */

public class RechargedetalisBean {
//    amount=20000
//    createDate=1484107035000
//    memo=充值-用户[51]-方式[快捷支付]
//    payMethod=-(45210)
//    status=SUCCESS
//    statusDes=已成功

    private String amount,
            createDate,
            memo,
            payMethod,
            status,
            statusDes;


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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
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
        return "RechargedetalisBean{" +
                "amount='" + amount + '\'' +
                ", createDate='" + createDate + '\'' +
                ", memo='" + memo + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", status='" + status + '\'' +
                ", statusDes='" + statusDes + '\'' +
                '}';
    }
}
