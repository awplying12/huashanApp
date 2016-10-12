package com.karazam.huashanapp.my.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentMyBinding;
import com.karazam.huashanapp.my.model.databinding.MyEntity;
import com.karazam.huashanapp.my.view.MyView;
import com.karazam.huashanapp.my.viewmodel.MyViewModel;
import com.karazam.huashanapp.my.viewmodel.MyViewModelImpl;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyFragment extends BaseFragment implements MyView {

    private View view;

    private MyEntity entity = new MyEntity();
    private FragmentMyBinding binding;
    private MyViewModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false);
        view = binding.getRoot();
        mModel = new MyViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);
        return view;
    }
}
