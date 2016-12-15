package com.karazam.huashanapp.manage.purchase.view.activity;

import android.databinding.DataBindingUtil;
import android.support.percent.PercentRelativeLayout;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityPurchaseBinding;
import com.karazam.huashanapp.main.Method;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseEntity;
import com.karazam.huashanapp.manage.purchase.view.PurchaseView;
import com.karazam.huashanapp.manage.purchase.viewmodel.PurchaseViewModel;
import com.karazam.huashanapp.manage.purchase.viewmodel.PurchaseViewModelImpl;
import com.karazam.huashanapp.my.transactiondetails.investment.view.activity.InvestmentActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PurchaseActivity extends BaseActivity implements PurchaseView{

    private ActivityPurchaseBinding binding;
    private PurchaseEntity entity = new PurchaseEntity();
    private PurchaseViewModel mModel;

    private EditText ed_amountofmoney;
    private TextView bt_purchase;
    private TextView tv_prompt;
    private CheckBox cb_agreement;

    private PercentRelativeLayout purchase_pl;

    private Project project;

    private PasswordView passwordView;

    private SMSauthenticationView sms;
    private View smsView;

    private PromptDialog dialog;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase);
        mModel = new PurchaseViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {
        project= new Project();
        project.setTitle("产融贷CR216001-30");
        project.setInterestRate("0.086");
        project.setPeriod("6");
        praject.set(project);
    }

    @Override
    public void initView() {
        ed_amountofmoney = (EditText) getView(R.id.ed_amountofmoney);
        bt_purchase = (TextView) getView(R.id.bt_purchase);
        tv_prompt = (TextView) getView(R.id.tv_prompt);
        cb_agreement = (CheckBox) getView(R.id.cb_agreement);
        passwordView = (PasswordView) getView(R.id.pwd_view);
        purchase_pl = (PercentRelativeLayout) getView(R.id.purchase_pl);
        sms = new SMSauthenticationView(this);
        dialog = new PromptDialog(this);


    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
        checkContent();
        setPasswordView();
        setSMSView();
        setDialog();
    }

    /**
     * 设置界面
     */
    private RxProperty<Project> praject = RxProperty.create();
    private void setLayout() {
        RxView.findById(this,R.id.content_pl).bind(praject, new Rx.Action<View, Project>() {
            @Override
            public void call(View target, Project project) {
                TextView det_name = (TextView) target.findViewById(R.id.det_name);
                TextView det_content = (TextView) target.findViewById(R.id.det_content);

                det_name.setText(StringUtil.interrupt(project.getTitle(),0,"未知"));

                String interestRate = (Integer.parseInt(StringUtil.interrupt(project.getInterestRate(),0,"0"))*100)+"";
                String period = StringUtil.interrupt(project.getPeriod(),0,"未知");
                String periodUnit = project.getPeriodUnit().equals("天")? project.getPeriodUnit():"个月";
                det_content.setText("年化收益率"+interestRate+"%"+"       |       "+"投资期限"+period+periodUnit);

            }
        });

        RxView.findById(this,R.id.pay_pl).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
            @Override
            public void call(View target, UserInformation userInformation) {


                TextView pay_method = (TextView) target.findViewById(R.id.pay_method);
                TextView pay_content = (TextView) target.findViewById(R.id.pay_content);
                ImageView pay_img = (ImageView) target.findViewById(R.id.pay_img);

//                String paymentmod = StringUtil.interrupt(HuaShanApplication.paymentmod,0,"");

                String paymentmod = StringUtil.interrupt(userInformation.getPaymentmod(),0,"");

                if(paymentmod.equals("bankCard")){
                    pay_img.setImageDrawable(getResources().getDrawable(R.drawable.zgyh_icon));

                    String bankCard = StringUtil.interrupt(userInformation.getBankCard(),0,"未知");
                    pay_method.setText(bankCard);

                    String cardInformation = StringUtil.interrupt(userInformation.getCardInformation(),0,"未知");
                    pay_content.setText(cardInformation);

                }else {
                    pay_img.setImageDrawable(getResources().getDrawable(R.drawable.zhye_icon));

                    pay_method.setText("账户余额");

                    String userbalance = StringUtil.interrupt(userInformation.getUserbalance(),0,"0.00");
                    pay_content.setText("可用余额 "+userbalance);
                }
            }
        });

    }

    /**
     * 检查输入的金额,是否可购买
     */
    private String num = "";
    private boolean conOK = false;
    private boolean agreementOK = false;
    private void checkContent(){

        RxTextView.textChangeEvents(ed_amountofmoney)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            tv_prompt.setText("");
                            conOK = false;
                        }else if(!str.matches("^[0-9]*$")){
                            tv_prompt.setText("请输入正确的金额");
                            conOK = false;
                        }else if(Integer.parseInt(str)%100 != 0){
                            tv_prompt.setText("请输入100的整数的金额");
                            conOK = false;
                        }else {
                            num = Method.calculateIncome(Integer.parseInt(str),project);
                            tv_prompt.setText(Html.fromHtml("预计收益为"+num+"元"));
                            conOK = true;
                        }

                        isClickable();
                    }
                });

        RxCompoundButton.checkedChanges(cb_agreement)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        agreementOK = aBoolean;
                        isClickable();
                    }
                });
    }

    /**
     * 可以购买
     */
    private void isClickable(){
        if(conOK && agreementOK){
            bt_purchase.setClickable(true);
            bt_purchase.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
        }else {
            bt_purchase.setClickable(false);
            bt_purchase.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
        }
    }

    /**
     * 设置支付密码控件PasswordView
     */
    private void setPasswordView(){

        passwordView.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {
                showToast(passwordView.getStrPassword());
                passwordView.out();
                dialog.show();

            }

            @Override
            public void onBack(View v) {
                passwordView.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });
    }

    /**
     * 弹起支付密码控件
     */
    @Override
    public void showPasswordView() {
        if(passwordView == null){
            return;
        }
        String money = ed_amountofmoney.getText().toString();
            passwordView.setMoney(money);
            passwordView.show();

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 设置短息认证控件
     */
    private void setSMSView(){
//        smsView = sms.getView();
        sms.setView(purchase_pl, new SMSauthenticationView.OnAuthenticationListener() {

            @Override
            public void onLeft(View view) {
                showToast("onleft");
                sms.dismiss();
            }

            @Override
            public void onRight(View view) {
                showToast("onRight");
                sms.verification();
            }
            @Override
            public void onHelp(View view) {
                showToast("onhelp");
            }

            @Override
            public void onResult(boolean result) {
                if(result){
                    sms.dismiss();

                    dialog.show();
                }
            }
        });
    }

    /**
     * 弹出短息认证控件
     */
    @Override
    public void addSMSView(){
        String money = ed_amountofmoney.getText().toString();
        sms.setText1("支付"+money+"元");
        sms.setText2("请输入手机尾号5618接受的验证码");
        sms.show();


//        imm.showSoftInput(ed_amountofmoney,InputMethodManager.SHOW_FORCED);
    }

    /**
     * 投资后的提示Dialog
     */
    public void setDialog(){
        dialog.setPrompt("","购买成功！");
        dialog.setMod(PromptDialog.MOD2);
        dialog.setClick("查看详情","继续购买", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                showToast("查看详情");
                toOtherActivity(PurchaseActivity.this, InvestmentActivity.class);
            }

            @Override
            public void onRight(View view) {
                showToast("继续购买");
                dialog.dismiss();
                ed_amountofmoney.setText("");
            }
        });
    }
}
