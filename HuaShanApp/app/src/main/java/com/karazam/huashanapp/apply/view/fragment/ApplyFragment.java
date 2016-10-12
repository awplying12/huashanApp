package com.karazam.huashanapp.apply.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.apply.model.databinding.ApplyEntity;
import com.karazam.huashanapp.apply.view.ApplyView;
import com.karazam.huashanapp.apply.viewmodel.ApplyViewModel;
import com.karazam.huashanapp.apply.viewmodel.ApplyViewModelImpl;
import com.karazam.huashanapp.databinding.FragmentApplyBinding;
import com.karazam.huashanapp.databinding.FragmentManageBinding;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ApplyFragment extends BaseFragment implements ApplyView{

    private View view;

    private FragmentApplyBinding binding;

    private ApplyEntity entity = new ApplyEntity();
    private ApplyViewModel mModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_apply,container,false);
        view = binding.getRoot();
        mModel = new ApplyViewModelImpl(entity,this,getContext(),getActivity());
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
