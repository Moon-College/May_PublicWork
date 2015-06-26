package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity implements OnClickListener {
	
	/**
	 * 返回第一个界面按钮
	 */
	private Button btn_three;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);

		btn_three = (Button) findViewById(R.id.btn_three);
		btn_three.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_three:
			Intent intent = new Intent();
			
			intent.putExtra("name", "Lucy");
			
			//如果FirstActivity是:singleTask【独享任务栈】模式
			//在创建一个实例之前，检查一个任务栈，如果任务栈中已经有该实例，就不重复创建，而是重用该实例，并且把该实例以上的其它实例全部移除（销毁）
			intent.setClass(this, FirstActivity.class); // 返回到第一个界面
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
