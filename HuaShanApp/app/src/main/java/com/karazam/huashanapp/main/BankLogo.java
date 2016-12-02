package com.karazam.huashanapp.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/12/2.
 */

public class BankLogo {

    public static Bitmap setLogo(Bitmap bitmap, Context context){

        View  view = LayoutInflater.from(context).inflate(R.layout.view_banklogo,null);

        ImageView img = (ImageView) view.findViewById(R.id.img_logo);
        img.setImageBitmap(bitmap);

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        view.buildDrawingCache();

        return view.getDrawingCache();
    }
}
