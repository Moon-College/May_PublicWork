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
	 * ��һ�ε�����
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
		 * ��ʼ����ת�����ĵ㣬��onResume��onStart����ôû��ȡ�������Ի��������ȡ ���Գɹ�
		 */
		centerX = iv.getWidth() / 2;
		centerY = iv.getHeight() / 2;
		Log.d(TAG, "get in onWindowFocusChanged centerX=" + centerX
				+ ",centerY=" + centerY);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		/*
		 * ��ȡ��ָ�Ĵ�������
		 */
		int pointerCount = event.getPointerCount();
		/*
		 * ��ȡaction
		 */
		int action = event.getAction();
		Log.d(TAG, "pointerCount=" + pointerCount + ",action=" + action);
		// TODO Auto-generated method stub
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "into ACTION_DOWN");

			/*
			 * ��¼��һ�ε�����ͼ���б�ʲ��Ҽ�¼
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
	 * �����֪�����ε������ߣ����������Ҷ���õ������ε������ڽ� �����Ϳ��Լ�����������֮��ļн�
	 * 
	 * @param centerX
	 *            ���ĵ��λ�õ�x���꣬�нǱ仯�ļ��������ĵ�Ϊ�걾
	 * @param centerY
	 *            ���ĵ��λ�õ�y���꣬�нǱ仯�ļ��������ĵ�Ϊ�걾
	 * @param preX
	 *            ֮ǰ�������x����
	 * @param preY
	 *            ֮ǰ�������y����
	 * @param nowX
	 *            ��ǰ�������x����
	 * @param nowY
	 *            ��ǰ�������y����
	 * @return �����ԣ�centerX��centerY��Ϊ����ԭ��ģ�����(preX,preY),(nowX,nowY)֮��ĽǶ�
	 */
	private float getActionDegrees(float centerX, float centerY, float preX,
			float preY, float nowX, float nowY) {

		/*
		 * (preX,preY),(nowX,nowY)��������֮��ľ���,Ҫ����ĽǶȾ���a�����߶�Ӧ�Ƕ�
		 */
		float a = computLengthBetween(preX, preY, nowX, nowY);
		float b = computLengthBetween(centerX, centerY, preX, preY);
		float c = computLengthBetween(centerX, centerY, nowX, nowY);
		// ���Ҷ���,����a��һ�߶�Ӧ�ĽǶȵ�cosֵ
		float cosA = (b * b + c * c - a * a) / (2 * b * c);
		// ��������ֵΪָ�����ֵĽǶȣ�Math����Ϊ�����ṩ�ķ���,�õ�a�߶�Ӧ�ĽǶ�degree
		float arcA = (float) Math.acos(cosA);
		float degree = (float) (arcA * 180 / Math.PI);

		/*
		 * ��������ı仯���ж��ֻ����ķ���
		 */
		// ���ǰ��������y��>=centerY������3��4����
		if (preY > centerY && nowY > centerY) {
			// 3--->4 ����
			if (nowX > centerX && preX < centerX) {
				return -degree;
			} else if (nowX < centerX && preX > centerX) {
				// 4--->3������
				return degree;
			}
		}

		// ����������x��>=centerX,����1��4����
		if (preX > centerX && nowX > centerX) {
			// 1---4 ����
			if (nowY > centerY && preY < centerY) {
				return degree;
			} else if (nowY < centerY && preY > centerY) {
				// 4--->1 ����
				return -degree;
			}
		}

		// ����������x��<centerX,����2��3����
		if (preX < centerX && nowX < centerX) {
			// 2--->3 ����
			if (nowY > centerY && preY < centerY) {
				return -degree;
			} else if (nowY < centerY && preY > centerY) {
				// 3---2����
				return degree;
			}
		}

		// ����������y��<centerY,����1��2����
		if (preY < centerY && nowY < centerY) {
			// 2--->1����
			if (nowX > centerX && preX < centerX) {
				return degree;
			} else if (nowX < centerX && preX > centerX) {
				// 1--->2����
				return -degree;
			}
		}

		float tanb = (preY - centerY) / (preX - centerX);
		float tanc = (nowY - centerY) / (nowX - centerX);

		if (nowY > centerY && preY > centerY && nowX > centerX
				&& preX > centerX && tanb > tanc) // ��4����
		{
			return -degree;
		} else if (nowY > centerY && preY > centerY && nowX < centerX
				&& preX < centerX && tanb > tanc) // ��3����
		{
			return -degree;
		} else if (nowY < centerY && preY < centerY && nowX < centerX
				&& preX < centerX && tanb > tanc) // ��2����
		{
			return -degree;
		} else if (nowY < centerY && preY < centerY && nowX > centerX
				&& preX > centerX && tanb > tanc) // ��1����
		{
			return -degree;
		}

		// �����Ķ�������
		return degree;
	}

	/**
	 * ����������֮��ľ���
	 * 
	 * @param x1
	 *            ��һ�����x����
	 * @param y1
	 *            ��һ�����y����
	 * @param x2
	 *            �ڶ������x����
	 * @param y2
	 *            �ڶ������y����
	 * @return ����֮��ľ���
	 */
	private float computLengthBetween(float x1, float y1, float x2, float y2) {
		// TODO Auto-generated method stub
		float dx = x1 - x2;
		float dy = y1 - y2;

		return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}