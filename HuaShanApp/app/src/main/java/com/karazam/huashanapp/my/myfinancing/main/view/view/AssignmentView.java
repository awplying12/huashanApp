package com.karazam.huashanapp.my.myfinancing.main.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/12/6.
 */

public class AssignmentView {

    private View view;
    private Context context;
    private ViewGroup layout;

    public AssignmentView(Context context) {
        this.context = context;
    }

    public View setView(ViewGroup layout){
        this.layout = layout;

        view = LayoutInflater.from(context).inflate(R.layout.view_assignment,null);

        initView();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
    }
}
