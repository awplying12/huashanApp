package com.karazam.huashanapp.manage.purchase.model.databinding;

import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.manage.details.model.databinding.Project;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchaseRetureBean {

    private String orderNo;
    private String capitalId;
    private MyAssetsBean assets;
    private Project project;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "PurchaseRetureBean{" +
                "orderNo='" + orderNo + '\'' +
                ", capitalId='" + capitalId + '\'' +
                ", assets=" + assets +
                ", project=" + project +
                '}';
    }
}
