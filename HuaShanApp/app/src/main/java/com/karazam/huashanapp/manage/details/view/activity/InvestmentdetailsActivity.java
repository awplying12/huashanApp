package com.karazam.huashanapp.manage.details.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.VerticalViewPager.VerticalViewPager;
import com.gelitenight.waveview.library.WaveHelper;
import com.gelitenight.waveview.library.WaveView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentDetailsBinding;
import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;
import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.view.view.VerticalAdapter;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModel;
import com.karazam.huashanapp.manage.details.viewmodel.InvestmentdetailsViewModelImpl;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/8.
 */

public class InvestmentdetailsActivity extends BaseActivity implements InvestmentdetailsView{

    private ActivityInvestmentDetailsBinding binding;
    private InvestmentdetailsEntity entity = new InvestmentdetailsEntity();

    private InvestmentdetailsViewModel mModel;

    private WaveView waveView;
    private WaveHelper mWaveHelper;

    private int H;
    private int sh;
    private int eh;

    private ImageView img_ll;
    private VerticalViewPager ViewPager;
    private TextView text_11;
    private TextView det_income;

    private ImageView tab_det;

    private AccelerateDecelerateInterpolator mSmoothInterpolator;
    private int mMinHeaderTranslation;
    private int mActionBarHeight;
    private TypedValue mTypedValue = new TypedValue();
    private View mHeader;
    private View header_2;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment_details);
        mModel = new InvestmentdetailsViewModelImpl(entity,this,this,this);
        binding.setHandler(mModel);
        binding.setEntity(entity);
    }

    @Override
    public void dealLogicBeforeInitView() {
        project.set(new Project(1,""));

        H = BaseActivity.ScreeH;
        sh = (int) (H*0.9);
        eh = (int) (H*0.8);


        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mMinHeaderTranslation = -((int) (BaseActivity.ScreeH*0.1));
//        mMinHeaderTranslation = ((int) (BaseActivity.ScreeH*0.1)+ getActionBarHeight());

    }

    @Override
    public void initView() {
        waveView = (WaveView) getView(R.id.wave_in);

        img_ll = (ImageView) getView(R.id.img_ll);

        mHeader = getView(R.id.header);
        header_2 = getView(R.id.header_2);
        text_11 = (TextView) getView(R.id.text_11);
        det_income = (TextView) getView(R.id.det_income);

        ViewPager = (VerticalViewPager) getView(R.id.viewpager_ve);

        tab_det = (ImageView) getView(R.id.tab_det);
    }

    @Override
    public void dealLogicAfterInitView() {
            setWaveView();
            setLayout();
            setVerticalViewPager();
    }

    private void setVerticalViewPager() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.image1);
        list.add(R.drawable.image2);

        VerticalAdapter adapter = new VerticalAdapter(list);

        ViewPager.setAdapter(adapter);

        ViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("fa","1");
                int scrollY = positionOffsetPixels;
                Log.i("scrollY : ",positionOffset+"  position   "+position);

                switch (i){
                    case 0:
                        mHeader.setTranslationY((float) ( Math.max(-scrollY, -tab_det.getHeight()*0.5)));
                        header_2.setTranslationY((float) ( Math.max(-scrollY, -tab_det.getHeight())));
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("fa","2");
                i = position;
                switch (i){
                    case 0:
                        mHeader.setTranslationY(0);
                        header_2.setTranslationY(0);
                        break;
                    case 1:
                        mHeader.setTranslationY(-(int) (tab_det.getHeight()*0.5));
                        header_2.setTranslationY(-(int) (tab_det.getHeight()));

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("fa","3");
            }
        });
    }

    /**
     * 设置界面
     */
    private  int i = 0;
    RxProperty<Project> project =  RxProperty.create();
    private void setLayout() {


        RxView.findById(this,R.id.content_pl).bind(project, new Rx.Action<View, Project>() {
            @Override
            public void call(View target, Project project) {
                target.findViewById(R.id.det_name);
                target.findViewById(R.id.det_income);
                target.findViewById(R.id.det_playenough);
                target.findViewById(R.id.det_time);

                TextView tv_money =  (TextView)  target.findViewById(R.id.det_money);
                String money = "200,000.00";
                tv_money.setText(Html.fromHtml(money+"<font color='#7b7b7b'>/1,000,000.00</font>"));

            }
        });

//        PagerFragmentAdapter adapter = new PagerFragmentAdapter(getSupportFragmentManager(),)



}
    private boolean is = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int sy = 0;
        int ey = 0;
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:

                Log.i("move","DOWN :  "+"  Y : "+y);
                if(eh< y && y <sh){
                    is =true;
                    showToast("OK1  "+is);
                    sy = (int) event.getY();

                }
                break;
            case MotionEvent.ACTION_MOVE:

//                Log.i("move","MOVE :  "+"X : "+x+"  Y : "+y);
                break;
            case MotionEvent.ACTION_UP:

                Log.i("move","UP :  "+"  Y : "+y);
//                if(is && (sy - event.getY()) ){
//
//                    showToast("OK2  "+is);
//                    is = false;
//                }


                break;
            default:
                break;
        }
        return false;
    }

    /**
     *设置滴水效果WaveView
     */
    private void setWaveView() {

        int mBorderColor = Color.parseColor("#0080FF");
        waveView.setBorder(0, mBorderColor);
        TextView t =(TextView)findViewById(R.id.ww_text);
        mWaveHelper = new WaveHelper(waveView,this,t);
        waveView.setShapeType(WaveView.ShapeType.SQUARE);

//        waveView.setWaveColor(
//                Color.parseColor("#0080FF"),
//                Color.parseColor("#0080FF"));
//        waveView.setBorder(1, mBorderColor);

        mWaveHelper.setPercent(0.20f);
        mWaveHelper.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mWaveHelper.start();
    }


    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }
        getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());
        return mActionBarHeight;
    }


}
