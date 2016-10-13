package com.karazam.huashanapp.finance.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;

import com.example.utils.custom.views.AutoScrollViewPager;
import com.example.utils.custom.views.ViewGroupIndicator;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentFinanceBinding;
import com.karazam.huashanapp.finance.model.databinding.FinanceEntity;
import com.karazam.huashanapp.finance.view.FinanceView;
import com.karazam.huashanapp.finance.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.finance.viewmodel.FinanceViewModel;
import com.karazam.huashanapp.finance.viewmodel.FinanceViewModelImpl;

/**
 * Created by Administrator on 2016/10/11.
 */

public class FinanceFragment extends BaseFragment implements FinanceView{

    private FragmentFinanceBinding binding;
    private FinanceEntity entity = new FinanceEntity();
    private FinanceViewModel mModel;

    private View view;

    private AutoScrollViewPager pager;

    private ViewGroupIndicator indicator;

    private int[] ids;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finance,container,false);
        view = binding.getRoot();
        mModel = new FinanceViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();


        ids = new int[]{R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image5};



        pager.setAdapter(new AutoScrollAdapter(ids,getContext()));

        pager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager pager, int position) {
                showToast("12312");
            }
        });

        indicator.setParent(pager);
        return view;
    }


    /**
     * 初始化View
     */
    private void initView() {

        pager = (AutoScrollViewPager) getView(R.id.scroll_pager,view);
        indicator = (ViewGroupIndicator) getView(R.id.scroll_pager_indicator,view);




    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("Activity-->", "onStart");
        indicator.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //    if (isPowerOff()) {
        indicator.stop();
        //    }
        Log.i("Activity-->", "onStop");

    }

}
