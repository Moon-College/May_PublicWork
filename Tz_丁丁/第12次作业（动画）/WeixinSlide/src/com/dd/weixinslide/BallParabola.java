package com.dd.weixinslide;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class BallParabola extends Activity {
	private ImageView ivEye;
	private int screenHeight;
	private int screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		TextView tv = (TextView) findViewById(R.id.tv);
		ivEye = (ImageView) findViewById(R.id.iv_eye);
		screenHeight = getWindowManager().getDefaultDisplay().getHeight()-ivEye.getHeight();
		screenWidth = getWindowManager().getDefaultDisplay().getWidth()-ivEye.getWidth();
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(5000);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						Log.v("home", "dd");
//						ivEye.setTranslationX(screenWidth.get);
//						ivEye.setTranslationY(screenHeight);
						ivEye.setX(screenWidth-(1-progress)*screenWidth);
						ivEye.setY(screenHeight - (1-progress)*screenHeight);
//						int[] location = new  int[2] ;
//						mChat.getLocationOnScreen(location);
//						//屏幕高度-消息列表绝对y坐标
//						
////						float y1 =location[1]-mChatOriginY*progress;
//						float y2 =screenHeight-location[1]*(1-progress);
////						float y = mChatOriginY - (progress*viewRoll);
//						mChat.setY(y2);
					}
				});
			
			}
		});
	}
	
	public void move(View v){
		Log.v("home", "d");
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(5000);
		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator va) {
				float progress = Float.parseFloat(va.getAnimatedValue().toString());
				Log.v("home", "dd");
				ivEye.setX(100-progress*100);
//				int[] location = new  int[2] ;
//				mChat.getLocationOnScreen(location);
//				//屏幕高度-消息列表绝对y坐标
//				
////				float y1 =location[1]-mChatOriginY*progress;
//				float y2 =screenHeight-location[1]*(1-progress);
////				float y = mChatOriginY - (progress*viewRoll);
//				mChat.setY(y2);
			}
		});
	
		
	}
}
