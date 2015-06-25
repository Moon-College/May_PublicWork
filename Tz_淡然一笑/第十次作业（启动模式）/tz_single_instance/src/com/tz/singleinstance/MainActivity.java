package com.tz.singleinstance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startOtherApp(View view){
		//���Activity��singleInstanceģʽ�������ᵥ������һ���µ�����ջ
		//��ʽ����Activity
		Intent intent = new Intent();
		intent.setAction("com.tz.singleinstance");
//		intent.addCategory(Intent.ACTION_DELETE);  //Ĭ�����
		startActivity(intent);
	}
}
