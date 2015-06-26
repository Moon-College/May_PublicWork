package com.decent.decentanimation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.decent.util.ReflictionUtil;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

public class GravityBallActivity extends Activity implements OnClickListener {

	protected static final String TAG = "GravityBallActivity";
	private Button startFall;
	private ImageView cameraBall;
	private int screenWidth;
	private int screenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gravityball);
		ReflictionUtil.InjectionView(GravityBallActivity.class.getName(),
				R.class.getName(), this);
		startFall.setOnClickListener(this);
		screenWidth = getWindowManager().getDefaultDisplay().getWidth()
				- 2*cameraBall.getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight()
				- 2*cameraBall.getHeight();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startGravityFallAnimation();
	}

	private void startGravityFallAnimation() {
		// TODO Auto-generated method stub
		ValueAnimator va = ValueAnimator.ofFloat(0, 1).setDuration(5000);
		va.start();

		va.addUpdateListener(new AnimatorUpdateListener() {

			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				float progress = Float.valueOf(arg0.getAnimatedValue()
						.toString());
				float x = progress*screenWidth;
				float y = progress*screenHeight+0.0002f*x+0.0004f*x*x;
				Log.d(TAG,"startGravityFallAnimation x="+x+",y="+y);
				ViewHelper.setX(cameraBall, x);
				ViewHelper.setY(cameraBall, y);
				/*
				 * 完成的时候回到原点
				 */
				if(progress == 1)
				{
					ViewHelper.setX(cameraBall, 0);
					ViewHelper.setY(cameraBall, 0);					
				}
			}
		});
	}

}
