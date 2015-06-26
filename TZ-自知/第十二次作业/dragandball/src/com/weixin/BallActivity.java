package com.weixin;

import com.example.dragandball.R;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class BallActivity extends Activity {
	private LinearLayout root;
	private Button start;
	
	private int x = 0, y = 0;
	private ImageView iv;
	private LayoutParams lp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myball);
		root = (LinearLayout) findViewById(R.id.root);
		start = (Button) findViewById(R.id.start);
		
		init();
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,50).setDuration(1000);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
						x = (int) (progress * 50) + 20;
						y = (int) Math.sqrt(x);
						if( x != 20) {
							iv.setX(x);
							iv.setY(y>0?y * 50:-y * 50); 
						}
					}
				});
			}
		});
	}
	private void init() {
		Bitmap bitmap = Bitmap.createBitmap(120, 120, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		canvas.drawCircle(60, 20, 20, paint);
		
		iv = new ImageView(this);
		iv.setBackgroundDrawable(new BitmapDrawable(bitmap));
		lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.BOTTOM ;
		iv.setLayoutParams(lp);
		root.addView(iv);
	}
	
}
