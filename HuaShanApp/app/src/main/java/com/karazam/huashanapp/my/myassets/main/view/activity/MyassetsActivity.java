package com.karazam.huashanapp.my.myassets.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyassetsBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.my.myassets.main.model.databinding.MyassetsEntity;
import com.karazam.huashanapp.my.myassets.main.view.MyassetsView;
import com.karazam.huashanapp.my.myassets.main.viewmodel.MyassetsViewModel;
import com.karazam.huashanapp.my.myassets.main.viewmodel.MyassetsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyassetsActivity extends BaseActivity implements MyassetsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMyassetsBinding binding;
    private MyassetsViewModel mModel;
    private MyassetsEntity entity = new MyassetsEntity();

    private SwipeRefreshLayout swl_pl;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myassets);
        mModel = new MyassetsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        swl_pl = (SwipeRefreshLayout) getView(R.id.swl_pl);
        swl_pl.setOnRefreshListener(this);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
    }



    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mModel.getMyAssets();
    }

    /**
     * 设置界面
     */
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {

                TextView det_income = (TextView) target.findViewById(R.id.det_income);
                TextView tv_available = (TextView) target.findViewById(R.id.tv_available);
                TextView tv_withdrawing = (TextView) target.findViewById(R.id.tv_withdrawing);
                TextView tv_investFrozen = (TextView) target.findViewById(R.id.tv_investFrozen);
                TextView tv_watingCapital = (TextView) target.findViewById(R.id.tv_watingCapital);
                TextView tv_watingProfits = (TextView) target.findViewById(R.id.tv_watingProfits);

                String allCapitalSum = myAssetsBean.getAllCapitalSum();
                det_income.setText(StringUtil.getMoneyType(StringUtil.interrupt(allCapitalSum,0,"0"),false));

                String available = myAssetsBean.getAvailable();
                tv_available.setText(StringUtil.getMoneyType(StringUtil.interrupt(available,0,"0"),false));

                String withdrawing = myAssetsBean.getWithdrawing();
                tv_withdrawing.setText(StringUtil.getMoneyType(StringUtil.interrupt(withdrawing,0,"0"),false));

                String investFrozen = myAssetsBean.getInvestFrozen();
                tv_investFrozen.setText(StringUtil.getMoneyType(StringUtil.interrupt(investFrozen,0,"0"),false));

                String watingCapital = myAssetsBean.getWatingCapital();
                tv_watingCapital.setText(StringUtil.getMoneyType(StringUtil.interrupt(watingCapital,0,"0"),false));

                String watingProfits = myAssetsBean.getWatingProfits();
                tv_watingProfits.setText(StringUtil.getMoneyType(StringUtil.interrupt(watingProfits,0,"0"),false));

            }
        });

    }

    @Override
    public void getMyAssetsFinish() {
        swl_pl.setRefreshing(false);
    }
}
