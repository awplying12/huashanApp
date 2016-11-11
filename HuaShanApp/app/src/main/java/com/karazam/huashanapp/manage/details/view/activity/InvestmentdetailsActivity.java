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
    private View mHeader;
    private View header_2;

    private TextView information;
    private TextView record;
    private TextView speed;


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

        mHeader = getView(R.id.header);
        header_2 = getView(R.id.header_2);
        text_11 = (TextView) getView(R.id.text_11);
        det_income = (TextView) getView(R.id.det_income);

        ViewPager = (VerticalViewPager) getView(R.id.viewpager_ve);

        tab_det = (PercentFrameLayout) getView(R.id.tab_det);

        information = (TextView) getView(R.id.bt_det_1);
        record = (TextView) getView(R.id.bt_det_2);
        speed = (TextView) getView(R.id.bt_det_3);
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

        ViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("fa","1");
                int scrollY = positionOffsetPixels;
                Log.i("scrollY : ",positionOffset+"  position   "+position);

                switch (i){
                    case 0:
                        mHeader.setTranslationY((float) ( Math.max(-scrollY, -tab_det.getHeight()*0.7)));
                        header_2.setTranslationY((float) ( Math.max(-scrollY, -tab_det.getHeight())));
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("fa","2");
                i = position;
                switch (i){
                    case 0:
                        mHeader.setTranslationY(0);
                        header_2.setTranslationY(0);
                        break;
                    case 1:
                        mHeader.setTranslationY(-(int) (tab_det.getHeight()*0.7));
                        header_2.setTranslationY(-(int) (tab_det.getHeight()));

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("fa","3");
            }
        });
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
        initTab();
        fragment2.setCurrentItem(num);
        switch (num){
            case 0:
                information.setBackgroundColor(Color.parseColor("#00ffffff"));
                break;
            case 1:
                record.setBackgroundColor(Color.parseColor("#00ffffff"));
                break;
            case 2:
                speed.setBackgroundColor(Color.parseColor("#00ffffff"));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化tab
     */
    private void initTab(){
        information.setBackgroundColor(Color.parseColor("#f0f0f0"));
        record.setBackgroundColor(Color.parseColor("#f0f0f0"));
        speed.setBackgroundColor(Color.parseColor("#f0f0f0"));
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
