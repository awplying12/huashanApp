package com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;


/**
 * Created by Administrator on 2016/11/24.
 */

public abstract class UnauthorizedViewModel extends BaseViewModel {

    public EditText use_name;
    public EditText id_num;
    public EditText card_num;
    public EditText bank;
    public EditText phone_num;

    public abstract void onNextstep(View view);

    public abstract void cleanName(View view);

    public abstract void cleanIdnum(View view);

    public abstract void cleanCardnum(View view);

    public abstract void cleanBank(View view);

    public abstract void cleanPhonenum(View view);

    public abstract void onAuthentication();
}
