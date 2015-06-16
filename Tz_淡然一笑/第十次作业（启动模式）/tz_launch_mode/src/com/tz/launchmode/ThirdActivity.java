package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity implements OnClickListener {
	
	/**
	 * ���ص�һ�����水ť
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
			
			//���FirstActivity��:singleTask����������ջ��ģʽ
			//�ڴ���һ��ʵ��֮ǰ�����һ������ջ���������ջ���Ѿ��и�ʵ�����Ͳ��ظ��������������ø�ʵ�������ҰѸ�ʵ�����ϵ�����ʵ��ȫ���Ƴ������٣�
			intent.setClass(this, FirstActivity.class); // ���ص���һ������
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
