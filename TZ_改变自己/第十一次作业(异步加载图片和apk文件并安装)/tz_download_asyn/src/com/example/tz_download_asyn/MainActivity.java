package com.example.tz_download_asyn;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn1, btn2, btn3, btn4;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MainActivity.this,
						DownloadImageActivity.class);
				startActivity(it);
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MainActivity.this,
						DownloadAPKActivity.class);
				startActivity(it);
			}
		});

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mHandler.post(run);
			}
		});

		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mHandler.removeCallbacks(run);
			}
		});

	}

	int index = 0;
	Runnable run = new Runnable() {
		@Override
		public void run() {
			index += 2;
			btn3.setText(String.valueOf(index));
			mHandler.removeCallbacks(run);
			mHandler.postDelayed(run, 200);
		}
	};

}
