package com.rocy.tzclasss12;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener {
	private RelativeLayout rl;
	private ImageView eye;
	//eye的高度
	private int topheight;
	
	//活动时候的坐标
	float moveY;
	private ValueAnimator upAnimator;
	private ValueAnimator downAnimator;
	private float upY;
	private float rlY;
	private float realtranslation;
	private int heightPixels;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		rl = (RelativeLayout) findViewById(R.id.rl2);
		eye = (ImageView) findViewById(R.id.iv_eye);
		rl.setOnTouchListener(this);
		
		topheight = eye.getHeight()+100;
		//上升动画
		upAnimator =ValueAnimator.ofFloat(0f,1f);
		upAnimator.setDuration(200);
		//下降动画
		downAnimator =ValueAnimator.ofFloat(0f,1f);
		downAnimator.setDuration(500);
		WindowManager wm =(WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics =new DisplayMetrics();
		display.getMetrics(metrics);
		heightPixels=metrics.heightPixels;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.rl2){
		int action = event.getAction();
		//获得手指y坐标
		float getY = 0 ;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "ACTION_DOWN");
			//按下去的时候捕获Y轴坐标
			getY = event.getY();
			break;
			
		case MotionEvent.ACTION_MOVE:
			moveY=event.getY()-getY;
			//滑动距离
			if(event.getY()<0){
				break;
			}else{
				rl.setTranslationY((float) (moveY*0.5));
			}
			Log.i("INFO", "ACTION_MOVE"+moveY);
			break;
			
		case MotionEvent.ACTION_CANCEL:	
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "ACTION_UP");
			//获得移动距离
			float translation = event.getY()-getY;
			realtranslation=(float) (translation*0.6);
			//获得控件实际坐标
			rlY = rl.getY();
			
			
			
			//自动返回到原点
			upAnimator.start();
			if(rlY<topheight){
			upAnimator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
						float value =Float.valueOf( animation.getAnimatedValue().toString());
						rl.setY(rlY-realtranslation*value);
						if(value==1){
							Toast.makeText(MainActivity.this, "到顶了", 0).show();
						}
				}
			});
			
			}else{
			 downAnimator.start();
			 downAnimator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					float progress= (Float) animation.getAnimatedValue();
					//改变rl的坐标
					rl.setY(rl.getHeight()+(heightPixels-rl.getHeight())*progress);
				}
			});
			}
			break;

		default:
			break;
		}
		return true;
		}
		return super.onTouchEvent(event);
	}
	
	
}
