package com.karazam.huashanapp.my.bankcard.bindcard.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardEntity;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.UpDatecardPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit.AddcardDataSource;
import com.karazam.huashanapp.my.bankcard.bindcard.view.BindcardView;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BankActivity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/30.
 */

public class BindcardViewModelImpl extends BindcardViewModel {

    private BindcardView mView;
    private BindcardEntity mEntity;
    private Context context;
    private BindcardActivity activity;

    private AddcardDataSource addcardDataSource;


    public BindcardViewModelImpl(BindcardView mView, BindcardEntity mEntity, Context context, BindcardActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        addcardDataSource = new AddcardDataSource();
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
//        mView.showToast("onNextstep");

        switch (flag){
            case 1:
                sendSMS();
                mView.addSMSView();

                break;
            case 2:
                onAddcard(false);
                break;
            case 3:
//                upDatecard();
                pwd_view.show();
                break;
            default:
                break;
        }
    }

    /**
     * 清除姓名
     * @param view
     */
    @Override
    public void cleanName(View view) {
//        use_name.setText("");
    }

    /**
     * 清除身份证号
     * @param view
     */
    @Override
    public void cleanIdnum(View view) {
//        id_num.setText("");
    }

    /**
     * 清除银行卡号
     * @param view
     */
    @Override
    public void cleanCardnum(View view) {
        card_num.setText("");
    }

    /**
     * 清除银行
     * @param view
     */
    @Override
    public void cleanBank(View view) {
//        bank.setText("");
    }

    /**
     * 清除电话号码
     * @param view
     */
    @Override
    public void cleanPhonenum(View view) {
        phone_num.setText("");
    }

    /**
     * 设置银行卡类型
     * @param view
     */
    @Override
    public void getBankData(View view) {
//        mView.showToast("getBankData");
        mView.toOtherActivity(activity, BankActivity.class);
    }

    /**
     * 添加银行卡
     */
    @Override
    public void onAddcard(boolean isQuick) {

        String card = StringUtil.interrupt(card_num.getText().toString(),0,"");
        String bank = StringUtil.interrupt(bankId,0,"-1");

        Log.i("BindcardActivity",smsview.getCode());

        BindcardPost post = new BindcardPost();
        post.setQuick(isQuick);
        post.setCard(card);
//        post.setCard("6217553803643445210");
        post.setBank(bank);


        if(flag == 1){
            String phone = StringUtil.interrupt(phone_num.getText().toString(),0,"");
            post.setMobile(phone);
            String code = StringUtil.interrupt(smsview.getCode(),0,"");
            post.setCaptch(code);
        }

        addcardDataSource.onAddcard(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<BindcardBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("BindcardActivity",e.getMessage());
                        mView.getResultError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<BindcardBean> bindcardBeanBaseReturn) {

                        if(bindcardBeanBaseReturn.isSuccess()){
                            BindcardBean bean = bindcardBeanBaseReturn.getData();
                            mView.getResultSuccess(bean);
                        } else {
                            mView.getResultFail(bindcardBeanBaseReturn.getMessage());
                        }
                    }
                });

    }

    /**
     * 修改银行卡
     */
    @Override
    public void upDatecard(String payPassword) {

        String card = StringUtil.interrupt(card_num.getText().toString(),0,"");
        String bank = StringUtil.interrupt(bankId,0,"-1");
        payPassword = DigestUtils.encrypt(payPassword);


        UpDatecardPost post = new UpDatecardPost();
        post.setCard(card);
//        post.setCard("6217003870043444210");
        post.setBank(bank);
        post.setPayPassword(payPassword);

        addcardDataSource.upDatecard(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<BindcardBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("BindcardActivity",e.getMessage());
                        mView.getResultError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<BindcardBean> bindcardBeanBaseReturn) {

                        if(bindcardBeanBaseReturn.isSuccess()){
                            BindcardBean bean = bindcardBeanBaseReturn.getData();
                            mView.getResultSuccess(bean);
                        } else {
                            mView.getResultFail(bindcardBeanBaseReturn.getMessage());
                        }
                    }
                });

    }

    /**
     * 发送短信验证
     */
    @Override
    public void sendSMS() {

        String card = StringUtil.interrupt(card_num.getText().toString(),0,"");
        String bank = StringUtil.interrupt(bankId,0,"-1");
        String phone = StringUtil.interrupt(phone_num.getText().toString(),0,"");


        BindcardPost post = new BindcardPost();
        post.setQuick(true);
        post.setCard(card);
//        post.setCard("6217553803643445210");
        post.setBank(bank);
        post.setMobile(phone);

        addcardDataSource.sendSMS(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                    }

                });

    }

}
