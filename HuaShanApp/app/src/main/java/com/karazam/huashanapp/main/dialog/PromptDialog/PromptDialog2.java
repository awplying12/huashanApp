package com.karazam.huashanapp.main.dialog.PromptDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2017/1/18.
 */

public class PromptDialog2 extends Dialog {


    private TextView text1;
    private TextView text2;
    private TextView text3;

//    private TextView bt_l;
//    private TextView bt_r;
    private TextView btn;

    private String text1Str = "";
    private String text2Str = "";
    private String text3Str = "";

//    private String btnlStr = "";
//    private String btnrStr = "";
    private String btnStr = "";

    public static final int MOD1 = 1;
    public static final int MOD2 = 2;

    private int mod = MOD1;

    public PromptDialog2(Context context) {
        super(context, R.style.Mydialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_prompt_2);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        initView();
        initMod();
        initData();
        initEvent();


    }

    /**
     *初始化界面控件
     */
    private void initView() {
        text1 = (TextView) findViewById(R.id.pro_tv_1);
        text2 = (TextView) findViewById(R.id.pro_tv_2);
        text3 = (TextView) findViewById(R.id.pro_tv_3);

//        bt_l = (TextView) findViewById(R.id.bt_pro_l);
//        bt_r = (TextView) findViewById(R.id.bt_pro_r);

        btn = (TextView) findViewById(R.id.bt_pro_btn);
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

        if(text3 != null){
            text3.setText(StringUtil.interrupt(text3Str,0,"未知"));
        }

//        if(bt_l != null){
//            bt_l.setText(StringUtil.interrupt(btnlStr,0,"未知"));
//        }
//
//        if(bt_r != null){
//            bt_r.setText(StringUtil.interrupt(btnrStr,0,"未知"));
//        }

        if(btn != null){
            btn.setText(StringUtil.interrupt(btnStr,0,"未知"));
        }
    }

    private void initEvent() {

//        bt_l.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDialoglistener != null) {
//                    mDialoglistener.onleft(v);
//                }
//            }
//        });
//
//        bt_r.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDialoglistener != null) {
//                    mDialoglistener.onRight(v);
//                }
//            }
//        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialoglistener != null) {
                    mDialoglistener.onButton(v);
                }
            }
        });

    }

    /**
     * 设置Mod
     */
    public void setMod(int mod){
        this.mod = mod;
    }
    public void initMod(){
        if(text1 == null &&text2 == null && text3 == null){
            return;
        }
        switch (mod){
            case 1:
                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    /**
     * 设置界面数据
     */
    public void setPrompt(String prompt3) {
        this.text3Str = prompt3;
    }

    public void setPrompt(String prompt1,String prompt2) {
        this.text1Str = prompt1;
        this.text2Str = prompt2;
        this.text3Str = prompt2;
    }

    public void setPrompt(String prompt1,String prompt2,String btn) {
        this.text1Str = prompt1;
        this.text2Str = prompt2;
        this.text3Str = prompt2;
//        this.btnlStr = btnL;
//        this.btnrStr = btnR;
        this.btnStr = btn;
    }

    public void setClick(String btn,OnDialogListener dialoglistener){
        this.mDialoglistener = dialoglistener;
//        this.btnlStr = btnL;
//        this.btnrStr = btnR;
        this.btnStr = btn;
    }

    private OnDialogListener mDialoglistener;

    public interface OnDialogListener{

        void onButton(View view);

    }



}
