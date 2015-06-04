package com.junwen.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentdemo.R;

public class TakeSmsActivity extends Activity implements OnClickListener{
	private EditText body;
	private EditText phoneNumber;
	private Button sendMsm;
	private Handler handler = new Handler(){
	public void handleMessage(android.os.Message msg) {
		Toast.makeText(TakeSmsActivity.this,"���ͳɹ�", 0).show();
	}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takesms_layout);
		initView();
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		body = (EditText) findViewById(R.id.edit_body);
		phoneNumber = (EditText) findViewById(R.id.edit_PhoneNumber);
		sendMsm = (Button) findViewById(R.id.btn_sendSms);
		sendMsm.setOnClickListener(this);
	}
	/**
	 * ��������Ͷ��Ű�ťʱ
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		//��ȡ�ֻ�����
		String number = phoneNumber.getText().toString().trim();
		//��ȡ����
		String bodyContent = body.getText().toString().trim();
		//�����첽���Ͷ���
		SendSmsMessage(number,bodyContent);
	}
	private void SendSmsMessage(final String number, final String bodyContent) {
		new Thread(){
			public void run() {
				//��ȡ���Ź�����
				SmsManager sms = SmsManager.getDefault();
				//�Ѷ��ŷָ�ɼ���
				ArrayList<String> items = sms.divideMessage(bodyContent);
				//��������
				for (String item : items) {
					//����
					sms.sendTextMessage(number, null, bodyContent, null, null);
				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	
	}
}
