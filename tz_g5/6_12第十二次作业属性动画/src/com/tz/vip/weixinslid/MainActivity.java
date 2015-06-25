package com.tz.vip.weixinslid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends Activity implements OnTouchListener {

    private RelativeLayout mChat, mTitle;
    private LinearLayout mCamera;
    private float downY;
    private float fingerRoll;
    private float viewRoll;
    private ImageView iv_eye;
    private float mChatOriginY;
    private ValueAnimator VALUE_ANIMATOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ;

        mTitle = (RelativeLayout) findViewById(R.id.title);

        mChat = (RelativeLayout) findViewById(R.id.rl2);
        mChat.setOnTouchListener(this);

        mCamera = (LinearLayout) findViewById(R.id.capture);
        mCamera.setOnTouchListener(this);

        iv_eye = (ImageView) findViewById(R.id.iv_eye);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int limit = iv_eye.getHeight() + 100;
        final int windowBottom = getWindow().getDecorView().getBottom();
        boolean top = mChat.getY() <= mCamera.getTop();
        boolean bottom = mChat.getY() >= mCamera.getBottom();
        if (VALUE_ANIMATOR!=null&&VALUE_ANIMATOR.isRunning()) return super.onTouchEvent(event);
        switch (event.getAction()) {
            //获取手指按下时的坐标
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                break;
            //手指移动的过程中，消息列表跟随移动
            case MotionEvent.ACTION_MOVE:
                //消息列表移动的距离=手指移动距离*0.6
                fingerRoll = event.getY() - downY;
                //手指网上滑动，或者没有滑动距离
                if (fingerRoll <= 0 && top) {
                    mChat.setY(mCamera.getTop());
                    break;
                }

                if (fingerRoll >= 0 && bottom) {
                    mChat.setY(windowBottom);
                    break;
                }
                viewRoll = fingerRoll * 0.8f;
                if (v.getId() == R.id.rl2) {
                    ViewHelper.setTranslationY(mChat, viewRoll);
                } else {
                    float translationY = windowBottom + viewRoll;
                    ViewHelper.setTranslationY(mChat, translationY);
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                fingerRoll = event.getY() - downY;
                viewRoll = fingerRoll * 0.8f;

                if (top) {
                    mChat.setY(mCamera.getTop());
                    break;
                }

                if (bottom) {
                    mChat.setY(mCamera.getBottom());
                    break;
                }
                //记录下手指松开时，消息列表的Y坐标
                mChatOriginY = mChat.getY();


                if (VALUE_ANIMATOR != null) VALUE_ANIMATOR.cancel();
                VALUE_ANIMATOR = ValueAnimator.ofFloat(0, 500f).setDuration((long) Math.abs(viewRoll));
                VALUE_ANIMATOR.start();
                final boolean isChat=v.getId() == R.id.rl2;
                boolean flag = viewRoll < limit;
//                if (!isChat) flag = !flag;
                if (flag) {
                    //消息列表滑动的距离小于限定值，滑回去
                    //否则，直接滑到底部（作业）
                    //花了500毫秒完成了，0到500的过程
                    //当值为250，动画完成的进度时，0.5
                    //动画执行的进度，跟消息列表滑动的进度保持一致
                    //开始动画
                    //这个动画要完成，消息列表滑动到初始位置这个过程
                    VALUE_ANIMATOR.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator va) {
                            //执行进度
                            float rate = Float.parseFloat(va.getAnimatedValue().toString()) / 500;
                            //消息列表的y坐标=松开手指时消息列表的Y坐标+(动画进度*消息列表滑动的总距离)
                            //2.2 解决setY
                            //往上滑动，Y坐标变小
                            float y = 0;
                            if (isChat) {
                                y = mChatOriginY - (rate * viewRoll);
                            } else {
                                y =  mChatOriginY - rate * ( mChatOriginY);
                            }
                            if (y<mCamera.getTop()) {
                                y = mCamera.getTop();
                            }
                            mChat.setY(y);

                        }
                    });
                    VALUE_ANIMATOR.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            mChat.setY(mCamera.getTop());
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                } else {
                    //开始动画

                    //这个动画要完成，消息列表滑动到初始位置这个过程

                    VALUE_ANIMATOR.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator va) {
                            //执行进度
                            float rate = Float.parseFloat(va.getAnimatedValue().toString()) / 500;
                            //消息列表的y坐标=松开手指时消息列表的Y坐标+(动画进度*消息列表滑动的总距离)
                            //2.2 解决setY
                            //往上滑动，Y坐标变小
                            float y = 0;
                            if (isChat) {
                                y = mChatOriginY + (rate * (windowBottom - viewRoll));
                            } else {

                            }


                            mChat.setY(y);

                        }
                    });
                    VALUE_ANIMATOR.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            mChat.setOnTouchListener(null);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            mChat.setOnTouchListener(MainActivity.this);
                            mChat.setY(windowBottom);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }


                fingerRoll = 0;
                break;
            default:
                break;
        }

        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animation:
                Intent intent = new Intent(this, ValueAnimateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        event.startTracking();
        return true;
    }
}
