package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/23.
 */

public abstract class MysettingsViewModel2 extends BaseViewModel{

    public EditText ed_nick;

    public abstract void cleanContent(View view);

    public abstract void saveData(View view);
}
