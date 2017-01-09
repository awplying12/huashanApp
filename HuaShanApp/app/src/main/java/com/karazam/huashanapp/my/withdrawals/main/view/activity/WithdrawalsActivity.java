package com.karazam.huashanapp.my.withdrawals.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityWithdrawalsBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity.WithdrawalsdetailsActivity;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsEntity;
import com.karazam.huashanapp.my.withdrawals.main.view.WithdrawalsView;
import com.karazam.huashanapp.my.withdrawals.main.viewmodel.WithdrawalsViewModel;
import com.karazam.huashanapp.my.withdrawals.main.viewmodel.WithdrawalsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/30.
 */

public class WithdrawalsActivity extends BaseActivity implements WithdrawalsView {

    private ActivityWithdrawalsBinding binding;
    private WithdrawalsViewModel mModel;
    private WithdrawalsEntity entity = new WithdrawalsEntity();


    private TextView avail_moneny;
    private TextView btn_withdrawals;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawals);
        mModel = new WithdrawalsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        mModel.ed_moneny = (EditText) getView(R.id.ed_moneny);
        mModel.pwd_view = (PasswordView) getView(R.id.pwd_view);
        avail_moneny = (TextView) getView(R.id.avail_moneny);
        btn_withdrawals = (TextView) getView(R.id.btn_withdrawals);

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
        setPasswordView();

    }

    /**
     * 设置界面
     */

    private void setLayout() {

        RxView.of(avail_moneny).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<TextView, MyAssetsBean>() {
            @Override
            public void call(TextView target, MyAssetsBean myAssetsBean) {

                String availStr = StringUtil.interrupt(myAssetsBean.getAvailable(),0,"0");
                mModel.avail = Double.parseDouble(availStr);
                target.setText("可用余额"+availStr+"元");


                checkContent(mModel.avail);
            }
        });

        RxView.findById(this,R.id.card_pf).bind(HuaShanApplication.withdrawCarRx, new Rx.Action<View, CardBean>() {
            @Override
            public void call(View target, CardBean cardBean) {
                mModel.card = cardBean;

                ImageView pay_img = (ImageView) target.findViewById(R.id.pay_img);
                TextView pay_method = (TextView) target.findViewById(R.id.pay_method);
                TextView pay_content = (TextView) target.findViewById(R.id.pay_content);

                String url = cardBean.getBankLogo();
                RxImageLoader.getLoaderObservable(pay_img,url).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Data data) {

                    }
                });

                String bankName = StringUtil.interrupt(cardBean.getBankName(),6,"");
                String cardNo = StringUtil.interrupt(cardBean.getCardNo(),0,"");
                if(!cardNo.equals("")){
                    cardNo = "(尾号"+cardNo.substring(8,12)+")";
                }
                pay_method.setText(bankName+cardNo);
            }
        });
    }



    /**
     * 检验内容
     */
    private boolean moneny = false;

    private void checkContent(final double avail) {

        RxTextView.textChangeEvents(mModel.ed_moneny)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            moneny = false;
                        } else if(str.equals("0")||str.equals("0.0")||str.equals("0.00")){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>提价金额不能为或是0"));
                            moneny = false;
                        }else if(!TextUtils.isEmpty(str) && Double.parseDouble(str) > avail){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>金额已超过可提现余额"));
                            moneny = false;
                        }else if(!TextUtils.isEmpty(str) && Double.parseDouble(str) < 50){
                            avail_moneny.setText(Html.fromHtml("<font color='#ff0000'>提现金额不能低于50"));
                            moneny = false;
                        }else {
                            avail_moneny.setText("可用余额"+avail+"元");
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
            btn_withdrawals.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
            btn_withdrawals.setClickable(true);

        }else {
            btn_withdrawals.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
            btn_withdrawals.setClickable(false);

        }
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
                mModel.toWithdrawals();
//                toOtherActivity(WithdrawalsActivity.this, WithdrawalsdetailsActivity.class);
            }

            @Override
            public void onBack(View v) {
                mModel.pwd_view.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });
    }

    /**
     * 提现成功
     * @param detailsId
     */
    @Override
    public void withdrawalsSuccess(String detailsId) {
        showToast("ok");
    }

    /**
     * 提现失败
     * @param s
     */
    @Override
    public void withdrawalsFail(String s) {
        showToast(s);
    }

    /**
     * 提现错误
     * @param e
     */
    @Override
    public void withdrawalsError(Throwable e) {
        showToast("网络故障!");
    }
}
