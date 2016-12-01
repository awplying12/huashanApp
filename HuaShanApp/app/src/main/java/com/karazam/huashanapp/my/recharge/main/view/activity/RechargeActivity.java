package com.karazam.huashanapp.my.recharge.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargeBinding;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModel;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModelImpl;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RechargeActivity extends BaseActivity implements RechargeView {

    private ActivityRechargeBinding binding;
    private RechargeViewModel mModel;
    private RechargeEntity entity = new RechargeEntity();


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recharge);
        mModel = new RechargeViewModelImpl(this,entity,this,this);
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
