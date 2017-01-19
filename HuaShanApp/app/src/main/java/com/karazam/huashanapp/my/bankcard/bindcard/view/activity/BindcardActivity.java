package com.karazam.huashanapp.my.bankcard.bindcard.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.example.utils.utils.CheckPhoneNumberUtil;
import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityBankcardBinding;
import com.karazam.huashanapp.databinding.ActivityBindcardBinding;
import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardEntity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.BindcardView;
import com.karazam.huashanapp.my.bankcard.bindcard.viewmodel.BindcardViewModel;
import com.karazam.huashanapp.my.bankcard.bindcard.viewmodel.BindcardViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/12/30.
 */

public class BindcardActivity extends BaseActivity implements BindcardView{

    private ActivityBindcardBinding binding;
    private BindcardEntity entity = new BindcardEntity();
    private BindcardViewModel mModel;

//    private FloatLabeledEditText fet_name;
//    private FloatLabeledEditText fet_idnum;
    private FloatLabeledEditText fet_cardnum;
//    private FloatLabeledEditText fet_bank;
    private FloatLabeledEditText fet_phonenum;

//    private ImageView hint1_img;
//    private ImageView hint2_img;
    private ImageView hint3_img;
//    private ImageView hint4_img;
    private ImageView hint5_img;

    private CheckPhoneNumberUtil phoneUtil;

    private TextView btn_next_step;
    private CheckBox cb_agreement;

//    private ImageView clean_name;
//    private ImageView clean_idnum;
    private ImageView clean_cardnum;
//    private ImageView clean_bank;
    private ImageView clean_phonenum;

    private PercentFrameLayout pf_phonenum;

    private PercentFrameLayout pf_bankk;

    private TextView tv_name;
    private TextView tv_id;


