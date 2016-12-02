package com.karazam.huashanapp.my.recharge.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargeBinding;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModel;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModelImpl;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.activity.RechargedetailsActivity;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RechargeActivity extends BaseActivity implements RechargeView {

    private ActivityRechargeBinding binding;
    private RechargeViewModel mModel;
    private RechargeEntity entity = new RechargeEntity();


    private TextView next_step;

    private TextView hint_tv;

    private PromptDialog dialog;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recharge);
        mModel = new RechargeViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        mModel.ed_money = (EditText) getView(R.id.ed_money);
        hint_tv = (TextView) getView(R.id.hint_tv);

        next_step = (TextView) getView(R.id.next_step);

        dialog = new PromptDialog(this);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkContent();



    }

    /**
     * 检查内容
     */
    private boolean moneny = false;
    private void checkContent() {

        RxTextView.textChangeEvents(mModel.ed_money)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint_tv.setVisibility(View.GONE);
                            moneny = false;
                        }else if(!TextUtils.isEmpty(str) && Double.parseDouble(str) < 1){
                            hint_tv.setVisibility(View.VISIBLE);
                            hint_tv.setText(Html.fromHtml("<font color='#ff0000'>充值金额有误"));
                            moneny = false;
                        }else {
                            hint_tv.setVisibility(View.GONE);
                            moneny = true;
                        }

                        checkButton();
                    }
                });

    }

    /**
     * 检查按钮“下一步”
     */
    private void checkButton() {

        if(moneny){
            next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
            next_step.setClickable(true);

        }else {
            next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
            next_step.setClickable(false);
        }

    }


    /**
     * 充值成功
     */
    @Override
    public void rechargeSuccess() {
        setDialog("购买成功！",
                Html.fromHtml("<font color='#00ff00'>查看详情").toString(),
                Html.fromHtml("<font color='#ff0000'>继续充值").toString());
        dialog.show();
    }

    /**
     * 充值失败
     */
    @Override
    public void rechargeFail() {
        setDialog("购买失败！",
                Html.fromHtml("<font color='#00ff00'>查看详情").toString(),
                Html.fromHtml("<font color='#ff0000'>重新充值").toString());
        dialog.show();
    }

    /**
     * 投资后的提示Dialog
     */
    public void setDialog(String str1,String str2,String str3){
        dialog.setPrompt("",str1);
        dialog.setMod(PromptDialog.MOD2);
        dialog.setClick(str2,str3, new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                toOtherActivity(RechargeActivity.this, RechargedetailsActivity.class);
            }

            @Override
            public void onRight(View view) {
                showToast("继续购买");
                dialog.dismiss();
                mModel.ed_money.setText("");
            }
        });
    }
}
