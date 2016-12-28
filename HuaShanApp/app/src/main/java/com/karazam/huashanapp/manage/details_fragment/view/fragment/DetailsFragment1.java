package com.karazam.huashanapp.manage.details_fragment.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.base.BaseFragment;
import com.gelitenight.waveview.library.WaveHelper;
import com.gelitenight.waveview.library.WaveView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentDetails1Binding;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment1Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment1View;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_1.DetailsFragment1ViewModel;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_1.DetailsFragment1ViewModelImpl;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment1 extends BaseFragment implements DetailsFragment1View {

    private View view;

    private FragmentDetails1Binding binding;
    private DetailsFragment1Entity entity = new DetailsFragment1Entity();

    private DetailsFragment1ViewModel mModel;

    private WaveView waveView;
    private WaveHelper mWaveHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_1,container,false);
        view = binding.getRoot();
        mModel = new DetailsFragment1ViewModelImpl(this,entity,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();

        setWaveView();
        setLayout();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {

        waveView = (WaveView) getView(R.id.wave_in,view);


    }

    /**
     * 设置界面
     */


    private void setLayout() {
        InvestmentdetailsActivity activity = (InvestmentdetailsActivity) getActivity();

//        RxView.findById(getActivity(), R.id.content_pl).bind(activity.project, new Rx.Action<View, Project>() {
//            @Override
//            public void call(View target, Project project) {
//
//                TextView tv_playenough = (TextView) target.findViewById(R.id.det_playenough);
//                TextView tv_time = (TextView) target.findViewById(R.id.det_time);
//
//
////                tv_playenough.setText("50");
//                TextView tv_money = (TextView) target.findViewById(R.id.det_money);
//                String money = "200,000.00";
//                tv_money.setText(Html.fromHtml(money + "<font color='#7b7b7b'>/1,000,000.00</font>"));
//
//                mWaveHelper.setPercent(0.3f);
//            }
//        });

    }


    /**
     *设置滴水效果WaveView
     */
    private void setWaveView() {

        int mBorderColor = Color.parseColor("#0080FF");
        waveView.setBorder(0, mBorderColor);
        TextView t =(TextView)getView(R.id.ww_text,view);
        mWaveHelper = new WaveHelper(waveView,getActivity(),t);
        waveView.setShapeType(WaveView.ShapeType.SQUARE);

//        waveView.setShapeType(WaveView.ShapeType.CIRCLE);


//        waveView.setBorder(1, mBorderColor);


//        Timer timer = new Timer();
//        TimerTask tk = new TimerTask() {
//            @Override
//            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                                waveView.setWaveColor(
//                                        Color.parseColor("#0080FF"),
//                                        Color.parseColor("#500080FF"));
//                                }
//                });
//            }
//        };
//        timer.schedule(tk,100);



        mWaveHelper.start();

    }
}
