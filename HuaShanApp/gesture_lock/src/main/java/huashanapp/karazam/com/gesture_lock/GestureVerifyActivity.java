package huashanapp.karazam.com.gesture_lock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.percent.PercentFrameLayout;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.utils.BitmapUtil;

import huashanapp.karazam.com.gesture_lock.widget.GestureContentView;
import huashanapp.karazam.com.gesture_lock.widget.GestureDrawline;
import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;


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
	private PercentFrameLayout mTopLayout;
//	private TextView mTextTitle;
//	private TextView mTextCancel;
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


	private TextView text1;
	private TextView text2;

	private String mPassword = "";
	private String phoneNum = "";
	private String header = "";

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
		header = getIntent().getStringExtra(GestureUtil.HeaderImg);

		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);


	}
	
	private void setUpViews() {
		mTopLayout = (PercentFrameLayout) findViewById(R.id.top_layout);
//		mTextTitle = (TextView) findViewById(R.id.text_title);
//		mTextCancel = (TextView) findViewById(R.id.text_cancel);

		text1 = (TextView) findViewById(R.id.text1);
		text2 = (TextView) findViewById(R.id.text2);

		mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
		mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextOther = (TextView) findViewById(R.id.text_other_account);

		setText();
		setheader();



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
								.fromHtml("<font color='#ff0000'>密码不正确!</font>"));
						// �����ƶ�����
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					}
				});
		// �������ƽ�����ʾ���ĸ���������
		mGestureContentView.setParentView(mGestureContainer);
	}

	/**
	 * 设置头像
	 */
	private void setheader() {
		RxImageLoader.init(this);
		if(TextUtils.isEmpty(header)){
			return;
		}
		RxImageLoader.getLoaderObservable(null,header).subscribe(new Subscriber<Data>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mImgUserLogo.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.user_logo));
			}

			@Override
			public void onNext(Data data) {
				Bitmap bitmap = data.bitmap;
				if(bitmap == null){
					mImgUserLogo.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.user_logo));
				}
				mImgUserLogo.setImageBitmap(BitmapUtil.toRoundBitmap(bitmap));
			}
		});
	}

	/**
	 * 设置文本
	 */
	private void setText() {
		Time time = new Time("GMT+8");
		time.setToNow();

		String day = String.valueOf(time.monthDay);
		String mon = String.valueOf(time.month);
		switch (time.month){
			case 0:
				mon = "Jan";
				break;
			case 1:
				mon = "Feb";
				break;
			case 2:
				mon = "Mar";
				break;
			case 3:
				mon = "Apr";
				break;
			case 4:
				mon = "May";
				break;
			case 5:
				mon = "Jun";
				break;
			case 6:
				mon = "Jul";
				break;
			case 7:
				mon = "Aug";
				break;
			case 8:
				mon = "Sep";
				break;
			case 9:
				mon = "Oct";
				break;
			case 10:
				mon = "Nov";
				break;
			case 11:
				mon = "Dec";
				break;
			default:
				break;
		}

		text1.setText(Html.fromHtml("<b><tt><big><big><big><big><big>"+day+"</b></tt></big></big></big></big></big>"+"  "+"<b><tt><small>"+mon));
	}

	private void setUpListeners() {
//		mTextCancel.setOnClickListener(this);
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
//		if (i == R.id.text_cancel) {
//			this.finish();
//
//		} else
		if(i == R.id.text_forget_gesture){

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
