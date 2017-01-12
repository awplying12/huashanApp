package com.karazam.huashanapp.my.myreturn.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnBean;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface MyReturnView extends BaseView{

    void myReturnSuccess(MyReturnBean bean);

    void myReturnFail(String s);

    void myReturnError(Throwable e);
}
