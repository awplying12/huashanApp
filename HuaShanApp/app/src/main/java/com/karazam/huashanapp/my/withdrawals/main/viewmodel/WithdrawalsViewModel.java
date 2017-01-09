package com.karazam.huashanapp.my.withdrawals.main.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

/**
 * Created by Administrator on 2016/11/30.
 */

public abstract class WithdrawalsViewModel extends BaseViewModel{

    public EditText ed_moneny;
    public double avail;


    public CardBean card;
    public PasswordView pwd_view;

    public abstract void allwithdrawals(View view);

    public abstract void withdrawals(View view);

    public abstract void explain(View view);

    public abstract void toWithdrawals();
}
