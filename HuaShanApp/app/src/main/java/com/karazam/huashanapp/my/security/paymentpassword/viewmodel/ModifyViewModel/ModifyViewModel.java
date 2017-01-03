package com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ModifyViewModel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class ModifyViewModel extends BaseViewModel {

    public EditText payment_pw;

    public abstract void onNextstep(View view);

    public abstract void checkPayment();
}
