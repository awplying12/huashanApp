package com.karazam.huashanapp.home.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.ScrollableViewPager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.apply.view.fragment.ApplyFragment;
import com.karazam.huashanapp.databinding.ActivityHomeBinding;
import com.karazam.huashanapp.finance.view.fragment.FinanceFragment;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.viewmodel.HomeViewModel;
import com.karazam.huashanapp.home.viewmodel.HomeViewModelImpl;
import com.karazam.huashanapp.manage.view.fragment.ManageFragment;
import com.karazam.huashanapp.my.view.fragment.MyFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    private HomeEntity mEntity = new HomeEntity();
    private ActivityHomeBinding binding;
    private HomeViewModel mModel;

    private int limit = 3;
    private ScrollableViewPager viewPager; //主体viewPager
    private TextView title_text;

    private TextView finance_text;
    private TextView manage_text;
    private TextView apply_text;
    private TextView my_text;

    private int isSelected;
    private int isDefault;

    private FinanceFragment financeFragment = new FinanceFragment();
    private ManageFragment manageFragment = new ManageFragment();
    private ApplyFragment applyFragment = new ApplyFragment();
    private MyFragment myFragment = new MyFragment();



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        mModel = new  HomeViewModelImpl(this,mEntity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(mEntity);
    }

    @Override
    public void dealLogicBeforeInitView() {
        isSelected = getResources().getColor(R.color.homeactivity_textcolor_selected);
        isDefault = getResources().getColor(R.color.homeactivity_textcolor_default);
    }

    @Override
    public void initView() {
        viewPager = (ScrollableViewPager) getView(R.id.viewpager_home);
        title_text = (TextView) getView(R.id.title_text);

        finance_text = (TextView) getView(R.id.finance_text);
        manage_text = (TextView) getView(R.id.manage_text);
        apply_text = (TextView) getView(R.id.apply_text);
        my_text = (TextView) getView(R.id.my_text);


    }

    @Override
    public void dealLogicAfterInitView() {
        setViewPager();
    }

    /**
     * 设置主体ViewPage
     */
    private void setViewPager() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(financeFragment);
        list.add(manageFragment);
        list.add(applyFragment);
        list.add(myFragment);

        viewPager.setScrollAble(false);
        viewPager.setOffscreenPageLimit(limit); //设置viewpager缓存一侧Fragment的数量
        viewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager(), list));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mModel.toFinanec(null);
                        break;
                    case 1:
                        mModel.toManage(null);
                        break;
                    case 2:
                        mModel.toApply(null);
                        break;
                    case 3:
                        mModel.toMy(null);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);
        finance_text.setTextColor(isSelected);

    }

    /**
     * 设置ViewPage展示页
     * @param position
     * @param titleStr
     */
    @Override
    public void setViewPagerCurrentItem(int position, String titleStr) {
        title_text.setText(StringUtil.interrupt(titleStr,0,""));
        viewPager.setCurrentItem(position);
        initBottomLayout();
        switch (position){
            case 0:
                finance_text.setTextColor(isSelected);
                break;
            case 1:
                manage_text.setTextColor(isSelected);
                break;
            case 2:
                apply_text.setTextColor(isSelected);
                break;
            case 3:
                my_text.setTextColor(isSelected);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化BottomLayout
     */
    private void initBottomLayout() {
        finance_text.setTextColor(isDefault);
        manage_text.setTextColor(isDefault);
        apply_text.setTextColor(isDefault);
        my_text.setTextColor(isDefault);
    }
}
