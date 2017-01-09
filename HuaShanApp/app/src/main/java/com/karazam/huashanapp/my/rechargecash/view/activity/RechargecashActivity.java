package com.karazam.huashanapp.my.rechargecash.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargecashBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.my.rechargecash.model.databinding.RechargecashEntity;
import com.karazam.huashanapp.my.rechargecash.view.RechargecashView;
import com.karazam.huashanapp.my.rechargecash.viewmodel.RechargecashViewModel;
import com.karazam.huashanapp.my.rechargecash.viewmodel.RechargecashViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RechargecashActivity extends BaseActivity implements RechargecashView {


    private ActivityRechargecashBinding binding;
    private RechargecashEntity entity = new RechargecashEntity();
    private RechargecashViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_rechargecash);
        mModel = new RechargecashViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
    }


    /**
     * 设置界面
     */
    private void setLayout() {

        RxView.findById(this,R.id.tv_balance).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {
                TextView tv = (TextView) target;    //available
                String available = StringUtil.interrupt(myAssetsBean.getAvailable(),0,"0");
                available = StringUtil.reservedDecimal(available,2);
                tv.setText(Html.fromHtml(available));
            }
        });

    }
}
