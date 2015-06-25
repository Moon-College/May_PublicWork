package com.tz.aysnctaskdownload;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tz.aysnctaskdownload.task.DownloadApkAsyncTask;
import com.tz.aysnctaskdownload.task.DownloadPictureAsyncTask;
import com.tz.aysnctaskdownload.task.listener.DownloadListener;

public class MainActivity extends Activity {
	public static final String ROOT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	private ImageView pictureView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pictureView = (ImageView) findViewById(R.id.show_picture);
	}

	public void downloadPicture(View v) {
		dialog = new ProgressDialog(MainActivity.this);
		String imageUrl = "http://img5q.duitang.com/uploads/item/201403/24/20140324151158_VHuVr.jpeg";
		DownloadListener<Bitmap> listener = new DownloadListener<Bitmap>() {

			@Override
			public void startLoading(int maxSize) {
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dialog.setMax(maxSize);
				dialog.show();
			}

			@Override
			public void loading(int loadingSize) {
				String message = (100 * loadingSize) / dialog.getMax() + "%";
				dialog.setMessage(message);
				dialog.setProgress(loadingSize);
			}

			@Override
			public void success(Bitmap bitmap) {
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				pictureView.setImageBitmap(bitmap);
			}

			@Override
			public void cancel() {
				dialog.cancel();
			}
		};
		DownloadPictureAsyncTask task = new DownloadPictureAsyncTask(listener);
		task.execute(imageUrl);
	}

	public void downloadApk(View v) {
		dialog = new ProgressDialog(MainActivity.this);
		String apkUrl = "http://dldir1.qq.com/qqfile/qq/edu/eduandroid/tencenteduforandroid_1.6.0.9_android_8050_100200200.apk";
		DownloadApkAsyncTask task = new DownloadApkAsyncTask(
				new DownloadListener<String>() {

					@Override
					public void startLoading(int maxSize) {
						dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						dialog.setProgressNumberFormat("%1d/%2dM");
						dialog.setMax(maxSize / (1 << 20));
						dialog.show();
					}

					@Override
					public void loading(int loadingSize) {
						String message = (100 * loadingSize) / dialog.getMax()
								+ "%";
						dialog.setMessage(message);
						dialog.setProgress(loadingSize/ (1 << 20));
					}

					@Override
					public void success(String filePath) {
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						installApk(filePath);
					}

					@Override
					public void cancel() {
						dialog.cancel();
					}

				});
		task.execute(apkUrl);
	}
	
	// 安装apk
	private void installApk(String apkFilePath) {
		File file = null;
		if (apkFilePath != null) {
			file = new File(apkFilePath);
		}
		
		if (file == null || (!file.exists())) {
			Toast.makeText(this, "文件已被删除", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent();
		intent.setDataAndType(Uri.parse("file://" + apkFilePath),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}
}
