package com.tz.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity implements OnClickListener {
	
	/**
	 * ��ת������������
	 */
	private Button btn_two;
	/**
	 * ����
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
			//���SecondActivity��:singleTop����������ջ����ģʽ
			//�ڴ���һ��ʵ��֮ǰ������������ջ���������ջ�����˸�ʵ�����Ͳ��ظ��������������ø�ʵ��
//			Intent intent = new Intent(this, SecondActivity.class);  //�����ť���ٴ������Լ�

			Intent intent = new Intent(this, ThirdActivity.class);  //��ת������������
			startActivity(intent);
			break;
		case R.id.btn_recount:  //����
			index++; //�Լ�
			btn_recount.setText(String.valueOf(index));
			break;
		default:
			break;
		}

	}

}
