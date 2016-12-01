package com.karazam.huashanapp.my.withdrawals.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityWithdrawalsBinding;
import com.karazam.huashanapp.main.UserInformation;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity.WithdrawalsdetailsActivity;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsEntity;
import com.karazam.huashanapp.my.withdrawals.main.view.WithdrawalsView;
import com.karazam.huashanapp.my.withdrawals.main.viewmodel.WithdrawalsViewModel;
import com.karazam.huashanapp.my.withdrawals.main.viewmodel.WithdrawalsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/30.
 */

public class WithdrawalsActivity extends BaseActivity implements WithdrawalsView {

    private ActivityWithdrawalsBinding binding;
    private WithdrawalsViewModel mModel;
    private WithdrawalsEntity entity = new WithdrawalsEntity();


    private TextView avail_moneny;
    private TextView btn_withdrawals;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawals);
        mModel = new WithdrawalsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        mModel.ed_moneny = (EditText) getView(R.id.ed_moneny);
        mModel.pwd_view = (PasswordView) getView(R.id.pwd_view);
        avail_moneny = (TextView) getView(R.id.avail_moneny);
        btn_withdrawals = (TextView) getView(R.id.btn_withdrawals);

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
        setPasswordView();

    }

    /**
     * 设置界面
     */

    private void setLayout() {
        RxView.of(avail_moneny).bind(HuaShanApplication.userInformationR, new Rx.Action<TextView, UserInformation>() {
            @Override
            public void call(TextView target, UserInformation userInformation) {

                String availStr = StringUtil.interrupt(userInformation.getUserbalance(),0,"0");
                mModel.avail = Double.parseDouble(availStr);
                target.setText("可用余额"+availStr+"元");

                checkContent(mModel.avail);
            }
        });
    }



    /**
     * 检验内容
     */
    private boolean moneny = false;

    private void checkContent(final double avail) {

        RxTextView.textChangeEvents(mModel.ed_moneny)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            moneny = false;
                        } else if(str.equals("0")){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>提价金额不能为或是0"));
                            moneny = false;
                        }else if(!TextUtils.isEmpty(str) && Double.parseDouble(str) > avail){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>金额已超过可提现余额"));
                            moneny = false;
                        }else if(!TextUtils.isEmpty(str) && Double.parseDouble(str) < 50){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>提现金额不能低于50"));
                            moneny = false;
                        }else {
                            avail_moneny.setText("可用余额"+avail+"元");
                            moneny = true;
                        }

                        checkButton();
                    }
                });
    }

    /**
     * 检查按钮“下一步”
     */
    private void checkButton() {

        if(moneny){
            btn_withdrawals.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
            btn_withdrawals.setClickable(true);

        }else {
            btn_withdrawals.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
            btn_withdrawals.setClickable(false);

        }
    }

    /**
     * 设置支付密码控件PasswordView
     */
    private void setPasswordView(){

        mModel.pwd_view.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {
                showToast(mModel.pwd_view.getStrPassword());
                mModel.pwd_view.out();

                toOtherActivity(WithdrawalsActivity.this, WithdrawalsdetailsActivity.class);
            }

            @Override
            public void onBack(View v) {
                mModel.pwd_view.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });
    }
}
