package com.karazam.huashanapp.user.register.view;

import com.example.utils.base.BaseView;

/**
 * Created by Administrator on 2016/10/20.
 */

public interface Register1View extends BaseView{

    void nextStep();

    void introduction();

    void checkMobileSuccess(String message);

    void checkMobileFaile(String e);
}
