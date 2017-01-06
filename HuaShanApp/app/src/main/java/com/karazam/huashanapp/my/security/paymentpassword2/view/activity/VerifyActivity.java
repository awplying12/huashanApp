package com.karazam.huashanapp.my.security.paymentpassword2.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityVerifyBinding;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel.VerifyViewModel;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel.VerifyViewModelImpl;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyActivity extends BaseActivity implements VerifyView {

    private ActivityVerifyBinding binding;
    private VerifyViewModel mModel;
    private VerifyEntity entity = new VerifyEntity();

    private SMSauthenticationView smsview;
    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify);
        mModel = new VerifyViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {
        setSMSView();
    }

    /**
     * 设置短息验证View
     */
    private void setSMSView() {
        smsview = new SMSauthenticationView(this);
        smsview.setView(HuaShanApplication.account,"MODIFY_PAY_PASSWORD_CODE",(ViewGroup) getView(R.id.content_wpl), new SMSauthenticationView.OnAuthenticationListener() {
            @Override
            public void onLeft(View view) {
                smsview.dismiss();
            }

            @Override
            public void onRight(View view) {
                smsview.verification();
                showProgressDialog();
            }

            @Override
            public void onHelp(View view) {

            }

            @Override
            public void onResend(View view) {

            }

            @Override
            public void onResult(boolean result) {
                dissmissProgressDialog();
                if(result){

//                    toOtherActivity(VerifyActivity.this, SetpaypsActivity.class);
                    Intent intent = new Intent(VerifyActivity.this, SetpaypsActivity.class);
                    intent.putExtra("isRealName","no");
                    intent.putExtra("type","update");
                    startActivity(intent);
                    smsview.dismiss();
                }
            }
        });

        smsview.setText1("验证手机号");
        String str = HuaShanApplication.account.substring(7,11);
        smsview.setText2("输入手机尾号"+str+"接收到的验证码");
    }

    /**
     * 弹出手机验证
     */
    @Override
    public void addSMS() {
        smsview.show();
    }
}
