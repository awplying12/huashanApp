package com.karazam.huashanapp.finance.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentFinanceBinding;
import com.karazam.huashanapp.finance.model.databinding.FinanceEntity;
import com.karazam.huashanapp.finance.view.FinanceView;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finance,container,false);
        view = binding.getRoot();
        mModel = new FinanceViewModelImpl(entity,this,getContext());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();

        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
    }
}
