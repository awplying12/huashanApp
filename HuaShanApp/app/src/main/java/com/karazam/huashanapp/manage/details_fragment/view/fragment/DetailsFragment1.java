package com.karazam.huashanapp.manage.details_fragment.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentDetails1Binding;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment1Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment1View;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_1.DetailsFragment1ViewModel;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_1.DetailsFragment1ViewModelImpl;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment1 extends BaseFragment implements DetailsFragment1View {

    private View view;

    private FragmentDetails1Binding binding;
    private DetailsFragment1Entity entity = new DetailsFragment1Entity();

    private DetailsFragment1ViewModel mModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_1,container,false);
        view = binding.getRoot();
        mModel = new DetailsFragment1ViewModelImpl(this,entity,getContext(),getActivity());
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
