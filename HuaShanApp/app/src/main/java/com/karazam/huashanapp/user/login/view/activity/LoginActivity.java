package com.karazam.huashanapp.user.login.view.activity;

import android.app.Service;
import android.databinding.DataBindingUtil;
import android.os.Vibrator;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityLoginBinding;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.view.LoginView;
import com.karazam.huashanapp.user.login.viewmodel.LoginViewModel;
import com.karazam.huashanapp.user.login.viewmodel.LoginViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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

    private String account;
    private String password;

    private PercentFrameLayout content_pl;
    // 震动动画
    private Animation shakeAnimation;
    // 插值器
    private CycleInterpolator cycleInterpolator;
    // 振动器
    private Vibrator shakeVibrator;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mModel = new LoginViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        // 初始化振动器
        shakeVibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);

        // 初始化震动动画
        shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
        shakeAnimation.setDuration(300);
        cycleInterpolator = new CycleInterpolator(8);
        shakeAnimation.setInterpolator(cycleInterpolator);
    }

    @Override
    public void initView() {
        ed_account = (EditText) getView(R.id.ed_account);
        ed_password = (EditText) getView(R.id.ed_password);

        btn_login = (TextView) getView(R.id.btn_login);

        content_pl = (PercentFrameLayout) getView(R.id.content_pl);
    }

    @Override
    public void dealLogicAfterInitView() {

        checkText();
        login();


    }

    /**
     * 登录
     */
    private RxProperty<Boolean> loginText = RxProperty.create();
    private void login() {

        RxView.clicks(btn_login)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showToast("正在登录！");
                        loginText.set(true);
                        mModel.login(account,password);
                    }
                });
        com.ogaclejapan.rx.binding.RxView.of(btn_login).bind(loginText, new Rx.Action<TextView, Boolean>() {
            @Override
            public void call(TextView target, Boolean aBoolean) {
                if(aBoolean){
                    target.setText("登录中...");
                }else {
                    target.setText("登 录");
                }
            }
        });
    }


    @Override
    public void loginSuccess() {    //登录成功
        showToast("登录成功");
        loginText.set(false);

        HuaShanApplication.editor.putInt("loginStatus",1).commit();
        HuaShanApplication.editor.putString("account",account).commit();
        HuaShanApplication.account = account;
        HuaShanApplication.loginStatus = 1;
        setResult(101);
        finish();
    }



    @Override
    public void loginFaile() {      //登录失败
        showToast("登录失败");
        loginText.set(false);

        content_pl.startAnimation(shakeAnimation);

        HuaShanApplication.editor.putInt("loginStatus",2).commit();
        HuaShanApplication.loginStatus = 2;
//        setResult(102);
//        finish();
    }

    /**
     * 检查登录信息
     */
    boolean accountOK = false;
    boolean passwordOK = false;
    private void checkText() {

        RxTextView.textChangeEvents(ed_account)     //账号
                .debounce(300, TimeUnit.MILLISECONDS)
                .doOnNext(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        account = textViewTextChangeEvent.text().toString().trim();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();
                        accountOK = !TextUtils.isEmpty(key);
                        isClickable();
                    }
                });

        RxTextView.textChangeEvents(ed_password)       //密码
                .debounce(300, TimeUnit.MILLISECONDS)
                .doOnNext(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        password = textViewTextChangeEvent.text().toString().trim();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String key = textViewTextChangeEvent.text().toString().trim();
                        passwordOK = !TextUtils.isEmpty(key);
                        isClickable();
                    }
                });
    }

    /**
     * 是否可点击登录
     */
    private void isClickable(){
        if(accountOK && passwordOK){
            btn_login.setClickable(true);
            btn_login.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
        }else {
            btn_login.setClickable(false);
            btn_login.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
        }
    }



}
