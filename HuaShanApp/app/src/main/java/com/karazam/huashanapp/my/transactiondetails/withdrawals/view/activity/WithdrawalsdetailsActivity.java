package com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityWithdrawalsdetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawaldetailsBean;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawalsdetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.WithdrawalsdetailsView;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view.StateAdapter;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view.StateitemBean;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel.WithdrawalsdetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel.WithdrawalsdetailsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WithdrawalsdetailsActivity extends BaseActivity implements WithdrawalsdetailsView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityWithdrawalsdetailsBinding binding;
    private WithdrawalsdetailsViewModel mModel;
    private WithdrawalsdetailsEntity entity = new WithdrawalsdetailsEntity();

    private RecyclerView rl_state;

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawalsdetails);
        mModel = new WithdrawalsdetailsViewModelImpl(this,entity,this,this);
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
        rl_state = (RecyclerView) getView(R.id.rl_state);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rl_state.setLayoutManager(lm);

        swipe_l = (SwipeRefreshLayout) getView(R.id.swipe_l);
        swipe_l.setOnRefreshListener(this);
        swipe_l.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
        setRecyclerView();



        mModel.getWithdrawalsdetails();
    }



    /**
     * 设置RecyclerView
     */
    private StateAdapter stateAdapter;
    private void setRecyclerView() {

        ArrayList<StateitemBean> list = new ArrayList<>();
//        list.add(new StateitemBean("提现申请","11-13 14:48"));
//        list.add(new StateitemBean("审核通过",""));
//        list.add(new StateitemBean("银行处理中",""));
//        list.add(new StateitemBean("完成",""));

        stateAdapter = new StateAdapter(list,this,rl_state);
        rl_state.setAdapter(stateAdapter);


    }

    /**
     * 设置界面
     */
    private RxProperty<WithdrawaldetailsBean>  withdrawaldetailsRx = RxProperty.create();
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(withdrawaldetailsRx, new Rx.Action<View, WithdrawaldetailsBean>() {
            @Override
            public void call(View target, WithdrawaldetailsBean withdrawaldetailsBean) {
                final ImageView bank_logo = (ImageView) target.findViewById(R.id.bank_logo);

                TextView bank_name = (TextView) target.findViewById(R.id.bank_name);
                TextView tv_amount = (TextView) target.findViewById(R.id.withdrawals_money);
                TextView tv_statusDes = (TextView) target.findViewById(R.id.withdrawals_state);
                TextView tv_fee = (TextView) target.findViewById(R.id.tv_fee);
                TextView tv_memo = (TextView) target.findViewById(R.id.tv_memo);
                TextView tv_card = (TextView) target.findViewById(R.id.tv_card);

                String bank = withdrawaldetailsBean.getBank();
                bank_name.setText(StringUtil.interrupt(bank,0,"未知"));

                String amount = withdrawaldetailsBean.getAmount();
                tv_amount.setText("-"+StringUtil.getMoneyType(StringUtil.reservedDecimal(StringUtil.interrupt(amount,0,"0"),2),false));

                String statusDes = withdrawaldetailsBean.getStatusDes();
                tv_statusDes.setText(StringUtil.interrupt(statusDes,0,"未知"));

                String fee = withdrawaldetailsBean.getFee();
                tv_fee.setText(StringUtil.reservedDecimal(StringUtil.interrupt(fee,0,"0"),2));

                String memo = withdrawaldetailsBean.getMemo();
                tv_memo.setText(StringUtil.interrupt(memo,20,"未知"));

                String card = withdrawaldetailsBean.getCard();
                tv_card.setText(StringUtil.interrupt(card,0,"未知"));

                stateAdapter.setList(withdrawaldetailsBean.getList());
                stateAdapter.notifyDataSetChanged();

                String logo = withdrawaldetailsBean.getLogo();
                RxImageLoader.getLoaderObservable(bank_logo,logo).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        bank_logo.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bankdef_logo));
                    }

                    @Override
                    public void onNext(Data data) {
                        if(data.bitmap == null){
                            bank_logo.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bankdef_logo));
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onRefresh() {
        mModel.getWithdrawalsdetails();
    }

    /**
     * 获取提现详情成功
     */
    @Override
    public void getWithdrawalsdetailsSuccess(WithdrawaldetailsBean bean) {
        swipe_l.setRefreshing(false);
        withdrawaldetailsRx.set(bean);

    }

    /**
     * 获取提现详情失败
     */
    @Override
    public void getWithdrawalsdetailsFail(String s) {
        swipe_l.setRefreshing(false);
        showToast(s);
    }

    /**
     * 获取提现详情错误
     */
    @Override
    public void getWithdrawalsdetailsError(Throwable e) {
        swipe_l.setRefreshing(false);
        showToast("网络故障！");
    }
}
