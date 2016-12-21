package com.karazam.huashanapp.my.security.gesturepassword.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;

/**
 * Created by Administrator on 2016/11/28.
 */

public interface GesturepwView extends BaseView{

    void toGestureEdit();

    void setGesPasswordSuccess(GespwReturn gespwReturn);

    void setGesPasswordFaile(Throwable e);



}
