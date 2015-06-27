package com.ccgao.imgescale;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
	private ImageView img;
	private Matrix matrix;
	private float startX;
	private float startY;
	private float oldDis;
	private Point mid_point;
	private Point imgCPoint;
	private float oldDegrees;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		img = (ImageView) findViewById(R.id.img);
		img.setOnTouchListener(this);
		matrix = new Matrix();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		imgCPoint = getImgCenter();
	}

	public boolean onTouch(View v, MotionEvent event) {
		int pointerCount = event.getPointerCount();
		if (pointerCount == 1) { // 一个手指拖动
			int action = event.getAction();
			/*
			 * 移动位置 switch (action) { case MotionEvent.ACTION_DOWN: startX = event.getX(); startY = event.getY(); break; case MotionEvent.ACTION_MOVE: float x = event.getX(); float y = event.getY(); matrix.postTranslate(x - startX, y - startY); img.setImageMatrix(matrix); startX = x; startY = y; break; case MotionEvent.ACTION_UP:
			 * 
			 * break;
			 * 
			 * default: break;
			 */
			// 旋转
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				oldDegrees = getDegrees(event);
				break;
			case MotionEvent.ACTION_MOVE:
				float newDegrees = getDegrees(event);
				matrix.postRotate(oldDegrees-newDegrees, imgCPoint.x, imgCPoint.y);
				img.setImageMatrix(matrix);
				oldDegrees = newDegrees;
				break;
			case MotionEvent.ACTION_UP:

				break;

			default:
				break;
			}
		} else if (pointerCount == 2) { // 两个手指缩放
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				mid_point = getCenterPoint(event);
				oldDis = getPointSpace(event);
				break;
			case MotionEvent.ACTION_MOVE:
				float newDis = getPointSpace(event);
				float scale = newDis / oldDis;
				matrix.postScale(scale, scale, mid_point.x, mid_point.y);
				img.setImageMatrix(matrix);
				oldDis = newDis;
				break;
			case MotionEvent.ACTION_POINTER_UP:

				break;

			default:
				break;
			}
		}

		return true;
	}

	private float getDegrees(MotionEvent event) {
		float degrees = 0;
		float x = event.getX() - imgCPoint.x;
		float y = imgCPoint.y - event.getY();
		degrees = (float) (Math.atan2(y, x) * (180 / Math.PI));
		return degrees;
	}

	/**
	 * 计算两根手指间距
	 * 
	 * @param event
	 *            触碰事件
	 * @return
	 * @return
	 */
	private float getPointSpace(MotionEvent event) {
		float dx = event.getX(0) - event.getX(1);
		float dy = event.getY(0) - event.getY(1);
		return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	/**
	 * 计算两根手指的中心点
	 * 
	 * @param event
	 *            手指触碰事件
	 * @return
	 */
	private Point getCenterPoint(MotionEvent event) {
		float pointX1 = event.getX(0);
		float pointY1 = event.getY(0);
		float pointX2 = event.getX(1);
		float pointY2 = event.getY(1);
		Point point = new Point();
		point.x = (int) ((pointX1 + pointX2) / 2);
		point.y = (int) ((pointY1 + pointY2) / 2);
		return point;
	}

	private Point getScreemCenter() {
		Point point = new Point();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		point.x = dm.widthPixels / 2;// 宽度
		point.y = dm.heightPixels / 2;// 高度
		return point;
	}

	private Point getImgCenter() {
		Point point = new Point();
		point.x = img.getWidth() / 2;// 宽度
		point.y = img.getHeight() / 2;// 高度
		return point;
	}
}