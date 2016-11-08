package com.karazam.huashanapp.manage.details.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.base.BaseActivity;
import com.example.utils.base.BaseView;
import com.gelitenight.waveview.library.WaveHelper;
import com.gelitenight.waveview.library.WaveView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentDetailsBinding;
import com.karazam.huashanapp.main.WaveActivity;
import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;
import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModel;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModelImpl;

/**
 * Created by Administrator on 2016/11/8.
 */

public class InvestmentdetailsActivity extends BaseActivity implements InvestmentdetailsView{

    private ActivityInvestmentDetailsBinding binding;
    private InvestmentdetailsEntity entity = new InvestmentdetailsEntity();

    private InvestmentdetailsViewModel mModel;

    private WaveView waveView;
    private WaveHelper mWaveHelper;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment_details);
        mModel = new InvestmentdetailsViewModelImpl(entity,this,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        waveView = (WaveView) getView(R.id.wave_in);
    }

    @Override
    public void dealLogicAfterInitView() {
            setWaveView();
    }

    private void setWaveView() {

        int mBorderColor = Color.parseColor("#0080FF");
        waveView.setBorder(0, mBorderColor);
        TextView t =(TextView)findViewById(R.id.ww_text);
        mWaveHelper = new WaveHelper(waveView,this,t);
        waveView.setShapeType(WaveView.ShapeType.SQUARE);

//        waveView.setWaveColor(
//                Color.parseColor("#0080FF"),
//                Color.parseColor("#0080FF"));
//        waveView.setBorder(1, mBorderColor);

        mWaveHelper.setPercent(0.45f);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }


}
