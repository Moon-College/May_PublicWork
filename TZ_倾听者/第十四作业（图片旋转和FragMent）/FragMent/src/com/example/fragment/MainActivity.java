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
		//��ȡ��ǰ��������ָ����
		int pointerCount = event.getPointerCount();
		if (pointerCount == 1) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//���XY����
				float startX = event.getX();
				float startY = event.getY();
				//���������ȡ��������ͼƬ���ĵ�ĽǶ�
				fromAngle = getAngle(startX,startY);
				break;
			case MotionEvent.ACTION_MOVE:
				//��ȡXY����
				float x = event.getX();
				float y = event.getY();
				//���������ȡ�����ƶ��ĽǶȣ�
				float newAngle = getAngle(x, y);
				//�����Ҫ�ƶ��ĽǶ�ֵ
				matrix.postRotate(fromAngle - newAngle, img.getWidth()/2,img.getHeight()/2);
				//�����ƶ�
				img.setImageMatrix(matrix);
				//�ѵ�ǰ�ĽǶȸ�ֵ����һ�εĽǶ�
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
	 * ����X��Y�����꣬���������ĵ�ĽǶ��Ƕ���
	 * @param startX
	 * @param startY
	 * @return
	 */
	private float getAngle(float startX, float startY) {
			//��ǰXY��ȥ���ĵ��XY
			float dx = startX - img.getWidth()/2;
			float dy = img.getHeight()/2 - startY;
			//��������ĵ�Ļ���
			double atan2 = Math.atan2(dy, dx);
			//�ѻ���ת��Ϊ�Ƕ�
			float distance = (float) (atan2 * 180 / Math.PI);
			return distance;
	}

}
