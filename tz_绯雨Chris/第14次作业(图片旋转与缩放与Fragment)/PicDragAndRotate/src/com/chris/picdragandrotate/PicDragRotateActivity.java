package com.chris.picdragandrotate;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class PicDragRotateActivity extends FragmentActivity
{

	protected static final String tag = "PicDragRotateActivity";
	protected static final int DEGREE_CALC_COUNT = 5;	//需要累计计算“旋转”和“缩放”区分角度的次数
	protected static final float MIN_ROTATE_DEGREE = 4.5f;	//区分“旋转”和“缩放”的最小改变角度，经验值，与DEGREE_CALC_COUNT有关
	private ImageView image;
	private Matrix matrix;

	protected PointF point0, point1;
	protected float orgDistance;
	protected float orgAngle, minDegree;
	protected int count = 0;

	private boolean dragFlag = true;
	private boolean scaleFlag = false;
	private boolean rotateFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pic_drag_rotate);
		initViews();
	}

	private void initViews()
	{
		point0 = new PointF();
		point1 = new PointF();
		matrix = new Matrix();
		image = (ImageView) findViewById(R.id.image);
		image.setOnTouchListener(img_onTouch);
	}

	OnTouchListener img_onTouch = new OnTouchListener()
	{

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			int pCnt = event.getPointerCount();

			switch (event.getActionMasked())
			{
			case MotionEvent.ACTION_DOWN:
				Log.i(tag, "ACTION_DOWN");
				point0.x = event.getX(0);
				point0.y = event.getY(0);
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				Log.i(tag, "ACTION_POINTER_DOWN");
				dragFlag = false; //关闭拖拽行为
				orgDistance = getDistanceByEvent(event);
				orgAngle = getAngleByEvent(event);
				break;
			case MotionEvent.ACTION_MOVE: //action move事件无法区分是哪个手指的移动,换句话说，就是event.getActionIndex()得到的永远是0
				//处理1根手指的拖拽行为
				if (1 == pCnt && dragFlag)
				{
					float destX0 = event.getX(0);
					float destY0 = event.getY(0);
					matrix.postTranslate(destX0 - point0.x, destY0 - point0.y);
					point0.x = destX0;
					point0.y = destY0;
				}
				
				//处理2根手指的行为
				else if (2 == pCnt)
				{
					float updateDistance = getDistanceByEvent(event);
					float updateAngle = getAngleByEvent(event);
					float scarlRate = updateDistance / orgDistance;
					float delateAngle = updateAngle - orgAngle;
					
					Log.d(tag, "updateDistance =" + updateDistance);
					Log.d(tag, "updateAngle = " + updateAngle);
					Log.d(tag, "scarlRate = " + scarlRate);
					Log.d(tag, "delateAngle = " + delateAngle);
					
					if(count++ < DEGREE_CALC_COUNT)
					{
						minDegree += delateAngle;	//该计数需要在所有手指都抬起来后清零
						break;
					}
					Log.d(tag, "minDgree = "+minDegree);
					
					if(minDegree >= MIN_ROTATE_DEGREE || minDegree < -MIN_ROTATE_DEGREE)
					{
						rotateFlag = true;	//如果累计旋转角度大于一个值，则判定为旋转行为
					}
					scaleFlag = !rotateFlag;	//否则判定为缩放行为
					
					//缩放行为
					if (scaleFlag)
					{
						//两点中心缩放 和 图片中心缩放 效果差不多。
//						matrix.postScale(scarlRate, scarlRate, (point0.x + point1.x) / 2, (point0.y + point1.y) / 2);
						matrix.postScale(scarlRate, scarlRate, (image.getRight()-image.getLeft()) / 2, (image.getBottom()-image.getTop()) / 2);
						orgDistance = updateDistance;
					}
					
					//旋转行为
					else if(rotateFlag)
					{
//						matrix.postRotate(updateAngle-orgAngle, (point0.x + point1.x) / 2, (point0.y + point1.y) / 2);
						//取图片中心点位置旋转
						matrix.postRotate(updateAngle-orgAngle, (image.getRight()-image.getLeft()) / 2, (image.getBottom()-image.getTop()) / 2);
						orgAngle = updateAngle;
					}
				}
				image.setImageMatrix(matrix);
				break;
			case MotionEvent.ACTION_POINTER_UP:
				Log.i(tag, "ACTION_POINTER_UP");
				scaleFlag = false; //关闭缩放
				rotateFlag = false; //关闭旋转
				break;
			case MotionEvent.ACTION_UP:
				Log.i(tag, "ACTION_UP");
				dragFlag = true; //只有当旋转、缩放完成，并抬起所有手指之后，才允许开启拖拽行为
				count = 0;
				minDegree = 0;
				break;
			default:
				break;
			}

			return true;
		}

		private float getAngleByEvent(MotionEvent event)
		{
			float deltaX = event.getX(1) - event.getX(0);
			float deltaY = event.getY(1) - event.getY(0);
			double radians = Math.atan2(deltaY, deltaX);

			return (float) Math.toDegrees(radians);
		}

		private float getDistanceByEvent(MotionEvent event)
		{
			float deltaX = event.getX(1) - event.getX(0);
			float deltaY = event.getY(1) - event.getY(0);

			return (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		}

	};

}
