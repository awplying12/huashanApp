package com.karazam.huashanapp.main.Bean.MyInformation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/29.
 */

public class MyInformationBean {
        private String corp;
        private BaseInfoBean baseInfo;
        private CardBean withdrawCard = new CardBean();
        private ArrayList<CardBean> quickCards = new ArrayList<>();

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    public ArrayList<CardBean> getQuickCards() {
        return quickCards;
    }

    public void setQuickCards(ArrayList<CardBean> quickCards) {
        this.quickCards = quickCards;
    }

    public CardBean getWithdrawCard() {
        return withdrawCard;
    }

    public void setWithdrawCard(CardBean withdrawCard) {
        this.withdrawCard = withdrawCard;
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
