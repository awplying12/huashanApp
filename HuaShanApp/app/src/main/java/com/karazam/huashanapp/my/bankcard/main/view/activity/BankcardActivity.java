package com.karazam.huashanapp.my.bankcard.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityBankcardBinding;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModel;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModelImpl;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BankcardActivity extends BaseActivity implements BankcardView{

    private ActivityBankcardBinding binding;
    private BankcardViewModel mModel;
    private BankcardEntity entity = new BankcardEntity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bankcard);
        mModel = new BankcardViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
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
