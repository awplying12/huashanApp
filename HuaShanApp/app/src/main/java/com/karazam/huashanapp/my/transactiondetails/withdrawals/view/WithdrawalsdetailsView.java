package com.karazam.huashanapp.my.transactiondetails.withdrawals.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentBean;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawaldetailsBean;

/**
 * Created by Administrator on 2016/12/1.
 */

public interface WithdrawalsdetailsView extends BaseView{

    void getWithdrawalsdetailsSuccess(WithdrawaldetailsBean bean);

    void getWithdrawalsdetailsFail(String s);

    void getWithdrawalsdetailsError(Throwable e);
}
