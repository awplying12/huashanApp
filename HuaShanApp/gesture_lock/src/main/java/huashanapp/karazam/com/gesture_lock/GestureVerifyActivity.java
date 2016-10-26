package huashanapp.karazam.com.gesture_lock;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import huashanapp.karazam.com.gesture_lock.widget.GestureContentView;
import huashanapp.karazam.com.gesture_lock.widget.GestureDrawline;


/**
 * 
 * ���ƻ���/У�����
 *
 */
public class GestureVerifyActivity extends Activity implements View.OnClickListener {
	/** �ֻ�����*/
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** ��ͼ */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private RelativeLayout mTopLayout;
	private TextView mTextTitle;
	private TextView mTextCancel;
	private ImageView mImgUserLogo;
	private TextView mTextPhoneNumber;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	private TextView mTextOther;
	private String mParamPhoneNumber;
	private long mExitTime = 0;
	private int mParamIntentCode;

	private String mPassword = "";
	private String phoneNum = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);

		ObtainExtraData();
		setUpViews();
		setUpListeners();
	}
	
	private void ObtainExtraData() {
		mPassword = getIntent().getStringExtra(GestureUtil.Password);
		phoneNum = getIntent().getStringExtra(GestureUtil.PhoneNum);
		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
	}
	
	private void setUpViews() {
		mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
		mTextTitle = (TextView) findViewById(R.id.text_title);
		mTextCancel = (TextView) findViewById(R.id.text_cancel);
		mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
		mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextOther = (TextView) findViewById(R.id.text_other_account);
		
		

		String password = TextUtils.isEmpty(mPassword)|| mPassword == null ? "1235789" : mPassword;

		mGestureContentView = new GestureContentView(this, true, password,
				new GestureDrawline.GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {

					}

					@Override
					public void checkedSuccess() {
						mGestureContentView.clearDrawlineState(0L);
						Toast.makeText(GestureVerifyActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
						GestureVerifyActivity.this.setResult(GestureUtil.GESTURELOCK_VERIFY_RESULTCODE);
						GestureVerifyActivity.this.finish();
					}

					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						mTextTip.setText(Html
								.fromHtml("<font color='#c70c1e'>密码不正确!</font>"));
						// �����ƶ�����
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					}
				});
		// �������ƽ�����ʾ���ĸ���������
		mGestureContentView.setParentView(mGestureContainer);
	}
	
	private void setUpListeners() {
		mTextCancel.setOnClickListener(this);
		mTextForget.setOnClickListener(this);
		mTextOther.setOnClickListener(this);

		if(!phoneNum.equals("") && !TextUtils.isEmpty(phoneNum)){
			mTextPhoneNumber.setText(getProtectedMobile(phoneNum));
		}

	}
	
	private String getProtectedMobile(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(phoneNumber.subSequence(0,3));
		builder.append("****");
		builder.append(phoneNumber.subSequence(7,11));
		return builder.toString();
	}
	
	

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.text_cancel) {
			this.finish();

		} else if(i == R.id.text_forget_gesture){

			if(mOnGestureVerifyClickListener == null){
				return;
			}
			mOnGestureVerifyClickListener.onForgetGesture();
		}else if(i == R.id.text_other_account){

			if(mOnGestureVerifyClickListener == null){
				return;
			}
			mOnGestureVerifyClickListener.onOtherAccount();
		}
	}

	public interface onGestureVerifyClickListener{
		void onForgetGesture();

		void onOtherAccount();
	}

	private static onGestureVerifyClickListener mOnGestureVerifyClickListener;

	public static void setOnGestureVerifyClickListener(onGestureVerifyClickListener onGestureVerifyClickListener){
		mOnGestureVerifyClickListener = onGestureVerifyClickListener;
	}

	
}
