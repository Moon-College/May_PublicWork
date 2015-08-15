package com.hq.ays.activity;


import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.hq.ays.common.BaseActivity;
import com.hq.ays.activity.R;

public class SplashActivity extends BaseActivity{
	private RelativeLayout relative_main;
	@Override
	public void setContentView() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initViews() {
		relative_main = (RelativeLayout) findViewById(R.id.splash_main);
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initData() {
		//开启动画
		startAnimation();
	}

	/**
	 * 开启视图动画
	 */
	private void startAnimation() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_alpha);
		relative_main.startAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//动画结束了
				startLoginActivity();
			}
		});
	}

	public void startLoginActivity(){
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

}