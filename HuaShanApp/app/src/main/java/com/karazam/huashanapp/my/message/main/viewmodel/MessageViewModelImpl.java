package com.karazam.huashanapp.my.message.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.message.main.model.databinding.MessageEntity;
import com.karazam.huashanapp.my.message.main.view.MessageView;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageViewModelImpl extends MessageViewModel{

    private MessageView mView;
    private MessageEntity mEntity;
    private MessageActivity activity;
    private Context context;

    public MessageViewModelImpl(MessageView mView, MessageEntity mEntity, MessageActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
