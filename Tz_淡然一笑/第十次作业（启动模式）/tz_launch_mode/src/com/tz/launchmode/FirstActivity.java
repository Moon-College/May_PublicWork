package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity����ģʽ
 * @author fcc
 *
 */
public class FirstActivity extends Activity implements OnClickListener {
	
	/**
	 * ��ת���ڶ������水ť
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
			//���Activity��standard����׼ģʽ����ÿ�ζ��ᴴ��һ���µ�ʵ����newһ���ࡿ
			//Ĭ����standard
			Intent intent = new Intent(this, SecondActivity.class);  //��ת���ڶ�������
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	//���FirstActivity��singleTaskģʽ������ص�onNewIntent()����
	//���FirstActivity��singleTopģʽ������ص�onNewIntent()����
	@Override
	protected void onNewIntent(Intent intent) {
		Log.i("INFO", intent.getStringExtra("name"));
		super.onNewIntent(intent);
	}

}
