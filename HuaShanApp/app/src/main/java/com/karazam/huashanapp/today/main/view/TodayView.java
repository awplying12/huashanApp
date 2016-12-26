package com.karazam.huashanapp.today.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.today.main.model.databinding.TodayBean;

/**
 * Created by Administrator on 2016/11/2.
 */

public interface TodayView extends BaseView {

    void getTodayDataSuccess(TodayBean bean);

    void getTodayDataFaile(String msg);
}
