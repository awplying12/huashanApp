package com.karazam.huashanapp.my.transactiondetails.investment.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentBinding;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentBean;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentEntity;
import com.karazam.huashanapp.my.transactiondetails.investment.view.InvestmentView;
import com.karazam.huashanapp.my.transactiondetails.investment.viewmodel.InvestmentViewModel;
import com.karazam.huashanapp.my.transactiondetails.investment.viewmodel.InvestmentViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/9.
 */

public class InvestmentActivity extends BaseActivity implements InvestmentView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityInvestmentBinding binding;
    private InvestmentViewModel mModel;
    private InvestmentEntity entity = new InvestmentEntity();

    private SwipeRefreshLayout swipe_l;



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment);
        mModel = new InvestmentViewModelImpl(this,entity,this,this);
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
        mModel.getInvestmentdetails();
    }

    @Override
    public void onRefresh() {

        mModel.getInvestmentdetails();

    }

    /**
     * 设置界面
     */
    private RxProperty<InvestmentBean> InvestmentbeanRx = RxProperty.create();
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(InvestmentbeanRx, new Rx.Action<View, InvestmentBean>() {
            @Override
            public void call(View target, InvestmentBean investmentBean) {
                TextView tv_title = (TextView) target.findViewById(R.id.tv_title);
                TextView tv_amount = (TextView) target.findViewById(R.id.tv_amount);
                TextView tv_status = (TextView) target.findViewById(R.id.tv_status);
                TextView tv_buyMethod = (TextView) target.findViewById(R.id.tv_buyMethod);
                TextView tv_memo = (TextView) target.findViewById(R.id.tv_memo);
                TextView tv_createDate = (TextView) target.findViewById(R.id.tv_createDate);

                String title = investmentBean.getTitle();
                tv_title.setText(StringUtil.interrupt(title,0,"未知"));

                String amount = investmentBean.getAmount();
                tv_amount.setText("-"+StringUtil.getMoneyType(StringUtil.reservedDecimal(StringUtil.interrupt(amount,0,"0"),2),false));

                String statusDes = investmentBean.getStatusDes();
                tv_status.setText(StringUtil.interrupt(statusDes,0,"未知"));

                String buyMethod = investmentBean.getBuyMethod();
                tv_buyMethod.setText(StringUtil.interrupt(buyMethod,12,"未知"));

                String memo = investmentBean.getMemo();
                tv_memo.setText(StringUtil.interrupt(memo,20,"未知"));
//
//                String createDate = investmentBean.getCreateDate();
//                tv_createDate.setText(StringUtil.interrupt(createDate,0,"未知"));

                String createDate = investmentBean.getCreateDate();
                Long date = Long.parseLong(StringUtil.interrupt(createDate,0,"0"));
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
     * 获取投资详情成功
     */
    @Override
    public void getInvestmentdetailsSuccess(InvestmentBean bean) {
        swipe_l.setRefreshing(false);

        InvestmentbeanRx.set(bean);
    }

    /**
     * 获取投资详情失败
     */
    @Override
    public void getInvestmentdetailsFail(String s) {
        swipe_l.setRefreshing(false);

        showToast(s);
    }

    /**
     * 获取投资详情错误
     */
    @Override
    public void getInvestmentdetailsError(Throwable e) {
        swipe_l.setRefreshing(false);

        showToast("网络故障！");
    }
}
