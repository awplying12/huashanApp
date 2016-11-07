package com.karazam.huashanapp.today.calendar.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityCalendarBinding;
import com.karazam.huashanapp.today.calendar.model.databinding.CalendarEntity;
import com.karazam.huashanapp.today.calendar.view.CalendarView;
import com.karazam.huashanapp.today.calendar.viewmodel.CalendarViewModel;
import com.karazam.huashanapp.today.calendar.viewmodel.CalendarViewModelImpl;

/**
 * Created by Administrator on 2016/11/7.
 */

public class CalendarActivity extends BaseActivity implements CalendarView {

    private ActivityCalendarBinding binding;
    private CalendarEntity entity = new CalendarEntity();
    private CalendarViewModel mModel;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        mModel = new CalendarViewModelImpl(this,entity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

    }
}
