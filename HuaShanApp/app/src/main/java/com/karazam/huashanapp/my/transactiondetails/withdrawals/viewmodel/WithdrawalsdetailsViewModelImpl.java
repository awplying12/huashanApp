package com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.retrofit.TransactiondetailsDataSource;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawalsdetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.WithdrawalsdetailsView;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity.WithdrawalsdetailsActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WithdrawalsdetailsViewModelImpl extends WithdrawalsdetailsViewModel {


    private WithdrawalsdetailsView mView;
    private WithdrawalsdetailsEntity mEntity;
    private Context context;
    private WithdrawalsdetailsActivity activity;

    private TransactiondetailsDataSource transactiondetailsDataSource;

    public WithdrawalsdetailsViewModelImpl(WithdrawalsdetailsView mView, WithdrawalsdetailsEntity mEntity, Context context, WithdrawalsdetailsActivity activity) {
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
     * 获取提现详情
     */
    @Override
    public void getWithdrawalsdetails() {

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
                        activity.dissmissProgressDialog();
                        Log.i("getWithdrawalsdetails","  e  :  "+e.toString());
                        mView.getWithdrawalsdetailsError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<TransactiondetailsBean> transactiondetailsBeanBaseReturn) {

                        if (transactiondetailsBeanBaseReturn.isSuccess()){
                            TransactiondetailsBean bean = transactiondetailsBeanBaseReturn.getData();

                            mView.getWithdrawalsdetailsSuccess(bean.getWithdrawal_order());
                        } else {
                            mView.getWithdrawalsdetailsFail(transactiondetailsBeanBaseReturn.getMessage());
                        }
                    }
                });

    }
}
