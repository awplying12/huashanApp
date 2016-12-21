package com.karazam.huashanapp.home.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/10/11.
 */

public abstract class HomeViewModel extends BaseViewModel{

    public abstract void toFinanec(View view);

    public abstract void toToday(View view);

    public abstract void toManage(View view);

    public abstract void toApply(View view);

    public abstract void toMy(View view);

    public abstract void setUp(View view);

    public abstract void onChecklogin();

}
