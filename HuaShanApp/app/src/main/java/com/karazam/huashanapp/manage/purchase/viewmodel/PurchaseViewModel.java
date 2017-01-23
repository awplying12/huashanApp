package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasBean;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class PurchaseViewModel extends BaseViewModel {


    public EditText ed_amountofmoney;
    public String borrowingId;
    public String mode;

    public String type;

    public CardBean cardBean = new CardBean();


    public abstract void onPaymentMethod(View view);

    public abstract void onPurchase(View view);

    public abstract void onAgreement(View view);

    public abstract void Recharge(View view);

    public abstract void onPurchase(String payPassword,String captcha);

    public abstract void sendSMS();


}
