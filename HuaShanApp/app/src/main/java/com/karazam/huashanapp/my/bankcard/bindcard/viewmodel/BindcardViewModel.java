package com.karazam.huashanapp.my.bankcard.bindcard.viewmodel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;

/**
 * Created by Administrator on 2016/12/30.
 */

public abstract class BindcardViewModel extends BaseViewModel {

//    public EditText use_name;
//    public EditText id_num;
    public EditText card_num;
//    public EditText bank;
    public EditText phone_num;
    public String bankId = "-1";

    public SMSauthenticationView smsview;

    public int flag;

    public abstract void onNextstep(View view);

    public abstract void cleanName(View view);

    public abstract void cleanIdnum(View view);

    public abstract void cleanCardnum(View view);

    public abstract void cleanBank(View view);

    public abstract void cleanPhonenum(View view);

    public abstract void getBankData(View view);

    public abstract void onAddcard(boolean isQuick);

    public abstract void upDatecard();

    public abstract void sendSMS();
}
