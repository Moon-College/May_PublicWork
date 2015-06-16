package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity启动模式
 * @author fcc
 *
 */
public class FirstActivity extends Activity implements OnClickListener {
	
	/**
	 * 跳转到第二个界面按钮
	 */
	private Button btn_one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);

		btn_one = (Button) findViewById(R.id.btn_one);
		btn_one.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			//如果Activity是standard【标准模式】，每次都会创建一个新的实例【new一个类】
			//默认是standard
			Intent intent = new Intent(this, SecondActivity.class);  //跳转到第二个界面
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	//如果FirstActivity是singleTask模式，它会回调onNewIntent()方法
	//如果FirstActivity是singleTop模式，它会回调onNewIntent()方法
	@Override
	protected void onNewIntent(Intent intent) {
		Log.i("INFO", intent.getStringExtra("name"));
		super.onNewIntent(intent);
	}

}
