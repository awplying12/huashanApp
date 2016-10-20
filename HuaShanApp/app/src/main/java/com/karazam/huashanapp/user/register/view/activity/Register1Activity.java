package com.karazam.huashanapp.user.register.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister1Binding;
import com.karazam.huashanapp.user.register.model.databinbing.Register1Entity;
import com.karazam.huashanapp.user.register.view.Register1View;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModelImpl;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register1Activity extends BaseActivity implements Register1View{

    private ActivityRegister1Binding binding;
    private Register1ViewModel mModel;
    private Register1Entity entity = new Register1Entity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_1);
        mModel = new Register1ViewModelImpl(entity,this,this,this);
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
