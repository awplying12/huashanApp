package com.karazam.huashanapp.my.transactiondetails.main.view.activity.view;

import com.example.utils.utils.DataUtil;

import java.text.ParseException;

/**
 * Created by Administrator on 2016/12/9.
 */

public class TransactionItem {

    // "orderId":1,
    // "amount":100000.00,
    // "type":"BALANCE",
    //  orderNo =  111201701121821493540524604551
    // "createDate":1483961853000,
    //  memo=充值-用户[51]-方式[快捷支付]-充值

    private long createDate;
    private String weekDay,
                orderId,
                orderNo,
                amount,
                type,memo;

    public TransactionItem() {
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getWeekDay() {

        try {
            if(DataUtil.IsToday(createDate)){
                weekDay = "今天";
            }else if(DataUtil.IsYesterday(createDate)){
                weekDay = "昨天";
            }else {
                weekDay = DataUtil.getWeekDay(createDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "TransactionItem{" +
                "createDate=" + createDate +
                ", weekDay='" + weekDay + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
