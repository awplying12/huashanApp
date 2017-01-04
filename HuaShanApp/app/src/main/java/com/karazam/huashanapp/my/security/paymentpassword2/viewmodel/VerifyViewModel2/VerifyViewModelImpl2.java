package com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel2;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.model.retrofit.VerifyidentityDataSource;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView2;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity2;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyViewModelImpl2 extends VerifyViewModel2 {

    private VerifyView2 mView;
    private VerifyEntity mEntity;
    private Context context;
    private VerifyActivity2 activity2;

    private VerifyidentityDataSource dataSource;

    public VerifyViewModelImpl2(VerifyView2 mView, VerifyEntity mEntity, Context context, VerifyActivity2 activity2) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity2 = activity2;

        dataSource = new VerifyidentityDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity2);
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextstep(View view) {

        if (hasCard){
            VerifyidentitybyID("1");
        } else {
            VerifyidentitybyID("0");
        }

    }

    /**
     * 清除卡号
     * @param view
     */
    @Override
    public void cleanCard(View view) {
        ed_card.setText("");
    }

    /**
     * 清除身份证
     * @param view
     */
    @Override
    public void cleanID(View view) {
        ed_id.setText("");
    }

    /**
     * 身份证验证
     */
    @Override
    public void VerifyidentitybyID(String type) {

        String idNo = ed_id.getText().toString();
        String cardNo = ed_card.getText().toString();

        dataSource.Verifyidentity(idNo,cardNo,type)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast("网络故障！");
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {
                        if(baseReturn.isSuccess()){
//                            mView.toOtherActivity(activity2, SetpaypsActivity.class);
                            Intent intent = new Intent(activity2, SetpaypsActivity.class);
                            intent.putExtra("isRealName","no");
                            intent.putExtra("type","update");
                            activity2.startActivity(intent);

                        }else {

                        }
                        mView.showToast(baseReturn.getMessage());
                    }
                });
    }



}
