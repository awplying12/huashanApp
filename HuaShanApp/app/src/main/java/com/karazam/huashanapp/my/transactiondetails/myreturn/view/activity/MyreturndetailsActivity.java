package com.karazam.huashanapp.my.transactiondetails.myreturn.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyreturndetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.MyreturndetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.RepaymentdetalisBean;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.MyreturndetailsView;
import com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel.MyreturndetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel.MyreturndetailsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MyreturndetailsActivity extends BaseActivity implements MyreturndetailsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMyreturndetailsBinding binding;
    private MyreturndetailsEntity entity = new MyreturndetailsEntity();
    private MyreturndetailsViewModel mModel;

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myreturndetails);
        mModel = new MyreturndetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
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
        mModel.getMyreturndetails();
    }

    @Override
    public void onRefresh() {
        mModel.getMyreturndetails();
    }

    /**
     * 设置界面
     */
    private RxProperty<RepaymentdetalisBean> repaymentdetalisRx = RxProperty.create();
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(repaymentdetalisRx, new Rx.Action<View, RepaymentdetalisBean>() {
            @Override
            public void call(View target, RepaymentdetalisBean repaymentdetalisBean) {
                TextView tv_title = (TextView) target.findViewById(R.id.tv_title);
                TextView tv_amount = (TextView) target.findViewById(R.id.tv_amount);
                TextView tv_state = (TextView) target.findViewById(R.id.tv_state);
                TextView tv_period = (TextView) target.findViewById(R.id.tv_period);
                TextView tv_memo = (TextView) target.findViewById(R.id.tv_memo);
                TextView tv_createDate = (TextView) target.findViewById(R.id.tv_createDate);

                String title = repaymentdetalisBean.getTitle();
                tv_title.setText(StringUtil.interrupt(title,24,"未知"));

                String amount = repaymentdetalisBean.getAmount();
                tv_amount.setText("+"+StringUtil.getMoneyType(StringUtil.reservedDecimal(StringUtil.interrupt(amount,0,"0"),2),false));

                String stateDes = repaymentdetalisBean.getStatusDes();
                tv_state.setText(StringUtil.interrupt(stateDes,0,"未知"));

                String period = repaymentdetalisBean.getPeriod();
                tv_period.setText(StringUtil.interrupt(period,0,"未知"));

                String memo = repaymentdetalisBean.getMemo();
                tv_memo.setText(StringUtil.interrupt(memo,0,"未知"));


                String createDate = repaymentdetalisBean.getCreateDate();
                Long date =Long.parseLong(StringUtil.interrupt(createDate,0,"0"));
                if(date == null || date == 0){
                    tv_createDate.setText("");
                } else {
                    String time = DataUtil.getDate(new Date(date),"yyyy-MM-dd HH:mm");
                    tv_createDate.setText(StringUtil.interrupt(time,0,""));
                }
            }
        });
    }

    /**
     * 获取回款详情成功
     */
    @Override
    public void getMyreturndetailsSuccess(RepaymentdetalisBean bean) {
        swipe_l.setRefreshing(false);
        repaymentdetalisRx.set(bean);
    }

    /**
     * 获取回款详情失败
     */
    @Override
    public void getMyreturndetailsFail(String s) {
        swipe_l.setRefreshing(false);
        showToast(s);
    }

    /**
     * 获取回款详情失败
     */
    @Override
    public void getMyreturndetailsError(Throwable e) {
        swipe_l.setRefreshing(false);
        showToast("网络故障！");
    }


}
