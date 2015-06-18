package com.example.valueanimator.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.valueanimator.R;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout chat; // 需要滑动的View
	
	private ImageView eyes; // 眼睛
	
	private float scrollY; //按下的Y坐标
	
	private float roallY; //真实移动的距离
	
	private float chatHeight; //View的Y坐标

	private int screenHeight; //屏幕高度

	private float chatScrollY;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initeView();
	}

	/**
	 * 初始化控件
	 */
	private void initeView() {
		
		//绑定
		chat = (RelativeLayout) findViewById(R.id.rl2);
		eyes = (ImageView) findViewById(R.id.iv_eye);
		
		//绑定事件
		chat.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		chatHeight = chat.getY(); //获取当前view的Y坐标
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//按下时
			
			scrollY = event.getY(); //记录按下的Y坐标
			
			break;
		case MotionEvent.ACTION_MOVE:
			//移动时
			
			float distance = event.getY() - scrollY; //求出他们的距离
			
			roallY = (float) (distance * 0.6); //计算距离
			
			ViewHelper.setTranslationY(chat, roallY); //为这个View设置动画
			
			break;
		case MotionEvent.ACTION_UP:
			//松开时
			//判断移动的距离是否超过了眼睛的高度
			if(roallY > eyes.getHeight()+80){
			screenHeight = getScreenHeight(); //获取屏幕高度
			chatScrollY = screenHeight - chatHeight; //屏幕高度 - view的Y坐标，就是需要移动的距离
			ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(500);
			animator.start();
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator arg0) {
					float value = Float.valueOf(arg0.getAnimatedValue().toString()); //求出每时间课的
					float y = chatHeight + (chatScrollY * value); //获得每一个时刻需要移动的Y坐标
					chat.setY(y); //设置Y坐标
				}
			});
			}else{
				//让view返回到原来的位置
				ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(500);
				animator.start();
				animator.addUpdateListener(new AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator arg0) {
						float value = Float.valueOf(arg0.getAnimatedValue().toString()); //取出没一点空间指标的值
						float y = chatHeight - (roallY * value); //算出每一点需要移动的量
						chat.setY(y); //让这个view设置他的移动
					}
				});
			}
			break;
		default:
			break;
		}
		return true;
	}
	/**
	 * 获取屏幕高度
	 * @return
	 */
	public int getScreenHeight(){
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

}
