package com.decent.decenttouch;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class DecentTouchActivity extends FragmentActivity implements
		OnTouchListener {
	private static final String TAG = "DecentTouchActivity";
	/*
	 * 上一次的坐标
	 */
	private float preX1;
	private float preY1;
	private float preX2;
	private float preY2;
	private float centerX;
	private float centerY;
	/** Called when the activity is first created. */
	private ImageView iv;
	private Matrix matrix;

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public void resetPicture() {
		matrix.setRotate(0, centerX, centerY);
		iv.setImageMatrix(matrix);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnTouchListener(this);
		matrix = new Matrix();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStop();
		centerX = iv.getWidth() / 2;
		centerY = iv.getHeight() / 2;
		Log.d(TAG, "get in onStart centerX=" + centerX + ",centerY=" + centerY);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		centerX = iv.getWidth() / 2;
		centerY = iv.getHeight() / 2;
		Log.d(TAG, "get in onWindowFocusChanged centerX=" + centerX
				+ ",centerY=" + centerY);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		/*
		 * 初始化旋转的中心点，在onResume和onStart里面么没有取到，所以换在这里获取 可以成功
		 */
		centerX = iv.getWidth() / 2;
		centerY = iv.getHeight() / 2;
		Log.d(TAG, "get in onWindowFocusChanged centerX=" + centerX
				+ ",centerY=" + centerY);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		/*
		 * 获取手指的触摸个数
		 */
		int pointerCount = event.getPointerCount();
		/*
		 * 获取action
		 */
		int action = event.getAction();
		Log.d(TAG, "pointerCount=" + pointerCount + ",action=" + action);
		// TODO Auto-generated method stub
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "into ACTION_DOWN");

			/*
			 * 记录上一次的坐标和计算斜率并且记录
			 */
			preX1 = event.getX();
			preY1 = event.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "into ACTION_MOVE");
			float nowX1 = event.getX();
			float nowY1 = event.getY();

			float nowGradient = (float) getActionDegrees(centerX, centerY,
					preX1, preY1, nowX1, nowY1);
			matrix.postRotate(nowGradient, centerX, centerY);
			if (nowGradient < 0) {
				Log.d(TAG, "into nowGradient=" + nowGradient);
				Log.d(TAG, "into nowX1=" + nowX1 + ",nowY1=" + nowY1
						+ ",preX1=" + preX1 + ",preY1=" + preY1);
			}
			iv.setImageMatrix(matrix);
			preX1 = nowX1;
			preY1 = nowY1;
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "into ACTION_UP");

			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 如果已知三角形的三条边，可以由余弦定理得到三角形的三个内角 这样就可以计算出两次左边之间的夹角
	 * 
	 * @param centerX
	 *            中心点的位置的x坐标，夹角变化的计算以中心点为标本
	 * @param centerY
	 *            中心点的位置的y坐标，夹角变化的计算以中心点为标本
	 * @param preX
	 *            之前触摸点的x坐标
	 * @param preY
	 *            之前触摸点的y坐标
	 * @param nowX
	 *            当前触摸点的x坐标
	 * @param nowY
	 *            当前触摸点的y坐标
	 * @return 返回以（centerX，centerY）为坐标原点的，两个(preX,preY),(nowX,nowY)之间的角度
	 */
	private float getActionDegrees(float centerX, float centerY, float preX,
			float preY, float nowX, float nowY) {

		/*
		 * (preX,preY),(nowX,nowY)这两个点之间的距离,要计算的角度就是a这条边对应角度
		 */
		float a = computLengthBetween(preX, preY, nowX, nowY);
		float b = computLengthBetween(centerX, centerY, preX, preY);
		float c = computLengthBetween(centerX, centerY, nowX, nowY);
		// 余弦定理,计算a那一边对应的角度的cos值
		float cosA = (b * b + c * c - a * a) / (2 * b * c);
		// 返回余弦值为指定数字的角度，Math函数为我们提供的方法,得到a边对应的角度degree
		float arcA = (float) Math.acos(cosA);
		float degree = (float) (arcA * 180 / Math.PI);

		/*
		 * 根据坐标的变化，判断手滑动的方向
		 */
		// 如果前后两个点y都>=centerY，则都在3，4象限
		if (preY > centerY && nowY > centerY) {
			// 3--->4 反向
			if (nowX > centerX && preX < centerX) {
				return -degree;
			} else if (nowX < centerX && preX > centerX) {
				// 4--->3正向向
				return degree;
			}
		}

		// 如果两个点的x都>=centerX,则都在1，4象限
		if (preX > centerX && nowX > centerX) {
			// 1---4 正向
			if (nowY > centerY && preY < centerY) {
				return degree;
			} else if (nowY < centerY && preY > centerY) {
				// 4--->1 反向
				return -degree;
			}
		}

		// 如果两个点的x都<centerX,则都在2，3象限
		if (preX < centerX && nowX < centerX) {
			// 2--->3 反向
			if (nowY > centerY && preY < centerY) {
				return -degree;
			} else if (nowY < centerY && preY > centerY) {
				// 3---2正向
				return degree;
			}
		}

		// 如果两个点的y都<centerY,则都在1，2象限
		if (preY < centerY && nowY < centerY) {
			// 2--->1正向
			if (nowX > centerX && preX < centerX) {
				return degree;
			} else if (nowX < centerX && preX > centerX) {
				// 1--->2反向
				return -degree;
			}
		}

		float tanb = (preY - centerY) / (preX - centerX);
		float tanc = (nowY - centerY) / (nowX - centerX);

		if (nowY > centerY && preY > centerY && nowX > centerX
				&& preX > centerX && tanb > tanc) // 第4象限
		{
			return -degree;
		} else if (nowY > centerY && preY > centerY && nowX < centerX
				&& preX < centerX && tanb > tanc) // 第3象限
		{
			return -degree;
		} else if (nowY < centerY && preY < centerY && nowX < centerX
				&& preX < centerX && tanb > tanc) // 第2象限
		{
			return -degree;
		} else if (nowY < centerY && preY < centerY && nowX > centerX
				&& preX > centerX && tanb > tanc) // 第1象限
		{
			return -degree;
		}

		// 其他的都是正向
		return degree;
	}

	/**
	 * 计算两个点之间的距离
	 * 
	 * @param x1
	 *            第一个点的x坐标
	 * @param y1
	 *            第一个点的y坐标
	 * @param x2
	 *            第二个点的x坐标
	 * @param y2
	 *            第二个点的y坐标
	 * @return 两点之间的距离
	 */
	private float computLengthBetween(float x1, float y1, float x2, float y2) {
		// TODO Auto-generated method stub
		float dx = x1 - x2;
		float dy = y1 - y2;

		return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}