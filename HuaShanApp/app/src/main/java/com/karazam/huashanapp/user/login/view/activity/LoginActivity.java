package com.karazam.huashanapp.user.login.view.activity;

import android.databinding.DataBindingUtil;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityLoginBinding;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.view.LoginView;
import com.karazam.huashanapp.user.login.viewmodel.LoginViewModel;
import com.karazam.huashanapp.user.login.viewmodel.LoginViewModelImpl;

/**
 * Created by Administrator on 2016/10/18.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    private ActivityLoginBinding binding;

    private LoginViewModel mModel;
    private LoginEntity entity = new LoginEntity();


    private EditText ed_account;
    private EditText ed_password;

    private TextView btn_login;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mModel = new LoginViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        ed_account = (EditText) getView(R.id.ed_account);
        ed_password = (EditText) getView(R.id.ed_password);

        btn_login = (TextView) getView(R.id.btn_login);
    }

    @Override
    public void dealLogicAfterInitView() {


    }


}
