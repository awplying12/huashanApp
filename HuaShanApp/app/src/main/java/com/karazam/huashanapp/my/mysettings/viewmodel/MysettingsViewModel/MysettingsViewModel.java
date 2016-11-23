package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/22.
 */

public abstract class MysettingsViewModel extends BaseViewModel {

    public abstract void setupHeader(View view);

    public abstract void setupUserName(View view);
}
