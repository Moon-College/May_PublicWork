package com.qfx.intentstartway;

import java.io.File;
import java.io.IOException;

import com.qfx.intentstartway.bean.Student;
import com.qfx.intentstartway.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView iv;
	private Button btn;
	
	private File mFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		btn = (Button) findViewById(R.id.btn_camera);
		btn.setOnClickListener(this);
	}
	
	public void jump(View v) {
//		jumpToMain();
//		jumpToTow();
//		jumpToTow1();
//		jumpToTow2();
		jumpToTow3();
	}
	
	//隐式启动
	public void jumpToMain() {
		//用构造方法，或者通过set方法都可以
//		Intent intent = new Intent("android.intent.action.MAIN");
		Intent intent = new Intent();
		//只有action时会启动所有配置action为main的的程序，action只会有一个，多个会覆盖其中一个
		//配置清单的action可以配置多个，启动任何一个被配置的action都会启动这个应用
		intent.setAction("android.intent.action.MAIN");
		//添加category为launcher时，只会启动launcher桌面的程序
		//category可以添加多个
		intent.addCategory("android.intent.category.LAUNCHER");
		startActivity(intent);
	}
	
	//显示启动
	public void jumpToTow() {
//		Intent intent = new Intent(this, TwoActivity.class);
		Intent intent = new Intent();
		//显示调用
		intent.setClass(this, TwoActivity.class);
		startActivity(intent);
	}
	
	//隐式调用
	public void jumpToTow1() {
		Intent intent = new Intent();
		intent.setAction("com.qfx.intentstartway.TWO");
		//代码里面没添加category，系统默认给default，若清单里面没添加那么程序会报类没找到异常
		intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}
	
	public void jumpToTow2() {
		Intent intent = new Intent();
		User user = new User();
		user.setName("张小飞");
		user.setAge(25);
		intent.putExtra("name", user.getName());
		intent.putExtra("age", user.getAge());
		intent.putExtra("isMarried", false);
		intent.putExtra("user", user);
		intent.setClass(this, TwoActivity.class);
		startActivity(intent);
	}
	
	public void jumpToTow3() {
		Intent intent = new Intent();
		Student stu = new Student();
		stu.setSname("张小飞");
		stu.setSage(25);
		intent.putExtra("student", stu);
		intent.setClass(this, TwoActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera_test.jpg");
		if (!mFile.exists()) {
			try {
				mFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
		startActivityForResult(intent, 10);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//当照相被取消，将返回0
		if (resultCode == 0) {
			return;
		}
		if (requestCode == 10) {
			Bitmap bm = compressBmpFromBitmap(mFile);
			iv.setImageBitmap(bm);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	public Bitmap compressBmpFromBitmap(File file) {
		// 对大分辨率和大图做处理
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			//不读到内存
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(file.getPath(), options);
			
			// 设置采样率
			options.inSampleSize = calculateInSampleSize(options, 400, 800);
			// 下面两条同时设置才会有效
			options.inPurgeable = true;
			options.inInputShareable = true;
			// 创建内存
			options.inJustDecodeBounds = false;
			// 使图片不抖动
			options.inDither = false;
			bitmap = BitmapFactory.decodeFile(file.getPath(), options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	public int calculateInSampleSize(BitmapFactory.Options options, int width, int height) {
		int outwidth = options.outWidth;
		int outHeight = options.outHeight;
		int inSampleSize = 1;
		if (outwidth > width || outHeight > height) {
			int widthRatio = Math.round(((float) outwidth / (float) width));
			int heightRatio = Math.round(((float) outHeight / (float) height));
			inSampleSize = widthRatio > heightRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
}
