package com.karazam.huashanapp.my.transactiondetails.investment.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.refreshToken.RefreshToken;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentEntity;
import com.karazam.huashanapp.my.transactiondetails.investment.view.InvestmentView;
import com.karazam.huashanapp.my.transactiondetails.investment.view.activity.InvestmentActivity;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.retrofit.TransactiondetailsDataSource;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/9.
 */

public class InvestmentViewModelImpl extends InvestmentViewModel {

    private InvestmentView mView;
    private InvestmentEntity mEntity;
    private Context context;
    private InvestmentActivity activity;

    private TransactiondetailsDataSource transactiondetailsDataSource;

    public InvestmentViewModelImpl(InvestmentView mView, InvestmentEntity mEntity, Context context, InvestmentActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        transactiondetailsDataSource = new TransactiondetailsDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 获取投资详情
     */
    @Override
    public void getInvestmentdetails(final boolean isfirst) {

        activity.showProgressDialog();

        transactiondetailsDataSource.getTransactiondetails(orderNo,type)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<TransactiondetailsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getInvestmentdetails"," e  :  "+e.toString());
                        mView.getInvestmentdetailsError(e);
                        activity.dissmissProgressDialog();

                        if(e instanceof ConnectException){  // token 过期处理

                            if(!isfirst){
                                HuaShanApplication.safeExit();
                                return;
                            }
                            RefreshToken.refresh(new RefreshToken.RefreshTokenInterface() {
                                @Override
                                public void onSuccess(String s) {
                                    getInvestmentdetails(false);
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
                        activity.dissmissProgressDialog();
                        if(transactiondetailsBeanBaseReturn.isSuccess()){
                            TransactiondetailsBean bean = transactiondetailsBeanBaseReturn.getData();

                            mView.getInvestmentdetailsSuccess(bean.getInvestment_order());
                        } else {
                            mView.getInvestmentdetailsFail(transactiondetailsBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
