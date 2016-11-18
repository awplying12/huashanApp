package com.karazam.huashanapp.main.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/11/18.
 */

public class PromptDialog extends Dialog {

    private TextView text1;
    private TextView text2;

    private TextView bt_l;
    private TextView bt_r;

    private String text1Str = "";
    private String text2Str = "";

    private String btnlStr = "";
    private String btnrStr = "";

    public PromptDialog(Context context) {
        super(context, R.style.Mydialog);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_prompt);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        initView();
        initData();
        initEvent();


    }

    /**
     *初始化界面控件
     */
    private void initView() {
        text1 = (TextView) findViewById(R.id.pro_tv_1);
        text2 = (TextView) findViewById(R.id.pro_tv_2);

        bt_l = (TextView) findViewById(R.id.bt_pro_l);
        bt_r = (TextView) findViewById(R.id.bt_pro_r);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData(){
        if(text1 != null){
            text1.setText(StringUtil.interrupt(text1Str,0,"未知"));
        }

        if(text2 != null){
            text2.setText(StringUtil.interrupt(text2Str,0,"未知"));
        }

        if(bt_l != null){
            bt_l.setText(StringUtil.interrupt(btnlStr,0,"未知"));
        }

        if(bt_r != null){
            bt_r.setText(StringUtil.interrupt(btnrStr,0,"未知"));
        }
    }

    private void initEvent() {

        bt_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialoglistener != null) {
                    mDialoglistener.onleft(v);
                }
            }
        });

        bt_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialoglistener != null) {
                    mDialoglistener.onRight(v);
                }
            }
        });
    }

    /**
     * 设置界面数据
     */
    public void setPrompt(String prompt1,String prompt2) {
        this.text1Str = prompt1;
        this.text2Str = prompt2;
    }

    public void setClick(String btnL,String btnR,OnDialogListener dialoglistener){
        this.mDialoglistener = dialoglistener;
        this.btnlStr = btnL;
        this.btnrStr = btnR;
    }

    private OnDialogListener mDialoglistener;

    public interface OnDialogListener{
        void onleft(View view);

        void onRight(View view);
    }



}
