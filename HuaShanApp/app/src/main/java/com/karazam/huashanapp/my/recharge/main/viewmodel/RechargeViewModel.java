package com.karazam.huashanapp.my.recharge.main.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class RechargeViewModel extends BaseViewModel{

    public EditText ed_money;
    public CardBean card;
    public String orderNo;

    public abstract void nextStep(View view);

    public abstract void changeCard(View view);

    public abstract void Recharge();

    public abstract void sendSMS();
}
