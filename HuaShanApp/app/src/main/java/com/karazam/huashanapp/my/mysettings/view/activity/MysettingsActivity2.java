package com.karazam.huashanapp.my.mysettings.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.example.utils.base.BaseView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMysettings2Binding;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView2;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2.MysettingsViewModel2;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2.MysettingsViewModelImpl2;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MysettingsActivity2 extends BaseActivity implements MysettingsView2 {

    private ActivityMysettings2Binding binding;
    private MysettingsViewModel2 mModel;
    private MysettingsEntity entity = new MysettingsEntity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mysettings2);
        mModel = new MysettingsViewModelImpl2(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

    }
}
