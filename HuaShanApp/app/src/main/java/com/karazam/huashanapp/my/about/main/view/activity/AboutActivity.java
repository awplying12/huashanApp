package com.karazam.huashanapp.my.about.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityAboutBinding;
import com.karazam.huashanapp.my.about.main.model.databinding.AboutEntity;
import com.karazam.huashanapp.my.about.main.view.AboutView;
import com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel.AboutViewModel;
import com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel.AboutViewModelImpl;

/**
 * Created by Administrator on 2016/11/29.
 */

public class AboutActivity extends BaseActivity implements AboutView {

    private ActivityAboutBinding binding;
    private AboutViewModel mModel;
    private AboutEntity entity = new AboutEntity();
    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        mModel = new AboutViewModelImpl(this,entity,this,this);
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
