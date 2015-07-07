package com.keven.customview;

import com.keven.damp.R;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

public class RippleView extends Button {

	private float mDownX;
	private float mDownY;
	// private float mMaxRadius = 50;
	private float mRadius;
	private Path path = new Path();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mRippleColor = Color.parseColor("#3c3cff");
	private float disappearRadius;
	private float screenDensity;
	private int mMaxRadius;
	private float mAlpha = 0.3f;

	public RippleView(Context context) {
		super(context);

	}

	public RippleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public RippleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RippleView);
		mRippleColor = typedArray.getColor(R.styleable.RippleView_rippleColor,
				mRippleColor);
		mAlpha = typedArray.getFloat(R.styleable.RippleView_alpha, mAlpha);
		typedArray.recycle();
		screenDensity = context.getResources().getDisplayMetrics().density;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		if (mRadius <= 0) {
			return;
		}
		// À©É¢½¥±ääÖÈ¾
		RadialGradient radialGradient = new RadialGradient(mDownX, mDownY,
				mRadius, Color.BLUE, mRippleColor, TileMode.CLAMP);
		mPaint.setShader(radialGradient);
		canvas.save();
		path.reset();
		path.addCircle(mDownX, mDownY, mRadius, Path.Direction.CW);// Ë³Ê±Õë
		canvas.drawPath(path, mPaint);
		canvas.restore();
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = event.getX();
			mDownY = event.getY();
			Log.e("height", getHeight() + "");

			mMaxRadius = RippleUtils.dp2px(getHeight() / 2, screenDensity);
			// System.out.println(mMaxRadius);
			Log.e("maxRadius", mMaxRadius + "");
			ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mMaxRadius)
					.setDuration(200);
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

				public void onAnimationUpdate(ValueAnimator animation) {
					float progress = Float.parseFloat(animation
							.getAnimatedValue().toString());
					RippleView.this.mRadius = progress;// Ô²°ë¾¶
					invalidate();
				}
			});
			valueAnimator.start();
			break;
		case MotionEvent.ACTION_MOVE:
			mDownX = event.getX();
			mDownY = event.getY();
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			mDownX = event.getX();
			mDownY = event.getY();
			disappearRadius = Math.max(
					RippleUtils.dp2px(getWidth(), screenDensity), mRadius);
			valueAnimator = ValueAnimator.ofFloat(mMaxRadius, disappearRadius)
					.setDuration(200);
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

				public void onAnimationUpdate(ValueAnimator animation) {
					float progress = Float.parseFloat(animation
							.getAnimatedValue().toString());
					if (progress == disappearRadius) {
						RippleView.this.mRadius = 0;
					}
					invalidate();
				}
			});
			valueAnimator.start();

			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
