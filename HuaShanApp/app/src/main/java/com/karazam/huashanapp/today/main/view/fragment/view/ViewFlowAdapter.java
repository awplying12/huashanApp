package com.karazam.huashanapp.today.main.view.fragment.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utils.base.BaseBaseAdapter;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/12/18.
 */

public class ViewFlowAdapter extends BaseBaseAdapter<Integer>{
    public ViewFlowAdapter(Context context, ArrayList<Integer> list) {
        super(context, list);
    }

    @Override
    public View getViewBase(int position, View convertView, ViewGroup parent) {
       final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = holder.imageView = new ImageView(getContext());
            holder.imageView
                    .setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageDrawable(getContext().getResources().getDrawable(getList().get(position)));

        return convertView;
    }

    public class ViewHolder{
        private ImageView imageView;

    }

}
