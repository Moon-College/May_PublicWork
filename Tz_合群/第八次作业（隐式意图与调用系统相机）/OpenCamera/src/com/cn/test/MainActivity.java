package com.cn.test;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button camera, hidden_start;
	private ImageView img;
	public Uri mUri;
	private File file;
	private String imgName;
	private static String fileUri = Environment.getExternalStorageDirectory()
			+ "/picture" + System.currentTimeMillis() + ".jpg";
	private static final int IMAGE_ACTIVITY_ACTION_CODE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();

	}
	//≥ı ºªØ ”Õº
	private void initView() {
		// TODO Auto-generated method stub
		camera = (Button) findViewById(R.id.take_camera);
		hidden_start = (Button) findViewById(R.id.start_secode);
		img = (ImageView) findViewById(R.id.img);
		camera.setOnClickListener(this);
		hidden_start.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.take_camera:
			takePhoto();
			break;
		case R.id.start_secode:
			Intent mintent = new Intent();
			mintent.setAction("com.cn.test.Hidden");
			mintent.addCategory(Intent.CATEGORY_DEFAULT);
			startActivity(mintent);
			break;
		default:
			break;
		}

	}

	private void takePhoto() {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(new File(fileUri)));
		startActivityForResult(intent, IMAGE_ACTIVITY_ACTION_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == IMAGE_ACTIVITY_ACTION_CODE) {
			if (resultCode == RESULT_OK) {
				if (data != null) {
					Bitmap bitmap = data.getParcelableExtra("data");
					img.setImageBitmap(bitmap);
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}