package com.karazam.huashanapp.my.transactiondetails.myreturn.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.RepaymentdetalisBean;

/**
 * Created by Administrator on 2017/1/13.
 */

public interface MyreturndetailsView extends BaseView {

    void getMyreturndetailsSuccess(RepaymentdetalisBean bean);

    void getMyreturndetailsFail(String s);

    void getMyreturndetailsError(Throwable e);
}
