package com.karazam.huashanapp.home.viewmodel;

import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HomeViewModelImpl extends HomeViewModel {

    private HomeView mView;
    private HomeEntity mEntity;

    public HomeViewModelImpl(HomeView mView, HomeEntity mEntity) {
        this.mView = mView;
        this.mEntity = mEntity;
    }
}
