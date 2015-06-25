package com.main;

import java.io.File;

import com.example.opencamera.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static int REQUESTCOD = 1000;
	private Button opencamera, openintent;
	private ImageView camerapic;
	private String cameraPicName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		opencamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				cameraPicName = System.currentTimeMillis() + ".jpg";
				File photofile = new File("/sdcard/" + cameraPicName);
				Uri uri1 = Uri.fromFile(photofile);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
				startActivityForResult(intent, REQUESTCOD);
			}
		});

		openintent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("liyawei");
				intent.addCategory("android.intent.category.DEFAULT");
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK && requestCode == REQUESTCOD) {
			String picUri = "/sdcard/" + cameraPicName;
			File file1 = new File(picUri);
			if (file1.exists()) {
				new MyPic().execute(file1);
			}
		}

	}

	class MyPic extends AsyncTask<File, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(File... params) {
			return BitmapFactory.decodeFile(params[0].toString());
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				camerapic.setBackground(new BitmapDrawable(result));
			}
			super.onPostExecute(result);
		}
	}

	private void init() {
		opencamera = (Button) findViewById(R.id.opencamera);
		openintent = (Button) findViewById(R.id.openintent);
		camerapic = (ImageView) findViewById(R.id.camerapic);
	}

}
