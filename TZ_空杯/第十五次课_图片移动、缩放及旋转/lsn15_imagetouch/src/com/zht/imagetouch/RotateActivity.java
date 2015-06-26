/**
 * Project Name:lsn15_imagetouch
 * File Name:RotateActivity.java
 * Package Name:com.zht.imagetouch
 * Date:2015-6-18涓嬪崍6:48:19
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.imagetouch;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * ClassName:RotateActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-18 6:48:19 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class RotateActivity extends Activity {
	private Matrix matrix;
	private ImageView iv;
	private float centerX;
	private float centerY;
	private Point oldMidPoint;
	private Point newMidPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.image);
		matrix = new Matrix();

		iv.setOnTouchListener(new OnTouchListener() {
			private double fromDegree;
			private double toDegree;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 计算中心点坐标
				centerX = iv.getWidth() / 2;
				centerY = iv.getHeight() / 2;
				int pointerCount = event.getPointerCount();
				if (1 == pointerCount) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						// 计算初始角度
						fromDegree = getDegree(event.getX(), event.getY());
						break;
					case MotionEvent.ACTION_MOVE:
						// 计算手指移动当前的角度
						toDegree = getDegree(event.getX(), event.getY());

						matrix.postRotate((float) (fromDegree - toDegree),
								centerX, centerY);
						fromDegree = toDegree;
						iv.setImageMatrix(matrix);
						break;
					case MotionEvent.ACTION_UP:
						break;
					default:
						break;
					}
				} else if (2 == pointerCount) {
					switch (event.getActionMasked()) {
					case MotionEvent.ACTION_POINTER_DOWN:
						oldMidPoint = getMidPoint(event);
						fromDegree = getDegree(oldMidPoint.x, oldMidPoint.y);
						break;
					case MotionEvent.ACTION_MOVE:
						newMidPoint = getMidPoint(event);
						toDegree = getDegree(newMidPoint.x, newMidPoint.y);
						matrix.postRotate((float) (fromDegree - toDegree),
								centerX, centerY);
						fromDegree = toDegree;
						iv.setImageMatrix(matrix);
					default:
						break;
					}
				}
				return true;
			}

			// 计算旋转的角度
			private double getDegree(float x, float y) {
				// 获取当前点与中心点的x、y的距离
				float dx = x - centerX;
				float dy = centerY - y; 
				float radian = (float) Math.atan2(dy, dx);
				return radian * 180 / Math.PI;
			}

			// 获取两手指点坐标中心
			private Point getMidPoint(MotionEvent event) {
				Point point = new Point();
				float x1 = event.getX(0);
				float y1 = event.getY(0);
				float x2 = event.getX(1);
				float y2 = event.getY(1);
				point.x = (int) ((x1 + x2) / 2);
				point.y = (int) ((y1 + y2) / 2);
				return point;
			}
		});
	}
}
