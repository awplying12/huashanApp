package com.karazam.huashanapp.my.transactiondetails.recharge.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetalisBean;

/**
 * Created by Administrator on 2016/12/2.
 */

public interface RechargedetailsView extends BaseView{

    void getWithdrawalsdetailsSuccess(RechargedetalisBean bean);

    void getWithdrawalsdetailsFail(String s);

    void getWithdrawalsdetailsError(Throwable e);
}
