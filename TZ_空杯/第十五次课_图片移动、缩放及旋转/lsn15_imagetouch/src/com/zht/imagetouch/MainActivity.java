/**
 * Project Name:ImageTouch
 * File Name:MainActivity.java
 * Package Name:com.zht.imagetouch
 * Date:2015-6-18‰∏ãÂçà4:12:46
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
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-18 ‰∏ãÂçà4:12:46 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private Matrix matrix;
	private ImageView iv;
	private float startX;
	private float startY;
	private Point midPoint;
	private float oldDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.image);
		matrix = new Matrix();
		iv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int pointerCount = event.getPointerCount();
				if (1 == pointerCount) {// Œª“∆
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Log.i("INFO", "down");
						startX = event.getX();
						startY = event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						Log.i("INFO", "move");
						float curX = event.getX();
						float curY = event.getY();
						matrix.postTranslate(curX - startX, curY - startY);
						startX = curX;
						startY = curY;
						iv.setImageMatrix(matrix);
						break;
					case MotionEvent.ACTION_UP:
						break;
					default:
						break;
					}
				} else if (2 == pointerCount) {// Àı∑≈
					switch (event.getActionMasked()) {
					case MotionEvent.ACTION_POINTER_DOWN:
						midPoint = getMidPoint(event);
						oldDis = getDistance(event);
						Log.i("INFO", "oldDis:"+oldDis);
						break;
					case MotionEvent.ACTION_MOVE:
						float newDis = getDistance(event);
						Log.i("INFO", "newDis:"+newDis);
						float scale = newDis/oldDis ;
						Log.i("INFO", "scale:"+scale);
						Log.i("INFO", "pointX:"+midPoint.x+" pointY"+midPoint.y);
						matrix.postScale(scale, scale, midPoint.x, midPoint.y);
						oldDis = newDis;
						iv.setImageMatrix(matrix);
						break;
					case MotionEvent.ACTION_POINTER_UP:
						break;
					default:
						break;
					}
				}
				return true;
			}

			private float getDistance(MotionEvent event) {
				float dx = event.getX(0) - event.getX(1);
				float dy = event.getY(0) - event.getY(1);

				return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			}

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
