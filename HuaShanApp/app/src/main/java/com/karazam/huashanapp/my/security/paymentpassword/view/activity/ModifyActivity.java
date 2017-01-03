package com.karazam.huashanapp.my.security.paymentpassword.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityModifyBinding;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.ModifyView;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ModifyViewModel.ModifyViewModel;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ModifyViewModel.ModifyViewModelImpl;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ModifyActivity extends BaseActivity implements ModifyView {

    private ActivityModifyBinding binding;
    private ModifyViewModel mModel;
    private PaymentpwEntity entity = new PaymentpwEntity();

    private FloatLabeledEditText fet_paymentpw;

    private TextView next_step;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify);
        mModel = new ModifyViewModelImpl(this,entity,this,this);
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
        fet_paymentpw = (FloatLabeledEditText) getView(R.id.fet_paymentpw);
        fet_paymentpw.setHintTextViewColor(Color.parseColor("#0894EC"));
        mModel.payment_pw = (EditText) getView(R.id.payment_pw);
        next_step = (TextView) getView(R.id.next_step);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkContent();
    }

    /**
     * 检查内容
     */
    private void checkContent() {

        RxTextView.textChangeEvents(mModel.payment_pw)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                            next_step.setClickable(false);
                        }else {
                            next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                            next_step.setClickable(true);
                        }
                    }
                });

    }
}
