package com.example.tz_touch_image;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	private ImageView image;
	private Matrix matrix;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image =(ImageView) findViewById(R.id.image);
		matrix = new Matrix();
		image.setImageMatrix(matrix);
		image.setOnTouchListener(this);
	}

	float startX;
	float startY;
	Point midPoint;
	float oldDis;
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int pointerCount = event.getPointerCount(); //手指数量
		Log.e("wdj","pointerCount = " + pointerCount);
		if(pointerCount == 1) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = (int) event.getX();
				startY = (int) event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e("wdj","ACTION_MOVE");
				float moving_x = event.getX();
				float moving_y = event.getY();
				//matrix.setTranslate(moving_x - startX, moving_y - startY);
				matrix.postTranslate(moving_x - startX, moving_y - startY);
				startX = moving_x;
				startY = moving_y;
				image.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_UP:
				
				break;

			default:
				break;
			}
		} else if(pointerCount == 2){
			//缩放
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				//首先获取两根手指的坐标计算中心点
				midPoint = getMidPoint(event);
				oldDis = getDisByEvent(event);
				break;
			case MotionEvent.ACTION_MOVE:
				float newDis = getDisByEvent(event);
				float scale = newDis / oldDis;
				matrix.postScale(scale, scale, midPoint.x,midPoint.y);
				oldDis = newDis;
				image.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_POINTER_UP:
				//
				break;

			default:
				break;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param event
	 */
	private float getDisByEvent(MotionEvent event) {
		float dx = event.getX(0) - event.getY(1);
		float dy = event.getY(0) - event.getY(1);
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	private Point getMidPoint(MotionEvent event) {
		Point p = new Point();
		float x1 = event.getX(0);
		float y1 = event.getY(0);
		float x2 = event.getX(1);
		float y2 = event.getY(1);
		p.x = (int) ((x1 + x2) / 2);
		p.y = (int) ((y1 + y2) / 2);
		return p;
	}

	

}
