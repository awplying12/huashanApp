package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;

import com.karazam.huashanapp.main.dialog.PromptDialog.PromptDialog;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;
import com.karazam.huashanapp.manage.paymentmod.view.activity.PaymentmodActivity;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasSMSPost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseEntity;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasePost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseRetureBean;
import com.karazam.huashanapp.manage.purchase.model.retrofit.PurchasSMSDataSource;
import com.karazam.huashanapp.manage.purchase.model.retrofit.PurchaseDataSource;
import com.karazam.huashanapp.manage.purchase.view.PurchaseView;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;

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

    private PromptDialog certificationDialog;;

    private PromptDialog rechargeDialog;


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
     * 充值
     * @param view
     */
    private int flag = -1;
    @Override
    public void Recharge(View view) {
        flag = 1;
        if(!HuaShanApplication.certificationStatus){
            setCertificationDialog();
            if(certificationDialog != null){
                certificationDialog.setPrompt("充值需要实名认证","您要前往实名认证吗？");
                certificationDialog.show();
            }

            return;
        }

        if(HuaShanApplication.myInformation.getQuickCards() == null || HuaShanApplication.myInformation.getQuickCards().size() == 0){
            setRechargeDialog();
            if(rechargeDialog != null){
                rechargeDialog.setPrompt("您还没有绑定快捷卡","是否要前往绑定");
                rechargeDialog.show();
            }
            return;
        }

        mView.toOtherActivity(activity, RechargeActivity.class);
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
                            ManagedetailsBean managedetailsBean = new ManagedetailsBean();
                            managedetailsBean.setProject(bean.getProject());
                            HuaShanApplication.project.set(managedetailsBean);
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


    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(activity);
        certificationDialog.setMod(PromptDialog.MOD1);


        certificationDialog.setClick("否", "是", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                if(certificationDialog != null){
                    certificationDialog.dismiss();
                }
            }

            @Override
            public void onRight(View view) {
                mView.toOtherActivity(activity, UnauthorizedActivity.class);
                if(certificationDialog != null){
                    certificationDialog.dismiss();
                }
            }
        });

    }

    private void setRechargeDialog(){
        rechargeDialog = new PromptDialog(activity);
        rechargeDialog.setMod(PromptDialog.MOD1);

        rechargeDialog.setClick("否", "是", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {

                if(rechargeDialog != null){
                    rechargeDialog.dismiss();
                }

            }

            @Override
            public void onRight(View view) {

                Intent intent = new Intent(activity, BindcardActivity.class);
                intent.putExtra("flag",flag);
                activity.startActivity(intent);

                if(rechargeDialog != null){
                    rechargeDialog.dismiss();
                }
            }
        });
    }



}
