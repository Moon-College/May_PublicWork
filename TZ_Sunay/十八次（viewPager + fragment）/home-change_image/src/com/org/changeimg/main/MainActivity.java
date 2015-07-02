package com.org.changeimg.main;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.example.home_change_image.R;

public class MainActivity extends Activity implements OnTouchListener {
    private ImageView img;
    private Matrix matrix;
    private float startX,startY;
    private Point mid_center;
    private float oldDis;
    private double fromDegree;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        img = (ImageView) findViewById(R.id.img);
        matrix = new Matrix();
        img.setImageMatrix(matrix);
        img.setOnTouchListener(this);
    }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int width = img.getWidth();
		int height = img.getHeight();
	     int pointerCount = event.getPointerCount();
		 if(pointerCount == 1){
			   switch (event.getAction()) {
				
				case MotionEvent.ACTION_DOWN:
			     Log.i("INFO", "wwo ai ni down  ");
			      startX = event.getX();
			      startY = event.getY();
			      //获取该店与中心点的dx 与dy 
			      float x = startX - width/2;
			      float y = height/2 - startY;
			      float tan = (float) Math.atan2(y, x);
			      fromDegree = tan*180/Math.PI;
					break;
				case MotionEvent.ACTION_MOVE:
					   Log.i("INFO", "wwo ai ni move  ");
			      float movingX  = event.getX();
			      float movngY = event.getY();
			      float  porintX = movingX - width/2;
			      float pointY = height/2 - startY;
			      //计算出与中心角的度数 
			      float moving_tan = (float) Math.atan2(pointY, porintX);
			      double toDegree = moving_tan*180/Math.PI;
			      matrix.postRotate((float)(fromDegree - toDegree), width/2, height/2);
			      img.setImageMatrix(matrix);
					break;
				case MotionEvent.ACTION_CANCEL:
					   Log.i("INFO", "wwo ai ni up  ");
					
					break;
				default:
					break;
		 }
	  
	}
		return true;
	}
	
}