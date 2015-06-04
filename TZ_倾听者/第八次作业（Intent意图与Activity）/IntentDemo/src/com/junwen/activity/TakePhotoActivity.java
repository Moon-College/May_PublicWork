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
	//请求码
	private static final int REQUEST_IMAGE_CAPTURE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephoto_layout);
		initView();
	}
	/**
	 * 初始化
	 * @author June
	 */
	private void initView() {
		img = (ImageView) findViewById(R.id.img);
	}
	/**
	 * 当按钮按下时调用
	 * @author June
	 * @param view 按钮
	 */
	public void onItemClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.takePhoto:
			//拍照
			//设置打开相机的Action
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			//判断这个意图是否能够被访问，如果能够被打开，则让你start开启这个意图
			if( intent.resolveActivity(getPackageManager()) != null) {
				startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 当上一个页面返回时调用
	 * @author June
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//如果请求码正确和返回结果码是正确的
		if( requestCode == requestCode && resultCode == RESULT_OK) {
			//如果有数据
			if( data != null) {
				//获取数据集合
				Bundle extras = data.getExtras();
				//根据键获取值
				Bitmap bitmap = (Bitmap) extras.get("data");
				//设置图片
				img.setImageBitmap(bitmap);
			}
		}
			
	}
}
