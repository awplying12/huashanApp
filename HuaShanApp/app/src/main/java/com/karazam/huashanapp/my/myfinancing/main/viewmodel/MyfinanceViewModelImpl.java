package com.karazam.huashanapp.my.myfinancing.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.Bean.financialproject.FinancialProject;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.model.retrofit.MyfinanceDataSource;
import com.karazam.huashanapp.my.myfinancing.main.view.MyfinanceView;
import com.karazam.huashanapp.my.myfinancing.main.view.activity.MyfinanceActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyfinanceViewModelImpl extends MyfinanceViewModel {

    private MyfinanceEntity mEntity;
    private MyfinanceView mView;
    private Context context;
    private MyfinanceActivity activity;

    private MyfinanceDataSource myfinanceDataSource;

    public MyfinanceViewModelImpl(MyfinanceEntity mEntity, MyfinanceView mView, Context context, MyfinanceActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        myfinanceDataSource = new MyfinanceDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    @Override
    public void Finanec(View view) {

        if(isEmpty){
            mView.showToast("立即前往购买");
        }else {
            mView.showToast("买入");
        }

        activity.setResult(67);
        mView.FinishActivity(activity);
    }

    /**
     * 理财数据接口
     */
    @Override
    public void getMyfinanceData(String progress,int currentPage) {      //investing("投资中")
                                                                            // repaying("还款中")
                                                                            //completed("已完成")
        activity.showProgressDialog();

        myfinanceDataSource.getMyfinance(progress,currentPage+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<MyfinanceBean>>() {
                    @Override
                    public void onCompleted() {
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getMyfinanceData"," e  :  "+e.toString());
                        mView.myfinanceeError(e);
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseReturn<MyfinanceBean> myfinanceBeanBaseReturn) {

                        if(myfinanceBeanBaseReturn.isSuccess()){
                            MyfinanceBean bean = myfinanceBeanBaseReturn.getData();

                            allpage = bean.getPages();
                            mView.myfinanceSuccess(bean);
                        } else {
                            mView.myfinanceFail(myfinanceBeanBaseReturn.getMessage());
                        }
                    }
                });

    }
}
