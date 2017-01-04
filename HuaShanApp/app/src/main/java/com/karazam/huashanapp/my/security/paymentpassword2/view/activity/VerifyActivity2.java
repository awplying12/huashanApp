package com.karazam.huashanapp.my.security.paymentpassword2.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityVerify2Binding;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView2;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel2.VerifyViewModel2;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel2.VerifyViewModelImpl2;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyActivity2 extends BaseActivity implements VerifyView2 {

    private ActivityVerify2Binding binding;
    private VerifyEntity entity = new VerifyEntity();
    private VerifyViewModel2 mModel;

    private FloatLabeledEditText fet_cardnum;
    private FloatLabeledEditText fet_idnum;

    private ImageView clean_card;
    private ImageView clean_idnum;

    private ImageView hint1_img;
    private ImageView hint2_img;

    private TextView btn_next_step;

    private PercentFrameLayout pf_bankcard;
    private PercentFrameLayout pf_cardnum;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify2);
        mModel = new VerifyViewModelImpl2(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);
    }

    @Override
    public void initView() {
        mModel.ed_card = (EditText) getView(R.id.ed_cardnum);
        mModel.ed_id = (EditText) getView(R.id.ed_idnum);

        fet_cardnum = (FloatLabeledEditText) getView(R.id.fet_cardnum);
        fet_cardnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_idnum = (FloatLabeledEditText) getView(R.id.fet_idnum);
        fet_idnum.setHintTextViewColor(Color.parseColor("#0894EC"));

        clean_card = (ImageView) getView(R.id.clean_card);
        clean_idnum = (ImageView) getView(R.id.clean_idnum);

        hint1_img = (ImageView) getView(R.id.hint1_img);
        hint2_img = (ImageView) getView(R.id.hint2_img);

        btn_next_step = (TextView) getView(R.id.next_step);

        pf_bankcard = (PercentFrameLayout) getView(R.id.pf_bankcard);
        pf_cardnum = (PercentFrameLayout) getView(R.id.pf_cardnum);
    }

    @Override
    public void dealLogicAfterInitView() {

        if(HuaShanApplication.myInformation.getQuickCards().size() == 0 && HuaShanApplication.myInformation.getWithdrawCardl().getBankCardId() == null){
            pf_bankcard.setVisibility(View.GONE);
            pf_cardnum.setVisibility(View.GONE);
            mModel.hasCard = false;
        }else {
            pf_bankcard.setVisibility(View.VISIBLE);
            pf_cardnum.setVisibility(View.VISIBLE);

            mModel.hasCard = true;
        }

        checkContent();
    }

    /**
     * 检验内容
     */
    private boolean card_num = false;
    private boolean id_num = false;

    private void checkContent() {

        RxTextView.textChangeEvents(mModel.ed_card)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint1_img.setVisibility(View.GONE);
                            clean_card.setVisibility(View.GONE);
                            card_num = false;
                        }else if(str.matches("^(\\d{15,20})$")){
                            hint1_img.setVisibility(View.VISIBLE);
                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
                            clean_card.setVisibility(View.VISIBLE);
                            card_num = true;
                        }else {
                            hint1_img.setVisibility(View.VISIBLE);
                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
                            clean_card.setVisibility(View.VISIBLE);
                            card_num = false;
                        }

                        checkButton();
                    }
                });

        RxTextView.textChangeEvents(mModel.ed_id)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint2_img.setVisibility(View.GONE);
                            clean_idnum.setVisibility(View.GONE);
                            id_num = false;
                        }else if(str.matches("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$")){
                            hint2_img.setVisibility(View.VISIBLE);
                            hint2_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
                            clean_idnum.setVisibility(View.VISIBLE);
                            id_num = true;
                        }else {
                            hint2_img.setVisibility(View.VISIBLE);
                            hint2_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
                            clean_idnum.setVisibility(View.VISIBLE);
                            id_num = false;
                        }

                        checkButton();
                    }
                });
    }

    /**
     * 检查按钮“下一步”
     */
    private void checkButton() {
        if(mModel.hasCard) {

            if (id_num && card_num) {
                btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                btn_next_step.setClickable(true);

            } else {
                btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                btn_next_step.setClickable(false);

            }

        } else {

            if (id_num ) {
                btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                btn_next_step.setClickable(true);

            } else {
                btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                btn_next_step.setClickable(false);

            }

        }
    }
}
