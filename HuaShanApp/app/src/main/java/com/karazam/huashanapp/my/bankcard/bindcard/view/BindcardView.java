package com.karazam.huashanapp.my.bankcard.bindcard.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;

/**
 * Created by Administrator on 2016/12/30.
 */

public interface BindcardView extends BaseView {


    void addSMSView();

    void disSMSView();

    void getResultSuccess(BindcardBean bean);

    void getResultFail(String s);

    void getResultError(Throwable e);


}
