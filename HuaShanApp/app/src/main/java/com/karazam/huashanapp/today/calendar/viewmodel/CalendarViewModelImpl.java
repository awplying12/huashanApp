package com.karazam.huashanapp.today.calendar.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.today.calendar.model.databinding.CalendarEntity;
import com.karazam.huashanapp.today.calendar.view.CalendarView;
import com.karazam.huashanapp.today.calendar.view.activity.CalendarActivity;

/**
 * Created by Administrator on 2016/11/7.
 */

public class CalendarViewModelImpl extends CalendarViewModel {

    private CalendarView mView;
    private CalendarEntity mEntity;
    private Context context;
    private CalendarActivity activity;

    public CalendarViewModelImpl(CalendarView mView, CalendarEntity mEntity, Context context, CalendarActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
