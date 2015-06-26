package com.cn.test;

import com.cn.test.utils.Reflection;
import com.nineoldandroids.animation.ValueAnimator;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created on2015-6-19 ÉÏÎç9:23:27 BallActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class BallActivity extends Activity implements OnClickListener {
	private LinearLayout ll;
	private Button start;
	private float x = 0, y = 0;
	private ImageView img;
	private LayoutParams lp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Reflection.initView(this);
		initView();
		start.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		Bitmap bitmap = Bitmap.createBitmap(120, 120, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setAntiAlias(true);
		canvas.drawCircle(60, 20, 20, paint);

		img = new ImageView(this);
		img.setBackgroundDrawable(new BitmapDrawable(bitmap));
		lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.BOTTOM;
		img.setLayoutParams(lp);
		ll.addView(img);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 50).setDuration(
				1000);
		valueAnimator.start();
		valueAnimator
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float progress = Float.parseFloat(animation
								.getAnimatedValue().toString());
						x = (int) (progress * 50) + 20;
						y = (int) Math.sqrt(x);
						if (x != 20) {
							img.setX(x);
							img.setY(y > 0 ? y * 50 : -y * 50);
						}
					}
				});
	}

}
