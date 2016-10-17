package com.karazam.huashanapp.manage.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentManageBinding;
import com.karazam.huashanapp.manage.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.view.ManageView;
import com.karazam.huashanapp.manage.view.view.TitleBarAdapter;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModel;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ManageFragment extends BaseFragment implements ManageView{


    private View view;

    private FragmentManageBinding binding;
    private ManageEntity entity = new ManageEntity();
    private ManageViewModel mModel;

    private RecyclerView titlebar_rl;
    private TitleBarAdapter titlebarAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage,container,false);
        view = binding.getRoot();
        mModel = new ManageViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();
        setTitlebBar();

        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        titlebar_rl = (RecyclerView) getView(R.id.manage_titlebar_rl,view);
        LinearLayoutManager  lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        titlebar_rl.setLayoutManager(lm);
    }

    /**
     * 设置标题栏
     */
    private void setTitlebBar() {
        ArrayList<String> list = new ArrayList<>();
        list.add("信用标");
        list.add("担保标");
        list.add("债务标");
        list.add("转让专区");

        titlebarAdapter = new TitleBarAdapter(list,getContext());
        titlebar_rl.setAdapter(titlebarAdapter);

        titlebarAdapter.setmOnItemClickListener(new TitleBarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0: //信用标
                        break;
                    case 1: //担保标
                        break;
                    case 2: //债务标
                        break;
                    case 3: //转让专区
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
