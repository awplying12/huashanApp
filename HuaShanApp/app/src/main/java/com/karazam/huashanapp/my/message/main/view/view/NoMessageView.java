package com.karazam.huashanapp.my.message.main.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2017/1/25.
 */

public class NoMessageView {

    private View view;
    private Context context;
    private ViewGroup layout;

    public NoMessageView(Context context) {
        this.context = context;


    }

    public View setView(){


        view = LayoutInflater.from(context).inflate(R.layout.view_nomessage,null);

        return view;
    }
}
