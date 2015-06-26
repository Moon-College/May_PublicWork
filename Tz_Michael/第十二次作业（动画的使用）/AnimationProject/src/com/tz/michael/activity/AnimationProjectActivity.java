package com.tz.michael.activity;

import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class AnimationProjectActivity extends Activity {
	
	private ImageView img;
	/**水平方向的加速度*/
	private float a_x=-2f;
	/**竖直方向的加速度*/
	private float a_y=9.8f;
	/**水平方向的初速度*/
	private float v_x=50f;
	/**view的坐标*/
	private float cur_x,cur_y;
	/**全局变量时间t*/
	private int t;
	private Handler handler=new Handler();
	private Runnable runnable=new Runnable() {
		
		public void run() {
			//逻辑方法
			logic();
			ViewHelper.setTranslationX(img, cur_x);
			ViewHelper.setTranslationY(img, cur_y);
			handler.removeCallbacks(runnable);
			handler.postDelayed(runnable, 500);
			t++;
		}
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        img=(ImageView) findViewById(R.id.img);
    }
    
    /**
     * 数据变化逻辑
     */
    protected void logic() {
		cur_x=(float) (v_x*t+0.5*a_x*Math.pow(t, 2));
		cur_y=(float) (0.5*Math.pow(t, 2)*a_y);
	}

	public void myOnClick(View v){
    	switch (v.getId()) {
		case R.id.btn_start:
			startAnimation();
			break;
		case R.id.btn_jump:
			startActivity(new Intent(this,WeiXinDownPullActivity.class));
			break;
		default:
			break;
		}
    }

    /**
     * 开启动画
     */
	private void startAnimation() {
		handler.post(runnable);
	}
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}
	
	
}