package com.casit.hc;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class PictureActionActivity extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
	private ImageView imageview;
	private Matrix matrix;
	private float startX, startY;
	private float movingX, movingY;
	private Point mid_center;
	private float oldDis;
	private float center_rotate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageview = (ImageView) findViewById(R.id.iv);
        matrix = new Matrix();
        imageview.setImageMatrix(matrix);
        imageview.setOnTouchListener(this);
    }
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int pointerCount = event.getPointerCount();
		if(pointerCount==1){
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
		//		Log.i("INFO", "DOWN");
			    startX =  (int) event.getX();
			    startY =  (int) event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
		//		Log.i("INFO", "MOVE");
				movingX = event.getX();
				movingY = event.getY();

				center_rotate = getRotateDegree(startX ,startY,movingX,movingY,200,200);
				Log.i("INFO", center_rotate+"");	
				matrix.postRotate(center_rotate, 200, 200);
		//		matrix.setTranslate(movingX-startX, movingY-startY);
		//		matrix.postTranslate(movingX-startX, movingY-startY);
				startX = movingX;
				startY = movingY;
				imageview.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_UP:
		//		Log.i("INFO", "UP");
				break;

			default:
				break;
			}
		}else if(pointerCount==2){
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				mid_center = getMidPoint(event);
				oldDis = getDisByEvent(event);
				//获得两个手指的坐标中心点
				break;
			case MotionEvent.ACTION_MOVE:
				float nowDis = getDisByEvent(event);
                float scale = nowDis/oldDis;
       
                matrix.postScale(scale,scale,mid_center.x,mid_center.y);
                oldDis = nowDis;
                imageview.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_UP:
				break;
			default:
				break;
			}
		}
		return true;
	}
	private float getRotateDegree(float startX2, float startY2, float movingX2,
			float movingY2, int oriX, int oriY) {
		 float tandgreeold = (float) Math.atan(( startY2- oriY)/(startX2 - oriX));
		 float tandgreenew = (float) Math.atan((movingY = oriY)/(movingX - oriX));
	
		return tandgreenew-tandgreeold;
	}
	private float getDisByEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float dx = (event.getX(0)-event.getX(1));
		float dy =  event.getY(0)+event.getY(1);
		return (float) Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
	}

	private Point getMidPoint(MotionEvent event){
	    Point point = new Point() ;
		float x1 = event.getX(0);
		float y1 = event.getY(0);
	    float x2 = event.getX(1);
	    float y2 = event.getY(1);
	    point.x = (int) ((x1+x2)/2);
	    point.y = (int) ((y1+y2)/2);
		return point;
	}
}
