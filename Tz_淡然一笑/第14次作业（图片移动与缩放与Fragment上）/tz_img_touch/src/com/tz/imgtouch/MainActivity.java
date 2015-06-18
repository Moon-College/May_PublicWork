package com.tz.imgtouch;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	/**
	 * 图片
	 */
	private ImageView img;
	/**
	 * 矩阵
	 */
	private Matrix matrix;
	/**
	 * 获取手指刚按下去x坐标,y坐标
	 */
	private float start_x, start_y;
	/**
	 * /获取手指移动x坐标,y坐标
	 */
	private float move_x, move_y;
	/**
	 * 中心点
	 */
	private Point middlePoint;
	/**
	 * 第一次的距离,新移动的距离
	 */
	private float oldDis, newDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取屏幕的分辨率
		// Display mDisplay = getWindowManager().getDefaultDisplay();
		// int width = mDisplay.getWidth();
		// int height = mDisplay.getHeight();
		// Log.i("INFO", "width = " + width);
		// Log.i("INFO", "height = " + height);

		img = (ImageView) findViewById(R.id.img);
		// 初始化矩阵
		matrix = new Matrix();
		// 设置图片矩阵
		img.setImageMatrix(matrix);
		// 设置图片onTouch事件
		img.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 获取手指的数量
		int pointerCount = event.getPointerCount();
		if(pointerCount == 1){
			//旋转图片
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				start_x = event.getX();
				start_y = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				move_x = event.getX();
				move_y = event.getY();
				
				matrix.setTranslate(0, 0);
				float atan2 = (float)Math.atan2(move_x - start_x, move_y - start_y);
				matrix.setRotate((float) (atan2*180/Math.PI));
				
				img.setImageMatrix(matrix);
				
				start_x = move_x;
				start_y = move_y;
				break;
			default:
				break;
			}
		}
		/*
		if (pointerCount == 1) {
			// 移动图片
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i("INFO", "down");
				// 获取手指刚按下去坐标
				start_x = event.getX();
				start_y = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "move");
				// 获取手指移动的坐标
				move_x = event.getX();
				move_y = event.getY();
				// 像素矩阵位移开始变化，开始运动
				matrix.postTranslate(move_x - start_x, move_y - start_y);
				// 当前的坐标就是上一次移动的坐标
				start_x = move_x;
				start_y = move_y;
				// IamgeView通过新的矩阵来摆放图片的位置
				img.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_UP:
				// Log.i("INFO", "up");
				break;
			default:
				break;
			}
		} 
		*/else if (pointerCount == 2) {
			// 缩放图片
			// event.getActionMasked()标记多根手指
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				// 获取中心点坐标
				middlePoint = getMiddlePoint(event);
				// 获取手指间的距离
				oldDis = getDisByEvent(event);
				break;
			case MotionEvent.ACTION_MOVE:
				// 获取两根手指moving的距离
				newDis = getDisByEvent(event);
				// 缩放比例
				float scale = newDis / oldDis;
				matrix.postScale(scale, scale, middlePoint.x, middlePoint.y);
				// 新的距离赋值给旧的距离，以便下一次继续缩放
				oldDis = newDis;
				img.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_POINTER_UP:
				// Log.i("INFO", "up");
				break;
			default:
				break;
			}
		}
		return true;
	}

	/**
	 * 获取中心点坐标
	 * 
	 * @param event
	 */
	private Point getMiddlePoint(MotionEvent event) {
		// 获取两根手指的坐标，计算中心点
		Point point = new Point();
		point.x = (int) ((event.getX() + event.getX(1)) / 2);
		point.y = (int) ((event.getY() + event.getY(1)) / 2);
		return point;
	}

	/**
	 * /获取两根手指之间的距离
	 * 
	 * @param event
	 * @return
	 */
	private float getDisByEvent(MotionEvent event) {
		float dx = event.getX(0) - event.getX(1);
		float dy = event.getY(0) - event.getY(1);
		// return (float) Math.sqrt(dx*dx+dy*dy);
		return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
}
