package com.karazam.huashanapp.my.security.paymentpassword.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityForgetBinding;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.ForgetView;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ForgetViewModel.ForgetViewModel;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ForgetViewModel.ForgetViewModelImpl;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ForgetActivity extends BaseActivity implements ForgetView{

    private ActivityForgetBinding binding;
    private ForgetViewModel mModel;
    private PaymentpwEntity entity = new PaymentpwEntity();

    private TextView hint_text;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget);
        mModel = new ForgetViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);
    }

    @Override
    public void initView() {
        hint_text = (TextView) getView(R.id.hint_text);
    }

    @Override
    public void dealLogicAfterInitView() {
        setHinttext();
    }

    /**
     * 设置提文字
     */
    private void setHinttext() {  //如无法修改支付密码，请拨打400-606-5500转7，由客服协助您进行修改

        hint_text.setText(Html.fromHtml("如无法修改支付密码，请拨打<font color='#0894EC'>0971-8011979</font>，由客服协助您进行修改"));

    }
}
