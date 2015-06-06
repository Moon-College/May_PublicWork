package com.junwen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intentdemo.R;

public class MainActivity extends Activity {

	private Intent intent;
	private Context context;
	//����ҳ���action
	private final String OPEN_PHONE = "com.junwen.actionPhone";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		context = this;
	}

	public void onItemClick(View view) {
		intent = new Intent();
		switch (view.getId()) {
		case R.id.btn_takePhoto:
			// ����
			startIntent(TakePhotoActivity.class);
			break;
		case R.id.btn_takePhone:
			// ��绰
			startIntent(TakePhoneActivity.class);
			break;
		case R.id.btn_takeSms:
			// ������
			startIntent(TakeSmsActivity.class);
			break;
		case R.id.btn_takeEmail:
			// ���ʼ�
			startIntent(TakeEamilActivity.class);
			break;
		case R.id.btn_inflater:
			// ����ʽ��ͼ
			//action
			intent.setAction(OPEN_PHONE);
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			if( intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * ��תҳ��
	 * @param context
	 * @param cls
	 */
	private void startIntent(Class<?> cls) {
		intent.setClass(context,cls);
		startActivity(intent);
	}
}
