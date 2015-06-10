package com.example.asyncdownload;

import com.example.utils.AsyncDownApk;
import com.example.utils.AsyncDownBitmap;
import com.zoujm.utils.ReflectionUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button down_img,down_apk,down_cancel;
	private ImageView iv;
	private ProgressBar progress;
	private TextView tv_progress;
	private AsyncDownBitmap asyncDownBitymap;
	private AsyncDownApk asyncDownApk;
	private boolean cancel;
	
	private String url = "http://d.hiphotos.baidu.com/image/pic/item/024f78f0f736afc38265f124b119ebc4b7451202.jpg";
	private String apk_url = "http://115.29.34.61/ibco/page/GFAirnet.apk";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtil.initView(this, R.id.class);
		down_img.setOnClickListener(this);
		down_apk.setOnClickListener(this);
		down_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down_img:
			asyncDownBitymap = new AsyncDownBitmap(iv,progress,tv_progress);
			asyncDownBitymap.execute(url);
			break;
		case R.id.down_apk:
			asyncDownApk = new AsyncDownApk(MainActivity.this, progress, tv_progress);
			asyncDownApk.execute(apk_url);
			break;
		case R.id.down_cancel:
			cancel = (cancel == false?true:false);
			if(cancel){
				asyncDownBitymap.cancel(cancel);
			}else{
			}
			break;
		default:
			break;
		}
	}


}
