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
 * ������Ļ�����Ǵ�Ӧ��ʱ��ĵ�һ��Ļ
 * 
 * @author K450J
 * 
 */
public class SplashActivity extends BaseActivity {

	private RelativeLayout splash_main;
	private ProgressBar pb;

	@Override
	public void initContentView() {
		// ��ʾsplash��Ļ
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initView() {
		// �����Ļ�ܵ�layoutԪ��
		splash_main = (RelativeLayout) findViewById(R.id.splash_main);
		pb = (ProgressBar) findViewById(R.id.pb);

		// ���ö���
		Animation alphaAnimation = AnimationUtils.loadAnimation(this,
				R.anim.splash_alpha);
		splash_main.setAnimation(alphaAnimation);
		alphaAnimation.start();
		// ͬʱ�ڶ�����������̨��ʼ���й������������ݿ�
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
							"��ʼ������ʧ�ܣ�����SD");
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
	 * �����й������б����ݿ��ļ�
	 */
	private void loadDb() throws Exception {
		// �ж�sd���Ƿ�mount
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// �ж��ļ��������Ƿ���mydata�ļ�
			File locationDbFile = new File(DecentConstants.LOCATION_DB_PATH);
			if (!locationDbFile.exists()) {
				// ���û�оͼ����ļ���sd����Ӧ��·��

				// ���R.raw.mydata��InputStream
				InputStream is = getResources().openRawResource(R.raw.mydata);
				// �ڶ�֮ǰ��ȡ�ܵĴ�С,����pb
				int totalBytes = is.available();
				pb.setMax(totalBytes);

				// ����buffer�������locationDbFile��os
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
			// ���û��ʲôҲ����
			DecentToast.showToastLong(this, "��ʼ��ת����¼����");
		} else {
			DecentToast.showToastLong(this, "����SD���Ƿ����");
		}
	}
}