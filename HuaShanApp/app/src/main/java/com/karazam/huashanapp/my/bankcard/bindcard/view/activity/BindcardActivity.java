package com.karazam.huashanapp.my.bankcard.bindcard.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityBankcardBinding;
import com.karazam.huashanapp.databinding.ActivityBindcardBinding;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardEntity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.BindcardView;
import com.karazam.huashanapp.my.bankcard.bindcard.viewmodel.BindcardViewModel;
import com.karazam.huashanapp.my.bankcard.bindcard.viewmodel.BindcardViewModelImpl;

/**
 * Created by Administrator on 2016/12/30.
 */

public class BindcardActivity extends BaseActivity implements BindcardView{

    private ActivityBindcardBinding binding;
    private BindcardEntity entity = new BindcardEntity();
    private BindcardViewModel mModel;



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bindcard);
        mModel = new BindcardViewModelImpl(this,entity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
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
