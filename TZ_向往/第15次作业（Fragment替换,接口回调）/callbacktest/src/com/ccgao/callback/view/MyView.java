package com.ccgao.callback.view;

import com.ccgao.callback.R;
import com.ccgao.callback.Interface.MyListencener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	private float startX;
	private float startY;
	private Bitmap myBitmap;
	private MyListencener onMyListencener;
	public MyListencener getOnMyListencener() {
		return onMyListencener;
	}

	public void setOnMyListencener(MyListencener onMyListencener) {
		this.onMyListencener = onMyListencener;
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		myBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(myBitmap, 0, 0, null);
		super.onDraw(canvas);		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "ACTION_UP");
			if ((startX == event.getX()) && (startY == event.getY())) {
				onMyListencener.onKnok(this);
			}
			break;

		default:
			break;
		}
		return true;
	}

}
