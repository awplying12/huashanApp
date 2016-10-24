package com.karazam.huashanapp.user.register.view.activity;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister2Binding;
import com.karazam.huashanapp.main.registerMain.registerActivity;
import com.karazam.huashanapp.user.register.model.databinbing.Register2Entity;
import com.karazam.huashanapp.user.register.view.Register2View;
import com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel.Register2ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel.Register2ViewModelImpl;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register2Activity extends BaseActivity implements Register2View {

    private ActivityRegister2Binding binding;
    private Register2Entity entity = new Register2Entity();
    private Register2ViewModel mModel;

    private EditText ed_verify_code;
    private TextView btn_next_step_2;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_2);
        mModel = new Register2ViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        registerActivity.allRegisterActivity.add(this);
    }

    @Override
    public void initView() {

        ed_verify_code = (EditText) getView(R.id.ed_verify_code_register);

        btn_next_step_2 = (TextView) getView(R.id.btn_next_step_2);

    }

    @Override
    public void dealLogicAfterInitView() {
        checkText();
    }

    /**
     * 检查信息
     */
    private void checkText() {

        RxTextView.textChangeEvents(ed_verify_code)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(key)){
                            btn_next_step_2.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                            btn_next_step_2.setClickable(false);
                        }else {
                            btn_next_step_2.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                            btn_next_step_2.setClickable(true);
                        }
                    }
                });
    }
}
