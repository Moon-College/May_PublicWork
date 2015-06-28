package com.vincen.touch_event;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageTranslateActivity extends Activity implements OnTouchListener{
	private ImageView img ;
	private float startX,startY;
	private Matrix matrix ;
	private Point mid_center ;
	private float oldDis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_translate);
		matrix = new Matrix();
		img = (ImageView) findViewById(R.id.img);
		img.setImageMatrix(matrix);
		img.setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		int point = event.getPointerCount();  //手指数量
		if(point == 1){
			//移动
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				Log.i("INFO", "down");
				startX =event.getX();
				startY = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "move");
				float moveX = event.getX();
				float moveY = event.getY();
				matrix.postTranslate(moveX-startX,moveY-startY); //像素矩阵开始位移动画
				img.setImageMatrix(matrix);
				startX =event.getX();
				startY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				Log.i("INFO", "up");
				break;
				
			default:
				break;
			}
		}else if(point == 2){
			//缩放
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				//首先获取两根手指的坐标计算中心点
				mid_center = getMidPoint(event);
				//获取两手指间的距离
				oldDis = getDisByEvent(event);
				break;
			case MotionEvent.ACTION_MOVE:
				//获取Moving中的两根手指的距离
				float newDis = getDisByEvent(event);
				float scale = newDis/oldDis;
				//前两个参数是缩放的比例，后两个参数为中心点的X.Y坐标
				matrix.postScale(scale, scale, mid_center.x, mid_center.y);
				img.setImageMatrix(matrix);
				oldDis = newDis ;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				Log.i("INFO", "up");
				break;
				
			default:
				break;
			}
		}
		
		
		return true;
	}
	
	private float getDisByEvent(MotionEvent event) {
		float dx = event.getX(0)-event.getX(1);
		float dy = event.getY(0) - event.getY(1);
		return (float)Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
	}

	private Point getMidPoint(MotionEvent event) {
		Point point = new Point();
		float x1 = event.getX(0);
		float y1 = event.getY(0); //第一根手指的坐标
		float x2 = event.getX(1);
		float y2 = event.getY(1); //第二根手指的坐标
		point.x = (int)(x1+x2)/2;
		point.y = (int) (y1+y2)/2;
		return point;
	}

}
