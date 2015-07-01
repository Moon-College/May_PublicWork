package com.ccgao.myanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        iv = (ImageView) findViewById(R.id.iv);
    }
    
	public void play(View btn){
		//加载动画
//    	Animation animation= AnimationUtils.loadAnimation(this, R.anim.alpha); 
//    	Animation animation= AnimationUtils.loadAnimation(this, R.anim.rotate); 
//    	Animation animation= AnimationUtils.loadAnimation(this, R.anim.scale); 
    	Animation animation= AnimationUtils.loadAnimation(this, R.anim.translate); 
    	//应用动画到控件
    	iv.startAnimation(animation);
    	
    	//代码手动创建动画
//    	RotateAnimation ra= new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//    	ra.setDuration(2000);
//    	ra.setFillAfter(true);
//    	iv.startAnimation(ra);
    }
}