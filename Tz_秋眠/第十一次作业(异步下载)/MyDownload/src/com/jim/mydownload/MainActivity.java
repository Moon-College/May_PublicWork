package com.jim.mydownload;

import com.jim.mydownload.utils.AsysncTaskDownAPK;
import com.jim.mydownload.utils.AsysncTaskDownImage;
import com.jim.mydownload.utils.ReflectionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private String ImageUrl = "http://p0.so.qhimg.com/t019781ffa28ecd4e6a.jpg";
	private TextView tv1;
	private ProgressBar progressBar;
	private Button buttonDownloadImage;
	private Button buttonDownloadAPK;
	private ImageView imageView;
	private String APK_URL = "http://dldir1.qq.com/dlomg/weishi/weishi_guanwang.apk";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtils.initViews(this, R.id.class);
		buttonDownloadImage.setOnClickListener(this);
		buttonDownloadAPK.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonDownloadImage:
			AsysncTaskDownImage asysncTaskDownImage = new AsysncTaskDownImage(imageView, progressBar, tv1);
			asysncTaskDownImage.execute(ImageUrl);
			break;
		case R.id.buttonDownloadAPK:
			AsysncTaskDownAPK asysncTaskDownAPK = new AsysncTaskDownAPK(progressBar, tv1, this);
			asysncTaskDownAPK.execute(APK_URL);
			break;
		default:
			break;
		}
	}
}