package com.decent.courier.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Intent;
import android.os.Environment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.decent.courier.common.BaseActivity;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentToast;

/**
 * 溅射屏幕，就是打开应用时候的第一屏幕
 * 
 * @author K450J
 * 
 */
public class SplashActivity extends BaseActivity {

	private RelativeLayout splash_main;
	private ProgressBar pb;

	@Override
	public void initContentView() {
		// 显示splash屏幕
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initView() {
		// 获得屏幕总的layout元素
		splash_main = (RelativeLayout) findViewById(R.id.splash_main);
		pb = (ProgressBar) findViewById(R.id.pb);

		// 设置动画
		Animation alphaAnimation = AnimationUtils.loadAnimation(this,
				R.anim.splash_alpha);
		splash_main.setAnimation(alphaAnimation);
		alphaAnimation.start();
		// 同时在动画监听器后台初始化中国行政地区数据库
		alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				try {
					loadDb();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					DecentToast.showToastLong(SplashActivity.this,
							"初始化数据失败，请检查SD");
					return;
				}
				
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
			}
		});

	}

	@Override
	public void initListener() {
		//
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	/**
	 * 加载中国地区列表数据库文件
	 */
	private void loadDb() throws Exception {
		// 判断sd卡是否mount
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 判断文件夹下面是否有mydata文件
			File locationDbFile = new File(DecentConstants.LOCATION_DB_PATH);
			if (!locationDbFile.exists()) {
				// 如果没有就加载文件到sd卡对应的路径

				// 获得R.raw.mydata的InputStream
				InputStream is = getResources().openRawResource(R.raw.mydata);
				// 在读之前读取总的大小,设置pb
				int totalBytes = is.available();
				pb.setMax(totalBytes);

				// 申请buffer区，获得locationDbFile的os
				byte[] buffer = new byte[totalBytes / 5];
				int readSizeThisTime = 0;
				int totalReadSize = readSizeThisTime;
				OutputStream os = new FileOutputStream(locationDbFile);

				// pb.setc
				os = new FileOutputStream(locationDbFile);
				while ((readSizeThisTime = is.read(buffer, 0, buffer.length)) != -1) {
					totalReadSize += readSizeThisTime;
					pb.setProgress(totalReadSize);
					os.write(buffer, 0, readSizeThisTime);
				}
				os.flush();
				os.close();
				is.close();

			}
			// 如果没有什么也不做
			DecentToast.showToastLong(this, "开始跳转到登录界面");
		} else {
			DecentToast.showToastLong(this, "请坚持SD卡是否插入");
		}
	}
}