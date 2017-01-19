package com.karazam.huashanapp.my.realname.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.example.utils.utils.CheckPhoneNumberUtil;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityUnauthorizedBinding;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameBean;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.UnauthorizedView;
import com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel.UnauthorizedViewModel;
import com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel.UnauthorizedViewModelImpl;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UnauthorizedActivity extends BaseActivity implements UnauthorizedView{

    private ActivityUnauthorizedBinding binding;
    private RealnameEntity entity = new RealnameEntity();
    private UnauthorizedViewModel mModel;

    private FloatLabeledEditText fet_name;
    private FloatLabeledEditText fet_idnum;
    private FloatLabeledEditText fet_cardnum;
    private FloatLabeledEditText fet_bank;
    private FloatLabeledEditText fet_phonenum;

    private ImageView hint1_img;
    private ImageView hint2_img;
    private ImageView hint3_img;
    private ImageView hint4_img;
    private ImageView hint5_img;

    private CheckPhoneNumberUtil  phoneUtil;

    private TextView btn_next_step;
    private CheckBox cb_agreement;

    private ImageView clean_name;
    private ImageView clean_idnum;
    private ImageView clean_cardnum;
    private ImageView clean_bank;
    private ImageView clean_phonenum;
    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unauthorized);
        mModel = new UnauthorizedViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);

        phoneUtil = new CheckPhoneNumberUtil();

    }

    @Override
    public void initView() {
        fet_name = (FloatLabeledEditText) getView(R.id.fet_name);
        fet_name.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_idnum = (FloatLabeledEditText) getView(R.id.fet_idnum);
        fet_idnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_cardnum = (FloatLabeledEditText) getView(R.id.fet_cardnum);
        fet_cardnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_bank = (FloatLabeledEditText) getView(R.id.fet_bank);
        fet_bank.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_phonenum = (FloatLabeledEditText) getView(R.id.fet_phonenum);
        fet_phonenum.setHintTextViewColor(Color.parseColor("#0894EC"));

        hint1_img = (ImageView) getView(R.id.hint1_img);
        hint2_img = (ImageView) getView(R.id.hint2_img);
        hint3_img = (ImageView) getView(R.id.hint3_img);
        hint4_img = (ImageView) getView(R.id.hint4_img);
        hint5_img = (ImageView) getView(R.id.hint5_img);

        mModel.use_name = (EditText) getView(R.id.use_name);
        mModel.id_num = (EditText) getView(R.id.id_num);
        mModel.bank = (EditText) getView(R.id.bank);
        mModel.card_num = (EditText) getView(R.id.card_num);
        mModel.phone_num = (EditText) getView(R.id.phone_num);

        btn_next_step = (TextView) getView(R.id.btn_next_step);
        cb_agreement = (CheckBox) getView(R.id.cb_agreement);

        clean_name = (ImageView) getView(R.id.clean_name);
        clean_idnum = (ImageView) getView(R.id.clean_idnum);
        clean_cardnum = (ImageView) getView(R.id.clean_cardnum);
        clean_bank = (ImageView) getView(R.id.clean_bank);
        clean_phonenum = (ImageView) getView(R.id.clean_phonenum);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkContent();
        checkButton();
        initSMSView();

    }



    /**
     * 检查内容
     */
    private boolean use_name;
    private boolean id_num;
    private boolean card_num;
    private boolean bank;
    private boolean phone_num;
    private boolean agreement;
    private void checkContent() {

        RxTextView.textChangeEvents( mModel.use_name)   //真实姓名
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint1_img.setVisibility(View.GONE);
                            clean_name.setVisibility(View.GONE);
                            use_name = false;
                        }else if(str.matches("^[\\u4e00-\\u9fa5]{2,6}$")){
                            hint1_img.setVisibility(View.VISIBLE);
                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
                            clean_name.setVisibility(View.VISIBLE);
                            use_name = true;
                        }else {
                            hint1_img.setVisibility(View.VISIBLE);
                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
                            clean_name.setVisibility(View.VISIBLE);
                            use_name = false;
                        }

                        checkButton();
                    }
                });

        RxTextView.textChangeEvents( mModel.id_num)  //身份证号
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

