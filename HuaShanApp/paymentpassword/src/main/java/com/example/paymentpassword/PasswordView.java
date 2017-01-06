package com.example.paymentpassword;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PasswordView extends RelativeLayout {
	Context context;
	private String strPassword; // 输入的密码
	private TextView[] tvList; // 用数组保存6个TextView，为什么用数组？
	private LinearLayout ll_pwd; // 因为就6个输入框不会变了，用数组内存申请固定空间，比List省空间（自己认为）
	private GridView gridView; // 用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
	private ArrayList<Map<String, String>> valueList; // 有人可能有疑问，为何这里不用数组了？
														// 因为要用Adapter中适配，用数组不能往adapter中填充

	private RelativeLayout rl_bottom;
	private int currentIndex = -1; // 用于记录当前输入密码格位置

	private PopupWindow popupWindow;

	private TextView tv_forgetPwd;
	private FrameLayout fl_cancel;
	private TextView tv_money;

	public PasswordView(Context context) {
		this(context, null);
	}

	public PasswordView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		View view = View.inflate(context, R.layout.layout_popup_bottom, null);
		valueList = new ArrayList<Map<String, String>>();

		rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.layout_popupdemo, null);// 定义后退弹出框
		ll_pwd = (LinearLayout) contentView.findViewById(R.id.ll_pwd);

		tv_forgetPwd = (TextView) contentView.findViewById(R.id.tv_forgetPwd);
		fl_cancel = (FrameLayout) contentView.findViewById(R.id.fl_cancel);
		tv_money = (TextView) contentView.findViewById(R.id.tv_money);

		initTvList();

		gridView = (GridView) contentView.findViewById(R.id.gv_keybord);// 泡泡窗口的布局
		initData();

		addView(view); // 必须要，不然不显示控件
		popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.MATCH_PARENT,// width

				ViewGroup.LayoutParams.WRAP_CONTENT);// higth
		popupWindow.setFocusable(false);
		popupWindow.setAnimationStyle(R.style.animation);

		tv_forgetPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(mpass == null){
					return;
				}
				mpass.onForgetpassword(view);
			}
		});

		fl_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(mpass == null){
					return;
				}
				mpass.onBack(view);
			}
		});

	}

	private TextView textView1;

	private void initTvList(){
		tvList = new TextView[6];
		ll_pwd.removeAllViews();

		for (int i = 0; i < 6; i++) {
//			TextView textView = new TextView(context);
			textView1 = new TextView(context);
			android.widget.LinearLayout.LayoutParams layoutParams = new android.widget.LinearLayout.LayoutParams(
					0, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 1);
			textView1.setGravity(Gravity.CENTER);
			textView1.setTransformationMethod(PasswordTransformationMethod.getInstance());
			textView1.setTextSize(32);
			textView1.setLayoutParams(layoutParams);
			ll_pwd.addView(textView1);
			if (i != 5) {
				View view2 = new View(context);
				android.widget.LinearLayout.LayoutParams layoutParams1 = new android.widget.LinearLayout.LayoutParams(
						1,
						android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
						0);
				view2.setLayoutParams(layoutParams1);
				view2.setBackgroundColor(Color.parseColor("#999999"));
				ll_pwd.addView(view2);

			}
			tvList[i] = textView1;
		}


		setOnFinishInput_s();
	}

	private OnPasswordViewListener mpass;

	public interface OnPasswordViewListener {
		void inputFinish();

		void onBack(View v);

		void onForgetpassword(View v);
	}

	public void setOnPasswordViewListener(OnPasswordViewListener pass){
		this.mpass = pass;
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
	}

	public void show() {
		strPassword = "";
		currentIndex = -1;
		initTvList();
		initData();
		if(this.getVisibility() == GONE){
			this.setVisibility(VISIBLE);
		}
		popupWindow.showAtLocation(rl_bottom, Gravity.BOTTOM, 0, 0); // 确定在界面中出现的位置
	}

	public void out(){
		if(this.getVisibility() == VISIBLE){
			this.setVisibility(GONE);
		}
		popupWindow.dismiss();
	}

	/**
	 * 加载模拟键盘上数据的代码
	 */
	private void initData() {
		valueList = new ArrayList<Map<String, String>>();
		/* 初始化按钮上应该显示的数字 */
		for (int i = 1; i < 13; i++) {
			Map<String, String> map = new HashMap<String, String>();
			if (i < 10) {
				map.put("name", String.valueOf(i));
			} else if (i == 10) {
				map.put("name", "");
			} else if (i == 11) {
				map.put("name", String.valueOf(0));
			} else if (i == 12) {
				map.put("name", "×");
			} else {
				map.put("name", "");
			}
			valueList.add(map);
		}
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if (position < 11 && position != 9) { // 点击0~9按钮
					if (currentIndex >= -1 && currentIndex < 5) { // 判断输入位置————要小心数组越界
						tvList[++currentIndex].setText(valueList.get(position)
								.get("name"));
					}
				} else {
					if (position == 11) { // 点击退格键
						if (currentIndex - 1 >= -1) { // 判断是否删除完毕————要小心数组越界
							tvList[currentIndex--].setText("");
						}
					}
				}
			}
		});
	}

	// 设置监听方法，在第6位输入完成后触发
	public void setOnFinishInput_s() {
//		if(tvList == null || tvList.length == 0){
//			return;
//		}

		RxTextView.textChangeEvents(tvList[5])
				.debounce(300, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<TextViewTextChangeEvent>() {
					@Override
					public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
						String str = textViewTextChangeEvent.text().toString().trim();
						if (str.length() == 1) {
							strPassword = ""; // 每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
							for (int i = 0; i < 6; i++) {
								strPassword += tvList[i].getText().toString().trim();
							}
							if (mpass!=null) {
								mpass.inputFinish(); // 接口中要实现的方法，完成密码输入完成后的响应逻辑
							}
						}
					}
				});
	}

	/* 获取输入的密码 */
	public String getStrPassword() {
		return strPassword;
	}

	
	// GrideView的适配器
	BaseAdapter adapter = new BaseAdapter() {
		@Override
		public int getCount() {
			return valueList.size();
		}

		@Override
		public Object getItem(int position) {
			return valueList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.item_gride, null);
				viewHolder = new ViewHolder();
				viewHolder.btnKey = (TextView) convertView
						.findViewById(R.id.btn_keys);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

//			if(position != 12){
				viewHolder.btnKey.setText(valueList.get(position).get("name"));
//			}

			if (position == 9||position==11) {
				viewHolder.btnKey.setBackgroundDrawable(Utils.getStateListDrawable(context));
				viewHolder.btnKey.setEnabled(false);
			}
			if (position == 11) {
				viewHolder.btnKey.setBackgroundDrawable(Utils.getStateListDrawable(context));
			}

			return convertView;
		}
	};
	
	
	/**
	 * 存放控件
	 */
	public final class ViewHolder {
		public TextView btnKey;
	}

	public void setMoney(String mondey){
		tv_money.setText(mondey);
	}
}
