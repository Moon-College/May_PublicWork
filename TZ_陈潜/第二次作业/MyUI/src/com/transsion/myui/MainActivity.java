package com.transsion.myui;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//实例化容器
		LinearLayout ll=new LinearLayout(this);
		//设置排列顺序
		ll.setOrientation(0);
		//设置属性的宽高
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//添加属性
		ll.setLayoutParams(lp);
		//创建EditText
		EditText et=new EditText(this);
		//设置EditText宽高
		LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 1);
		//添加EditText属性
		et.setLayoutParams(llp);
		//EditText添加到容器Linearlayout中
		ll.addView(et, 0);
		Button bt=new Button(this);
		bt.setText("搜索");
		LinearLayout.LayoutParams lpp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 0);
		et.setLayoutParams(llp);
		ll.addView(bt);
		//Linearlayout添加到父容器里
		setContentView(ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
