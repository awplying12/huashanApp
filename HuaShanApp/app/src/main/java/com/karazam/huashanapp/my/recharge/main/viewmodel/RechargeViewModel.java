package com.karazam.huashanapp.my.recharge.main.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class RechargeViewModel extends BaseViewModel{

    public EditText ed_money;

    public abstract void nextStep(View view);

    public abstract void Recharge();
}
