package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.paymentmod.view.activity.PaymentmodActivity;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasSMSPost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseEntity;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasePost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseRetureBean;
import com.karazam.huashanapp.manage.purchase.model.retrofit.PurchasSMSDataSource;
import com.karazam.huashanapp.manage.purchase.model.retrofit.PurchaseDataSource;
import com.karazam.huashanapp.manage.purchase.view.PurchaseView;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PurchaseViewModelImpl extends PurchaseViewModel {

    private PurchaseEntity mEntity;
    private PurchaseView mView;
    private Context context;
    private PurchaseActivity activity;

    private PurchaseDataSource purchaseDataSource;
    private PurchasSMSDataSource smsDataSource;

    public PurchaseViewModelImpl(PurchaseEntity mEntity, PurchaseView mView, Context context, PurchaseActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        purchaseDataSource = new PurchaseDataSource();
        smsDataSource = new PurchasSMSDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 更多支付方式
     * @param view
     */
    @Override
    public void onPaymentMethod(View view) {
//        mView.showToast("更多支付方式");
        mView.toOtherActivity(activity, PaymentmodActivity.class);
    }

    /**
     * 购买
     * @param view
     */

    @Override
    public void onPurchase(View view) {
        mView.showToast("购买");
//        RxView.of(new View(context)).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
//            @Override
//            public void call(View target, UserInformation userInformation) {
//                String mode = userInformation.getPaymentmod();
//
//                if(mode.equals("")){  //余额支付
//                    mView.showPasswordView();
//                }else if(mode.equals("bankCard")){
//                    mView.addSMSView();
//                }
//            }
//        });



                if(mode.equals("BALANCE_PAY")){  //余额支付
                    checkPaymentpassword();

                    mView.showPasswordView();
                }else if(mode.equals("QUICK_PAY")){  //银行卡
                    sendSMS();
                    mView.addSMSView();
                }

    }

    /**
     * 检查支付密码
     */
    private void checkPaymentpassword() {

    }

    /**
     * 投资协议
     * @param view
     */
    @Override
    public void onAgreement(View view) {
        mView.showToast("投资协议");
    }

    /**
     * 投资
     */
    @Override
    public void onPurchase(String payPassword,String captcha) {

        String mon = ed_amountofmoney.getText().toString();

        PurchasePost post = new PurchasePost();
        post.setProjectId(borrowingId);
        post.setPaymentMethod(mode);
        post.setAmount(mon);
        post.setPayPassword(payPassword);
        post.setCaptcha(captcha);
        purchaseDataSource.Purchase(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<PurchaseRetureBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onPurchase"," e  :  "+e.toString());
                        mView.purchaseError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<PurchaseRetureBean> purchaseRetureBeanBaseReturn) {
                        if (purchaseRetureBeanBaseReturn.isSuccess()){

                            PurchaseRetureBean bean = purchaseRetureBeanBaseReturn.getData();
                            Log.i("Assets",bean.getAssets().toString());
                            HuaShanApplication.setMyAssets(bean.getAssets());

                            mView.purchaseSuccess(bean.getOrderNo());
                        }else {
                            mView.purchaseFail(purchaseRetureBeanBaseReturn.getMessage());
                        }
                    }
                });


    }

    /**
     * 快捷支付验证码发送
     */
    @Override
    public void sendSMS() {

        String bankCardId = cardBean.getBankCardId();
        String mon = ed_amountofmoney.getText().toString();

        PurchasSMSPost post = new PurchasSMSPost();
        post.setAmount(mon);
        post.setBankCardId(bankCardId);
        post.setProjectId(borrowingId);
        smsDataSource.sendSMS(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("sendSMS"," e  :  "+e.toString());
                        mView.purchaseError(e);
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {
                        if(baseReturn.isSuccess()){

                        }else {
                            mView.showToast(baseReturn.getMessage());
                        }
                    }
                });

    }


}
