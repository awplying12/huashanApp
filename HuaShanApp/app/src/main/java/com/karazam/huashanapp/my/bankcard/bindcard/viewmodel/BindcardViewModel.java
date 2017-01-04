package com.karazam.huashanapp.my.bankcard.bindcard.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/30.
 */

public abstract class BindcardViewModel extends BaseViewModel {

//    public EditText use_name;
//    public EditText id_num;
    public EditText card_num;
//    public EditText bank;
    public EditText phone_num;

    public int falg;

    public abstract void onNextstep(View view);

    public abstract void cleanName(View view);

    public abstract void cleanIdnum(View view);

    public abstract void cleanCardnum(View view);

    public abstract void cleanBank(View view);

    public abstract void cleanPhonenum(View view);

    public abstract void getBankData(View view);
}
