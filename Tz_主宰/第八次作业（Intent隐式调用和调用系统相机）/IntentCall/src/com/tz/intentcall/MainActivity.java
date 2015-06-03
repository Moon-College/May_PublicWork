package com.tz.intentcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startActivity1 = (Button) findViewById(R.id.start_first_activity); 
		Button startActivity2 = (Button) findViewById(R.id.start_second_activity);
		Button startMyStartCameraApp = (Button) findViewById(R.id.start_my_camera);
		
		startActivity1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, FirstActivity.class);
				startActivity(intent);
			}
		});
		
		startActivity2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("tz.intent.action.ACTIVITY");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.addCategory("ta.intent.category.SECOND");
				intent.addCategory("tz.intent.category.TEST");
				startActivity(intent);
			}
		});
		
		// 启动调用系统相机的App主页
		startMyStartCameraApp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("ta.intent.category.MYCAMERA");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.addCategory("ta.intent.category.MYCAMERA");
				intent.addCategory("android.intent.category.LAUNCHER");
				startActivity(intent);
			}
		});
	}	
}
