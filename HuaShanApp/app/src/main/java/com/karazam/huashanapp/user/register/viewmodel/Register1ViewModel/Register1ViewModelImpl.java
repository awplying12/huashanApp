package com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.user.register.model.databinbing.Register1Entity;
import com.karazam.huashanapp.user.register.view.Register1View;
import com.karazam.huashanapp.user.register.view.activity.Register1Activity;
import com.karazam.huashanapp.user.register.view.activity.Register2Activity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register1ViewModelImpl extends Register1ViewModel {

    private Register1Entity mEntity;
    private Register1View mView;
    private Context context;
    private Register1Activity activity;

    public Register1ViewModelImpl(Register1Entity mEntity, Register1View mView, Context context, Register1Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
        setPhoneNum();
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }

    /**
     *设置电话号码
     */

    private void setPhoneNum() {
        RxView.findById(activity, R.id.phoneNum_register).bind(phoneNum, new Rx.Action<View, String>() {
            @Override
            public void call(View target, String s) {
                TextView view = (TextView) target;
                view.setText(s);
            }
        });

    }

    /**
     * 确定
     * @param view
     */
    @Override
    public void onSure(View view) {

        mView.toOtherActivity(activity, Register2Activity.class);
        mView.introduction();
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextStep(View view) {
        mView.showToast("onNextStep");
        mView.nextStep();
    }

    @Override
    public void onIntroduction(View view) {
        mView.introduction();
    }



}
