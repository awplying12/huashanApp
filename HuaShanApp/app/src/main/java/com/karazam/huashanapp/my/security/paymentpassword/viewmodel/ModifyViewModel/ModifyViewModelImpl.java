package com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ModifyViewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.model.retrofit.PaymentDataSource;
import com.karazam.huashanapp.my.security.paymentpassword.view.ModifyView;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.ModifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ModifyViewModelImpl extends ModifyViewModel {

    private ModifyView mView;
    private PaymentpwEntity mEntity;
    private Context context;
    private ModifyActivity activity;

    private PaymentDataSource dataSource;

    public ModifyViewModelImpl(ModifyView mView, PaymentpwEntity mEntity, Context context, ModifyActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        dataSource = new PaymentDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextstep(View view) {
        checkPayment();
    }

    /**
     * 校检支付密码
     */
    @Override
    public void checkPayment() {

        String payPassword = payment_pw.getText().toString();
        dataSource.onPayment(payPassword,"check")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("checkPayment"," e  :  "+e.toString());
                        mView.showToast("网络故障！");
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                        if(baseReturn.isSuccess()){
                            mView.toOtherActivity(activity, VerifyActivity.class);
                        }else {
                            mView.showToast(baseReturn.getMessage());
                        }
                    }
                });

    }
}
