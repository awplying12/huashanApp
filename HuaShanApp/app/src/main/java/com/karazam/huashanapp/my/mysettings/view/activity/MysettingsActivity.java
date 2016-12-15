package com.karazam.huashanapp.my.mysettings.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.BitmapUtil;
import com.example.utils.utils.PathUtil;
import com.example.utils.utils.Picturedialog;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMysettingsBinding;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModel.MysettingsViewModel;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModel.MysettingsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.io.File;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MysettingsActivity extends BaseActivity implements MysettingsView {

    private ActivityMysettingsBinding binding;
    private MysettingsEntity entity = new MysettingsEntity();
    private MysettingsViewModel mModel;

    private Picturedialog picturedialog;

    private PercentFrameLayout name_ll;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mysettings);
        mModel = new MysettingsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        name_ll = (PercentFrameLayout) getView(R.id.name_ll);
    }

    @Override
    public void dealLogicAfterInitView() {
        setHeader();
        setPicturedialog();
        setData();
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        RxView.findById(this,R.id.content_pl).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
            @Override
            public void call(View target, UserInformation userInformation) {
                TextView name = (TextView) target.findViewById(R.id.user_name);
                TextView nickname = (TextView) target.findViewById(R.id.user_nickname);
                TextView phonenum = (TextView) target.findViewById(R.id.phonenum);
                PercentFrameLayout name_ll = (PercentFrameLayout) target.findViewById(R.id.name_ll);

                String nameStr = userInformation.getUserName();


                String nicknameStr = userInformation.getNickname();
                nickname.setText(StringUtil.interrupt(nicknameStr,12,userInformation.getPhonenum()));

                boolean status = userInformation.isStatus();
                if(status){
                    name.setText(StringUtil.interrupt(nameStr,0,"去认证"));
                }else {
                    name.setText("去认证");
                }

                String phonenumStr = userInformation.getPhonenum();
                phonenum.setText(StringUtil.interrupt(phonenumStr,0,""));
            }
        });


    }

    /**
     * 设置头像
     */
    private void setHeader() {
        RxView.findById(this,R.id.use_header).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {

            @Override
            public void call(View target, UserInformation userInformation) {
                final ImageView header = (ImageView) target;
                Log.i("e","1");
                if(TextUtils.isEmpty(userInformation.getHeaderImg())){
                    header.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user_logo));
                    return;
                }
                RxImageLoader.getLoaderObservable(null,userInformation.getHeaderImg()).subscribe(new Subscriber<Data>() {
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

    private String path = PathUtil.getPath();
    private void setPicturedialog() {
        picturedialog = new Picturedialog(this);
        picturedialog.setView((ViewGroup)getView(R.id.content_pl), new Picturedialog.OnPictureListener() {
            @Override
            public void onPhotograph(View view) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (BitmapUtil.isSDCardExist()) {

                    File file = new File(path, StringUtil.IMAGE_FILE_NAME);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                }
                startActivityForResult(intentFromCapture,StringUtil.CAMERA_REQUEST_CODE);
            }

            @Override
            public void onAlbum(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, StringUtil.IMAGE_REQUEST_CODE);
            }

            @Override
            public void onCancel(View view) {
                picturedialog.dismiss();
            }
        });
    }

    @Override
    public void addPicturedialog() {
        picturedialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case StringUtil.IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case StringUtil.CAMERA_REQUEST_CODE:
                    // 判断存储卡是否可以用，可用进行存储

                    if (BitmapUtil.isSDCardExist()) {
                        File tempFile = new File(path, StringUtil.IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));

                    } else {
                        showToast("未找到存储卡，无法存储照片。");
                    }
                    break;
                case StringUtil.RESULT_REQUEST_CODE: // 图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private String bitmapBase;
    private Bitmap photo = null;
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");

            bitmapBase = BitmapUtil.comp(photo);

            Log.i("bitmapBase",bitmapBase);
        }
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, StringUtil.RESULT_REQUEST_CODE);
    }
}
