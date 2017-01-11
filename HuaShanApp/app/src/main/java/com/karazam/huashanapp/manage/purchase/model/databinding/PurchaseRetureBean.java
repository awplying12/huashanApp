package com.karazam.huashanapp.manage.purchase.model.databinding;

import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchaseRetureBean {

    private String orderNo;
    private String capitalId;
    private MyAssetsBean assets;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(String capitalId) {
        this.capitalId = capitalId;
    }

    public MyAssetsBean getAssets() {
        return assets;
    }

    public void setAssets(MyAssetsBean assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "PurchaseRetureBean{" +
                "orderNo='" + orderNo + '\'' +
                ", capitalId='" + capitalId + '\'' +
                ", assets=" + assets +
                '}';
    }
}
