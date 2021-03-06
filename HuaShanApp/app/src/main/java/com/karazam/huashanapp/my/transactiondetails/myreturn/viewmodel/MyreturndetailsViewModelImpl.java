package com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.refreshToken.RefreshToken;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.retrofit.TransactiondetailsDataSource;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.MyreturndetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.MyreturndetailsView;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.activity.MyreturndetailsActivity;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/13.
 */

public class MyreturndetailsViewModelImpl extends MyreturndetailsViewModel {

    private MyreturndetailsView mView;
    private MyreturndetailsEntity mEntity;
    private MyreturndetailsActivity activity;
    private Context context;

    private TransactiondetailsDataSource transactiondetailsDataSource;

    public MyreturndetailsViewModelImpl(MyreturndetailsView mView, MyreturndetailsEntity mEntity, MyreturndetailsActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;

        transactiondetailsDataSource = new TransactiondetailsDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 获取回款详情
     */
    @Override
    public void getMyreturndetails(final boolean isfirst) {

        activity.showProgressDialog();

        transactiondetailsDataSource.getTransactiondetails(orderNo,type)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<TransactiondetailsBean>>() {
                    @Override
                    public void onCompleted() {
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getMyreturndetails"," e  :  "+e.toString());
                        mView.getMyreturndetailsError(e);
                        activity.dissmissProgressDialog();

                        if(e.toString().equals("retrofit2.adapter.rxjava.HttpException: HTTP 302 Internal Server Error")){  // token 过期处理

                            if(!isfirst){
                                HuaShanApplication.safeExit();
                                return;
                            }
                            RefreshToken.refresh(new RefreshToken.RefreshTokenInterface() {
                                @Override
                                public void onSuccess(String s) {
                                    getMyreturndetails(false);
                                }

                                @Override
                                public void onFaile(String s) {
                                    HuaShanApplication.safeExit();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    HuaShanApplication.safeExit();
                                }
                            });
                        }
                    }

                    @Override
                    public void onNext(BaseReturn<TransactiondetailsBean> transactiondetailsBeanBaseReturn) {

                        if(transactiondetailsBeanBaseReturn.isSuccess()){
                            TransactiondetailsBean bean = transactiondetailsBeanBaseReturn.getData();

                            mView.getMyreturndetailsSuccess(bean.getRepayment_order());
                        } else {
                            mView.getMyreturndetailsFail(transactiondetailsBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
