package com.karazam.huashanapp.my.message.messagedetails.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsBean;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsEntity;
import com.karazam.huashanapp.my.message.messagedetails.model.retrofit.MessagedetailsDataSource;
import com.karazam.huashanapp.my.message.messagedetails.view.MessagedetailsView;
import com.karazam.huashanapp.my.message.messagedetails.view.activity.MessagedetailsActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MessagedetailsViewModelImpl extends MessagedetailsViewModel {

    private MessagedetailsView mView;
    private MessagedetailsEntity mEntity;
    private Context context;
    private MessagedetailsActivity activity;

    private MessagedetailsDataSource messagedetailsDataSource;

    public MessagedetailsViewModelImpl(MessagedetailsView mView, MessagedetailsEntity mEntity, Context context, MessagedetailsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        messagedetailsDataSource = new MessagedetailsDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 获取消息详情
     */
    @Override
    public void getMessagedetails(int page) {

        activity.showProgressDialog();

        messagedetailsDataSource.getMessagedetails(type,page+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<MessagedetailsBean>>() {
                    @Override
                    public void onCompleted() {
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getMessagedetails","  e  :  "+e.toString());
                        mView.getMessagedetailsError(e);
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseReturn<MessagedetailsBean> messagedetailsBeanBaseReturn) {

                        if(messagedetailsBeanBaseReturn.isSuccess()){

                            MessagedetailsBean bean = messagedetailsBeanBaseReturn.getData();
                            allpage = bean.getPages();

                            mView.getMessagedetailsSuccess(bean);
                        }else {
                            mView.getMessagedetailsFail(messagedetailsBeanBaseReturn.getMessage());
                        }
                    }
                });

    }
}
