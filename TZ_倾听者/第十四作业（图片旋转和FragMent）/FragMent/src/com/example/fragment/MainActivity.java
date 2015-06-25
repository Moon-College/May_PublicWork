package com.example.fragment;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	private ImageView img;
	private float fromAngle;
	private Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.img);
		img.setOnTouchListener(this);
		matrix = new Matrix();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//获取当前触摸的手指个数
		int pointerCount = event.getPointerCount();
		if (pointerCount == 1) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//获得XY坐标
				float startX = event.getX();
				float startY = event.getY();
				//根据坐标获取此坐标离图片中心点的角度
				fromAngle = getAngle(startX,startY);
				break;
			case MotionEvent.ACTION_MOVE:
				//获取XY坐标
				float x = event.getX();
				float y = event.getY();
				//根据坐标获取最新移动的角度，
				float newAngle = getAngle(x, y);
				//求出需要移动的角度值
				matrix.postRotate(fromAngle - newAngle, img.getWidth()/2,img.getHeight()/2);
				//设置移动
				img.setImageMatrix(matrix);
				//把当前的角度赋值给上一次的角度
				fromAngle = newAngle;
				break;
			case MotionEvent.ACTION_UP:
				break;
			default:
				break;
			}

		}

		return true;
	}
	
	/**
	 * 根据X和Y的坐标，返回离中心点的角度是多少
	 * @param startX
	 * @param startY
	 * @return
	 */
	private float getAngle(float startX, float startY) {
			//当前XY减去中心点的XY
			float dx = startX - img.getWidth()/2;
			float dy = img.getHeight()/2 - startY;
			//求出离中心点的弧度
			double atan2 = Math.atan2(dy, dx);
			//把弧度转换为角度
			float distance = (float) (atan2 * 180 / Math.PI);
			return distance;
	}

}
