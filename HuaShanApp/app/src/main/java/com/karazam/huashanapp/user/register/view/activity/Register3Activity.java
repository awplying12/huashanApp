package com.karazam.huashanapp.user.register.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.percent.PercentFrameLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRegister3Binding;
import com.karazam.huashanapp.main.registerMain.registerActivity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.register.model.databinbing.Register3Entity;
import com.karazam.huashanapp.user.register.view.Register3View;
import com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel.Register3ViewModel;
import com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel.Register3ViewModelImpl;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import huashanapp.karazam.com.gesture_lock.GestureUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register3Activity extends BaseActivity implements Register3View {

    private ActivityRegister3Binding binding;
    private Register3Entity entity = new Register3Entity();
    private Register3ViewModel mModel;


    private TextView btn_complete;
    private ImageView show_icon;

    private PercentFrameLayout useragreement_pl;
    private LinearLayout useragreement_ll;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_3);
        mModel = new Register3ViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        registerActivity.allRegisterActivity.add(this);

        mModel.mobilel = getIntent().getStringExtra("PhoneNum");
        mModel.smsCode = getIntent().getStringExtra("VerifyCode");
    }

    @Override
    public void initView() {
        btn_complete = (TextView) getView(R.id.btn_complete);
        mModel.ed_password = (EditText) getView(R.id.ed_password_register);
        show_icon = (ImageView) getView(R.id.show_icon);

        useragreement_pl = (PercentFrameLayout) getView(R.id.useragreement_pl);
        useragreement_ll = (LinearLayout) getView(R.id.useragreement_ll);
    }

    @Override
    public void dealLogicAfterInitView() {
          checkText();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GestureUtil.GESTURELOCK_REQUESTCODE){
            switch (resultCode){
                case GestureUtil.GESTURELOCK_EDIT_RESULTCODE:
                    String key = data.getStringExtra(GestureUtil.Password);
                    showToast(key);
                    HuaShanApplication.editor.putString("gesture_lock",key).commit();
                    registerActivity.finishAll();
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * 检查信息
     */
    private void checkText() {
        RxTextView.textChangeEvents(mModel.ed_password)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();

                        if(key.matches("^([0-9]|[a-z]|[A-Z]){6,20}$")){

                            btn_complete.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                            btn_complete.setClickable(true);
                        }else {

                            btn_complete.setClickable(false);
                            btn_complete.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                        }
                    }
                });

        com.jakewharton.rxbinding.view.RxView.clicks(show_icon)
                .debounce(100,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        if(show_icon.isSelected()){
                            show_icon.setSelected(false);
                            mModel.ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }else {
                            show_icon.setSelected(true);
                            mModel.ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }

                    }
                });
    }


    /**
     * 显示用户协议
     */

    @Override
    public void showUserAgreement() {

//        if(useragreement_pl.getVisibility() == View.GONE ){
            useragreement_pl.setVisibility(View.VISIBLE);
            useragreement_ll.setVisibility(View.VISIBLE);
//        }

//        useragreement_ll.clearAnimation();
        Animation up = AnimationUtils.loadAnimation(this,R.anim.anim_arrow_up);
        useragreement_ll.setAnimation(up);
        up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//               useragreement_ll.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        View view = LayoutInflater.from(this).inflate(R.layout.layout_useragreement,null);
        useragreement_ll.addView(view);
    }

    /**
     * 收起用户协议
     */

    @Override
    public void stopUserAgreement(){
//        if(useragreement_pl.getVisibility() == View.VISIBLE ){

        useragreement_ll.clearAnimation();
        Animation down = AnimationUtils.loadAnimation(this,R.anim.anim_arrow_down);
            useragreement_ll.setAnimation(down);
            down.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    useragreement_ll.clearAnimation();
                    useragreement_ll.setVisibility(View.GONE);
                    useragreement_pl.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
//        }
    }

    /**
     * 注册成功
     */
    @Override
    public void registerSuccess(TokenData data) {

    }

    /**
     * 注册失败
     */
    @Override
    public void registerFaile(Throwable e) {

    }

}
