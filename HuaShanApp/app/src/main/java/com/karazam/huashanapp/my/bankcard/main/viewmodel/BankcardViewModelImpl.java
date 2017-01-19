package com.karazam.huashanapp.my.bankcard.main.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog.PromptDialog;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.DeletecardPost;
import com.karazam.huashanapp.my.bankcard.main.model.retrofit.DeletecardDataSource;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.view.activity.BankcardActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BankcardViewModelImpl extends BankcardViewModel{

    private BankcardView mView;
    private BankcardEntity mEntity;
    private Context context;
    private BankcardActivity activity;

    private PromptDialog certificationDialog;

    private DeletecardDataSource deletecardDataSource;

    public BankcardViewModelImpl(BankcardView mView, BankcardEntity mEntity, Context context, BankcardActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        deletecardDataSource = new DeletecardDataSource();

        setCertificationDialog();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 绑定银行卡
     * @param view
     */
    @Override
    public void toBindcard(View view) {

        if(!HuaShanApplication.certificationStatus){

            if(certificationDialog != null){
                certificationDialog.show();
            }

            return;
        }

        Intent intent = new Intent(activity, BindcardActivity.class);
        intent.putExtra("flag",flag);
//        activity.startActivity(intent);
        activity.startActivityForResult(intent,101);
    }

    /**
     * 解绑银行卡
     * @param payPassword
     */
    @Override
    public void toUnbundling(String payPassword) {

        if(id.equals("-1")){
            mView.showToast("银行卡不存在");
            return;
        }

        DeletecardPost post = new DeletecardPost();
        post.setId(id);
        payPassword = DigestUtils.encrypt(payPassword);
        post.setPayPassword(payPassword);
        deletecardDataSource.deletaCard(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<BindcardBean>>() {
                    @Override
                    public void onCompleted() {
                        id = "-1";
                    }

                    @Override
                    public void onError(Throwable e) {
                        id = "-1";
                        Log.i("toUnbundling","  e : "+e.toString());
                        mView.unBundlingError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<BindcardBean> bindcardBeanBaseReturn) {

                        if(bindcardBeanBaseReturn.isSuccess()){
                            BindcardBean bean = bindcardBeanBaseReturn.getData();
                            HuaShanApplication.myInformation.setQuickCards(bean.getQuickCards());
                            HuaShanApplication.quickCardsRX.set(bean.getQuickCards());
                            mView.unBundlingSuccess(bean);
                        } else {
                            mView.unBundlingFail(bindcardBeanBaseReturn.getMessage());
                        }
                    }
                });

    }


    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(context);
        certificationDialog.setMod(PromptDialog.MOD1);
        certificationDialog.setPrompt("绑卡需要实名认证","您要前往实名认证吗？");

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

}
