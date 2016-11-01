package com.gelitenight.waveview.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WaveHelper {
    private WaveView mWaveView;
    private TextView textView;
    private Activity activity;

    private AnimatorSet mAnimatorSet;

    private long time = 10000;

    public WaveHelper(WaveView waveView, Activity activity, TextView textView) {
        mWaveView = waveView;
        this.activity = activity;
        this.textView = textView;
        initAnimation(0.5f);
    }

    public void start() {
        mWaveView.setShowWave(true);
        if (mAnimatorSet != null) {
            mAnimatorSet.start();
        }
    }

    private void initAnimation(float percent) {
        test(percent);
        List<Animator> animators = new ArrayList<>();

        // horizontal animation.
        // wave waves infinitely.
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                mWaveView, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);

        // vertical animation.
        // water level increases from 0 to center of WaveView
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                mWaveView, "waterLevelRatio", 0f, percent);
        waterLevelAnim.setDuration(time);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);

        // amplitude animation.
        // wave grows big then grows small, repeatedly
        ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                mWaveView, "amplitudeRatio", 0.0001f, 0.02f);
        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        amplitudeAnim.setDuration(5000);
        amplitudeAnim.setInterpolator(new LinearInterpolator());
        animators.add(amplitudeAnim);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animators);


    }

    private void test(final float percent) {


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                float s = 0;
                while (s <= percent) {


                    final int finalS  = (int) (s*100);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            textView.setText(finalS +"%");
                        }
                    });

                    s += 0.01f;

                    try {
                        Thread.sleep((long) (time/(percent*100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        textView.setText((int) (percent*100) +"%");
//                        textView.setText("满额");
                    }
                });

            }
        });
        thread.start();




    }

    public void setPercent(float percent){
        mAnimatorSet.end();
        initAnimation(percent);
        mAnimatorSet.start();

    }

    public void cancel() {
        if (mAnimatorSet != null) {
//            mAnimatorSet.cancel();
            mAnimatorSet.end();
        }
    }
}
