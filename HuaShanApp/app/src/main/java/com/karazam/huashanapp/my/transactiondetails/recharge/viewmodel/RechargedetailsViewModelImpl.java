package com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.retrofit.TransactiondetailsDataSource;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.RechargedetailsView;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.activity.RechargedetailsActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RechargedetailsViewModelImpl extends RechargedetailsViewModel {

    private RechargedetailsView mView;
    private RechargedetailsEntity mEntity;
    private Context context;
    private RechargedetailsActivity activity;

    private TransactiondetailsDataSource transactiondetailsDataSource;

    public RechargedetailsViewModelImpl(RechargedetailsView mView, RechargedetailsEntity mEntity, Context context, RechargedetailsActivity activity) {
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
     * 获取充值详情
     */
    @Override
    public void getRechargedetails() {

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
                        Log.i("getRechargedetails","  e  :  "+e.toString());
                        activity.dissmissProgressDialog();
                        mView.getWithdrawalsdetailsError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<TransactiondetailsBean> transactiondetailsBeanBaseReturn) {

                        if(transactiondetailsBeanBaseReturn.isSuccess()){
                            TransactiondetailsBean bean = transactiondetailsBeanBaseReturn.getData();

                            mView.getWithdrawalsdetailsSuccess(bean.getRecharge_order());
                        } else {
                            mView.getWithdrawalsdetailsFail(transactiondetailsBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
