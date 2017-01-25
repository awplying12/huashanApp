package com.karazam.huashanapp.my.transactiondetails.recharge.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargedetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetalisBean;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.RechargedetailsView;
import com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel.RechargedetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel.RechargedetailsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RechargedetailsActivity extends BaseActivity implements RechargedetailsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityRechargedetailsBinding binding;
    private RechargedetailsViewModel mModel;
    private RechargedetailsEntity entity = new RechargedetailsEntity();

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rechargedetails);
        mModel = new RechargedetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

        mModel.orderNo = getIntent().getStringExtra("orderNo");
        mModel.orderId =getIntent().getStringExtra("orderId");
        mModel.type =getIntent().getStringExtra("type");

    }

    @Override
    public void initView() {
        swipe_l = (SwipeRefreshLayout) getView(R.id.swipe_l);
        swipe_l.setOnRefreshListener(this);
        swipe_l.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();

        mModel.getRechargedetails();
    }

    /**
     * 设置界面
     */
    private RxProperty<RechargedetalisBean> rechargedetalisRx = RxProperty.create();
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(rechargedetalisRx, new Rx.Action<View, RechargedetalisBean>() {
            @Override
            public void call(View target, RechargedetalisBean rechargedetalisBean) {

                TextView tv_amount = (TextView) target.findViewById(R.id.withdrawals_money);
                TextView tv_statusDes = (TextView) target.findViewById(R.id.withdrawals_state);
                TextView tv_payMethod = (TextView) target.findViewById(R.id.textView5);
                TextView tv_memo = (TextView) target.findViewById(R.id.tv_memo);
                TextView tv_createDate = (TextView) target.findViewById(R.id.tv_data);

                String amount = rechargedetalisBean.getAmount();
                tv_amount.setText("+"+StringUtil.getMoneyType(StringUtil.reservedDecimal(StringUtil.interrupt(amount,0,"0"),2),false));

                String statusDes = rechargedetalisBean.getStatusDes();
                tv_statusDes.setText(StringUtil.interrupt(statusDes,0,"未知"));

                String payMethod = rechargedetalisBean.getPayMethod();
                Log.i("payMethod",rechargedetalisBean.getPayMethod());
                tv_payMethod.setText(StringUtil.interrupt(payMethod,20,"未知"));

                String memo = rechargedetalisBean.getMemo();
                tv_memo.setText(StringUtil.interrupt(memo,20,"未知"));

                String createDate = rechargedetalisBean.getCreateDate();
                Long date = Long.parseLong(StringUtil.interrupt(createDate,0,"0"));
                if(date == null || date == 0){
                    tv_createDate.setText("");
                } else {
                    String time = DataUtil.getDate(new Date(createDate),"yyyy-MM-dd HH:mm");
                    tv_createDate.setText(StringUtil.interrupt(time,0,""));
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        mModel.getRechargedetails();
    }

    /**
     * 获取充值详情
     * @param bean
     */
    @Override
    public void getWithdrawalsdetailsSuccess(RechargedetalisBean bean) {
        swipe_l.setRefreshing(false);
        rechargedetalisRx.set(bean);
    }

    /**
     * 获取充值失败
     * @param s
     */
    @Override
    public void getWithdrawalsdetailsFail(String s) {
        swipe_l.setRefreshing(false);
        showToast(s);
    }

    /**
     * 获取充值错误
     * @param e
     */
    @Override
    public void getWithdrawalsdetailsError(Throwable e) {
        swipe_l.setRefreshing(false);
        showToast("网络故障！");
    }
}
