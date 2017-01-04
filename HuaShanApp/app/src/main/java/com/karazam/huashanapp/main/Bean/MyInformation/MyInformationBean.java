package com.karazam.huashanapp.main.Bean.MyInformation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/29.
 */

public class MyInformationBean {

        private BaseInfoBean baseInfo;
        private CardBean withdrawCard = new CardBean();
        private ArrayList<CardBean> quickCards = new ArrayList<>();

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    public CardBean getWithdrawCardl() {
        return withdrawCard;
    }

    public void setWithdrawCardl(CardBean withdrawCardl) {
        this.withdrawCard = withdrawCardl;
    }

    public ArrayList<CardBean> getQuickCards() {
        return quickCards;
    }

    public void setQuickCards(ArrayList<CardBean> quickCards) {
        this.quickCards = quickCards;
    }

    @Override
    public String toString() {
        return "MyInformationBean{" +
                "baseInfo=" + baseInfo +
                ", withdrawCardl=" + withdrawCard +
                ", quickCards=" + quickCards +
                '}';
    }

}
