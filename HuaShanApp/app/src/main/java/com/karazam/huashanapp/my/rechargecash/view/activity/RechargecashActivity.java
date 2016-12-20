package com.karazam.huashanapp.my.rechargecash.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargecashBinding;
import com.karazam.huashanapp.my.rechargecash.model.databinding.RechargecashEntity;
import com.karazam.huashanapp.my.rechargecash.view.RechargecashView;
import com.karazam.huashanapp.my.rechargecash.viewmodel.RechargecashViewModel;
import com.karazam.huashanapp.my.rechargecash.viewmodel.RechargecashViewModelImpl;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RechargecashActivity extends BaseActivity implements RechargecashView {


    private ActivityRechargecashBinding binding;
    private RechargecashEntity entity = new RechargecashEntity();
    private RechargecashViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_rechargecash);
        mModel = new RechargecashViewModelImpl(this,entity,this,this);
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