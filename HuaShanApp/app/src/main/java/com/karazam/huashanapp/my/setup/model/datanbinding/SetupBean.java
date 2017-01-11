package com.karazam.huashanapp.my.setup.model.datanbinding;

import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;

/**
 * Created by Administrator on 2017/1/10.
 */

public class SetupBean {

    private BaseInfoBean baseInfo;

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    public String toString() {
        return "SetupBean{" +
                "baseInfo=" + baseInfo +
                '}';
    }
}
