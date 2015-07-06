package com.itskylin.android.rotation.study;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	private OnHitClickListener onHitClickListener;

	private Paint paint;

	private float x;

	private float y;

	public MyView(Context context) {
		super(context);
		initView();
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(20);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawText("Test.....", 100, 100, paint);
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = event.getX();
			y = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			if (x == event.getX() && y == event.getY()) {
				onHitClickListener.onHitClick(this);
			}
			break;
		default:
			break;
		}
		return true;
	}

	public OnHitClickListener getOnHitClickListener() {
		return onHitClickListener;
	}

	public void setOnHitClickListener(OnHitClickListener onHitClickListener) {
		this.onHitClickListener = onHitClickListener;
	}
}
