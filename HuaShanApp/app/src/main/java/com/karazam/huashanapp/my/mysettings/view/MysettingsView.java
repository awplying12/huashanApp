package com.karazam.huashanapp.my.mysettings.view;

import com.example.utils.base.BaseView;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface MysettingsView extends BaseView {

    void addPicturedialog();

    void setHeaderSuccess();

    void setHeaderFail(String s);

    void setHeaderError(Throwable e);
}
