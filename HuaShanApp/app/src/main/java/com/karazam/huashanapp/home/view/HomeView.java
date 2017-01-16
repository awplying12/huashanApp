package com.karazam.huashanapp.home.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;

/**
 * Created by Administrator on 2016/10/11.
 */

public interface HomeView extends BaseView{

    void setViewPagerCurrentItem(int position, String titleStr);

    void toLoginActivity();

    void CheckloginSuccess(CheckloginReturn checkloginReturn);

    void CheckloginFaile(String s);

    void CheckloginError(Throwable e);

    void getBaseDataFinish();
}
