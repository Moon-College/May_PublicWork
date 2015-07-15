package com.limz.animationdemo.activity;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class AttributeAnimationActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	private Button startButton;
	private ImageView imageView;
	private boolean isRunning;
	private Context mContext;
	private float initX;
	private float initY;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribute);
        
        init();
        
    }

	private void init() {
		startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.image);
		isRunning = false;
		mContext = this;
	}

	@Override
	protected void onResume() {
		super.onResume();
//		getImageLocation();
	}

	private void getImageLocation() {
		int[] location = new int[2];
		imageView.getLocationOnScreen(location);
		initX = location[0];
		initY = location[1] - imageView.getHeight();
//		initX = imageView.getX();
//		initY = imageView.getY();
//		Log.d("mingzhu", "x = " + initX + " ;   y = " + initY);
//		Log.d("mingzhu", "imageView.getHeight() : " + imageView.getHeight());
	}

	public void onClick(View v) {

		if(!isRunning) {
			isRunning = true;
		} else {
			isRunning = false;
		}
		
		getImageLocation();
		final ValueAnimator animator = ValueAnimator.ofFloat(0, 1).setDuration(5000);
		animator.start();
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			public void onAnimationUpdate(ValueAnimator arg0) {
				if(!isRunning) {
					startButton.setText("开始动画");
				} else {
					float progress = Float.valueOf(
							animator.getAnimatedValue().toString());
					WindowManager wm = (WindowManager) mContext.getSystemService(
							Context.WINDOW_SERVICE);
					float x_len = wm.getDefaultDisplay().getWidth();
					float y_len = wm.getDefaultDisplay().getHeight();
					float temp = x_len * x_len / y_len;
					float dx = initX + progress * x_len;
					float dy = initY + dx * dx / temp;
					
					Log.d("mingzhu", "temp : " + temp);
					Log.d("mingzhu", "initX = " + initX + " ;   initY = " + initY);
					Log.d("mingzhu", "dx : " + dx + ",   dy : " + dy);
					if(dx <= x_len && dy <= y_len) {
						imageView.setX(dx);
						imageView.setY(dy);
						startButton.setText("暂停动画");
					} else {
						initAnimator();
					}
					
				}
			}
		});
	}
	
	private void initAnimator() {
		imageView.setX(initX);
		imageView.setY(initY);
		startButton.setText("暂停动画");
	}
}