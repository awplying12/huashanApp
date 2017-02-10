package com.karazam.huashanapp.my.withdrawals.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.BalanceinformationView;
import com.karazam.huashanapp.main.refreshToken.RefreshToken;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsBean;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsEntity;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsPost;
import com.karazam.huashanapp.my.withdrawals.main.model.retrofit.WithdrawalsDataSource;
import com.karazam.huashanapp.my.withdrawals.main.view.WithdrawalsView;
import com.karazam.huashanapp.my.withdrawals.main.view.activity.WithdrawalsActivity;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/30.
 */

public class WithdrawalsViewModelImpl extends WithdrawalsViewModel {

    private WithdrawalsView mView;
    private WithdrawalsEntity mEntity;
    private Context context;
    private WithdrawalsActivity activity;

    private BalanceinformationView balanceinformationView;
    private WithdrawalsDataSource dataSource;

    public WithdrawalsViewModelImpl(WithdrawalsView mView, WithdrawalsEntity mEntity, Context context, WithdrawalsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        dataSource = new WithdrawalsDataSource();
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
//        mView.showToast("提现");
        String sumStr  =ed_moneny.getText().toString();
        double sum = Double.parseDouble(sumStr);
        double service = (sum*0.1)/100;

        if((avail - sum) < service){
            balanceinformationView.setArrivalamount(StringUtil.reservedDecimal((sum - service),2)+"");
            balanceinformationView.setServicecharge(StringUtil.reservedDecimal(service,2)+"");

            balanceinformationView.show();
        }else {
            String money = ed_moneny.getText().toString();
            pwd_view.setMoney("投资金额:"+money);
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
     * 提现接口
     */
    @Override
    public void toWithdrawals(final boolean isfirst) {

        activity.showProgressDialog();

        String mon = ed_moneny.getText().toString();
        String bankCard = StringUtil.interrupt(card.getBankCardId(),0,"");
        String payPassword = DigestUtils.encrypt(pwd_view.getStrPassword());

        WithdrawalsPost post = new WithdrawalsPost();
        post.setAmount(mon);
        post.setBankCard(bankCard);
        post.setUserId(HuaShanApplication.uuid);
        post.setPayPassword(payPassword);
        dataSource.Withdrawals(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<WithdrawalsBean>>() {
                    @Override
                    public void onCompleted() {
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("toWithdrawals","  e  :  "+e.toString());
                        mView.withdrawalsError(e);
                        activity.dissmissProgressDialog();

                        if(e.toString().equals("retrofit2.adapter.rxjava.HttpException: HTTP 302 Internal Server Error")){  // token 过期处理

                            if(!isfirst){
                                HuaShanApplication.safeExit();
                                return;
                            }
                            RefreshToken.refresh(new RefreshToken.RefreshTokenInterface() {
                                @Override
                                public void onSuccess(String s) {
                                    toWithdrawals(false);
                                }

                                @Override
                                public void onFaile(String s) {
                                    HuaShanApplication.safeExit();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    HuaShanApplication.safeExit();
                                }
                            });
                        }
                    }

                    @Override
                    public void onNext(BaseReturn<WithdrawalsBean> withdrawalsBeanBaseReturn) {
                        if(withdrawalsBeanBaseReturn.isSuccess()){
                            WithdrawalsBean bean = withdrawalsBeanBaseReturn.getData();

                            mView.withdrawalsSuccess(bean.getOrderNo());

                            HuaShanApplication.setMyAssets(bean.getAssets());
                        }else {
                            mView.withdrawalsFail(withdrawalsBeanBaseReturn.getMessage());
                        }
                    }
                });

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
