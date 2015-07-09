package com.itskylin.android.rotation;

import com.itskylin.android.rotation.study.MyView;
import com.itskylin.android.rotation.study.OnHitClickListener;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener,
		OnHitClickListener {

	private MyView myView;
	private RelativeLayout rl;
	private ImageView iv;
	private double fromDegree;
	private Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) this.findViewById(R.id.iv);
		rl = (RelativeLayout) this.findViewById(R.id.rl);

		iv.setOnTouchListener(this);
		matrix = new Matrix();
		iv.setImageMatrix(matrix);
		myView = new MyView(this);
		rl.addView(myView);
		myView.setOnHitClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int width = iv.getWidth();
		int height = iv.getHeight();
		if (event.getPointerCount() == 1) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				fromDegree = getEvent(event, width, height);
				Log.i("fromDegree", "fromDegree:" + fromDegree);
				break;
			case MotionEvent.ACTION_MOVE:
				float toDegree = getEvent(event, width, height);
				Log.i("fromDegree", "toDegree:" + toDegree);
				matrix.postRotate((float) (fromDegree - toDegree),
						(float) (width / 2), (float) (height / 2));
				iv.setImageMatrix(matrix);
				fromDegree = toDegree;
				break;

			default:
				break;
			}
		}
		return true;
	}

	private float getEvent(MotionEvent event, int width, int height) {
		float x = event.getX();
		float y = event.getY();
		float dx2 = x - width / 2;
		float dy2 = height / 2 - y;
		float atan2 = (float) Math.atan2(dy2, dx2);
		return (float) (atan2 * 180 / Math.PI);
	}

	@Override
	public void onHitClick(View v) {
		Toast.makeText(this, "单击了....", 1000).show();
	}
}
