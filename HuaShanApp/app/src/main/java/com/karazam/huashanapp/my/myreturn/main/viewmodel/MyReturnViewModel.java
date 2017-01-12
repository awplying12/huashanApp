package com.karazam.huashanapp.my.myreturn.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/7.
 */

public abstract class MyReturnViewModel extends BaseViewModel {

    public boolean isEmpty = true;

    public static int allpage = 1;

    public abstract void Finanec(View view);

    public abstract void getMyReturnData(int page);
}
