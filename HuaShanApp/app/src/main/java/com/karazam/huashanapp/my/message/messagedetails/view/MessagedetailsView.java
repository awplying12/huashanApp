package com.karazam.huashanapp.my.message.messagedetails.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsBean;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface MessagedetailsView extends BaseView{

    void getMessagedetailsSuccess(MessagedetailsBean bean);

    void getMessagedetailsFail(String s);

    void getMessagedetailsError(Throwable e);
}
