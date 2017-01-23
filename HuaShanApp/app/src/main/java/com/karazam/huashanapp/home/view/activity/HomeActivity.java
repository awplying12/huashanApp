package com.karazam.huashanapp.home.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.ScrollableViewPager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.apply.view.fragment.ApplyFragment;
import com.karazam.huashanapp.databinding.ActivityHomeBinding;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.viewmodel.HomeViewModel;
import com.karazam.huashanapp.home.viewmodel.HomeViewModelImpl;
import com.karazam.huashanapp.manage.main.view.fragment.ManageFragment;
import com.karazam.huashanapp.my.main.view.fragment.MyFragment;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;
import com.karazam.huashanapp.today.main.view.fragment.TodayFragment;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

import static android.R.id.message;
import static com.karazam.huashanapp.HuaShanApplication.loginStatus;

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
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        isSelected = getResources().getColor(R.color.homeactivity_textcolor_selected);
        isDefault = getResources().getColor(R.color.homeactivity_textcolor_default);


        if(loginStatus == 1){
            mModel.onChecklogin();
        }

        Bundle bundle = getIntent().getBundleExtra("HomeActivity");
        if(bundle != null ){
                        //打开自定义的Activity
            Intent i = new Intent(this, MessageActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(i);
        }

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
        setLogin();

    }

    /**
     * 登录状态
     */
    private void setLogin() {
        RxView.of(viewPager).bind(HuaShanApplication.loginStatusRx, new Rx.Action<View, Integer>() {
            @Override
            public void call(View target, Integer integer) {

                if(integer == 1){
                    getBaseData();
                    setViewPagerCurrentItem(2,"我的");
                }else {
                    setViewPagerCurrentItem(0,"今日");
                }

            }
        });
    }

    /**
     * 跳转登录界面
     */
    @Override
    public void toLoginActivity(){
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivityForResult(intent,10);
    }

    /**
     * 同步成功
     * @param checkloginReturn
     */
    @Override
    public void CheckloginSuccess(CheckloginReturn checkloginReturn) {
        showToast("同步成功");

        getBaseData();
    }

    /**
     * 同步失败
     */
    @Override
    public void CheckloginFaile(String s) {
        showToast("同步失败 ："+s);
        HuaShanApplication.safeExit();
    }

    /**
     * 同步错误
     * @param e
     */
    @Override
    public void CheckloginError(Throwable e) {

        showToast("网络故障！");
        HuaShanApplication.safeExit();
    }

    /**
     * 获取基本数据完成
     */
    @Override
    public void getBaseDataFinish() {
        myFragment.finishRefresh();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 10: //homeActivity
                if(resultCode == 101){  //登录
//                    showToast("登录 ： "+ HuaShanApplication.loginStatus);
//                    getBaseData();
//                    setViewPagerCurrentItem(3,"我的");
                }
//                else if(resultCode == 102){
//                    showToast("登录 ： "+ HuaShanApplication.loginStatus);
//                    setViewPagerCurrentItem(3,"我的");
//                }
                break;
        }

        if(resultCode ==67 && requestCode == 67){
            setCurrentItem(1,0);
        }
    }

    public void setCurrentItem(int position,int item){
        viewPager.setCurrentItem(position,false);
        manageFragment.setCurrentItem(item);
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

        viewPager.setScrollAble(false);
        viewPager.setOffscreenPageLimit(limit); //设置viewpager缓存一侧Fragment的数量
//        viewPager.setPage
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
        viewPager.setCurrentItem(position,false);
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

    /**
     * 获取基本数据
     */
    public void getBaseData(){
        mModel.getMyInformation();
        mModel.getMyAssets();
        mModel.setRegistrationId();
    }

    /**
     * 今日fragment的头像使用toMy的逻辑
     */
    public void toMyToday(){
        mModel.toMy(null);
    }

    /**
     * 再按一次退出程序
     */
    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;// 更新firstTime
                    return true;
                } else { // 两次按键小于2秒时，退出应用
                    // Intent home = new Intent(Intent.ACTION_MAIN);
                    // home.addCategory(Intent.CATEGORY_HOME);
                    // startActivity(home);
                    SharedPreferences sp = getSharedPreferences("imei", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
