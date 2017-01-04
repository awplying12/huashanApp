package com.karazam.huashanapp.my.realname.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameBean;

/**
 * Created by Administrator on 2016/11/24.
 */

public interface UnauthorizedView extends BaseView{

    void addSMSView();

    void disSMSView();

    void onRealnameSuccess(RealnameBean bean);

    void onRealnameFaile(String s);

    void onRealnameError(Throwable e);
}
