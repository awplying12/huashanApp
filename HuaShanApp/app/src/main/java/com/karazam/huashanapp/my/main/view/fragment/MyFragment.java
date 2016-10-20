package com.karazam.huashanapp.my.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.example.utils.custom.RecyclerViewItemDecoration;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentMyBinding;
import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.my.main.view.fragment.view.MyInformationAdapter;
import com.karazam.huashanapp.my.main.view.fragment.view.MyInformationModel;
import com.karazam.huashanapp.my.main.viewmodel.MyViewModel;
import com.karazam.huashanapp.my.main.viewmodel.MyViewModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyFragment extends BaseFragment implements MyView {

    private View view;

    private MyEntity entity = new MyEntity();
    private FragmentMyBinding binding;
    private MyViewModel mModel;

    private RecyclerView myInformation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false);
        view = binding.getRoot();
        mModel = new MyViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);

        initView();
        setMyInformationlayout();

        return view;
    }

    /**
     * 设置我的信息布局内容
     */
    private void setMyInformationlayout() {

        GridLayoutManager mgr=new GridLayoutManager(getActivity(),3);   //一行3个
        myInformation.setLayoutManager(mgr);

        final ArrayList<MyInformationModel> models = new ArrayList<>();
        models.add(new MyInformationModel(0,"安全中心"));
        models.add(new MyInformationModel(0,"交易记录"));
        models.add(new MyInformationModel(0,"借款管理"));
        models.add(new MyInformationModel(0,"投资管理"));
        models.add(new MyInformationModel(0,"银行卡管理"));
        models.add(new MyInformationModel(0,"推荐管理"));
        models.add(new MyInformationModel(0,"我的优惠劵"));
        models.add(new MyInformationModel(-1,"-1"));
        models.add(new MyInformationModel(-1,"-1"));

        MyInformationAdapter adapter = new MyInformationAdapter(models,getContext(),myInformation);
        myInformation.setAdapter(adapter);
        myInformation.addItemDecoration(new RecyclerViewItemDecoration(
                RecyclerViewItemDecoration.MODE_GRID, "#adadad",1,0,0));

        adapter.setmOnItemClickListener(new MyInformationAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        mModel.toSecurityCenter(null);
                        break;
                    case 1:
                        mModel.toTransactionRecord(null);
                        break;
                    case 2:
                        mModel.toLoanManagement(null);
                        break;
                    case 3:
                        mModel.toInvestmentManagement(null);
                        break;
                    case 4:
                        mModel.toBankcardManagement(null);
                        break;
                    case 5:
                        mModel.toRecommendedManagement(null);
                        break;
                    case 6:
                        mModel.toDiscountCoupons(null);
                        break;
                    default:
                        break;

                }
            }
        });
    }

    /**
     * 初始化View
     */
    private void initView() {

        myInformation = (RecyclerView) getView(R.id.rl_my_information,view);
    }
}
