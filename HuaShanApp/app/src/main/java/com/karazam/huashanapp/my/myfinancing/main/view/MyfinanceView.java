package com.karazam.huashanapp.my.myfinancing.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface MyfinanceView extends BaseView{

    void myfinanceSuccess(MyfinanceBean bean);

    void myfinanceFail(String s);

    void myfinanceeError(Throwable e);
}
