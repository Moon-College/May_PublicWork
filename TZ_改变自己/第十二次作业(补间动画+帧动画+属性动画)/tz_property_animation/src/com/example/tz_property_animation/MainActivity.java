package com.example.tz_property_animation;

import com.nineoldandroids.view.ViewHelper;

import android.os.Bundle;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout mChat;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private ImageView ivEye;
	private float mChatOriginY;
	private Bitmap bmEye;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mChat = (RelativeLayout) findViewById(R.id.rl2);
		mChat.setOnTouchListener(this);
		ivEye = (ImageView) findViewById(R.id.iv_eye);
		bmEye = BitmapFactory.decodeResource(getResources(), R.drawable.eye);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//滑动距离的限定范围
		int limit = ivEye.getHeight() + 100;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			//手指滑动的距离
			fingerRoll = event.getY() - downY;
			if(fingerRoll <= 0) {
				break;
			}
			
			viewRoll = fingerRoll * 0.8f;
			ViewHelper.setTranslationY(mChat, viewRoll);
			ivEye.setImageBitmap(bmEye);
			break;
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.8f;
		    mChatOriginY = mChat.getY();
		    //消息列表滑动的距离小于限定值
		    if(viewRoll < limit) {
		    	//属性动画
				//花了500毫秒执行一个动画，值0变成了1
				//值的范围，取决于你用它做什么
		    	ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
		    	valueAnimator.start();
		    	valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					//500毫秒中，onAnimationUpdate方法会被反复的调用
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						//获取动画执行的进度
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						//消息列表的Y坐标
						//mChatOriginY 松开手指时的Y坐标
						float y = mChatOriginY - (progress * viewRoll);
						mChat.setY(y);
						
						//动画完成
						if (progress == 1) {
							mChat.setY(0);
						}
					}
				});
		    	
		    } //否则，直接滑动到底部
			else{
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(1000);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						//作业
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						float y = mChatOriginY + (progress * (mChat.getHeight() - fingerRoll));
						Log.e("wdj","progress = " + progress + ", y = " + y + ",mChat.getHeight() = " + mChat.getHeight());
						Log.e("wdj" , "mChatOriginY" + mChatOriginY + ",fingerRoll = " + fingerRoll);
						mChat.setY(y);
					}
				});
			}
			break;

		default:
			break;
		}
		
		
		return true;
	}

	
}
