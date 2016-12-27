package com.karazam.huashanapp.manage.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.main.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.main.model.databinding.ManagedataBean;
import com.karazam.huashanapp.manage.main.model.retrofit.ManagedataDataSource;
import com.karazam.huashanapp.manage.main.view.ManageView;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ManageViewModelImpl extends ManageViewModel {

    private ManageEntity mEntity;
    private ManageView mView;
    private Context context;
    private Activity activity;

    private ManagedataDataSource dataSource;

    public ManageViewModelImpl(ManageEntity mEntity, ManageView mView, Context context, Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        dataSource = new ManagedataDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    @Override
    public void getManageData(String type, int page) {

                dataSource.getManagedata(type,page+"")
                        .throttleFirst(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<BaseReturn<ManagedataBean>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("ManageData","e  :  "+e.toString());
                            }

                            @Override
                            public void onNext(BaseReturn<ManagedataBean> managedataBeanBaseReturn) {

                                    if(managedataBeanBaseReturn.isSuccess()){
                                        ManagedataBean bean = managedataBeanBaseReturn.getData();
                                        mView.getManageDataSuccess(bean.getRows());

                                    }else {
                                        mView.getManageDataFaile(managedataBeanBaseReturn.getMessage());
                                    }
                            }
                        });

    }
}
