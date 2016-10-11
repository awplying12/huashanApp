package com.karazam.huashanapp.manage.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentManageBinding;
import com.karazam.huashanapp.manage.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.view.ManageView;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModel;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModelImpl;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ManageFragment extends BaseFragment implements ManageView{


    private View view;

    private FragmentManageBinding binding;
    private ManageEntity entity = new ManageEntity();
    private ManageViewModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage,container,false);
        view = binding.getRoot();
        mModel = new ManageViewModelImpl(entity,this,getContext(),getActivity());
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
