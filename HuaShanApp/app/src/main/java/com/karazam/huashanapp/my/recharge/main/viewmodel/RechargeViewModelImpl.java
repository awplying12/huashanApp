package com.karazam.huashanapp.my.recharge.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.recharge.changecard.ChangecardActivity;
import com.karazam.huashanapp.my.recharge.main.model.databinding.OrderBean;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeBean;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.model.retrofit.QuickpaySMSDataSource;
import com.karazam.huashanapp.my.recharge.main.model.retrofit.RechargeDataSource;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RechargeViewModelImpl extends RechargeViewModel {

    private RechargeView mView;
    private RechargeEntity mEntity;
    private Context context;
    private RechargeActivity activity;

    private SMSauthenticationView smsview;

    private QuickpaySMSDataSource quickpaySMSDataSource;
    private RechargeDataSource rechargeDataSource;

    public RechargeViewModelImpl(RechargeView mView, RechargeEntity mEntity, Context context, RechargeActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        quickpaySMSDataSource = new QuickpaySMSDataSource();
        rechargeDataSource = new RechargeDataSource();

        setSMSview();
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
    public void nextStep(View view) {
//        mView.showToast("nextStep");


        String mon = ed_money.getText().toString();
        smsview.setText1("支付"+mon+"元");
        String phone = StringUtil.interrupt(card.getMobile(),0,"");
        if(!phone.equals("")){
            phone = phone.substring(7,11);
        }else {
            mView.showToast("请核实您的预留手机号");
            return;
        }
        smsview.setText2("请输入手机尾号"+phone+"接受的验证码");

        sendSMS();
        smsview.show();
    }

    /**
     * 更换银行卡
     * @param view
     */
    @Override
    public void changeCard(View view) {
//        mView.showToast("changeCard");
        mView.toOtherActivity(activity, ChangecardActivity.class);
    }

    /**
     * 充值
     */
    @Override
    public void Recharge() {
//        mView.rechargeSuccess();
//

        String money = ed_money.getText().toString();
        String bankCardId = card.getBankCardId();
        rechargeDataSource.Recharge(money,bankCardId,orderNo,smsview.getCode())
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<RechargeBean>>() {
                    @Override
                    public void onCompleted() {
                        smsview.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Recharge","e  :  "+e.toString());
                        mView.rechargeeError(e);
                        smsview.dismiss();
                    }

                    @Override
                    public void onNext(BaseReturn<RechargeBean> rechargeBeanBaseReturn) {
                        if(rechargeBeanBaseReturn.isSuccess()){
                            RechargeBean bean = rechargeBeanBaseReturn.getData();
                            mView.rechargeSuccess(bean.getOrderNo());

                            HuaShanApplication.setMyAssets(bean.getAssets());
                        }else {
                            mView.rechargeFail(rechargeBeanBaseReturn.getMessage());
                        }
                    }

                });
//                .subscribe(new Subscriber<BaseReturn<String>>() {
//                    @Override
//                    public void onCompleted() {
//                        smsview.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("Recharge","e  :  "+e.toString());
//                        mView.rechargeeError(e);
//                        smsview.dismiss();
//                    }
//
//                    @Override
//                    public void onNext(BaseReturn<String> stringBaseReturn) {
//                        if(stringBaseReturn.isSuccess()){
//                            String detailsId = stringBaseReturn.getData();
//                            mView.rechargeSuccess(detailsId);
//                        }else {
//                            mView.rechargeFail(stringBaseReturn.getMessage());
//                        }
//                    }
//                });

    }



    /**
     * 设置短息验证
     */
    private void setSMSview() {
        smsview = new SMSauthenticationView(context);

        smsview.setView(HuaShanApplication.account,"",(ViewGroup) mView.getView(R.id.content_pl), new SMSauthenticationView.OnAuthenticationListener() {
            @Override
            public void onLeft(View view) {
                smsview.dismiss();
            }

            @Override
            public void onRight(View view) {
//                smsview.verification();
                Recharge();
            }

            @Override
            public void onHelp(View view) {

            }

            @Override
            public void onResend(View view) {
                sendSMS();
            }

            @Override
            public void onResult(boolean result) {
                if(result){
                    Recharge();

                }
            }
        });
    }


    @Override
    public void sendSMS() {

        String money = ed_money.getText().toString();
        String bankCardId = card.getBankCardId();
        quickpaySMSDataSource.sendQuickSMS(money,bankCardId)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<OrderBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("sendSMS","  e   :  "+e.toString());
                        mView.showToast("网络故障！");
                    }

                    @Override
                    public void onNext(BaseReturn<OrderBean> orderBeanBaseReturn) {
                        if(orderBeanBaseReturn.isSuccess()){
                            orderNo = orderBeanBaseReturn.getData().getOrderNo();
                        } else {
                            mView.showToast(orderBeanBaseReturn.getMessage());
                        }
                    }
                });

    }
}
