package com.karazam.huashanapp.user.findpassword.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityVerificationBinding;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.VerificationEntity;
import com.karazam.huashanapp.user.findpassword.main.view.VerificationView;
import com.karazam.huashanapp.user.findpassword.main.viewmodel.VerificationViewModel;
import com.karazam.huashanapp.user.findpassword.main.viewmodel.VerificationViewModelImpl;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/31.
 */

public class VerificationActivity extends BaseActivity implements VerificationView{

    private ActivityVerificationBinding binding;

    private VerificationEntity entity = new VerificationEntity();
    private VerificationViewModel mModel;

    private int sign;

    private EditText ed_verify_code;
    private TextView btn_next_step_2;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification);
        mModel = new VerificationViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        sign = getIntent().getIntExtra("sign",-1);

    }

    @Override
    public void initView() {

        ed_verify_code = (EditText) getView(R.id.ed_verify_code_register);

        btn_next_step_2 = (TextView) getView(R.id.btn_next_step_2);
    }

    @Override
    public void dealLogicAfterInitView() {


        checkText();
        switch (sign){
            case 1: //password
                break;
            case 2: //geturelock
                break;
            default:
                break;
        }
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