//        RxTextView.textChangeEvents( mModel.card_num)  //银行卡号
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//                    @Override
//                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        String str = textViewTextChangeEvent.text().toString().trim();
//
//                        if(TextUtils.isEmpty(str)){
//                            hint3_img.setVisibility(View.GONE);
//                            clean_cardnum.setVisibility(View.GONE);
//                            card_num = false;
//                        }else if(str.matches("^(\\d{15,20})$")){
//                            hint3_img.setVisibility(View.VISIBLE);
//                            hint3_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
//                            clean_cardnum.setVisibility(View.VISIBLE);
//                            card_num = true;
//                        }else {
//                            hint3_img.setVisibility(View.VISIBLE);
//                            hint3_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
//                            clean_cardnum.setVisibility(View.VISIBLE);
//                            card_num = false;
//                        }
//
//                        checkButton();
//                    }
//                });
//
//        RxTextView.textChangeEvents( mModel.bank)  //银行类
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//                    @Override
//                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        String str = textViewTextChangeEvent.text().toString().trim();
//
//                        if(TextUtils.isEmpty(str)){
//                            hint4_img.setVisibility(View.GONE);
//                            clean_bank.setVisibility(View.GONE);
//                            bank = false;
//                        }else if(str.matches("^[\\u4e00-\\u9fa5]*$")){
//                            hint4_img.setVisibility(View.VISIBLE);
//                            hint4_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
//                            clean_bank.setVisibility(View.VISIBLE);
//                            bank = true;
//                        }else {
//                            hint4_img.setVisibility(View.VISIBLE);
//                            hint4_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
//                            clean_bank.setVisibility(View.VISIBLE);
//                            bank = false;
//                        }
//
//                        checkButton();
//                    }
//                });
//
//        RxTextView.textChangeEvents( mModel.phone_num)  //电话号码
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//                    @Override
//                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        String str = textViewTextChangeEvent.text().toString().trim();
//
//                        if(TextUtils.isEmpty(str)){
//                            hint5_img.setVisibility(View.GONE);
//                            clean_phonenum.setVisibility(View.GONE);
//                            phone_num = false;
//                        }else if(phoneUtil.CheckPhoneNumber(str)){
//                            hint5_img.setVisibility(View.VISIBLE);
//                            hint5_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
//                            clean_phonenum.setVisibility(View.VISIBLE);
//                            phone_num = true;
//                        }else {
//                            hint5_img.setVisibility(View.VISIBLE);
//                            hint5_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
//                            clean_phonenum.setVisibility(View.VISIBLE);
//                            phone_num = false;
//                        }
//
//                        checkButton();
//                    }
//                });


        RxCompoundButton.checkedChanges(cb_agreement)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        agreement = aBoolean;
                        checkButton();
                    }
                });
    }

    /**
     * 检查按钮“下一步”
     */
    private void checkButton() {

//        if(use_name && id_num && card_num && bank && phone_num && agreement){
//            btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
//            btn_next_step.setClickable(true);
//
//        }else {
//            btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
//            btn_next_step.setClickable(false);
//
//        }

        if(use_name && id_num){
            btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
            btn_next_step.setClickable(true);

        }else {
            btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
            btn_next_step.setClickable(false);

        }
    }

    /**
     * 初始化短息验证VIEW
     */
    private SMSauthenticationView smsview;
    private void initSMSView() {
        smsview = new SMSauthenticationView(this);
        smsview.setView(HuaShanApplication.account,"",(ViewGroup) getView(R.id.content_pl), new SMSauthenticationView.OnAuthenticationListener() {
            @Override
            public void onLeft(View view) {
                smsview.dismiss();
            }

            @Override
            public void onRight(View view) {
                smsview.verification();
            }

            @Override
            public void onHelp(View view) {
                showToast("onHelp");
            }

            @Override
            public void onResend(View view) {

            }

            @Override
            public void onResult(boolean result) {
                if(result){
                    mModel.onAuthentication();
                }
            }
        });
        smsview.setText1("快捷支付绑定");


    }

    /**
     * 弹出短息认证
     */
    @Override
    public void addSMSView() {
        String endnum = mModel.phone_num.getText().toString().substring(7,11);
        smsview.setText2("请输入手机尾号"+endnum+"接受的验证码");
        smsview.show();
    }

    /**
     * 退出短息认证
     */
    @Override
    public void disSMSView() {
        smsview.dismiss();
    }

    /**
     * 实名成功
     */
    @Override
    public void onRealnameSuccess(RealnameBean bean) {

        if(HuaShanApplication.myInformation != null){
            HuaShanApplication.myInformation.setBaseInfo(bean.getBaseInfo());
            HuaShanApplication.setMyInformation(HuaShanApplication.myInformation);
        }


        Intent intent = new Intent(this, SetpaypsActivity.class);
        intent.putExtra("isRealName","realName");
        intent.putExtra("type","create");
        startActivity(intent);


    }

    /**
     * 实名失败
     * @param s
     */
    @Override
    public void onRealnameFaile(String s) {
            showToast(s);
    }

    /**
     * 实名错误
     * @param e
     */
    @Override
    public void onRealnameError(Throwable e) {
            showToast("网络故障！");
    }
}
