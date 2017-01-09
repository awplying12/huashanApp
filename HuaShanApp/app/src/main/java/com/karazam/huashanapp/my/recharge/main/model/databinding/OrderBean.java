package com.karazam.huashanapp.my.recharge.main.model.databinding;

/**
 * Created by Administrator on 2017/1/9.
 */

public class OrderBean {
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}
