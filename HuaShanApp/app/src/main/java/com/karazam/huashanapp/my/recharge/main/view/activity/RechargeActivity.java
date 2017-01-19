package com.karazam.huashanapp.my.recharge.main.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargeBinding;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.dialog.PromptDialog.PromptDialog;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModel;
import com.karazam.huashanapp.my.recharge.main.viewmodel.RechargeViewModelImpl;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.activity.RechargedetailsActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

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
    private PromptDialog dialogFail;

    public static RxProperty<CardBean> cardBeanRx;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recharge);
        mModel = new RechargeViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        cardBeanRx = RxProperty.create();
        mModel.card = HuaShanApplication.myInformation.getQuickCards().get(0);
        cardBeanRx.set(mModel.card);
    }

    @Override
    public void initView() {
        mModel.ed_money = (EditText) getView(R.id.ed_money);
        hint_tv = (TextView) getView(R.id.hint_tv);

        next_step = (TextView) getView(R.id.next_step);

        dialog = new PromptDialog(this);
        dialogFail = new PromptDialog(this);

    }

    @Override
    public void dealLogicAfterInitView() {
        checkContent();

        setDialog();
        setDialogFail();
        
    }

    /**
     * 检查内容
     */
    private boolean moneny = false;
    private void checkContent() {


        RxView.findById(this,R.id.card_pf).bind(cardBeanRx, new Rx.Action<View, CardBean>() {
            @Override
            public void call(View target, CardBean cardBean) {
                mModel.card = cardBean;

                final ImageView pay_img = (ImageView) target.findViewById(R.id.pay_img);
                TextView pay_method = (TextView) target.findViewById(R.id.pay_method);
                TextView pay_content = (TextView) target.findViewById(R.id.pay_content);

                String url = cardBean.getBankLogo();
                RxImageLoader.getLoaderObservable(pay_img,url).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        pay_img.setImageDrawable(getResources().getDrawable(R.drawable.bankdef_logo));
                    }

                    @Override
                    public void onNext(Data data) {
                        if(data.bitmap == null){
                            pay_img.setImageDrawable(getResources().getDrawable(R.drawable.bankdef_logo));
                        }
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
    private String OrderNo = "-1";
    @Override
    public void rechargeSuccess(String orderNo) {
//        setDialog("购买成功！",
//                Html.fromHtml("<font color='#00ff00'>查看详情").toString(),
//                Html.fromHtml("<font color='#ff0000'>继续充值").toString());
//        dialog.show();
        OrderNo = orderNo;
        dialog.show();


    }

    /**
     * 充值失败
     */
    @Override
    public void rechargeFail(String s) {
//        setDialog("购买失败！",
//                Html.fromHtml("<font color='#00ff00'>查看详情").toString(),
//                Html.fromHtml("<font color='#ff0000'>重新充值").toString());
//        dialog.show();

        setDialogFail();
        dialogFail.setPrompt("充值失败！",s);
        dialogFail.show();
    }

    @Override
    public void rechargeeError(Throwable e) {
//        showToast("网络故障！");

        setDialogFail();
        dialogFail.setPrompt("充值失败！","网络故障！");
        dialogFail.show();
    }

    /**
     * 充值后的提示Dialog
     */
    public void setDialog(){
        dialog.setPrompt("","充值成功！");
        dialog.setMod(PromptDialog.MOD2);
        dialog.setClick("查看详情","继续充值", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
//                showToast("查看详情");
                gotoTransactiondetails(OrderNo,"-1","recharge",RechargedetailsActivity.class);

//                toOtherActivity(PurchaseActivity.this, InvestmentActivity.class);
            }

            @Override
            public void onRight(View view) {
//                showToast("继续购买");
                dialog.dismiss();
                mModel.ed_money.setText("");
            }
        });
    }

    /**
     * 提现后的提示Dialog
     */
    public void setDialogFail(){

        dialogFail.setMod(PromptDialog.MOD1);
        dialogFail.setClick("退出","继续充值", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
//                showToast("查看详情");
//                toOtherActivity(PurchaseActivity.this, InvestmentActivity.class);
                dialogFail.dismiss();
                finish();
            }

            @Override
            public void onRight(View view) {
//                showToast("继续购买");
                dialogFail.dismiss();
                mModel.ed_money.setText("");
            }
        });
    }

    private void gotoTransactiondetails(String orderNo,String orderId,String type,Class<?> cls){
        Intent intent = new Intent(this,cls);
        intent.putExtra("orderId",orderId);
        intent.putExtra("orderNo",orderNo);
        intent.putExtra("type",type);
        startActivity(intent);
    }


}
