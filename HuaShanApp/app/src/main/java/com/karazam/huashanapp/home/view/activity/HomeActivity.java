package com.karazam.huashanapp.home.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.ScrollableViewPager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.apply.view.fragment.ApplyFragment;
import com.karazam.huashanapp.databinding.ActivityHomeBinding;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.viewmodel.HomeViewModel;
import com.karazam.huashanapp.home.viewmodel.HomeViewModelImpl;
import com.karazam.huashanapp.manage.main.view.fragment.ManageFragment;
import com.karazam.huashanapp.my.main.view.fragment.MyFragment;
import com.karazam.huashanapp.today.main.view.fragment.TodayFragment;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;

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

//    private TextView finance_text;

    private TextView today_text;
    private TextView manage_text;
//    private TextView mall_text;
    private TextView my_text;

    private ImageView today_img;
    private ImageView manage_img;
//    private ImageView mall_img;
    private ImageView my_img;

    private int isSelected;
    private int isDefault;

//    private FinanceFragment financeFragment = new FinanceFragment();
    private TodayFragment todayFragment = new TodayFragment();
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

//        finance_text = (TextView) getView(R.id.finance_text);

        today_text = (TextView) getView(R.id.today_text);
        manage_text = (TextView) getView(R.id.manage_text);
//        mall_text = (TextView) getView(R.id.mall_text);
        my_text = (TextView) getView(R.id.my_text);

        today_img = (ImageView) getView(R.id.today_img);
        manage_img = (ImageView) getView(R.id.manage_img);
//        mall_img = (ImageView) getView(R.id.mall_img);
        my_img = (ImageView) getView(R.id.my_img);

    }

    @Override
    public void dealLogicAfterInitView() {
        setViewPager();
    }

    /**
     * 跳转登录界面
     */
    @Override
    public void toLoginActivity(){
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 10: //homeActivity
                if(resultCode == 101){  //登录
                    showToast("登录 ： "+ HuaShanApplication.loginStatus);
                    setViewPagerCurrentItem(3,"我的");
                }
//                else if(resultCode == 102){
//                    showToast("登录 ： "+ HuaShanApplication.loginStatus);
//                    setViewPagerCurrentItem(3,"我的");
//                }
                break;
        }

        if(resultCode ==67 && requestCode == 67){
            viewPager.setCurrentItem(1);
            manageFragment.setCurrentItem(0);
        }
    }

    /**
     * 设置主体ViewPage
     */
    private void setViewPager() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(todayFragment);
        list.add(manageFragment);
//        list.add(applyFragment);
        list.add(myFragment);

        viewPager.setScrollAble(true);
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
                        mModel.toToday(null);
                        break;
                    case 1:
                        mModel.toManage(null);
                        break;
//                    case 2:
//                        mModel.toApply(null);
//                        break;
                    case 2:
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
        today_text.setTextColor(isSelected);
        today_img.setSelected(true);

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
                today_text.setTextColor(isSelected);
                today_img.setSelected(true);
                break;
            case 1:
                manage_text.setTextColor(isSelected);
                manage_img.setSelected(true);
                break;
//            case 2:
//                mall_text.setTextColor(isSelected);
//                mall_img.setSelected(true);
//                break;
            case 2:
                my_text.setTextColor(isSelected);
                my_img.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化BottomLayout
     */
    private void initBottomLayout() {
        today_text.setTextColor(isDefault);
        manage_text.setTextColor(isDefault);
//        mall_text.setTextColor(isDefault);
        my_text.setTextColor(isDefault);

        today_img.setSelected(false);
        manage_img.setSelected(false);
//        mall_img.setSelected(false);
        my_img.setSelected(false);
    }



}
