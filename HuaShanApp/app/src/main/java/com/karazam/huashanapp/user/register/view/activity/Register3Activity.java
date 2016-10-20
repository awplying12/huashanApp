package com.karazam.huashanapp.user.register.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister3Binding;
import com.karazam.huashanapp.user.register.model.databinbing.Register3Entity;
import com.karazam.huashanapp.user.register.view.Register3View;
import com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel.Register3ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel.Register3ViewModelImpl;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register3Activity extends BaseActivity implements Register3View {

    private ActivityRegister3Binding binding;
    private Register3Entity entity = new Register3Entity();
    private Register3ViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_3);
        mModel = new Register3ViewModelImpl(this,entity,this,this);
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
