package com.junwen.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentdemo.R;

public class TakePhoneActivity extends Activity implements OnClickListener {
	// 电话号码输入框
	private EditText edit_Text;
	// 选择联系人
	private Button btnSelectContacts;
	// 打开拨号界面
	private Button btnPlayPhoneLayout;
	// 拨打电话
	private Button btnPlayPhone;
	//联系人集合
	private static int REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephone_layout);
		//初始化
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		
		//控件绑定
		edit_Text = (EditText) findViewById(R.id.edit_Text);
		btnSelectContacts = (Button) findViewById(R.id.select_Contacts);
		btnPlayPhoneLayout = (Button) findViewById(R.id.playPhone_layout);
		btnPlayPhone = (Button) findViewById(R.id.playPhone);
		
		//事件添加
		btnPlayPhone.setOnClickListener(this);
		btnSelectContacts.setOnClickListener(this);
		btnPlayPhoneLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent =null;
		switch (v.getId()) {
		case R.id.select_Contacts:
			//获取联系人,开启联系人列表
			intent = new Intent();
			intent.setClass(TakePhoneActivity.this, ContactActivity.class);
			if(intent.resolveActivity(getPackageManager()) != null) {
				startActivityForResult(intent, REQUEST_CODE);
			}
			break;
		case R.id.playPhone:
			//打电话
			intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			String number = edit_Text.getText().toString().trim();
			//设置Data
			intent.setData(Uri.parse("tel:"+number));
			//判断是否可以进行这个意图
			if(intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}else {
				//intent有问题了
				Toast.makeText(TakePhoneActivity.this, "Intent配置有问题", 0).show();
			}
			break;
		case R.id.playPhone_layout:
			//打开拨号界面
			intent = new Intent(this,PhoneLayoutActivity.class);
			if(intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//判断是否是联系人页面跳转回来的，并且带有参数
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
			//获取电话号码
			Bundle extras = data.getExtras();
			String number = (String) extras.get("number");
			String phoneNum = number.replace("-", " ").replace(" ", "");
			//然后把电话号码设置到EditText里
			edit_Text.setText(phoneNum);
		}
		
	}
}
