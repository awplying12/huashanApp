package com.karazam.huashanapp.user.register.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister2Binding;
import com.karazam.huashanapp.user.register.model.databinbing.Register2Entity;
import com.karazam.huashanapp.user.register.view.Register2View;
import com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel.Register2ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel.Register2ViewModelImpl;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register2Activity extends BaseActivity implements Register2View {

    private ActivityRegister2Binding binding;
    private Register2Entity entity = new Register2Entity();
    private Register2ViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_2);
        mModel = new Register2ViewModelImpl(this,entity,this,this);
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
