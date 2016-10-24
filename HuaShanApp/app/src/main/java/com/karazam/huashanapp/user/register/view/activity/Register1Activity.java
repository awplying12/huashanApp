package com.karazam.huashanapp.user.register.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.CheckPhoneNumberUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister1Binding;
import com.karazam.huashanapp.main.registerMain.registerActivity;
import com.karazam.huashanapp.user.register.model.databinbing.Register1Entity;
import com.karazam.huashanapp.user.register.view.Register1View;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModelImpl;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register1Activity extends BaseActivity implements Register1View{

    private ActivityRegister1Binding binding;
    private Register1ViewModel mModel;
    private Register1Entity entity = new Register1Entity();

    private EditText account_register;
    private TextView btn_next_step_1;
    private CheckPhoneNumberUtil ut;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_1);
        mModel = new Register1ViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        ut = new CheckPhoneNumberUtil();
        registerActivity.allRegisterActivity.add(this);
    }

    @Override
    public void initView() {
        account_register = (EditText) getView(R.id.ed_account_register);

        btn_next_step_1 = (TextView) getView(R.id.btn_next_step_1);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkText();


    }


    /**
     * 检查注册信息
     */
    private void checkText() {

        RxTextView.textChangeEvents(account_register)     //账号
                .debounce(300, TimeUnit.MILLISECONDS)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();


//                            if(ut.CheckPhoneNumber(key) || (!account_register.isFocusable())){
//                                getView(R.id.refgister_hint_text).setVisibility(View.GONE);
//                            }else {
//                                getView(R.id.refgister_hint_text).setVisibility(View.VISIBLE);
//                            }

                            if(ut.CheckPhoneNumber(key)){
                                getView(R.id.refgister_hint_text).setVisibility(View.GONE);
                                btn_next_step_1.setClickable(true);
                                btn_next_step_1.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);

                                mModel.phoneNum.set(key);
                            }else {
                                btn_next_step_1.setClickable(false);
                                getView(R.id.refgister_hint_text).setVisibility(View.VISIBLE);
                                btn_next_step_1.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                            }
                    }
                });
    }


    @Override
    public void nextStep() {
        getView(R.id.pl_1).setVisibility(View.VISIBLE);
        exitKeyboard();
    }

    @Override
    public void introduction() {
        getView(R.id.pl_1).setVisibility(View.GONE);
    }
}
