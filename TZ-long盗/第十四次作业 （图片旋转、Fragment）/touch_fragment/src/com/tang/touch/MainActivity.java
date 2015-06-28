package com.tang.touch;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	private ImageView img;
	private float startX;
	private float startY;
	private Matrix matrix;
	private float startAngle;
	private Point startPoint;
	private Point min_content;
	private float oldDis;
	private float newDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		img=(ImageView)findViewById(R.id.imageView1);
		img.setOnTouchListener(this);
		matrix = new Matrix();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int pointerCount = event.getPointerCount();
		if (pointerCount == 1) {//图片旋转
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX=event.getX();
				startY =event.getY();
				//移动前的角度
				startAngle =getCenterAngle(startX,startY);
				Log.e("info", "ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e("info", "ACTION_MOVE");
				float newX = event.getX();
				float newY = event.getY();					
				//移动后的角度
				float newAngle = getCenterAngle(newX, newY);			
				matrix.postRotate(startAngle-newAngle, img.getWidth()/2, img.getHeight()/2);			
//				matrix.postTranslate(newX-startX, newY-startY);			
				img.setImageMatrix(matrix);
				startAngle = newAngle;			
				break;
				
			case MotionEvent.ACTION_UP:
				Log.e("info", "ACTION_UP");
				break;
			default:
				break;
			}
		}else if(pointerCount == 2){//图片放大缩小
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:		
				min_content =getPoint(event);
				oldDis=getDisEvent(event);
				Log.e("info", "ACTION_POINTER_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e("info", "ACTION_MOVE");
				newDis=getDisEvent(event);
				float scale = newDis/oldDis;
				matrix.postScale(scale, scale, min_content.x, min_content.y);
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
	/**
	 * 根据x,y计算离中心点的角度
	 * @param x
	 * @param y
	 * @return
	 */
	private float getCenterAngle(float x,float y){
		float dx = x - img.getWidth()/2;
		float dy = img.getHeight()/2-y;
		double atan = Math.atan2(dy, dx);
		float radian=(float) (atan*180/Math.PI);		
		return radian;		
	}
	/**
	 * 得到中心点
	 * @param event
	 * @return
	 */
	private Point getPoint(MotionEvent event){
		Point point = new Point();
		float x1 = event.getX(0);
		float y1 = event.getY(0);
		float x2 = event.getX(1);
		float y2 = event.getY(1);
		
		point.x = (int) ((x1+x2)/2);
		point.y = (int) ((y1+y2)/2);
		return point;
		
	}
	/**
	 * 得到俩点间的距离
	 * @param event
	 * @return
	 */
	private float getDisEvent(MotionEvent event){
		float dx=event.getX(0)-event.getX(1);
		float dy=event.getY(0)-event.getY(1);
		return (float) Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
	}
}
