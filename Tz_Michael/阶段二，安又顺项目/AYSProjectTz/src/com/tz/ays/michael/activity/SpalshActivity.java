package com.tz.ays.michael.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.tz.ays.michael.R;
import com.tz.ays.michael.common.BaseActivity;
import com.tz.ays.michael.common.Constrant;
/**
 * 引导页
 * @author szm
 *
 */
public class SpalshActivity extends BaseActivity {

	RelativeLayout bg;
	private ProgressBar pb;
	
	@Override
	public void setContentLayout() {
		setContentView(R.layout.spalsh);
	}

	@Override
	public void initViews() {
		bg = (RelativeLayout) findViewById(R.id.splash_main);
		pb = (ProgressBar) findViewById(R.id.pb);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_alpha);
		bg.setAnimation(anim);
		anim.start();
		anim.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			public void onAnimationEnd(Animation animation) {
				//加载数据库
				try {
					loadDb();
					startLogin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void startLogin() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * 将数据库加载到sd卡
	 * @throws IOException 
	 */
	protected void loadDb() throws IOException {
		//判断sd卡是否可用
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//先判断数据库是否存在
			File f=new File(Constrant.DB_PATH);
			if(!f.exists()){
				pb.setVisibility(View.VISIBLE);
				InputStream in = getResources().openRawResource(R.raw.mydata);
				pb.setMax(in.available());
				byte[] b=new byte[100];
				int len=0;
				FileOutputStream fos=new FileOutputStream(f);
				while((len=in.read(b))!=-1){
					fos.write(b, 0, len);
					pb.setProgress(pb.getProgress()+len);
				}
				fos.close();
				in.close();
			}
		}else{
			Toast.makeText(this, "请检查sd卡", 0).show();
		}
	}

	@Override
	public void setListenners() {
		
	}

	@Override
	public void getData() {
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
}