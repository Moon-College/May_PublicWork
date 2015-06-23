package com.jim.mypic;

import com.jimmypic.R;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	private ImageView imageView;
	private Matrix matrix;// 矩阵

	private float startX1;// 手指开始的X位置
	private float startY1;// 手指开始的Y位置

	private float oldDis, newDis;// 手指间开始的距离与结束的距离

	private float scale;// 测量比例

	private PointF pointMid;// 中心点
	private double fromDegree;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageResource(R.drawable.test1);
		imageView.setOnTouchListener(this);
		matrix = new Matrix();
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		int pointerCount = event.getPointerCount();// 获取点的数量也就是手指的数量
		int w = imageView.getWidth();
		int h = imageView.getHeight();
		if (pointerCount == 1) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				startX1 = event.getX(0);
				startY1 = event.getX(0);
				float dx = startX1 - w/2;
				float dy = h/2 - startY1;
				float anta2 = (float)Math.atan2(dy, dx);
				fromDegree = anta2*180/Math.PI;
				break;
			case MotionEvent.ACTION_MOVE:
				float moveX = event.getX(0);
				float moveY = event.getY(0);
				matrix.postTranslate( moveX-startX1, moveY - startY1);
				imageView.setImageMatrix(matrix);
				startX1 = moveX;
				startY1 = moveY;
				break;
			default:
				break;
			}
		} else if (pointerCount == 2) {
			switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDis = getDisByPointer(event);
				pointMid = getMid(event);
				break;
			case MotionEvent.ACTION_MOVE:
				newDis = getDisByPointer(event);
				scale = newDis / oldDis;
				pointMid = getMid(event);
				matrix.postSkew(scale, scale, pointMid.x, pointMid.y);
				imageView.setImageMatrix(matrix);
				oldDis = newDis;
				break;
			default:
				break;
			}
		}
		return true;
	}

	private PointF getMid(MotionEvent event) {
		// TODO Auto-generated method stub
		PointF pf = new PointF();
		pf.x = (event.getX(0) + event.getX(1)) / 2;
		pf.y = (event.getY(0) + event.getY(1)) / 2;
		return pf;
	}

	private float getDisByPointer(MotionEvent event) {
		// TODO Auto-generated method stub
		float x1 = event.getX(0);
		float y1 = event.getY(0);

		float x2 = event.getX(1);
		float y2 = event.getY(1);
		return FloatMath.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
}