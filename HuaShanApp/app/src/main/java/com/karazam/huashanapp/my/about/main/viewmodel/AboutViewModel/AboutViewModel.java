package com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel;

import android.view.View;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.update.Updata;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class AboutViewModel extends BaseViewModel {

    public Updata updata;

    public abstract void updata(View view);
}
