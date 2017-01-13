package com.karazam.huashanapp.my.transactiondetails.main.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.stickylistview_library.ExpandableStickyListHeadersListView;
import com.example.stickylistview_library.RefreshLayout;
import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityTransactionBinding;
import com.karazam.huashanapp.my.transactiondetails.investment.view.activity.InvestmentActivity;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionBean;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionEntity;
import com.karazam.huashanapp.my.transactiondetails.main.view.TransactionView;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.view.TransactionAdapter;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.view.TransactionItem;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModel;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModelImpl;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.activity.MyreturndetailsActivity;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.activity.RechargedetailsActivity;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity.WithdrawalsdetailsActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/8.
 */

public class TransactionActivity extends BaseActivity implements TransactionView{

    private ActivityTransactionBinding binding;
    private TransactionViewModel mModel;
    private TransactionEntity entity = new TransactionEntity();

    private ExpandableStickyListHeadersListView mListView;

    private RefreshLayout swl_pl;

    private static int page = 1;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction);
        mModel = new TransactionViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        mListView = (ExpandableStickyListHeadersListView) getView(R.id.transaction_lv);

        swl_pl = (RefreshLayout) getView(R.id.swl_pl);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {
        setList();
        setRefreshLayout();
        Refresh();
    }

    /**
     * 上下拉刷新
     */
    private void setRefreshLayout() {
        swl_pl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                showToast("onRefresh");
                Refresh();
//                swl_pl.setRefreshing(false);
            }
        });

        swl_pl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
//                showToast("onLoad");
                addData();
//                swl_pl.setLoading(false);

            }
        });
    }

    /**
     * 设置记录List
     */
    private RxProperty<TransactionBean> transactionBeanRx = RxProperty.create();
    private ArrayList<TransactionItem> beans;
    private TransactionAdapter adapter;
    private void setList() {
        beans = new ArrayList();

        adapter = new TransactionAdapter(this,beans,this);
        mListView.setAdapter(adapter);

        RxView.of(mListView).bind(transactionBeanRx, new Rx.Action<ExpandableStickyListHeadersListView, TransactionBean>() {
            @Override
            public void call(ExpandableStickyListHeadersListView target, TransactionBean transactionBean) {

                if(page == 1){
                    beans = transactionBean.getOrders();
                } else {
                    beans.addAll(transactionBean.getOrders());
                }

//                beans = transactionBean.getOrders();
                adapter.setList(beans);
                adapter.notifyDataSetChanged();

                page++;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                showToast(i+"");
                TransactionItem item = adapter.getList().get(i);

                String orderId = item.getOrderId();
                String orderNo = item.getOrderNo();
                String type = item.getType();

                if (type.equals("investment")){ //投资
                    gotoTransactiondetails(orderNo,orderId,type, InvestmentActivity.class);
                } else if(type.equals("withdrawal")){ //提现
                    gotoTransactiondetails(orderNo,orderId,type, WithdrawalsdetailsActivity.class);
                } else if(type.equals("recharge")){ //充值
                    gotoTransactiondetails(orderNo,orderId,type, RechargedetailsActivity.class);
                } else if(type.equals("repayment")){ //回款
                    gotoTransactiondetails(orderNo,orderId,type,MyreturndetailsActivity.class);
                } else {

                }

            }
        });

    }

    private void gotoTransactiondetails(String orderNo,String orderId,String type,Class<?> cls){
        Intent intent = new Intent(this,cls);
        intent.putExtra("orderId",orderId);
        intent.putExtra("orderNo",orderNo);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    /**
     * 刷新
     */
    private void Refresh() {
        page = 1;
        mModel.getTransaction(page);
    }

    /**
     * 添加数据
     */
    private void addData(){

        if(page >mModel.allpage){

//            showToast("2");
            Observable.from(new String[1])
                    .throttleFirst(8000, TimeUnit.MILLISECONDS)
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            showToast("到最后一页了！");
                            swl_pl.setLoading(false);
                        }
                    });

            return;
        }

        mModel.getTransaction(page);
    }

    /**
     * 初始化刷新
     * @param flag
     */
    private void initRefreshing(boolean flag){
        if(swl_pl == null){
            return;
        }
        swl_pl.setRefreshing(flag);
        swl_pl.setLoading(flag);
    }

    /**
     * 获取交易记录
     * @param bean
     */
    @Override
    public void getTransactionSuccess(TransactionBean bean) {

        initRefreshing(false);
        transactionBeanRx.set(bean);

    }

    /**
     * 获取交易记录
     * @param s
     */
    @Override
    public void getTransactionFail(String s) {
        showToast(s);
        initRefreshing(false);
    }

    /**
     * 获取交易记录
     * @param e
     */
    @Override
    public void getTransactionError(Throwable e) {
        showToast("网络故障！");
        initRefreshing(false);
    }
}
