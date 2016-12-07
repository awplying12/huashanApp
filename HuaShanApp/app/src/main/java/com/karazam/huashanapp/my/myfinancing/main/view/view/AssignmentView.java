package com.karazam.huashanapp.my.myfinancing.main.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.financialproject.FinancialInformation;
import com.karazam.huashanapp.main.financialproject.FinancialProject;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/6.
 */

public class AssignmentView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView btn_transfer;
    private ImageView btn_cancel;

    private CheckBox cb_agreement;

    public interface OnAssignmentViewListener{
        void onTransfer();

        void onCancel();
    }

    private OnAssignmentViewListener mOnAssignmentViewListener;

    public AssignmentView(Context context) {
        this.context = context;
    }

    public View setView(ViewGroup layout,OnAssignmentViewListener onAssignmentViewListener){
        this.layout = layout;
        this.mOnAssignmentViewListener = onAssignmentViewListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_assignment,null);

        initView();
        initLayout();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        btn_transfer = (TextView) view.findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(this);

        btn_cancel = (ImageView) view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        cb_agreement = (CheckBox) view.findViewById(R.id.cb_agreement);
    }

    public void show(){
        if(view == null){
            return;
        }
        layout.addView(view,layout.getLayoutParams());
        init();
    }

    public void dismiss() {
        if(layout == null || view == null){
            return;
        }
        layout.removeView(view);
    }

    /**
     * 标信息
     */
    private RxProperty<FinancialInformation> Information = RxProperty.create();
    public void setFinancialInformation(FinancialInformation information){
        Information.set(information);
    }

    /**
     * 初始化界面
     */
    private int background = R.drawable.btn_bg_img_0894ec_5dp;


    private void initLayout(){

        RxView.of(view).bind(Information, new Rx.Action<View, FinancialInformation>() {
            @Override
            public void call(View target, FinancialInformation financialInformation) {

                if(financialInformation.isState()){
                    btn_transfer.setText("确认转让");
                    background = R.drawable.btn_bg_img_0894ec_5dp;
                }else {
                    btn_transfer.setText("确认撤销");
                    background = R.drawable.btn_bg_img_ff5722_5dp;
                }

            }
        });

        RxCompoundButton.checkedChanges(cb_agreement)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            btn_transfer.setClickable(true);
                            btn_transfer.setBackgroundResource(background);
                        }else {
                            btn_transfer.setClickable(false);
                            btn_transfer.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                        }
                    }
                });

    }

    private void init(){
        cb_agreement.setChecked(false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_transfer:
                if (mOnAssignmentViewListener == null){
                    return;
                }
                mOnAssignmentViewListener.onTransfer();
                break;
            case R.id.btn_cancel:
                if(mOnAssignmentViewListener == null){
                    return;
                }
                mOnAssignmentViewListener.onCancel();
                break;
            default:
                break;
        }
    }
}
