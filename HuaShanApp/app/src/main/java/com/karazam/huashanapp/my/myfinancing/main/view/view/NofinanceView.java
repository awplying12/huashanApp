package com.karazam.huashanapp.my.myfinancing.main.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/12/5.
 */

public class NofinanceView {

    private View view;
    private Context context;
    private ViewGroup layout;

    public NofinanceView(Context context) {
        this.context = context;


    }

    public View setView(){


        view = LayoutInflater.from(context).inflate(R.layout.view_nofinance,null);

        return view;
    }


}
