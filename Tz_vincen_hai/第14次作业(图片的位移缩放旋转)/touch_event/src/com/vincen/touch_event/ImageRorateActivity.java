package com.vincen.touch_event;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageRorateActivity extends Activity implements OnTouchListener{
	private ImageView img ;
	private Bitmap oldBitmap ;
	private float startX,startY;
	private Matrix matrix ;
    private float preDegree , lastDegree ;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_rotate);
		img = (ImageView) findViewById(R.id.img2);
		matrix = new Matrix();
		oldBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bb);
		img.setImageBitmap(oldBitmap);
		img.setImageMatrix(matrix);
		img.setOnTouchListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		int point = event.getPointerCount();  //��ָ����
		if(point == 1){
			//�ƶ�
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
				matrix.postTranslate(moveX-startX,moveY-startY); //���ؾ���ʼλ�ƶ���
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
			//��ת
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				//��ȡ��ָ����ʱ�ĽǶ�
				 preDegree = computeDegree(new Point((int)event.getX(0), (int)event.getY(0)), new Point((int)event.getX(1), (int)event.getY(1)));  
				break;
			case MotionEvent.ACTION_MOVE:
				//��ȡ��ָ�ƶ��ĽǶ�
				 lastDegree = computeDegree(new Point((int)event.getX(0), (int)event.getY(0)), new Point((int)event.getX(1), (int)event.getY(1)));  
				 matrix.postRotate(preDegree - lastDegree, img.getWidth()/2, img.getHeight()/2);  
				 img.setImageMatrix(matrix);
				 preDegree = lastDegree;
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
	
	  /**
     * ���������봹ֱ����н�
     * @param p1
     * @param p2
     * @return
     */ 
    public float computeDegree(Point p1, Point p2){ 
        float tran_x = p1.x - p2.x; 
        float tran_y = p1.y - p2.y; 
        float degree = 0.0f; 
        float angle = (float)(Math.asin(tran_x/Math.sqrt(tran_x*tran_x + tran_y* tran_y))*180/Math.PI); 
        if(!Float.isNaN(angle)){ 
            if(tran_x >= 0 && tran_y <= 0){//��һ���� 
                degree = angle; 
            }else if(tran_x <= 0 && tran_y <= 0){//�ڶ����� 
                degree = angle; 
            }else if(tran_x <= 0 && tran_y >= 0){//�������� 
                degree = -180 - angle; 
            }else if(tran_x >= 0 && tran_y >= 0){//�������� 
                degree = 180 - angle; 
            } 
        } 
        return degree; 
    }  
}
