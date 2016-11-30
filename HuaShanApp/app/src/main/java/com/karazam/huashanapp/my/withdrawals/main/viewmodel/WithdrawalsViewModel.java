package com.karazam.huashanapp.my.withdrawals.main.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/30.
 */

public abstract class WithdrawalsViewModel extends BaseViewModel{

    public EditText ed_moneny;
    public double avail;

    public PasswordView pwd_view;

    public abstract void allwithdrawals(View view);

    public abstract void withdrawals(View view);

    public abstract void explain(View view);
}
