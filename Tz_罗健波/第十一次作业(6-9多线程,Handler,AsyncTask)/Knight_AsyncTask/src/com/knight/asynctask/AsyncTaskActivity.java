package com.knight.asynctask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.knight.asynctask.util.DownloadApkTask;
import com.knight.asynctask.util.DownloadImageTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
public class AsyncTaskActivity extends Activity implements OnClickListener {

    private Button downloadImg,downloadApk;
	private TextView tv;
	private ImageView image;
	private ProgressBar progressImg,progressApk;
	private int maxLength;
	private String imageUri = "http://www.wanche100.com/uploadfile/News/201311/4/20131144540671.jpg";
	//"http://121.40.98.88:8085/android/Android_loveShowMirror.apk"
	private String apkUri = "http://www.apk.anzhi.com/data3/apk/201505/25/5179feb1d0ee799629c0b7e7fe73c5db_42523900.apk";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

	/**
	 * initalization  control and listener
	 * @Title: init
	 * @Description: TODO
	 * @return: void
	 */
	private void init() {
		downloadImg = (Button)findViewById(R.id.download_image);
		downloadApk = (Button)findViewById(R.id.download_apk);
		progressImg = (ProgressBar)findViewById(R.id.progressImg);
		progressApk = (ProgressBar) findViewById(R.id.progressApk);
		image = (ImageView)findViewById(R.id.image);
		tv = (TextView)findViewById(R.id.tv_prog);
		downloadImg.setOnClickListener(this);
		downloadApk.setOnClickListener(this);
	}

	/**
	 * onclick invoke AsyncTask download Bitmap
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.download_image:
			if (downloadImg.getText().toString().equals("下载图片")) {
				DownloadImageTask imageTask = new DownloadImageTask(this,tv,progressImg,image);
				imageTask.execute(imageUri);
				downloadImg.setText("暂停");
			}
			if (tv.getText().toString().equals("当前进度:100%")) {
				downloadImg.setText("完成");
			}
			break;
		case R.id.download_apk:
			DownloadApkTask apkTask = new DownloadApkTask(this, tv, progressApk);
			apkTask.execute(apkUri);
			break;

		default:
			break;
		}
		
	}
	
	
}
