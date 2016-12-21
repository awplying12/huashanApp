package com.karazam.huashanapp.my.message.messagedetails.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsEntity;
import com.karazam.huashanapp.my.message.messagedetails.view.MessagedetailsView;
import com.karazam.huashanapp.my.message.messagedetails.view.activity.MessagedetailsActivity;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MessagedetailsViewModelImpl extends MessagedetailsViewModel {

    private MessagedetailsView mView;
    private MessagedetailsEntity mEntity;
    private Context context;
    private MessagedetailsActivity activity;

    public MessagedetailsViewModelImpl(MessagedetailsView mView, MessagedetailsEntity mEntity, Context context, MessagedetailsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
