package com.cn.asynctask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.cn.asynctask.utis.ApkTask;
import com.cn.asynctask.utis.ImageTask;
import com.cn.asynctask.utis.Reflection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_download_img, btn_download_apk;
	private ImageView image;
	private TextView tv;
	private ProgressBar pb;
	private String img_url = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1308/08/c0/24273880_1375934245744_800x600.jpg";
	private String apk_url = "http://sqdd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";
	private ImageTask imageTask;
	private ApkTask apkTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		Reflection.initView(this, R.id.class);
		// btn_download_apk=(Button) findViewById(R.id.btn_download_apk);
		// btn_download_img=(Button) findViewById(R.id.btn_download_img);
		// pb=(ProgressBar) findViewById(R.id.pb);
		// tv=(TextView) findViewById(R.id.tv);
		btn_download_img.setOnClickListener(this);
		btn_download_apk.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_download_img:
			imageTask = new ImageTask(image, tv, pb);
			imageTask.execute(img_url);
			break;
		case R.id.btn_download_apk:
			apkTask = new ApkTask(pb, tv, this);
			apkTask.execute(apk_url);
			break;
		default:
			break;
		}

	}

}