package com.karazam.huashanapp.my.setup.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/22.
 */

public abstract class SetupViewModel extends BaseViewModel {

    public abstract void toMysettings(View view);

    public abstract void toRealname(View view);

    public abstract void toSecurity(View view);

    public abstract void toSoftwareSetting(View view);

    public abstract void toRecommend(View view);

    public abstract void toAbout(View view);
}
