package com.karazam.huashanapp.my.transactiondetails.main.model.databinding;

import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentBean;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.RepaymentdetalisBean;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetalisBean;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawaldetailsBean;

/**
 * Created by Administrator on 2017/1/12.
 */

public class TransactiondetailsBean {

    private InvestmentBean investment_order;

    private RechargedetalisBean recharge_order;

    private RepaymentdetalisBean repayment_order;

    private WithdrawaldetailsBean withdrawal_order;

    public InvestmentBean getInvestment_order() {
        return investment_order;
    }

    public void setInvestment_order(InvestmentBean investment_order) {
        this.investment_order = investment_order;
    }

    public RechargedetalisBean getRecharge_order() {
        return recharge_order;
    }

    public void setRecharge_order(RechargedetalisBean recharge_order) {
        this.recharge_order = recharge_order;
    }

    public RepaymentdetalisBean getRepayment_order() {
        return repayment_order;
    }

    public void setRepayment_order(RepaymentdetalisBean repayment_order) {
        this.repayment_order = repayment_order;
    }

    public WithdrawaldetailsBean getWithdrawal_order() {
        return withdrawal_order;
    }

    public void setWithdrawal_order(WithdrawaldetailsBean withdrawal_order) {
        this.withdrawal_order = withdrawal_order;
    }

    @Override
    public String toString() {
        return "TransactiondetailsBean{" +
                "investment_order=" + investment_order +
                ", recharge_order=" + recharge_order +
                ", repayment_order=" + repayment_order +
                ", withdrawal_order=" + withdrawal_order +
                '}';
    }
}
