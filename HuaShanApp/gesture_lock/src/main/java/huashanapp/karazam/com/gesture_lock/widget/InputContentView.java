package huashanapp.karazam.com.gesture_lock.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;

import huashanapp.karazam.com.gesture_lock.R;


/**
 * Created by Administrator on 2016/11/29.
 */

public class InputContentView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView input_tv_1;
    private EditText input_ed;

    public InputContentView(Context context) {
        this.context = context;
    }

    private OnInputContentListener mOnInputContentListener;



    public interface OnInputContentListener{
        void onLeft(View view);

        void onRight(View view);

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
}
