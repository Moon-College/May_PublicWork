package com.tz.michael.activity;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class WeiXinDownPullActivity extends Activity implements OnTouchListener{

	private RelativeLayout ra_pre;
	private ImageView img;
	/**手指按下时的y坐标*/
	private float downY;
	/**view滑动的距离*/
	private float viewScroll;
	private int flag=1;
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			final int h_flag=msg.arg1;
			final int h_originY=msg.arg2;
			ValueAnimator vAnimator=new ValueAnimator().ofFloat(0,1).setDuration(500);
			vAnimator.start();
			vAnimator.addUpdateListener(new AnimatorUpdateListener() {
				
				public void onAnimationUpdate(ValueAnimator arg0) {
					float rate=Float.parseFloat(arg0.getAnimatedValue().toString());
					ViewHelper.setTranslationY(ra_pre, h_flag==1?viewScroll*rate+h_originY:h_originY-viewScroll*rate);
				}
			});
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixin_pulldown);
		ra_pre=(RelativeLayout) findViewById(R.id.ra_pre);
		img=(ImageView) findViewById(R.id.img);
		ra_pre.setOnTouchListener(this);
	}

	public boolean onTouch(View v, MotionEvent event) {
		int height_limit=200;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY=event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float fingerScroll = event.getY()-downY;
			if(fingerScroll<=0){
				break;
			}
			viewScroll=(float) (fingerScroll*0.6);
			ViewHelper.setTranslationY(ra_pre, viewScroll);
			break;
		case MotionEvent.ACTION_UP:
			int[] location=new int[2];
			ra_pre.getLocationOnScreen(location);
			int mOriginY = location[1];
			if(viewScroll<height_limit){
				flag=0;
			}
			Log.i("flag", "---vv--"+viewScroll);
			Log.i("flag", "----------"+flag);
			Message msg=handler.obtainMessage();
			msg.arg2=mOriginY;
			msg.arg1=flag;
			handler.sendMessage(msg);
			break;
		default:
			break;
		}
		return true;
	}
	
}
