package com.karazam.huashanapp.my.setup.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.BitmapUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivitySetupBinding;
import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupEntity;
import com.karazam.huashanapp.my.setup.view.SetupView;
import com.karazam.huashanapp.my.setup.viewmodel.SetupViewModel;
import com.karazam.huashanapp.my.setup.viewmodel.SetupViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/22.
 */

public class SetupActivity extends BaseActivity implements SetupView {

    private ActivitySetupBinding binding;
    private SetupEntity entity = new SetupEntity();
    private SetupViewModel mModel;



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup);
        mModel = new SetupViewModelImpl(this,entity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {
        setHeader();
        setData();
    }


    /**
     * 设置头像
     */
    private void setHeader() {

        RxView.findById(this,R.id.use_header).bind(HuaShanApplication.baseInfoBeanRX, new Rx.Action<View, BaseInfoBean>() {
            @Override
            public void call(View target, BaseInfoBean baseInfoBean) {
                final ImageView header = (ImageView) target;

                if(TextUtils.isEmpty(baseInfoBean.getAvatar())){
                    header.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user_logo));
                    return;
                }


                RxImageLoader.getLoaderObservable(null,baseInfoBean.getAvatar()).subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("e",e.toString());
                    }

                    @Override
                    public void onNext(Data data) {
                        Bitmap heaher = data.bitmap;
                        if(header == null){
                            return;
                        }
                        header.setImageBitmap(BitmapUtil.toRoundBitmap(heaher));
                    }
                });
            }
        });

    }

    /**
     * 设置界面数据
     */
    private void setData() {


        RxView.findById(this,R.id.data_ll).bind(HuaShanApplication.baseInfoBeanRX, new Rx.Action<View, BaseInfoBean>() {
            @Override
            public void call(View target, BaseInfoBean baseInfoBean) {
                TextView name = (TextView) target.findViewById(R.id.user_name);
                TextView nickname = (TextView) target.findViewById(R.id.user_nickname);
                TextView status = (TextView) target.findViewById(R.id.status);
                TextView phonenum = (TextView) target.findViewById(R.id.phonenum);

                String nameStr = baseInfoBean.getRealname();

                String nicknameStr = baseInfoBean.getName();

                nickname.setText(StringUtil.interrupt(nicknameStr,12,HuaShanApplication.account));

                boolean statusStr = HuaShanApplication.certificationStatus;
                if(statusStr){
                    status.setText("已认证");
                    name.setText(StringUtil.interrupt(nameStr,0,"未认证"));
                }else {
                    status.setText("去认证");
                    name.setText("未认证");
                }


                String phone = baseInfoBean.getMobile();
                phonenum.setText(StringUtil.interrupt(phone,0,""));

            }
        });
    }
}