    public static RxProperty<BankBean> BankBeanRX = RxProperty.create();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bindcard);
        mModel = new BindcardViewModelImpl(this,entity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
    }

    @Override
    public void dealLogicBeforeInitView() {
        phoneUtil = new CheckPhoneNumberUtil();

        mModel.flag = getIntent().getIntExtra("flag",-1);
        BankBeanRX = RxProperty.create();
    }

    @Override
    public void initView() {

        tv_name = (TextView) getView(R.id.tv_name);
        tv_id = (TextView) getView(R.id.tv_id);

        mModel.pwd_view = (PasswordView) getView(R.id.pwd_view);


//        fet_name = (FloatLabeledEditText) getView(R.id.fet_name);
//        fet_name.setHintTextViewColor(Color.parseColor("#0894EC"));
//        fet_idnum = (FloatLabeledEditText) getView(R.id.fet_idnum);
//        fet_idnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_cardnum = (FloatLabeledEditText) getView(R.id.fet_cardnum);
        fet_cardnum.setHintTextViewColor(Color.parseColor("#0894EC"));
//        fet_bank = (FloatLabeledEditText) getView(R.id.fet_bank);
//        fet_bank.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_phonenum = (FloatLabeledEditText) getView(R.id.fet_phonenum);
        fet_phonenum.setHintTextViewColor(Color.parseColor("#0894EC"));

        pf_phonenum = (PercentFrameLayout) getView(R.id.pf_phonenum);
        if(mModel.flag == 2 || mModel.flag == 3){
            pf_phonenum.setVisibility(View.GONE);
        }

        pf_bankk = (PercentFrameLayout) getView(R.id.pf_bankk);

//        hint1_img = (ImageView) getView(R.id.hint1_img);
//        hint2_img = (ImageView) getView(R.id.hint2_img);
        hint3_img = (ImageView) getView(R.id.hint3_img);
//        hint4_img = (ImageView) getView(R.id.hint4_img);
        hint5_img = (ImageView) getView(R.id.hint5_img);

//        mModel.use_name = (EditText) getView(R.id.use_name);
//        mModel.id_num = (EditText) getView(R.id.id_num);
//        mModel.bank = (EditText) getView(R.id.bank);
        mModel.card_num = (EditText) getView(R.id.card_num);
        mModel.phone_num = (EditText) getView(R.id.phone_num);

        btn_next_step = (TextView) getView(R.id.btn_next_step);
        cb_agreement = (CheckBox) getView(R.id.cb_agreement);

//        clean_name = (ImageView) getView(R.id.clean_name);
//        clean_idnum = (ImageView) getView(R.id.clean_idnum);
        clean_cardnum = (ImageView) getView(R.id.clean_cardnum);
//        clean_bank = (ImageView) getView(R.id.clean_bank);
        clean_phonenum = (ImageView) getView(R.id.clean_phonenum);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkContent();
        checkButton();
        initSMSView();
        setPasswordView();
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

//        RxTextView.textChangeEvents( mModel.use_name)   //真实姓名
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//                    @Override
//                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        String str = textViewTextChangeEvent.text().toString().trim();
//
//                        if(TextUtils.isEmpty(str)){
//                            hint1_img.setVisibility(View.GONE);
//                            clean_name.setVisibility(View.GONE);
//                            use_name = false;
//                        }else if(str.matches("^[\\u4e00-\\u9fa5]{2,6}$")){
//                            hint1_img.setVisibility(View.VISIBLE);
//                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
//                            clean_name.setVisibility(View.VISIBLE);
//                            use_name = true;
//                        }else {
//                            hint1_img.setVisibility(View.VISIBLE);
//                            hint1_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
//                            clean_name.setVisibility(View.VISIBLE);
//                            use_name = false;
//                        }
//
//                        checkButton();
//                    }
//                });

//        RxTextView.textChangeEvents( mModel.id_num)  //身份证号
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//                    @Override
//                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        String str = textViewTextChangeEvent.text().toString().trim();
//
//                        if(TextUtils.isEmpty(str)){
//                            hint2_img.setVisibility(View.GONE);
//                            clean_idnum.setVisibility(View.GONE);
//                            id_num = false;
//                        }else if(str.matches("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$")){
//                            hint2_img.setVisibility(View.VISIBLE);
//                            hint2_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
//                            clean_idnum.setVisibility(View.VISIBLE);
//                            id_num = true;
//                        }else {
//                            hint2_img.setVisibility(View.VISIBLE);
//                            hint2_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
//                            clean_idnum.setVisibility(View.VISIBLE);
//                            id_num = false;
//                        }
//
//                        checkButton();
//                    }
//                });

        RxView.of(new View(this)).bind(HuaShanApplication.baseInfoBeanRX,   new Rx.Action<View, BaseInfoBean>() {
            @Override
            public void call(View target, BaseInfoBean baseInfoBean) {

                String name = baseInfoBean.getRealname();
                tv_name.setText(StringUtil.interrupt(name,0,""));

                String id = baseInfoBean.getIdno();
                tv_id.setText(StringUtil.interrupt(id,0,""));
            }
        });

        RxTextView.textChangeEvents( mModel.card_num)  //银行卡号
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint3_img.setVisibility(View.GONE);
                            clean_cardnum.setVisibility(View.GONE);
                            card_num = false;
                        }else if(str.matches("^(\\d{15,20})$")){
                            hint3_img.setVisibility(View.VISIBLE);
                            hint3_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
                            clean_cardnum.setVisibility(View.VISIBLE);
                            card_num = true;
                        }else {
                            hint3_img.setVisibility(View.VISIBLE);
                            hint3_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
                            clean_cardnum.setVisibility(View.VISIBLE);
                            card_num = false;
                        }

                        checkButton();
                    }
                });

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

        RxTextView.textChangeEvents( mModel.phone_num)  //电话号码
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            hint5_img.setVisibility(View.GONE);
                            clean_phonenum.setVisibility(View.GONE);
                            phone_num = false;
                        }else if(phoneUtil.CheckPhoneNumber(str)){
                            hint5_img.setVisibility(View.VISIBLE);
                            hint5_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.correct_icon));
                            clean_phonenum.setVisibility(View.VISIBLE);
                            phone_num = true;
                        }else {
                            hint5_img.setVisibility(View.VISIBLE);
                            hint5_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.error_icon));
                            clean_phonenum.setVisibility(View.VISIBLE);
                            phone_num = false;
                        }

                        checkButton();
                    }
                });


        RxCompoundButton.checkedChanges(cb_agreement)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        agreement = aBoolean;
                        checkButton();
                    }
                });

        RxView.findById(this,R.id.pf_bankk).bind(BankBeanRX, new Rx.Action<View, BankBean>() {
            @Override
            public void call(View target, BankBean bankBean) {

                mModel.bankId = StringUtil.interrupt(bankBean.getId(),0,"-1");
                if(mModel.bankId.equals("-1")){
                    bank = false;
                }else {
                    bank = true;
                }


                final ImageView logo = (ImageView) target.findViewById(R.id.back_logo);
                TextView name = (TextView) target.findViewById(R.id.back_name);

                String nameStr = bankBean.getName();
                name.setText(StringUtil.interrupt(nameStr,10,""));

                RxImageLoader.getLoaderObservable(logo,StringUtil.interrupt(bankBean.getLogo(),0,"")).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        logo.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bankdef_logo));
                    }

                    @Override
                    public void onNext(Data data) {
                        if(data.bitmap == null){
                            logo.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bankdef_logo));
                        }
                    }
                });

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
        if(mModel.flag == 1){
            if(card_num && bank && phone_num && agreement){
                btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                btn_next_step.setClickable(true);

            }else {
                btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                btn_next_step.setClickable(false);

            }
        }else if(mModel.flag == 2 || mModel.flag == 3){
            if(card_num && bank && agreement){
                btn_next_step.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                btn_next_step.setClickable(true);

            }else {
                btn_next_step.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                btn_next_step.setClickable(false);

            }
        }

    }

    /**
     * 初始化短息验证VIEW
     */

    private void initSMSView() {
        mModel.smsview = new SMSauthenticationView(this);
        mModel.smsview.setView(HuaShanApplication.account,"",(ViewGroup) getView(R.id.content_pl), new SMSauthenticationView.OnAuthenticationListener() {
            @Override
            public void onLeft(View view) {
                mModel.smsview.dismiss();
            }

            @Override
            public void onRight(View view) {
                mModel.smsview.toResult();
            }

            @Override
            public void onHelp(View view) {
                showToast("onHelp");
            }

            @Override
            public void onResend(View view) {
//                showToast("onResend");
                mModel.sendSMS();
            }

            @Override
            public void onResult(boolean result) {

                if(result){
//                    mModel.onAuthentication();
                    mModel.onAddcard(true);
                }
            }
        });
        mModel.smsview.setText1("快捷支付绑定");


    }

    /**
     * 设置支付密码控件PasswordView
     */
    private void setPasswordView(){

        mModel.pwd_view.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {
//                showToast(mModel.pwd_view.getStrPassword());
                mModel.pwd_view.out();
//                dialog.show();
                mModel.upDatecard(StringUtil.interrupt(mModel.pwd_view.getStrPassword(),0,""));
            }

            @Override
            public void onBack(View v) {
                mModel.pwd_view.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });
        mModel.pwd_view.setMoney("银行卡操作");
    }

    /**
     * 弹出短息认证
     */
    @Override
    public void addSMSView() {
        String endnum = mModel.phone_num.getText().toString().substring(7,11);
        mModel.smsview.setText2("请输入手机尾号"+endnum+"接受的验证码");
        mModel.smsview.show();
    }

    /**
     * 退出短息认证
     */
    @Override
    public void disSMSView() {
        mModel.smsview.dismiss();
    }

    /**
     * 结果成功
     */
    @Override
    public void getResultSuccess(BindcardBean bean) {
//        Log.i("BindcardActivity","200");
        switch (mModel.flag){
            case 1:
                ArrayList<CardBean> cardBeens = bean.getQuickCards();
                HuaShanApplication.myInformation.setQuickCards(cardBeens);
                HuaShanApplication.quickCardsRX.set(cardBeens);


                break;
            case 2:
//                Log.i("BindcardActivity","33");
               CardBean cardBeen = bean.getWithdrawCard();
                HuaShanApplication.myInformation.setWithdrawCardl(cardBeen);
                HuaShanApplication.withdrawCarRx.set(cardBeen);

                break;
            case 3:

                CardBean cardBeen1 = bean.getWithdrawCard();
                HuaShanApplication.myInformation.setWithdrawCardl(cardBeen1);
                HuaShanApplication.withdrawCarRx.set(cardBeen1);
                break;
            default:
                break;
        }

        this.setResult(mModel.flag);
        this.finish();
    }

    /**
     * 结果失败
     * @param s
     */
    @Override
    public void getResultFail(String s) {
        showToast(s);
    }

    /**
     * 结果错误
     * @param e
     */
    @Override
    public void getResultError(Throwable e) {
        showToast("网络故障！");
    }
}
