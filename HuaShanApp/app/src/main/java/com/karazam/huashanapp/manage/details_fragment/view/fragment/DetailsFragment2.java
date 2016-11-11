package com.karazam.huashanapp.manage.details_fragment.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.recyclerviewpager.RecyclerViewPager.CarouselViewPager;
import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseFragment;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentDetails2Binding;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment2Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment2View;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.view.ConAdapter;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.view.ImgAdapter;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModel;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModelImpl;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment2 extends BaseFragment implements DetailsFragment2View {

    private View view;

    private FragmentDetails2Binding binding;
    private DetailsFragment2ViewModel mModel;
    private DetailsFragment2Entity entity =new DetailsFragment2Entity();

    private LinearLayout content_ll;

    private View view1;
    private View view2;
    private View view3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_2,container,false);
        view = binding.getRoot();
        mModel = new DetailsFragment2ViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);


        initView();

        setContent();
        return view;
    }



    /**
     * 初始化View
     */
    private void initView() {
        content_ll = (LinearLayout) getView(R.id.content_ll,view);

        view1 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_1,null);
        view2 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_2,null);
        view3 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_3,null);
    }

    /**
     * 设置ViewPager
     */
    private void setContent() {
        onView1();
        onView2();

        content_ll.addView(view1);
    }

    public void setCurrentItem(int position){
        content_ll.removeAllViews();
        switch (position){
            case 0:
//                view1.setLayoutParams(content_ll.getLayoutParams());
                content_ll.addView(view1);
                break;
            case 1:
                content_ll.addView(view2);
                break;
            case 2:
                content_ll.addView(view3);
                break;
            default:
                break;
        }
    }

    private void onView1(){
        CarouselViewPager recyclerView = (CarouselViewPager) view1.findViewById(R.id.img_rl);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(lm);

        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        recyclerView.setAdapter(new ImgAdapter(list,getContext()));
        RxView.of(recyclerView).bind(InvestmentdetailsActivity.project, new Rx.Action<View, Project>() {
            @Override
            public void call(View target, Project project) {

            }
        });

    }

    private void onView2(){
        RecyclerView recyclerView = (RecyclerView) view2.findViewById(R.id.con_rl);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);

        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        recyclerView.setAdapter(new ConAdapter(list,getContext()));
    }

    private void onView3(){

    }
}
