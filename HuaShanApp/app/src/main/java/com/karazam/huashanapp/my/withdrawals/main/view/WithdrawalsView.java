package com.karazam.huashanapp.my.withdrawals.main.view;

import com.example.utils.base.BaseView;

/**
 * Created by Administrator on 2016/11/30.
 */

public interface WithdrawalsView extends BaseView{

    void withdrawalsSuccess(String OrderNo);

    void withdrawalsFail(String s);

    void withdrawalsError(Throwable e);
}
