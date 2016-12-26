package com.karazam.huashanapp.my.security.paymentpassword3.view.activity;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentpassword.SetpasswordView;
import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivitySetpaypsBinding;
import com.karazam.huashanapp.my.security.paymentpassword3.model.datainding.SetpaypsEntity;
import com.karazam.huashanapp.my.security.paymentpassword3.view.SetpaypsView;
import com.karazam.huashanapp.my.security.paymentpassword3.viewmodel.SetpaypsViewModel;
import com.karazam.huashanapp.my.security.paymentpassword3.viewmodel.SetpaypsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class SetpaypsActivity extends BaseActivity implements SetpaypsView {

    private ActivitySetpaypsBinding binding;
    private SetpaypsViewModel mModel;
    private SetpaypsEntity entity = new SetpaypsEntity();

    private SetpasswordView spwd_view;

    private TextView hint_text;
    private TextView btn_confirm;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setpayps);
        mModel = new SetpaypsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);
        mModel.tag = getIntent().getStringExtra("isRealName");
    }

    @Override
    public void initView() {
        spwd_view = (SetpasswordView) getView(R.id.spwd_view);

        hint_text = (TextView) getView(R.id.hint_text);
        btn_confirm = (TextView) getView(R.id.btn_confirm);
    }

    @Override
    public void dealLogicAfterInitView() {
        setPasswordView();
        setContent();
    }

    /**
     * 设置内容
     */
    private void setContent() {

        RxView.of(hint_text).bind(mModel.pw, new Rx.Action<TextView, String>() {
            @Override
            public void call(TextView target, String s) {


                if(TextUtils.isEmpty(mModel.one)){
                    target.setText("设置6位数字支付密码");
                    btn_confirm.setVisibility(View.GONE);
                }else {
                    target.setText("确认6位数字支付密码");
                    btn_confirm.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    /**
     * 设置支付密码控件PasswordView
     */
    private void setPasswordView(){
        spwd_view.setOnPasswordViewListener(new SetpasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {



                if(TextUtils.isEmpty(mModel.one) && TextUtils.isEmpty(mModel.two)){
                    mModel.one =spwd_view.getStrPassword();
                    spwd_view.initContent();
                }else if(!TextUtils.isEmpty(mModel.one) && TextUtils.isEmpty(mModel.two)){
                    mModel.two =spwd_view.getStrPassword();
                }
                mModel.pw.set("");

            }
        });

    }

    /**
     * 重置
     */
    @Override
    public void Reset() {
        mModel.one = "";
        mModel.two = "";
        spwd_view.initContent();
        mModel.pw.set("");
    }



}
