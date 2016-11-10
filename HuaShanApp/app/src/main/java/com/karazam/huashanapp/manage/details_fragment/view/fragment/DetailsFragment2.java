package com.karazam.huashanapp.manage.details_fragment.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentDetails2Binding;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment2Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment2View;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModel;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModelImpl;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment2 extends BaseFragment implements DetailsFragment2View {

    private View view;

    private FragmentDetails2Binding binding;
    private DetailsFragment2ViewModel mModel;
    private DetailsFragment2Entity entity =new DetailsFragment2Entity();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_2,container,false);
        view = binding.getRoot();
        mModel = new DetailsFragment2ViewModelImpl(entity,this,getContext(),getActivity());
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
