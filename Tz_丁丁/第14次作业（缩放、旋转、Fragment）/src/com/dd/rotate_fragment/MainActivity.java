package com.dd.rotate_fragment;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
	private ImageView iv;
	private Matrix matrix;
	private float startX, startY;
	private float startX2;
	private float startY2;
	private float oldDis,newDis; 	
	private float scale;
	private PointF pointMid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		iv.setImageResource(R.drawable.asdsad23213213213);
		iv.setOnTouchListener(this);
		matrix = new Matrix();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		int pointerCount = event.getPointerCount();
		if (pointerCount == 1) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX(0);
				startY = event.getY(0);
				break;
			case MotionEvent.ACTION_MOVE:
				float oldX = event.getX(0);
				float oldY = event.getY(0);
				matrix.setTranslate(0, 0);
				float atan2 = (float)Math.atan2(oldY-startY,oldX-startX);
				matrix.setRotate((float) (atan2*180/Math.PI));
//				matrix.postTranslate(oldX - startX, oldY - startY);
				iv.setImageMatrix(matrix);
				startX = oldX;
				startY = oldY;
				break;

			default:
				break;
			}
		}
/*		if (pointerCount == 1) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX(0);
				startY = event.getY(0);
				break;
			case MotionEvent.ACTION_MOVE:
				float oldX = event.getX(0);
				float oldY = event.getY(0);
				matrix.postTranslate(oldX - startX, oldY - startY);
				iv.setImageMatrix(matrix);
				startX = oldX;
				startY = oldY;
				break;
				
			default:
				break;
			}
		}
*/		else if (pointerCount == 2) {
			//event.getActionMasked
			switch (action&MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDis = getDisByXY(event);
				pointMid = getMid(event);
				break;
			case MotionEvent.ACTION_MOVE:
				newDis = getDisByXY(event);
				scale = newDis/oldDis;
				//前两个参数是比例，后两个是移动的距离
				matrix.postScale(scale, scale, pointMid.x, pointMid.y);
				iv.setImageMatrix(matrix);
				oldDis = newDis;
				break;

			default:
				break;
			}
			float oldX2 = event.getX(1);
			float oldY2 = event.getY(1);
			// matrix.postScale((oldX+oldX2)/2, (oldY+oldY2)/2, px, py);
			// float atan2 = (float)
			// Math.atan2(oldY2-(oldY2+oldY)/2,oldX2-(oldX2+oldX)/2);
			// matrix.postRotate((float) (atan2*180/Math.PI));
		}
		return true;
	}

	private PointF getMid(MotionEvent event) {
		PointF f = new PointF();
		f.x = (event.getX()+event.getX(1))/2;
		f.y = (event.getY()+event.getY(1))/2;
		return f;
	}

	private float getDisByXY(MotionEvent event) {
		float x = event.getX();
		float y = event.getY(); 
		
		float x2 = event.getX(1);
		float y2 = event.getY(1);
		//Math.pow(dx,2);
		return FloatMath.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
		
	}
}
