package com.karazam.huashanapp.manage.purchase.model.databinding;

import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchasBean {

    private String paymentMethod = "QUICK_PAY";
    private CardBean bean;
    private MyAssetsBean assets;


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardBean getBean() {
        return bean;
    }

    public void setBean(CardBean bean) {
        this.bean = bean;
    }

    public MyAssetsBean getAssets() {
        return assets;
    }

    public void setAssets(MyAssetsBean assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "PurchasBean{" +
                "paymentMethod='" + paymentMethod + '\'' +
                ", bean=" + bean +
                ", assets=" + assets +
                '}';
    }
}
