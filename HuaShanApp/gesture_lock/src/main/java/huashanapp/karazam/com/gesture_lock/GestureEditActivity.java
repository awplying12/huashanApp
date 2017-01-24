package huashanapp.karazam.com.gesture_lock;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.gesture.Gesture;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.utils.StringUtil;

import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.GespwReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.verifypassword.GespasswordDataSource;
import huashanapp.karazam.com.gesture_lock.widget.BackView;
import huashanapp.karazam.com.gesture_lock.widget.GestureContentView;
import huashanapp.karazam.com.gesture_lock.widget.GestureDrawline;
import huashanapp.karazam.com.gesture_lock.widget.InputContentView;
import huashanapp.karazam.com.gesture_lock.widget.LockIndicator;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 
 * �����������ý���
 *
 */
public class GestureEditActivity extends Activity implements OnClickListener {
	/** �ֻ�����*/
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** ��ͼ */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	/** �״���ʾ�����������룬����ѡ������ */
	public static final String PARAM_IS_FIRST_ADVICE = "PARAM_IS_FIRST_ADVICE";
//	private TextView mTextTitle;
	private ImageView img_cancel;
	private LockIndicator mLockIndicator;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextReset;
	private String mParamSetUpcode = null;
	private String mParamPhoneNumber;
	private boolean mIsFirstInput = true;
	private String mFirstPassword = null;	//密码
	private String mConfirmPassword = null;
	private int mParamIntentCode;

	private BackView backView;

	private String uuid;
	private String account;
	private String token;

