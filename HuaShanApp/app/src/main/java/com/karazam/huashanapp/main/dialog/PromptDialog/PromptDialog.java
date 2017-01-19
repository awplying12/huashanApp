package com.karazam.huashanapp.main.dialog.PromptDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/11/18.
 */

public class PromptDialog extends Dialog {

    private TextView text1;
    private TextView text2;
    private TextView text3;

    private TextView bt_l;
    private TextView bt_r;


    public static final int MOD1 = 1;
    public static final int MOD2 = 2;

    private int mod = MOD1;

    private RxProperty<TextProperty> text1StrRX = RxProperty.create();
    private RxProperty<TextProperty> text2StrRX = RxProperty.create();
    private RxProperty<TextProperty> text3StrRX = RxProperty.create();

    private RxProperty<TextProperty> bt_lStrRX = RxProperty.create();
    private RxProperty<TextProperty> bt_rStrRX = RxProperty.create();

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

        bt_l = (TextView) findViewById(R.id.bt_pro_l);
        bt_r = (TextView) findViewById(R.id.bt_pro_r);


    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData(){





        RxView.of(text1).bind(text1StrRX, new Rx.Action<TextView, TextProperty>() {
            @Override
            public void call(TextView target, TextProperty textProperty) {

                if(text1 != null){
                    text1.setText(StringUtil.interrupt(textProperty.getText(),0,"未知"));
                }
            }
        });

        RxView.of(text2).bind(text2StrRX, new Rx.Action<TextView, TextProperty>() {
            @Override
            public void call(TextView target, TextProperty textProperty) {

                if(text2 != null){
                    text2.setText(StringUtil.interrupt(textProperty.getText(),0,"未知"));
                }
            }
        });

        RxView.of(text3).bind(text3StrRX, new Rx.Action<TextView, TextProperty>() {
            @Override
            public void call(TextView target, TextProperty textProperty) {

                if(text3 != null){
                    text3.setText(StringUtil.interrupt(textProperty.getText(),0,"未知"));
                }
            }
        });


        RxView.of(bt_l).bind(bt_lStrRX, new Rx.Action<TextView, TextProperty>() {
            @Override
            public void call(TextView target, TextProperty textProperty) {

                if(bt_l != null){
                    bt_l.setText(StringUtil.interrupt(textProperty.getText(),0,"未知"));
                }
            }
        });

        RxView.of(bt_r).bind(bt_rStrRX, new Rx.Action<TextView, TextProperty>() {
            @Override
            public void call(TextView target, TextProperty textProperty) {

                if(bt_r != null){
                    bt_r.setText(StringUtil.interrupt(textProperty.getText(),0,"未知"));
                }
            }
        });
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
//        this.text3Str = prompt3;
        this.text3StrRX.set(new TextProperty(prompt3));
    }

    public void setPrompt(String prompt1,String prompt2) {
//        this.text1Str = prompt1;
        this.text1StrRX.set(new TextProperty(prompt1));
//        this.text2Str = prompt2;
        this.text2StrRX.set(new TextProperty(prompt2));
//        this.text3Str = prompt2;
        this.text3StrRX.set(new TextProperty(prompt2));
    }

    public void setPrompt(String prompt1,String prompt2,String btnL,String btnR) {
//        this.text1Str = prompt1;
        this.text1StrRX.set(new TextProperty(prompt1));
//        this.text2Str = prompt2;
        this.text2StrRX.set(new TextProperty(prompt2));
//        this.text3Str = prompt2;
        this.text3StrRX.set(new TextProperty(prompt2));
//        this.btnlStr = btnL;
        this.bt_lStrRX.set(new TextProperty(btnL));
//        this.btnrStr = btnR;
        this.bt_rStrRX.set(new TextProperty(btnL));
    }

    public void setClick(String btnL,String btnR,OnDialogListener dialoglistener){
        this.mDialoglistener = dialoglistener;
//        this.btnlStr = btnL;
        this.bt_lStrRX.set(new TextProperty(btnL));
//        this.btnrStr = btnR;
        this.bt_rStrRX.set(new TextProperty(btnR));
    }

    private OnDialogListener mDialoglistener;

    public interface OnDialogListener{
        void onleft(View view);

        void onRight(View view);
    }



}
