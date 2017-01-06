package com.karazam.huashanapp.my.bankcard.bindcard.model.databinding;

import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BindcardBean {

    private CardBean withdrawCard = new CardBean();
    private ArrayList<CardBean> quickCards = new ArrayList<>();

    public CardBean getWithdrawCard() {
        return withdrawCard;
    }

    public void setWithdrawCard(CardBean withdrawCard) {
        this.withdrawCard = withdrawCard;
    }

    public ArrayList<CardBean> getQuickCards() {
        return quickCards;
    }

    public void setQuickCards(ArrayList<CardBean> quickCards) {
        this.quickCards = quickCards;
    }

    @Override
    public String toString() {
        return "BindcardBean{" +
                "withdrawCard=" + withdrawCard +
                ", quickCards=" + quickCards +
                '}';
    }
}
