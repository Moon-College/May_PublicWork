package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity implements OnClickListener {
	
	/**
	 * 跳转到第三个界面
	 */
	private Button btn_two;
	/**
	 * 计数
	 */
	private Button btn_recount;
	
	private int index;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		btn_two = (Button) findViewById(R.id.btn_two);
		btn_recount = (Button) findViewById(R.id.btn_recount);
		
		btn_two.setOnClickListener(this);
		btn_recount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_two:  
			//如果SecondActivity是:singleTop【独享任务栈顶】模式
			//在创建一个实例之前，检查这个任务栈，如果任务栈顶有了该实例，就不重复创建，而是重用该实例
//			Intent intent = new Intent(this, SecondActivity.class);  //点击按钮，再次启动自己

			Intent intent = new Intent(this, ThirdActivity.class);  //跳转到第三个界面
			startActivity(intent);
			break;
		case R.id.btn_recount:  //计数
			index++; //自加
			btn_recount.setText(String.valueOf(index));
			break;
		default:
			break;
		}

	}

}
