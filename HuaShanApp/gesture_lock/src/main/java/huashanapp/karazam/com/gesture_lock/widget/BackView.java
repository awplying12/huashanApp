package huashanapp.karazam.com.gesture_lock.widget;

/**
 * Created by Administrator on 2016/12/15.
 */



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;

import huashanapp.karazam.com.gesture_lock.R;


/**
 * Created by Administrator on 2016/11/29.
 */

public class BackView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView text1,text2,text3;

    private boolean isShowing = false;

    public BackView(Context context) {
        this.context = context;
    }

    private OnBackViewListener mOnBackViewListener;


    public interface OnBackViewListener{
        void onLeft(View view);

        void onRight(View view);

    }
    public View setView(ViewGroup layout,OnBackViewListener onBackViewListener){
        this.layout = layout;
        this.mOnBackViewListener = onBackViewListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_back,null);

        initView();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        view.findViewById(R.id.bt_input_l).setOnClickListener(this);
        view.findViewById(R.id.bt_input_r).setOnClickListener(this);

        text1 = (TextView) view.findViewById(R.id.pro_tv_1);
        text2 = (TextView) view.findViewById(R.id.pro_tv_2);
        text3 = (TextView) view.findViewById(R.id.pro_tv_3);
    }

    public void setText1(String str){
        if(text1 == null){
            return;
        }
        text1.setText(StringUtil.interrupt(str,0,"未知"));
    }

    public void setText2(String str){
        if(text2 == null){
            return;
        }
        text2.setText(StringUtil.interrupt(str,0,"未知"));
    }

    public void setText3(String str){
        if(text3 == null){
            return;
        }
        text3.setText(StringUtil.interrupt(str,0,"未知"));
    }


    public void show(){

        if(isShowing || view == null){
            return;
        }
        isShowing = true;
        layout.addView(view,layout.getLayoutParams());
    }

    public void dismiss() {
        if(layout == null || view == null|| !isShowing){
            return;
        }
        isShowing = false;
        layout.removeView(view);
    }

    public boolean isShowing(){
        return isShowing;
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.bt_input_l) {
            if (mOnBackViewListener == null) {
                return;
            }
            mOnBackViewListener.onLeft(view);

        } else if (i == R.id.bt_input_r) {
            if (mOnBackViewListener == null) {
                return;
            }
            mOnBackViewListener.onRight(view);

        }
    }
}

