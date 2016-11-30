package com.karazam.huashanapp.my.withdrawals.main.viewmodel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.BalanceinformationView;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsEntity;
import com.karazam.huashanapp.my.withdrawals.main.view.WithdrawalsView;
import com.karazam.huashanapp.my.withdrawals.main.view.activity.WithdrawalsActivity;

/**
 * Created by Administrator on 2016/11/30.
 */

public class WithdrawalsViewModelImpl extends WithdrawalsViewModel {

    private WithdrawalsView mView;
    private WithdrawalsEntity mEntity;
    private Context context;
    private WithdrawalsActivity activity;

    private BalanceinformationView balanceinformationView;

    public WithdrawalsViewModelImpl(WithdrawalsView mView, WithdrawalsEntity mEntity, Context context, WithdrawalsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        setBalanceinformationView();


    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 全部提现
     * @param view
     */
    @Override
    public void allwithdrawals(View view) {
        ed_moneny.setText(avail+"");
    }

    /**
     * 提现
     * @param view
     */
    @Override
    public void withdrawals(View view) {
        mView.showToast("提现");
        String sumStr  =ed_moneny.getText().toString();
        double sum = Double.parseDouble(sumStr);
        double service = (sum*0.1)/100;

        if((avail - sum) < service){
            balanceinformationView.setArrivalamount(StringUtil.reservedDecimal((sum - service),2)+"");
            balanceinformationView.setServicecharge(StringUtil.reservedDecimal(service,2)+"");

            balanceinformationView.show();
        }else {
            pwd_view.show();
        }

    }

    /**
     * 提现说明
     * @param view
     */
    @Override
    public void explain(View view) {
        mView.showToast("提现说明");
    }

    /**
     * 设置BalanceinformationView
     */
    private void setBalanceinformationView() {
        balanceinformationView = new BalanceinformationView(context);

        balanceinformationView.setView((ViewGroup) mView.getView(R.id.content_pl), new BalanceinformationView.OnBalanceinformationListener() {

            @Override
            public void onContinue() {
                pwd_view.show();
                balanceinformationView.dismiss();
            }

            @Override
            public void onHelp() {

            }
        });
    }
}
