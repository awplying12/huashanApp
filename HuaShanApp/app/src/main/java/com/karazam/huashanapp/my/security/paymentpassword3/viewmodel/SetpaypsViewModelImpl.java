package com.karazam.huashanapp.my.security.paymentpassword3.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.my.realname.view.activity.AuthenticatedActivity;
import com.karazam.huashanapp.my.security.paymentpassword3.model.datainding.SetpaypsEntity;
import com.karazam.huashanapp.my.security.paymentpassword3.view.SetpaypsView;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class SetpaypsViewModelImpl extends SetpaypsViewModel {

    private SetpaypsView mView;
    private SetpaypsEntity mEntity;
    private Context context;
    private SetpaypsActivity activity;

    public SetpaypsViewModelImpl(SetpaypsView mView, SetpaypsEntity mEntity, Context context, SetpaypsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 确定
     * @param view
     */
    @Override
    public void onConfirm(View view) {

        if(TextUtils.isEmpty(one) || TextUtils.isEmpty(two)){
            mView.showToast("内容不能为空");
            return;
        }

        if(!one.equals(two)){
            mView.showToast("两次输入的密码不一致！");
            mView.Reset();
            return;
        }


        setUp();
    }

    /**
     * 设置密码
     */
    @Override
    public void setUp() {
        mView.showToast("设置完成");

        if(tag.equals("realName")){
            mView.toOtherActivity(activity,AuthenticatedActivity.class);
        }

        Observable.from(securitysPayment)
                .map(new Func1<BaseActivity, BaseActivity>() {
                    @Override
                    public BaseActivity call(BaseActivity baseActivity) {
                        return baseActivity;
                    }
                })
                .subscribe(new Subscriber<BaseActivity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseActivity baseActivity) {
                        baseActivity.finish();
                    }
                });

    }
}
