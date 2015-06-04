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
		Toast.makeText(TakeSmsActivity.this,"发送成功", 0).show();
	}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takesms_layout);
		initView();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		body = (EditText) findViewById(R.id.edit_body);
		phoneNumber = (EditText) findViewById(R.id.edit_PhoneNumber);
		sendMsm = (Button) findViewById(R.id.btn_sendSms);
		sendMsm.setOnClickListener(this);
	}
	/**
	 * 当点击发送短信按钮时
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		//获取手机号码
		String number = phoneNumber.getText().toString().trim();
		//获取内容
		String bodyContent = body.getText().toString().trim();
		//启动异步发送短信
		SendSmsMessage(number,bodyContent);
	}
	private void SendSmsMessage(final String number, final String bodyContent) {
		new Thread(){
			public void run() {
				//获取短信管理器
				SmsManager sms = SmsManager.getDefault();
				//把短信分割成集合
				ArrayList<String> items = sms.divideMessage(bodyContent);
				//遍历发送
				for (String item : items) {
					//发送
					sms.sendTextMessage(number, null, bodyContent, null, null);
				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	
	}
}
