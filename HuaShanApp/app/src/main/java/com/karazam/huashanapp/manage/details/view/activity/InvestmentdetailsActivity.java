package com.karazam.huashanapp.manage.details.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.percent.PercentFrameLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.utils.utils.StringUtil;
import com.gelitenight.waveview.library.WaveHelper;
import com.gelitenight.waveview.library.WaveView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentDetailsBinding;
import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;

import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;
import com.karazam.huashanapp.manage.details.model.databinding.Project;
import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.view.view.VerticalAdapter;

import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModel;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModelImpl;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.DetailsFragment1;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.DetailsFragment2;
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
    private TextView text_11,det_name,det_income,release_time;


    private PercentFrameLayout tab_det;

    private AccelerateDecelerateInterpolator mSmoothInterpolator;
    private int mMinHeaderTranslation;
    private int mActionBarHeight;
    private TypedValue mTypedValue = new TypedValue();

    private SwipeRefreshLayout swp_rl;

    private String borrowingId;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment_details);
        mModel = new InvestmentdetailsViewModelImpl(entity,this,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {


        H = BaseActivity.ScreeH;
        sh = (int) (H*0.9);
        eh = (int) (H*0.8);

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mMinHeaderTranslation = -((int) (BaseActivity.ScreeH*0.1));
//        mMinHeaderTranslation = ((int) (BaseActivity.ScreeH*0.1)+ getActionBarHeight());

        borrowingId = getIntent().getStringExtra("borrowingId");
    }


    @Override
    public void initView() {

        det_name = (TextView) getView(R.id.det_name);
        text_11 = (TextView) getView(R.id.text_11);
        det_income = (TextView) getView(R.id.det_income);
        release_time = (TextView) getView(R.id.release_time);

        ViewPager = (VerticalViewPager) getView(R.id.viewpager_ve);

        tab_det = (PercentFrameLayout) getView(R.id.tab_det);


    }

    @Override
    public void dealLogicAfterInitView() {
//            setWaveView();
            setLayout();
            setVerticalViewPager();

        mModel.getManagedetailsData(borrowingId);
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

        fragment2.setBorrowingId(borrowingId);
        list.add(fragment2);

        PagerFragmentAdapter adapter = new PagerFragmentAdapter(getSupportFragmentManager(),list);

        ViewPager.setAdapter(adapter);

    }

    /**
     * 设置界面
     */

    public RxProperty<ManagedetailsBean> project = RxProperty.create();
    private void setLayout() {

        RxView.findById(this,R.id.content_pl).bind(project, new Rx.Action<View, ManagedetailsBean>() {
            @Override
            public void call(View target, ManagedetailsBean managedetailsBean) {
                Project project = managedetailsBean.getProject();

                String title = project.getTitle();
                det_name.setText(StringUtil.interrupt(title,0,""));

                String interestRate = project.getInterestRate();
                interestRate = StringUtil.reservedDecimal(StringUtil.interrupt(interestRate,0,"0"),2);
                det_income.setText(interestRate);

                String publishDate = project.getPublishDate();
                release_time.setText("发布日期："+StringUtil.interrupt(publishDate,0,""));
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
     * 获取数据成功
     */
    @Override
    public void getManagedetailsDataSuccess(ManagedetailsBean data) {
        project.set(data);
    }

    /**
     * 获取数据失败
     * @param e
     */
    @Override
    public void getManagedetailsDataFaile(String e) {

    }

    /**
     * 获取数据故障
     * @param e
     */
    @Override
    public void getManagedetailsDataError(Throwable e) {

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
