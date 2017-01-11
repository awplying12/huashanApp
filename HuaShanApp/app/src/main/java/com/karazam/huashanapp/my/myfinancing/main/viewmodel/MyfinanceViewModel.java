package com.karazam.huashanapp.my.myfinancing.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/5.
 */

public abstract class MyfinanceViewModel extends BaseViewModel{

    public boolean isEmpty = true;

    public static int allpage = 1;
    public String mProgress = "investing";    //investing("投资中")
                                                // repaying("还款中")
                                                //completed("已完成")

    public abstract void Finanec(View view);

    public abstract void getMyfinanceData(String progress,int currentPage);
}
