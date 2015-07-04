package com.casit.animation;

import com.nineoldandroids.view.ViewHelper;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DropanimationActivity extends Activity implements OnTouchListener {
	
    private RelativeLayout mchat;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private ImageView ivEye;
	private float mChatOriginY;
	private int[] locations;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mchat = (RelativeLayout) findViewById(R.id.rl2);
        mchat.setOnTouchListener(this);
        ivEye = (ImageView) findViewById(R.id.iv_eye);

    }

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		//滑动距离的限定范围
		int limit =  ivEye.getHeight()+100;
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			fingerRoll = event.getY() - downY;
			if(fingerRoll<=0){
				break;
			}
			viewRoll = fingerRoll * 0.6f;
			ViewHelper.setTranslationY(mchat,  viewRoll);
			break;
		case MotionEvent.ACTION_UP:
			fingerRoll =event.getY()- downY;
			viewRoll = fingerRoll * 0.6f;
			mChatOriginY = mchat.getY();
			locations = new int[2]; 
			mchat.getLocationOnScreen(locations);
			if(viewRoll<limit){
				//花了500毫秒执行了一个动画，，主要取决怎么用
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);  
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					public void onAnimationUpdate(ValueAnimator animation) {
						//500毫秒中UPDATE方法会被反复的调用
						// TODO Auto-generated method stub
						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
					    float y = mChatOriginY - progress*viewRoll;
					    mchat.setY(y);
					}
				});
			}else{
				ValueAnimator valueAnimator2 =ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator2.start();
				valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					public void onAnimationUpdate(ValueAnimator animation) {
						// TODO Auto-generated method stub
					    DisplayMetrics dm = new DisplayMetrics(); 
					    getWindowManager().getDefaultDisplay().getMetrics(dm); 
					    int height = dm.heightPixels;  //得到高度 

						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
						float y = mChatOriginY + progress*(height-locations[1]);
						mchat.setY(y);
					}
				});
			}
			//消息列表滑动的距离小于限定值，划回去
			//否则直接滑动到底部
		default:
			break;
		}
		return true;
	}
    
}