	private GespasswordDataSource gespasswordDataSource;

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_edit);


		GestureUtil.activitys.add(this);
		setUpViews();

		String str = getIntent().getStringExtra("verification");

		uuid = getIntent().getStringExtra("uuid");
		account = getIntent().getStringExtra("account");
		token = getIntent().getStringExtra("token");

		gespasswordDataSource = new GespasswordDataSource();


		sharedPreferences = this.getSharedPreferences("huashan", 0);
		editor = sharedPreferences.edit();

		if(str != null && str.equals("verification")){
			setView();
		}
		setUpListeners();
		setBack();
	}
	
	private void setUpViews() {
//		mTextTitle = (TextView) findViewById(R.id.text_title);
		img_cancel = (ImageView) findViewById(R.id.img_cancel);
		img_cancel.setOnClickListener(this);
		mTextReset = (TextView) findViewById(R.id.text_reset);
		mTextReset.setClickable(false);
		mLockIndicator = (LockIndicator) findViewById(R.id.lock_indicator);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		// ��ʼ��һ����ʾ�������viewGroup
		mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
			@Override
			public void onGestureCodeInput(String inputCode) {



				if (!isInputPassValidate(inputCode)) {
					mTextTip.setText(Html.fromHtml("<font color='#ff0000'>密码过短!</font>"));
					mGestureContentView.clearDrawlineState(0L);
					return;
				}
				if (mIsFirstInput) {
					mFirstPassword = inputCode;
					updateCodeList(inputCode);
					mGestureContentView.clearDrawlineState(0L);
					mTextReset.setClickable(true);
					mTextReset.setText(getString(R.string.reset_gesture_code));
				} else {
					if (inputCode.equals(mFirstPassword)) {
//							Toast.makeText(GestureEditActivity.this, "密码设置成功!", Toast.LENGTH_SHORT).show();
						mGestureContentView.clearDrawlineState(0L);

							setGesPassword(mFirstPassword);

						Intent intent = new Intent();
						intent.putExtra(GestureUtil.Password,mFirstPassword);
						GestureEditActivity.this.setResult(GestureUtil.GESTURELOCK_EDIT_RESULTCODE,intent);

						Observable.from(GestureUtil.activitys)
								.filter(new Func1<Activity, Boolean>() {
									@Override
									public Boolean call(Activity activity) {
										return activity != null;
									}
								})
								.map(new Func1<Activity, Activity>() {
									@Override
									public Activity call(Activity activity) {
										return activity;
									}
								}).subscribe(new Action1<Activity>() {
							@Override
							public void call(Activity activity) {
								activity.finish();
							}
						});

						GestureEditActivity.this.finish();
					} else {
						mTextTip.setText(Html.fromHtml("<font color='#ff0000'>两次密码不一致!</font>"));
						// �����ƶ�����
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureEditActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
						// ���ֻ��Ƶ��ߣ�1.5������
						mGestureContentView.clearDrawlineState(1300L);
					}
				}
				mIsFirstInput = false;
				mTextTip.setText(getString(R.string.setup_gesture_pattern_again));
			}

			@Override
			public void checkedSuccess() {
				
			}

			@Override
			public void checkedFail() {
				
			}
		});
		// �������ƽ�����ʾ���ĸ���������
		mGestureContentView.setParentView(mGestureContainer);
		updateCodeList("");



	}

	private void setBack(){
		backView = new BackView(this);

		backView.setView((ViewGroup) findViewById(R.id.content_pl), new BackView.OnBackViewListener() {
			@Override
			public void onLeft(View view) {

				backView.dismiss();
			}

			@Override
			public void onRight(View view) {

				GestureEditActivity.this.finish();
			}

		});

	}
	
	private void setUpListeners() {
//		mTextCancel.setOnClickListener(this);
		mTextReset.setOnClickListener(this);
	}
	
	private void updateCodeList(String inputCode) {
		// ����ѡ���ͼ��
		mLockIndicator.setPath(inputCode);
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.img_cancel) {
//			this.finish();
			backView.show();

		}
		else
		if (i == R.id.text_reset) {
			mIsFirstInput = true;
			updateCodeList("");
			mTextTip.setText(getString(R.string.set_gesture_pattern));

		} else {
		}
	}

	/**
	 *  设置认证密码VIEW
	 */
	private InputContentView inputView;
	private void setView() {

		inputView = new InputContentView(this);

		inputView.setView((ViewGroup) findViewById(R.id.content_pl), new InputContentView.OnInputContentListener() {
			@Override
			public void onLeft(View view) {
				inputView.dismiss();
				finish();
			}

//			@Override
//			public void onRight(View view) {
//				inputView.dismiss();
//
//				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//			}

			@Override
			public void onRight(View view) {
				String str = inputView.getContent();
				if(TextUtils.isEmpty(str)){
//					mView.showToast("请输入密码");
					if(this == null){
						return;
					}
					Toast.makeText(GestureEditActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
					return;
				}else {
//                    nextStep();
					if(TextUtils.isEmpty(uuid) || TextUtils.isEmpty(token)){
						Toast.makeText(GestureEditActivity.this,"数据有误！",Toast.LENGTH_SHORT).show();
						return;
					}
					inputView.verifyPassword(uuid,token);
				}

			}

			@Override
			public void onResult(boolean result, String msg) {
				if(result){

					inputView.dismiss();

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

				} else {
					if(this == null){
						return;
					}
					Toast.makeText(GestureEditActivity.this,msg,Toast.LENGTH_SHORT).show();
					return;
				}
			}

			@Override
			public void onError(Throwable e) {

			}
		});

		inputView.setText1("华善账户："+ account);
		inputView.show();
	}

	/**
	 * 同步手势密码
	 */
	public void setGesPassword(String gesPassword) {

		gespasswordDataSource.setGesPassword(gesPassword,uuid,token).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<GespwReturn>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

//				mView.setGesPasswordFaile(e);
				Log.i("GespwReturn","e  :  "+e.toString());
			}

			@Override
			public void onNext(BaseReturn<GespwReturn> gespwReturnBaseReturn) {

				if(gespwReturnBaseReturn.isSuccess()){
					GespwReturn gespwReturn = gespwReturnBaseReturn.getData();
					Log.i("GespwReturn",gespwReturn.toString());
//					mView.setGesPasswordSuccess(gespwReturn);

					String str = gespwReturn.getGesPassword();
					editor.putString("gesture_lock", StringUtil.interrupt(str,0,"-1")).commit();
					editor.putBoolean("isGesture_lock",true).commit();


				}else {
//					mView.showToast(gespwReturnBaseReturn.getMessage());
					Toast.makeText(GestureEditActivity.this,gespwReturnBaseReturn.getMessage(),Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
	
	private boolean isInputPassValidate(String inputPassword) {
		if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
			return false;
		}
		return true;
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode==KeyEvent.KEYCODE_BACK){
			backView.show();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
