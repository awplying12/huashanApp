package com.karazam.huashanapp.apply.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.apply.view.ApplyView;
import com.karazam.huashanapp.databinding.FragmentApplyBinding;
import com.karazam.huashanapp.databinding.FragmentManageBinding;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ApplyFragment extends BaseFragment implements ApplyView{

    private View view;

    private FragmentApplyBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view;
    }
}
