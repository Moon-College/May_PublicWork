package com.tz.startcamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final int REQ_CODE = 1;
	private ImageView showPhotoView;
	private Uri cameraPhotoUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showPhotoView = (ImageView) findViewById(R.id.main_show_photo);
		Button startCamera = (Button) findViewById(R.id.main_start_system_camera);
		startCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cameraPhotoUri = getOutputMediaFileUri();

				// ����ϵͳ���
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// ��������ļ�·��
				intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraPhotoUri);
				startActivityForResult(intent, REQ_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
			// ����uri�õ�bitmap����ʾ
			Bitmap cameraBitmap = decodeUri2Bitmap(cameraPhotoUri);
			showPhotoView.setImageBitmap(cameraBitmap);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Uri getOutputMediaFileUri() {
		// ��ȡ��ǰʱ����Ϊ�ļ���
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String currDate = sdf.format(new Date());
		String imageName = String.format("IMG_%s.jpg", currDate);

		// ������DCIMĿ¼��
		File imageFile = new File(Environment.getExternalStorageDirectory()
				+ "/" + Environment.DIRECTORY_DCIM + "/Camera/" + imageName);
		return Uri.fromFile(imageFile);
	}

	private Bitmap decodeUri2Bitmap(Uri uri) {
		try {
			// ͨ��uri��ȡ������
			InputStream ins = getContentResolver().openInputStream(uri);
			// ��������ת����Bitmap����
			Bitmap bitmap = BitmapFactory.decodeStream(ins);
			return bitmap;
		} catch (FileNotFoundException e) {
			Log.e("ERROR decode uri failed! ", e.getMessage());
			return null;
		}
	}
}
