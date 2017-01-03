package com.karazam.huashanapp.my.realname.model.databinding;

import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;

/**
 * Created by Administrator on 2016/12/30.
 */

public class RealnameBean {
    private BaseInfoBean baseInfo;

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    public String toString() {
        return "RealnameBean{" +
                "baseInfo=" + baseInfo +
                '}';
    }
}
