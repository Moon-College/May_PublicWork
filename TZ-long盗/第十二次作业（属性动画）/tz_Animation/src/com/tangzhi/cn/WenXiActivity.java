package com.tangzhi.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class WenXiActivity extends Activity implements OnTouchListener {

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
        setContentView(R.layout.two);

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
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                fingerRoll = event.getY() - downY;
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
                //璁板綍涓嬫墜鎸囨澗寮�椂锛屾秷鎭垪琛ㄧ殑Y鍧愭爣
                mChatOriginY = mChat.getY();


                if (VALUE_ANIMATOR != null) VALUE_ANIMATOR.cancel();
                VALUE_ANIMATOR = ValueAnimator.ofFloat(0, 500f).setDuration((long) Math.abs(viewRoll));
                VALUE_ANIMATOR.start();
                final boolean isChat=v.getId() == R.id.rl2;
                boolean flag = viewRoll < limit;
                if (flag) {
                    VALUE_ANIMATOR.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator va) {
                            float rate = Float.parseFloat(va.getAnimatedValue().toString()) / 500;        
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
                    VALUE_ANIMATOR.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator va) {
                            float rate = Float.parseFloat(va.getAnimatedValue().toString()) / 500;
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
                            mChat.setOnTouchListener(WenXiActivity.this);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        event.startTracking();
        return true;
    }
}
