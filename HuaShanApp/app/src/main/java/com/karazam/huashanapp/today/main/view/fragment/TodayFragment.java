package com.karazam.huashanapp.today.main.view.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.base.BaseFragment;

import com.example.utils.custom.FullyLinearLayoutManager;
import com.example.utils.custom.VpSwipeRefreshLayout;
import com.example.utils.custom.bannerview.ViewFlow;
import com.example.utils.custom.views.AutoScrollViewPager;
import com.example.utils.utils.BitmapUtil;
import com.google.common.collect.Lists;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentTodayBinding;

import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.manage.main.view.view.ContentAdapter;
import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.view.TodayView;
import com.karazam.huashanapp.today.main.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.today.main.view.fragment.view.ExperienceAdapter;
import com.karazam.huashanapp.today.main.view.fragment.view.MyNestedScrollView;

import com.karazam.huashanapp.today.main.view.fragment.view.ViewFlowAdapter;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModel;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.GuideFragment;
import github.chenupt.springindicator.SpringIndicator;
import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/2.
 */

public class TodayFragment extends BaseFragment implements TodayView,SwipeRefreshLayout.OnRefreshListener {

    private View view;

    private FragmentTodayBinding binding;
    private TodayEntity entity = new TodayEntity();

    private TodayViewModel mModel;
    private TextView profit_num;

    private VpSwipeRefreshLayout swl;

    private AutoScrollViewPager pager;

//    private ViewFlow viewFlow;

    private AutoScrollAdapter autoScrollAdapter;
//    private SpringIndicator springIndicator;
    private CirclePageIndicator indicator;

    private MyNestedScrollView scrollview;


//    private RecyclerView commodity_rl;
//    private RecyclerView new_rl;

    private RecyclerView selected_rl;
    private RecyclerView experience_rl;

    private PercentFrameLayout viewpager_pl;
    private PercentFrameLayout title_pl;
    private ImageView head_img;

    private ImageView line_1;
    private ImageView line_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today,container,false);
        view = binding.getRoot();
        mModel = new TodayViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);

        initView();

        setHeader();
        initTime();
        setScrollView();
        AutoScrollViewPager();
        setExperience();
        setSelected();
//        setCommodity();
//        setNew();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        pager.startAutoScroll();
    }

    @Override
    public void onStop() {
        super.onStop();
        pager.stopAutoScroll();

    }

    /**
     * 初始化日期
     */
    private void initTime() {
//        RxView.findById(view,R.id.today_time).bind(HuaShanApplication.day, new Rx.Action<View, String>() {
//            @Override
//            public void call(View target, String s) {
//                TextView textView = (TextView) target;
//                textView.setText(s);
//            }
//        });

        RxView.findById(view,R.id.today_time).bind(HuaShanApplication.day, new Rx.Action<View, Time>() {
            @Override
            public void call(View target, Time time) {
                TextView textView = (TextView) target;
                textView.setText(time.monthDay+"");
            }
        });
    }


    /**
     * 初始化View
     */
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("NewApi")
    private void initView() {

        swl = (VpSwipeRefreshLayout) getView(R.id.today_swipe_ly,view);
        swl.setOnRefreshListener(this);
        swl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        pager = (AutoScrollViewPager) getView(R.id.today_scroll_pager,view);
//        viewFlow = (ViewFlow) getView(R.id.today_scroll_pager,view);
        indicator = (CirclePageIndicator) getView(R.id.indicator,view);

        viewpager_pl = (PercentFrameLayout) getView(R.id.viewpager_pl,view);
        viewpager_pl.setLayoutParams(new LinearLayout.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeW*0.4)));

        head_img = (ImageView) getView(R.id.head_img,view);

        title_pl = (PercentFrameLayout) getView(R.id.title_pl,view);

//        title_pl.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                         public boolean onPreDraw() {
//                        title_pl.getViewTreeObserver().removeOnPreDrawListener(this);
//                        title_pl.buildDrawingCache();
//
//                               Bitmap bmp = title_pl.getDrawingCache();
//                               BitmapUtil.blur(bmp,title_pl,getContext());
//                                return true;
//                            }
//                  });


        profit_num = (TextView) getView(R.id.profit_num,view);
//        final Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"FZXQJW.TTF");
        final Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"roboto/Roboto-Thin.ttf");
        profit_num.setTypeface(font);

        scrollview = (MyNestedScrollView) getView(R.id.scrollview,view);

        line_1 = (ImageView) getView(R.id.td_line_1,view);
        line_2 = (ImageView) getView(R.id.td_line_2,view);

//        commodity_rl = (RecyclerView) getView(R.id.commodity_rl,view);
//        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        commodity_rl.setLayoutManager(lm);
//
//        new_rl = (RecyclerView) getView(R.id.new_rl,view);
//        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//        new_rl.setLayoutManager(lm1);


        selected_rl = (RecyclerView) getView(R.id.selected_rl,view);
        FullyLinearLayoutManager lm2 = new FullyLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        selected_rl.setLayoutManager(lm2);

        experience_rl = (RecyclerView) getView(R.id.experience_rl,view);
        FullyLinearLayoutManager lm3 = new FullyLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        experience_rl.setLayoutManager(lm3);
    }

    @Override
    public void onRefresh() {
        swl.setRefreshing(false);
    }

    /**
     * 设置滚动ViewPager
     */
    private void AutoScrollViewPager(){

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());

        autoScrollAdapter = new AutoScrollAdapter(getActivity().getSupportFragmentManager(), manager,getBgRes(),getContext(),pager);
