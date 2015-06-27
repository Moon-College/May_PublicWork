package com.rocy.tzclass14;

import java.io.IOException;

import com.rocy.tzclass14.listener.OnMyClickListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnMyClickListener{

	private ImageView iv;
	private Matrix matrix;
	private Bitmap bitmap;
	private float centerX,centerY;
	private FrameLayout fl;
	private MyFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			bitmap = BitmapFactory.decodeStream(getAssets().open("hf.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iv = (ImageView)findViewById(R.id.iv);
		fl =(FrameLayout) findViewById(R.id.rl);
		
		fragment = new MyFragment();
		fragment.setmOnMyClickListener(this);
		FragmentManager manager = this.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.rl, fragment);
		transaction.addToBackStack("hello");
		transaction.commit();
		matrix =new Matrix();
		iv.setImageMatrix(matrix);
		
		iv.setOnTouchListener(new OnTouchListener() {
			
			private float dAcr;
			

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				centerY=iv.getHeight()/2;
				centerX=iv.getWidth()/2;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Log.i("INFO", "ACTION_DOWN");
					dAcr = getAcr(event);
					Log.i("INFO", "ACTION_DOWN"+dAcr);
					break;
				case MotionEvent.ACTION_MOVE:
					float moveAcr  = getAcr(event);
					matrix.postRotate(dAcr-moveAcr, centerX, centerY);
					iv.setImageMatrix(matrix);
					dAcr=moveAcr;
					break;
				case MotionEvent.ACTION_UP:
					Log.i("INFO", "ACTION_UP");
					
					break;

				default:
					break;
				}
				return true;
			}

			private float getAcr(MotionEvent event) {
				// TODO Auto-generated method stub
				//获得角度
				float x1 = event.getX();
				float y1 = event.getY();
				//中心点
				float dx = x1-centerX;
				float dy = centerY-y1;
				//获得反正切；
				double atan2 = Math.atan2(dy, dx);
				//正选
				double x = (atan2*180)/Math.PI;
				return (float) x;
			}
		});
	}

	@Override
	public void OnMyClick(View v) {
		// TODO Auto-generated method stub
		TextView tv =(TextView) v;
		tv.setText("再问你一次你吃饭了吗？");
	}
}
