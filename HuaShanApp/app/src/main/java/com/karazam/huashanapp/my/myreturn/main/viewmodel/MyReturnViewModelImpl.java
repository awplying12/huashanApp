package com.karazam.huashanapp.my.myreturn.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnBean;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnEntity;
import com.karazam.huashanapp.my.myreturn.main.model.retrofit.MyReturnDataSource;
import com.karazam.huashanapp.my.myreturn.main.view.MyReturnView;
import com.karazam.huashanapp.my.myreturn.main.view.activity.MyReturnActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MyReturnViewModelImpl extends MyReturnViewModel {

    private MyReturnEntity mEntity;
    private MyReturnView mView;
    private Context context;
    private MyReturnActivity activity;

    private MyReturnDataSource myReturnDataSource;

    public MyReturnViewModelImpl(MyReturnEntity mEntity, MyReturnView mView, Context context, MyReturnActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        myReturnDataSource = new MyReturnDataSource();
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
     * 获取我的回款数据
     * @param page
     */
    @Override
    public void getMyReturnData(int page) {

        activity.showProgressDialog();

        myReturnDataSource.getMyReturnData(page+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<MyReturnBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        activity.dissmissProgressDialog();
                        Log.i("getMyReturnData"," e  :  "+e.toString());
                        mView.myReturnError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<MyReturnBean> myReturnBeanBaseReturn) {

                        activity.dissmissProgressDialog();

                        if(myReturnBeanBaseReturn.isSuccess()){
                            MyReturnBean bean = myReturnBeanBaseReturn.getData();
                            Log.i("getMyReturnData",bean.toString());
                            mView.myReturnSuccess(bean);
                        } else {
                            mView.myReturnFail(myReturnBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
