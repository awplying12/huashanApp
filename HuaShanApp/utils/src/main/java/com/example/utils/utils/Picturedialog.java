package com.example.utils.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.R;

/**
 * Created by Administrator on 2016/11/23.
 */

public class Picturedialog {

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView photograph;
    private TextView album;
    private TextView cancel;

    public Picturedialog(Context context) {
        this.context = context;
    }

    public interface OnPictureListener{
        void onPhotograph(View view);

        void onAlbum(View view);

        void onCancel(View view);
    }

    private OnPictureListener mOnPictureListener;



    public View setView(ViewGroup layout,OnPictureListener OnPictureListener){

        this.layout = layout;
        this.mOnPictureListener = OnPictureListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_picture,null);
        initView();
        setClick();
        return view;
    }

    private void setClick() {
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnPictureListener == null){
                    return;
                }
                mOnPictureListener.onPhotograph(view);
            }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnPictureListener == null){
                    return;
                }
                mOnPictureListener.onAlbum(view);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnPictureListener == null){
                    return;
                }
                mOnPictureListener.onCancel(view);
            }
        });
    }

    /**
     * 初始化View
     */
    private void initView() {
        photograph = (TextView) view.findViewById(R.id.photograph);
        album = (TextView) view.findViewById(R.id.album);
        cancel = (TextView) view.findViewById(R.id.cancel);
    }

    public void show(){
        if(layout == null || view == null){
            return;
        }
        layout.addView(view,layout.getLayoutParams());

    }

    public void dismiss() {
        if(layout == null || view == null){
            return;
        }
        layout.removeView(view);
    }
}
