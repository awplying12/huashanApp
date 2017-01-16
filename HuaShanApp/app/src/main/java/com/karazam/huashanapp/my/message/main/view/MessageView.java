package com.karazam.huashanapp.my.message.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistBean;

/**
 * Created by Administrator on 2016/12/20.
 */

public interface MessageView extends BaseView{

    void getMessagelistSuccess(MessagelistBean bean);

    void getMessagelistFail(String s);

    void getMessagelistError(Throwable e);
}
