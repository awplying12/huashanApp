package com.karazam.huashanapp.user.findpassword.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.CheckPhoneNumberUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityFindpasswordBinding;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.FindpasswordEntity;
import com.karazam.huashanapp.user.findpassword.main.view.FindpasswordView;
import com.karazam.huashanapp.user.findpassword.main.viewmodel.FindpasswordViewModel;
import com.karazam.huashanapp.user.findpassword.main.viewmodel.FindpasswordViewModelImpl;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;



/**
 * Created by Administrator on 2016/10/31.
 */

public class FindpasswordActivity extends BaseActivity implements FindpasswordView {

    private ActivityFindpasswordBinding binding;

    private FindpasswordEntity entity = new FindpasswordEntity();
    private FindpasswordViewModel mModel;



//    private EditText ed_verify_code;
    private TextView btn_next_step_2;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_findpassword);
        mModel = new FindpasswordViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

//        ed_verify_code = (EditText) getView(R.id.ed_verify_code_register);
        mModel.et_phonenum = (EditText) getView(R.id.et_phonenum);
        mModel.ed_code = (EditText) getView(R.id.ed_code);
        mModel.ed_password = (EditText) getView(R.id.ed_password);
        mModel.ed_password_two = (EditText) getView(R.id.ed_password_two);

        btn_next_step_2 = (TextView) getView(R.id.btn_next_step_2);
    }

    @Override
    public void dealLogicAfterInitView() {


        checkText();
    }

    /**
     * 检查信息
     */
    private boolean phonenumb = false,
                    codeb = false,
                    passwordb = false,
                    password_twob = false;
    private void checkText() {


        RxTextView.textChangeEvents(mModel.et_phonenum)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        CheckPhoneNumberUtil util = CheckPhoneNumberUtil.getInstance();

                        if(TextUtils.isEmpty(key)){
                            phonenumb = false;
                            mModel.tv_time.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                            mModel.tv_time.setClickable(false);
                        }else if(!util.CheckPhoneNumber(key)){
                            phonenumb = false;
                            mModel.tv_time.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                            mModel.tv_time.setClickable(false);
                        }else {
                            phonenumb = true;
                            mModel.tv_time.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                            mModel.tv_time.setClickable(true);
                        }

                        checkNextStep();
                    }
                });

        RxTextView.textChangeEvents(mModel.ed_code)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(key)){
                            codeb = false;
                        }else {
                            codeb = true;
                        }

                        checkNextStep();
                    }
                });

        RxTextView.textChangeEvents(mModel.ed_password)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(key)){
                            passwordb = false;
                        }else {
                            passwordb = true;
                        }

                        checkNextStep();
                    }
                });

        RxTextView.textChangeEvents(mModel.ed_password_two)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(key)){
                            password_twob = false;
                        }else {
                            password_twob = true;
                        }

                        checkNextStep();
                    }
                });
    }

    private void checkNextStep(){

        if (phonenumb && codeb && passwordb && password_twob){
            btn_next_step_2.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
            btn_next_step_2.setClickable(true);
        }else {
            btn_next_step_2.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
            btn_next_step_2.setClickable(false);
        }

    }
}
