package com.karazam.huashanapp.my.myprofits.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyprofitsBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.my.myprofits.main.model.databinding.MyprofitsEntity;
import com.karazam.huashanapp.my.myprofits.main.view.MyprofitsView;
import com.karazam.huashanapp.my.myprofits.main.viewmodel.MyprofitsViewModel;
import com.karazam.huashanapp.my.myprofits.main.viewmodel.MyprofitsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MyprofitsActivity extends BaseActivity implements MyprofitsView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityMyprofitsBinding binding;
    private MyprofitsViewModel mModel;
    private MyprofitsEntity entity = new MyprofitsEntity();

    private SwipeRefreshLayout swl_pl;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myprofits);
        mModel = new MyprofitsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
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
     * 设置界面
     */
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {

                TextView det_income = (TextView) target.findViewById(R.id.det_income);
                TextView tv_alreadyProfits = (TextView) target.findViewById(R.id.tv_alreadyProfits);
                TextView tv_alreadyReferralFees = (TextView) target.findViewById(R.id.tv_alreadyReferralFees);


                String alreadyProfitsSum = StringUtil.interrupt(myAssetsBean.getAlreadyProfitsSum(),0,"0");
                det_income.setText(StringUtil.getMoneyType(alreadyProfitsSum,false));

                String alreadyProfits = StringUtil.interrupt(myAssetsBean.getAlreadyProfits(),0,"0");
                tv_alreadyProfits.setText(StringUtil.getMoneyType(alreadyProfits,false));

                String alreadyReferralFees = StringUtil.interrupt(myAssetsBean.getAlreadyReferralFees(),0,"0");
                tv_alreadyReferralFees.setText(StringUtil.getMoneyType(alreadyReferralFees,false));
            }
        });
    }

    @Override
    public void onRefresh() {
        mModel.getMyAssets();
    }

    @Override
    public void getMyAssetsFinish() {
        swl_pl.setRefreshing(false);
    }
}
