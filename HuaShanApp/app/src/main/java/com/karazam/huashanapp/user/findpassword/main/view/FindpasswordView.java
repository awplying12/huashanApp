package com.karazam.huashanapp.user.findpassword.main.view;

import com.example.utils.base.BaseView;

/**
 * Created by Administrator on 2016/10/31.
 */

public interface FindpasswordView extends BaseView{

    void FindpasswordSuccess(String msg);

    void FindpasswordFaile(String msg);

    void FindpasswordError(Throwable e);
}
