package com.yl.image;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
	private ImageView iv;
	private Matrix matrix;
	private Point center_point;
	private Point bg_point;
	private Point move_point;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnTouchListener(this);
		matrix = new Matrix();
		center_point = new Point();
		bg_point = new Point();
		move_point = new Point();
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			center_point = getCenterPoint(v);
			int x = (int) event.getX();
			int y = (int) event.getY();

			bg_point.set(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "move");
			int move_x = (int) event.getX();
			int move_y = (int) event.getY();
			move_point.set(move_x, move_y);
			float angle = getAngle(bg_point, center_point, move_point);
			if (move_x <= center_point.x ) {
				if(bg_point.y < move_y)
				angle = -angle;
			}else{
				if(bg_point.y >=move_y)
					angle = -angle;
			}
			Log.i("INFO", "angle=" + angle);
			matrix.postRotate(angle, center_point.x, center_point.y);
			iv.setImageMatrix(matrix);
			bg_point.set(move_x, move_y);
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}
		return true;
	}

	private float getAngle(Point bg_point, Point center_point, Point move_point) {
		int dx1 = bg_point.x - center_point.x;
		int dy1 = bg_point.y - center_point.y;
		int dx2 = bg_point.x - move_point.x;
		int dy2 = bg_point.y - move_point.y;
		int dx3 = move_point.x - center_point.x;
		int dy3 = move_point.y - center_point.y;
		int side1 = (int) Math.sqrt(dx1 * dx1 + dy1 * dy1);
		int side2 = (int) Math.sqrt(dx2 * dx2 + dy2 * dy2);
		int side3 = (int) Math.sqrt(dx3 * dx3 + dy3 * dy3);
		Log.i("INFO", "dx1=" + dx1);
		Log.i("INFO", "dy1=" + dy1);
		Log.i("INFO", "dx2=" + dx2);
		Log.i("INFO", "dy2=" + dy2);
		Log.i("INFO", "dx3=" + dx3);
		Log.i("INFO", "dy3=" + dy3);
		Log.i("INFO", "bg_point_x=" + bg_point.x + " bg_point_y=" + bg_point.y);
		Log.i("INFO", "center_point_x=" + center_point.x + " center_point_y="
				+ center_point.y);
		Log.i("INFO", "move_point_x=" + move_point.x + " move_point_y="
				+ move_point.y);
		Log.i("INFO", "side1=" + side1 + " side2=" + side2 + " side3=" + side3);
		return (float) Math
				.acos((side1 * side1 + side3 * side3 - side2 * side2)
						/ (2 * side1 * side3));
	}

	private Point getCenterPoint(View v) {
		Point point = new Point();
		// 获得左上角坐标
		int left_x = v.getLeft();
		int left_y = v.getTop();
		// 获得右下角坐标
		int right_x = left_x + v.getWidth();
		int right_y = left_y + v.getHeight();
		// 获得中心点坐标
		int center_x = (left_x + right_x) / 2;
		int center_y = (left_y + right_y) / 2;
		point.set(center_x, center_y);
		return point;
	}
}