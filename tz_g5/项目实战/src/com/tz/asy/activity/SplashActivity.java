package com.tz.asy.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.tz.asy.R;
import com.tz.asy.common.BaseActivity;
import com.tz.asy.common.MyConstants;


public class SplashActivity extends BaseActivity {
	RelativeLayout bg;
	private ProgressBar pb;
	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initView() {
		bg = (RelativeLayout) findViewById(R.id.splash_main);
		pb = (ProgressBar) findViewById(R.id.pb);
		//开启动画
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_alpha);
		bg.setAnimation(animation);
		animation.start();
		animation.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationEnd(Animation animation) {
				//加载数据库
				try {
					loadDb();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		
	}
	
	public void startLogin(){
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	/**
	 * 在引导页面加载省，市，县的数据库，数据库写进sd卡
	 * @throws Exception 
	 */
	private void loadDb() throws Exception {
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//sd准备好了
			//首先判断数据库在sd卡是否存在
			File file = new File(MyConstants.DB_PATH);
			if(!file.exists()){
				//讲raw里面的数据库写到sd卡
				InputStream is = this.getResources().openRawResource(R.raw.mydata);
				pb.setMax(is.available());//获取输入流的长度
				FileOutputStream fos = new FileOutputStream(file);
				int len = 0;
				byte [] buffer = new byte[1024];
				while((len = is.read(buffer))!=-1){
					fos.write(buffer,0,len);
					pb.setProgress(pb.getProgress()+len);
				}
				fos.close();
				is.close();
			}
		}else{
			Toast.makeText(this, "请检查sd卡", Toast.LENGTH_SHORT).show();
		}
		startLogin();
	}
	
}