//        Log.i("msh","123");
//       pager.setCurrentItem(3);
        pager.setTime(5000);
        pager.setAdapter(autoScrollAdapter);
        pager.setmDuration(500);

//        indicator.setParent(pager);

        indicator.setViewPager(pager);

        autoScrollAdapter.setOnAutoScrollPagerClickListener(new AutoScrollAdapter.OnAutoScrollPagerClickListener() {

            @Override
            public void onClick(View view, int position) {
                showToast(position+"");
            }

            @Override
            public boolean onLongClick(View view, int position) {
                showToast(position+"Long");
                return true;
            }
        });



    }

    private void setHeader(){
        RxView.of(head_img).bind(HuaShanApplication.userInformationR, new Rx.Action<ImageView, UserInformation>() {
            @Override
            public void call(final ImageView target, UserInformation userInformation) {
                String url = userInformation.getHeaderImg();
                RxImageLoader.getLoaderObservable(target,url).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        target.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.userhead_icon));
                    }

                    @Override
                    public void onNext(Data data) {
                        Bitmap bitmap = data.bitmap;
                        if (bitmap == null){
                            target.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.userhead_icon));
                        }
                        target.setImageBitmap(BitmapUtil.toRoundBitmap(bitmap));
                    }
                });
            }
        });
    }

    /**
     * 设置广告标题
     * @return
     */
    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4","5");
    }

    /**
     * 设置广告图片
     * @return
     */
    private ArrayList<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.winter, R.drawable.winter1, R.drawable.winter2, R.drawable.winter3,R.drawable.winter4);
//        return Lists.newArrayList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,R.drawable.image5);
    }

    /**
     * 设置滑动效果
     */
    private void setScrollView() {

        scrollview.setOnScrollInterface(new MyNestedScrollView.onScrollInterface() {
            @Override
            public void onSChanged(int l, int t, int oldl, int oldt) {


                int[] location1 = new int[2];
                line_1.getLocationOnScreen(location1);
                final int x1 = location1[0];
                final int y1 = location1[1];

                int[] location2 = new int[2];
                line_2.getLocationOnScreen(location2);
                final int x2 = location2[0];
                final int y2 = location2[1];


                if((y1-y2) <= 0 ){
                    line_1.setBackgroundColor(getResources().getColor(R.color.transparent));
                    line_2.setBackgroundColor(getResources().getColor(R.color.line_color));

//                    float scale = (float) t / BaseActivity.ScreeH;


                }else {
                    line_2.setBackgroundColor(getResources().getColor(R.color.transparent));
                    line_1.setBackgroundColor(getResources().getColor(R.color.line_color));


                }

                if(y1 > y2){
                    title_pl.setBackgroundColor(Color.parseColor("#00000000"));

                } else {        //当第一道线碰触到第二道线是时开始渐变
                    float scale = (float) t / y2;
                    float alpha = (255 * scale);
                    if(scale < 2){
                        title_pl.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
                    }else {
                        title_pl.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                }


            }
        });

    }

    /**
     * 每日精选——体验RecyclerView
     */
    private void setExperience(){
        ArrayList<Project> list = new ArrayList<>();
        list.add(new Project(0,"立即体检"));

        ExperienceAdapter adapter = new ExperienceAdapter(getContext(),list);

        experience_rl.setAdapter(adapter);

    }

    /**
     * 每日精选——推荐RecyclerView
     */
    private void setSelected(){


        ArrayList<Project> list = new ArrayList<>();
        list.add(new Project(0,"立即购买"));
        list.add(new Project(0,"立即购买"));
        list.add(new Project(0,"立即购买"));

        ContentAdapter adapter = new ContentAdapter(getContext(),list);

        selected_rl.setAdapter(adapter);

    }

//    /**
//     * 积分商城RecyclerView
//     */
//    private void setCommodity(){
//
//        final ArrayList<CommodityItem> items = new ArrayList<>();
//        items.add(new CommodityItem("手机主题馆","积分不够分期付","#00E3E3"));
//        items.add(new CommodityItem("购物卡主题馆","优惠活动双重享","#ff2d2d"));
//        items.add(new CommodityItem("购物卡主题馆","优惠活动双重享","#2894ff"));
//
//
//        CommodityAdapter adapter = new CommodityAdapter(items,commodity_rl);
//
//        commodity_rl.setAdapter(adapter);
//
//        adapter.setmOnItemClickListener(new CommodityAdapter.onItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                showToast(items.get(position).getTitle());
//            }
//        });
//
//    }
//
//    /**
//     * 知道RecyclerView
//     */
//    private void setNew(){
//
//        final ArrayList<NewItem> items = new ArrayList<>();
//        items.add(new NewItem());
//        items.add(new NewItem());
//        items.add(new NewItem());
//        items.add(new NewItem());
//        items.add(new NewItem());
//
//        NewAdapter adapter = new NewAdapter(getContext(),items);
//
//        new_rl.setAdapter(adapter);
//
//    }
}
