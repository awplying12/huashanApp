package com.karazam.huashanapp.my.message.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.main.model.databinding.MessageEntity;
import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistBean;
import com.karazam.huashanapp.my.message.main.model.retrofit.MessagelistDataSource;
import com.karazam.huashanapp.my.message.main.view.MessageView;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageViewModelImpl extends MessageViewModel{

    private MessageView mView;
    private MessageEntity mEntity;
    private MessageActivity activity;
    private Context context;

    private MessagelistDataSource messagelistDataSource;

    public MessageViewModelImpl(MessageView mView, MessageEntity mEntity, MessageActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;

        messagelistDataSource = new MessagelistDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 获取消息列表
     */
    @Override
    public void getMessagelist() {

        messagelistDataSource.getMessagelist()
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<MessagelistBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getMessagelist","  e  :  "+e.toString());
                        mView.getMessagelistError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<MessagelistBean> messagelistBeanBaseReturn) {

                        if(messagelistBeanBaseReturn.isSuccess()){
                            MessagelistBean bean = messagelistBeanBaseReturn.getData();

                            mView.getMessagelistSuccess(bean);
                        } else {
                            mView.getMessagelistFail(messagelistBeanBaseReturn.getMessage());
                        }

                    }
                });

    }
}
