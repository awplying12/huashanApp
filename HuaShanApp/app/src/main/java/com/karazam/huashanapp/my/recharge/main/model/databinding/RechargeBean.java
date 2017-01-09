package com.karazam.huashanapp.my.recharge.main.model.databinding;

import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;

/**
 * Created by Administrator on 2017/1/9.
 */

public class RechargeBean {

    private String capitalId;
    private MyAssetsBean assets;

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
        return "WithdrawalsBean{" +
                "capitalId='" + capitalId + '\'' +
                ", assets=" + assets +
                '}';
    }
}
