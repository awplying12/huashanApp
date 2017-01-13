package com.karazam.huashanapp.my.transactiondetails.investment.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentBean;

/**
 * Created by Administrator on 2016/12/9.
 */

public interface InvestmentView extends BaseView{

    void getInvestmentdetailsSuccess(InvestmentBean bean);

    void getInvestmentdetailsFail(String s);

    void getInvestmentdetailsError(Throwable e);
}
