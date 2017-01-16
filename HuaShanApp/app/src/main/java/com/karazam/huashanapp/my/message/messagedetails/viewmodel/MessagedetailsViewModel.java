package com.karazam.huashanapp.my.message.messagedetails.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/21.
 */

public abstract class MessagedetailsViewModel extends BaseViewModel {

    public String type;

    public static int allpage = 1;

    public abstract void getMessagedetails(int page);
}
