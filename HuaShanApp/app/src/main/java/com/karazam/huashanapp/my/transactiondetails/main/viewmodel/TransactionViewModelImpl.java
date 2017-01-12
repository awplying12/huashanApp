package com.karazam.huashanapp.my.transactiondetails.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionEntity;
import com.karazam.huashanapp.my.transactiondetails.main.model.retrofit.TransactionDataSource;
import com.karazam.huashanapp.my.transactiondetails.main.view.TransactionView;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.TransactionActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/8.
 */

public class TransactionViewModelImpl extends TransactionViewModel {

    private TransactionView mView;
    private TransactionEntity mEntity;
    private Context context;
    private TransactionActivity activity;

    private TransactionDataSource transactionDataSource;

    public TransactionViewModelImpl(TransactionView mView, TransactionEntity mEntity, Context context, TransactionActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        transactionDataSource = new TransactionDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 获取交易记录
     * @param page
     */
    @Override
    public void getTransaction(int page) {

        activity.showProgressDialog();

        transactionDataSource.getTransactionData(page+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<TransactionBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getTransaction"," e  :  "+e.toString());
                        mView.getTransactionError(e);
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseReturn<TransactionBean> transactionBeanBaseReturn) {
                        activity.dissmissProgressDialog();
                        if (transactionBeanBaseReturn.isSuccess()){
                            TransactionBean bean = transactionBeanBaseReturn.getData();
                            allpage = bean.getPages();
                            mView.getTransactionSuccess(bean);
                        }else {
                            mView.getTransactionFail(transactionBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
