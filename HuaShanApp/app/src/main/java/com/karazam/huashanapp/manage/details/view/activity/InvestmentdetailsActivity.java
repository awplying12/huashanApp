package com.karazam.huashanapp.manage.details.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.percent.PercentFrameLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.VerticalViewPager.VerticalViewPager;
import com.gelitenight.waveview.library.WaveHelper;
import com.gelitenight.waveview.library.WaveView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentDetailsBinding;
import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;

import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.view.view.VerticalAdapter;

import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModel;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModelImpl;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.DetailsFragment1;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.DetailsFragment2;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/8.
 */

public class InvestmentdetailsActivity extends BaseActivity implements InvestmentdetailsView {

    private ActivityInvestmentDetailsBinding binding;
    private InvestmentdetailsEntity entity = new InvestmentdetailsEntity();

    private InvestmentdetailsViewModel mModel;

    private WaveView waveView;
    private WaveHelper mWaveHelper;

    private int H;
    private int sh;
    private int eh;

    private VerticalViewPager ViewPager;
    private TextView text_11;
    private TextView det_income;

    private PercentFrameLayout tab_det;

    private AccelerateDecelerateInterpolator mSmoothInterpolator;
    private int mMinHeaderTranslation;
    private int mActionBarHeight;
    private TypedValue mTypedValue = new TypedValue();





    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment_details);
        mModel = new InvestmentdetailsViewModelImpl(entity,this,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
    }

    @Override
    public void dealLogicBeforeInitView() {
        project.set(new Project(1,""));

        H = BaseActivity.ScreeH;
        sh = (int) (H*0.9);
        eh = (int) (H*0.8);

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mMinHeaderTranslation = -((int) (BaseActivity.ScreeH*0.1));
//        mMinHeaderTranslation = ((int) (BaseActivity.ScreeH*0.1)+ getActionBarHeight());

    }

    @Override
    public void initView() {


        text_11 = (TextView) getView(R.id.text_11);
        det_income = (TextView) getView(R.id.det_income);

        ViewPager = (VerticalViewPager) getView(R.id.viewpager_ve);

        tab_det = (PercentFrameLayout) getView(R.id.tab_det);


    }

    @Override
    public void dealLogicAfterInitView() {
//            setWaveView();
            setLayout();
            setVerticalViewPager();
    }

    /**
     * 设置详情的ViewPager
     */
    private  int i = 0;
    private  DetailsFragment1 fragment1 = new DetailsFragment1();
    private  DetailsFragment2 fragment2 = new DetailsFragment2();
    private void setVerticalViewPager() {

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(fragment1);
        list.add(fragment2);

        PagerFragmentAdapter adapter = new PagerFragmentAdapter(getSupportFragmentManager(),list);

        ViewPager.setAdapter(adapter);

    }

    /**
     * 设置界面
     */

    public static RxProperty<Project> project =  RxProperty.create();
    private void setLayout() {

        RxView.findById(this, R.id.content_pl).bind(project, new Rx.Action<View, Project>() {
            @Override
            public void call(View target, Project project) {
               TextView name = (TextView) target.findViewById(R.id.det_name);
                TextView income = (TextView) target.findViewById(R.id.det_income);

//                name.setText("你大爷");
//                income.setText("9.30");

            }
        });

    }

    /**
     * 设置选择器
     */
    @Override
    public void setTab(int num) {

        fragment2.setCurrentItem(num);

    }


    /**
     * 滑动选择
     * @param positoin
     */
    public void SlidingSelection(int positoin){
        switch (positoin){
            case 0:
                mModel.onInformation(null);
                break;
            case 1:
                mModel.onRecord(null);
                break;
            case 2:
                mModel.onSpeed(null);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
//        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mWaveHelper.start();
    }





}
