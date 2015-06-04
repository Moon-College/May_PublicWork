package com.junwen.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.intentdemo.R;

public class TakePhotoActivity extends Activity {
	//ImageView
	private ImageView img;
	//������
	private static final int REQUEST_IMAGE_CAPTURE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephoto_layout);
		initView();
	}
	/**
	 * ��ʼ��
	 * @author June
	 */
	private void initView() {
		img = (ImageView) findViewById(R.id.img);
	}
	/**
	 * ����ť����ʱ����
	 * @author June
	 * @param view ��ť
	 */
	public void onItemClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.takePhoto:
			//����
			//���ô������Action
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			//�ж������ͼ�Ƿ��ܹ������ʣ�����ܹ����򿪣�������start���������ͼ
			if( intent.resolveActivity(getPackageManager()) != null) {
				startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * ����һ��ҳ�淵��ʱ����
	 * @author June
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//�����������ȷ�ͷ��ؽ��������ȷ��
		if( requestCode == requestCode && resultCode == RESULT_OK) {
			//���������
			if( data != null) {
				//��ȡ���ݼ���
				Bundle extras = data.getExtras();
				//���ݼ���ȡֵ
				Bitmap bitmap = (Bitmap) extras.get("data");
				//����ͼƬ
				img.setImageBitmap(bitmap);
			}
		}
			
	}
}
