package huashanapp.karazam.com.gesture_lock.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;

import java.util.concurrent.TimeUnit;

import huashanapp.karazam.com.gesture_lock.R;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.verifypassword.VerifyPasswordDataSource;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/11/29.
 */

public class InputContentView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView input_tv_1;
    private EditText input_ed;

    private VerifyPasswordDataSource verifyPasswordDataSource;

    public InputContentView(Context context) {
        this.context = context;
        verifyPasswordDataSource = new VerifyPasswordDataSource();
    }

    private OnInputContentListener mOnInputContentListener;



    public interface OnInputContentListener{
        void onLeft(View view);

        void onRight(View view);

        void onResult(boolean result,String msg);

        void onError(Throwable e);

    }
    public View setView(ViewGroup layout,OnInputContentListener onAuthenticationListener){
        this.layout = layout;
        this.mOnInputContentListener = onAuthenticationListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_input,null);

        initView();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        view.findViewById(R.id.bt_input_l).setOnClickListener(this);
        view.findViewById(R.id.bt_input_r).setOnClickListener(this);

        input_tv_1 = (TextView) view.findViewById(R.id.input_tv_1);
        input_ed = (EditText) view.findViewById(R.id.input_ed);
    }

    public void setText1(String str){
        if(input_tv_1 == null){
            return;
        }
        input_tv_1.setText(StringUtil.interrupt(str,0,"未知"));
    }

    public String getContent(){
        if(input_ed == null){
            return null;
        }
        return input_ed.getText().toString();
    }

    public void show(){
        if(view == null){
            return;
        }
        layout.addView(view,layout.getLayoutParams());
        input_ed.setText("");
    }

    public void dismiss() {
        if(layout == null || view == null){
            return;
        }
        layout.removeView(view);
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.bt_input_l) {
            if (mOnInputContentListener == null) {
                return;
            }
            mOnInputContentListener.onLeft(view);

        } else if (i == R.id.bt_input_r) {
            if (mOnInputContentListener == null) {
                return;
            }
            mOnInputContentListener.onRight(view);

        }
    }

        public void verifyPassword(String uuid,String token){

            String password = getContent();

            verifyPasswordDataSource.verifyPassword(password,uuid,token)
                    .throttleFirst(2000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<BaseReturn>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("verifyPassword"," e   :  "+e.toString());
                            if(mOnInputContentListener == null){
                                return;
                            }
                            mOnInputContentListener.onError(e);
                        }

                        @Override
                        public void onNext(BaseReturn baseReturn) {



                            if(mOnInputContentListener == null){
                                return;
                            }
                            mOnInputContentListener.onResult(baseReturn.isSuccess(),baseReturn.getMessage());

                        }
                    });

        }
}
