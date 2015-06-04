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
	// �绰���������
	private EditText edit_Text;
	// ѡ����ϵ��
	private Button btnSelectContacts;
	// �򿪲��Ž���
	private Button btnPlayPhoneLayout;
	// ����绰
	private Button btnPlayPhone;
	//��ϵ�˼���
	private static int REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephone_layout);
		//��ʼ��
		initView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		
		//�ؼ���
		edit_Text = (EditText) findViewById(R.id.edit_Text);
		btnSelectContacts = (Button) findViewById(R.id.select_Contacts);
		btnPlayPhoneLayout = (Button) findViewById(R.id.playPhone_layout);
		btnPlayPhone = (Button) findViewById(R.id.playPhone);
		
		//�¼����
		btnPlayPhone.setOnClickListener(this);
		btnSelectContacts.setOnClickListener(this);
		btnPlayPhoneLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent =null;
		switch (v.getId()) {
		case R.id.select_Contacts:
			//��ȡ��ϵ��,������ϵ���б�
			intent = new Intent();
			intent.setClass(TakePhoneActivity.this, ContactActivity.class);
			if(intent.resolveActivity(getPackageManager()) != null) {
				startActivityForResult(intent, REQUEST_CODE);
			}
			break;
		case R.id.playPhone:
			//��绰
			intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			String number = edit_Text.getText().toString().trim();
			//����Data
			intent.setData(Uri.parse("tel:"+number));
			//�ж��Ƿ���Խ��������ͼ
			if(intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}else {
				//intent��������
				Toast.makeText(TakePhoneActivity.this, "Intent����������", 0).show();
			}
			break;
		case R.id.playPhone_layout:
			//�򿪲��Ž���
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
		//�ж��Ƿ�����ϵ��ҳ����ת�����ģ����Ҵ��в���
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
			//��ȡ�绰����
			Bundle extras = data.getExtras();
			String number = (String) extras.get("number");
			String phoneNum = number.replace("-", " ").replace(" ", "");
			//Ȼ��ѵ绰�������õ�EditText��
			edit_Text.setText(phoneNum);
		}
		
	}
}
