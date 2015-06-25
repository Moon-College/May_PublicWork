package com.tz.michael.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MatrixAndFragmentActivity extends Activity implements OnTouchListener {
	
	private ImageView img;
	private Matrix matrix;
	private float downX,downY,downX2,downY2,centerX,centerY;
	/**x和y方向的缩放比例*/
	private float scallX,scallY;
	/**最终的缩放比例*/
	private float scall;
	/**是否可以旋转*/
	private boolean isCanRoast;
	private int roastCenterX;
	private int roastCenterY;
	private float angle_d;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        img=(ImageView) findViewById(R.id.img);
        matrix=new Matrix();
        img.setImageMatrix(matrix);
        img.setOnTouchListener(this);
    }

	public boolean onTouch(View v, MotionEvent event) {
		int pointCount=event.getPointerCount();
		if(pointCount==1){
			roastCenterX=img.getWidth()/2;
			int[] arr=new int[2];
			img.getLocationOnScreen(arr);
			roastCenterY=img.getHeight()/2;
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX=event.getX();
				downY=event.getY();
				angle_d=(float) getDegree(downY,downX);
				break;
			case MotionEvent.ACTION_MOVE:
				if(!isCanRoast){
					matrix.postTranslate(event.getX()-downX, event.getY()-downY);
				}else{
//					double a=Math.sqrt((event.getX()-downX)*(event.getX()-downX)+(event.getY()-downY)*(event.getY()-downY));
//					double b=Math.sqrt((roastCenterX-downX)*(roastCenterX-downX)+(roastCenterY-downY)*(roastCenterY-downY));
//					double c=Math.sqrt((downX-roastCenterX)*(downX-roastCenterX)+(downY-roastCenterY)*(downY-roastCenterY));
//					double cosA=(b*b+c*c-a*a)/(2*b*c);
//					double arcA = Math.acos(cosA);//0~PI
//					double angleA = arcA * 180 /Math.PI;
//					//这里顺时针和逆时针，不知怎么判断
//					if(event.getX()>downX&&event.getY()>downY||event.getX()>downX&&event.getY()<downY||event.getX()<downX&&event.getY()<downY||event.getX()<downX&&event.getY()>downY){
//						angleA=-angleA;
//					}else{
//						angleA=angleA;
//					}
					float angle_m=(float) getDegree(event.getY(),event.getX());
					matrix.postRotate((angle_m-angle_d), roastCenterX, roastCenterY);
					angle_d=angle_m;
				}
				img.setImageMatrix(matrix);
				downX=event.getX();
				downY=event.getY();
				break;
			case MotionEvent.ACTION_UP:
				
				break;
			default:
				break;
			}
		}else
			if(pointCount==2){
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_POINTER_DOWN:
					//获取两根手指的坐标
					downX=event.getX(0);
					downY=event.getY(0);
					downX2=event.getX(1);
					downY2=event.getY(1);
					//获取中心点的 坐标
					centerX=(downX+downX2)/2;
					centerY=(downY+downY2)/2;
					break;
				case MotionEvent.ACTION_MOVE:
					scallX=(event.getX(0)-event.getX(1))/(downX-downX2);
					scallY=(event.getY(0)-event.getY(1))/(downY-downY2);
					scall=Math.max(scallX, scallY);
					matrix.postScale(scall, scall,centerX,centerY);
					img.setImageMatrix(matrix);
					downX=event.getX(0);
					downY=event.getY(0);
					downX2=event.getX(1);
					downY2=event.getY(1);
					break;
				case MotionEvent.ACTION_POINTER_UP:
					break;
				default:
					break;
				}
			}else
				if(pointCount==3){
						Toast.makeText(MatrixAndFragmentActivity.this, "旋转模式开启", 0).show();
						isCanRoast=true;
				}
		return true;
	}
	/**
	 * 获得当前点的角度
	 * @param downY3
	 * @param downX3
	 * @return
	 */
	private double getDegree(float downY3, float downX3) {
		double angle_down=Math.atan2(downY, downX);
		return Math.toDegrees(angle_down);
	}

	public void myOnClick(View v){
		startActivity(new Intent(MatrixAndFragmentActivity.this,SecondActivity.class));
	}
}