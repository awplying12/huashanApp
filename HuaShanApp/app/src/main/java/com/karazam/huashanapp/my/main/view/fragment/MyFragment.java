package com.karazam.huashanapp.my.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseFragment;
import com.example.utils.custom.VpSwipeRefreshLayout;
import com.example.utils.utils.BitmapUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentMyBinding;
import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.my.main.view.view.AssetAdapter;
import com.karazam.huashanapp.my.main.viewmodel.MyViewModel;
import com.karazam.huashanapp.my.main.viewmodel.MyViewModelImpl;
import com.karazam.huashanapp.my.myassets.main.view.activity.MyassetsActivity;
import com.karazam.huashanapp.my.myprofits.main.view.activity.MyprofitsActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyFragment extends BaseFragment implements MyView,SwipeRefreshLayout.OnRefreshListener{

    private View view;

    private MyEntity entity = new MyEntity();
    private FragmentMyBinding binding;
    private MyViewModel mModel;

    private CirclePageIndicator indicator;
    private VpSwipeRefreshLayout swl_pl;

    private ViewPager vp_asset;
    private HomeActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false);
        view = binding.getRoot();
        mModel = new MyViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);

        initView();

        //设置头像
        setHeader();

        setAsset();
        setAssetClick();
        setLayout();

        return view;
    }




    /**
     * 初始化View
     */
    private void initView() {
        vp_asset = (ViewPager) getView(R.id.vp_asset,view);
        indicator = (CirclePageIndicator) getView(R.id.indicator,view);

        swl_pl = (VpSwipeRefreshLayout) getView(R.id.swl_pl,view);
        swl_pl.setOnRefreshListener(this);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        activity = (HomeActivity) getActivity();

    }

    @Override
    public void onRefresh() {

        activity.getBaseData();
    }

    public void finishRefresh(){
        if(swl_pl == null){
            return;
        }
        swl_pl.setRefreshing(false);
    }

   /**
    * 设置头像
    */
    private void setHeader() {

        RxView.findById(view,R.id.user_header).bind(HuaShanApplication.baseInfoBeanRX, new Rx.Action<View, BaseInfoBean>() {
            @Override
            public void call(View target, BaseInfoBean baseInfoBean) {
                final ImageView header = (ImageView) target;

//                Log.i("logo",baseInfoBean.getAvatar());

                if(baseInfoBean == null||TextUtils.isEmpty(baseInfoBean.getAvatar())){
                    header.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user_logo));
                    return;
                }

                RxImageLoader.getLoaderObservable(null,baseInfoBean.getAvatar()).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("e",e.toString());
                        header.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user_logo));
                    }

                    @Override
                    public void onNext(Data data) {
                        Bitmap heaher = data.bitmap;
                        if(header == null){
                            return;
                        }
                        header.setImageBitmap(BitmapUtil.toRoundBitmap(heaher));
                    }
                });
            }
        });
    }

    /**
     * 设置界面
     */
    private void setLayout() {
//        RxView.findById(view,R.id.tv_balance).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
//            @Override
//            public void call(View target, UserInformation userInformation) {
//                TextView tv = (TextView) target;
//                tv.setText(Html.fromHtml("可用余额<font color='#ffffff'> 0.00 </font>元"));
//            }
//        });
        RxView.findById(view,R.id.tv_balance).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {
                TextView tv = (TextView) target;    //available
                String available =StringUtil.interrupt(myAssetsBean.getAvailable(),0,"0");
                available = StringUtil.getMoneyType(available,false);
                tv.setText(Html.fromHtml("可用余额<font color='#ffffff'> "+available+" </font>元"));
            }
        });

    }


    /**
     * 资产ViewPager
     */
    private ArrayList<View> list = new ArrayList<>();
    private View view1;
    private View view2;
    private void setAsset() {
        view1 = LayoutInflater.from(getContext()).inflate(R.layout.view_asset_item,null);
        view2 = LayoutInflater.from(getContext()).inflate(R.layout.view_asset_item,null);

        list.add(view1);
        list.add(view2);


        AssetAdapter adapter = new AssetAdapter(getContext(),list);

        vp_asset.setAdapter(adapter);
        indicator.setViewPager(vp_asset);


        RxView.of(view1).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {
                TextView text_p1 = (TextView) target.findViewById(R.id.text_p1);
                TextView income = (TextView) target.findViewById(R.id.det_income);
                text_p1.setText("总资产(元)");

                String allCapitalSum =  StringUtil.interrupt(myAssetsBean.getAllCapitalSum(),0,"0");
                income.setText(StringUtil.getMoneyType(allCapitalSum,false));
            }
        });


        RxView.of(view2).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {
                TextView text_p1 = (TextView) target.findViewById(R.id.text_p1);
                TextView income = (TextView) target.findViewById(R.id.det_income);
                text_p1.setText("累计收益(元)");

                String alreadyProfitsSum = StringUtil.interrupt(myAssetsBean.getAlreadyProfitsSum(),0,"0");
                income.setText(StringUtil.getMoneyType(alreadyProfitsSum,false));
            }
        });


        vp_asset.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (MotionEventCompat.getActionMasked(motionEvent)){
                    case MotionEvent.ACTION_DOWN:
                        vp_asset.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        vp_asset.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        vp_asset.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

    }

    private void setAssetClick(){
        com.jakewharton.rxbinding.view.RxView.clicks(view1)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        toOtherActivity(getActivity(), MyassetsActivity.class);
                    }
                });

        com.jakewharton.rxbinding.view.RxView.clicks(view2)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        toOtherActivity(getActivity(), MyprofitsActivity.class);
                    }
                });
    }



}
