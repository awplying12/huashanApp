package com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel2;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class VerifyViewModel2 extends BaseViewModel {

    public EditText ed_card;
    public EditText ed_id;

    public abstract void onNextstep(View view);

    public abstract void cleanCard(View view);

    public abstract void cleanID(View view);
}
