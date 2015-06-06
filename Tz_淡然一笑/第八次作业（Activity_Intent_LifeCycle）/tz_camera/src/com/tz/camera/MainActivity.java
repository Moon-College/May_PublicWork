package com.tz.camera;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.tz.camera.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	/**
	 * 拍照
	 */
	private Button btn_camera;
	/**
	 * 本地图库
	 */
	private Button btn_localPic;
	/**
	 * 图片
	 */
	private ImageView img;
	/**
	 * 图片uri
	 */
	private Uri photoUri;
	
	public final int CAMERA = 0;
	public final int LOCLPIC = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化UI控件
		initViews();
		//事件监听
		setListeners();
	}

	/**
	 * 初始化UI控件
	 */
	private void initViews() {
		btn_camera = (Button) findViewById(R.id.btn_camera);
		btn_localPic = (Button) findViewById(R.id.btn_localPic);
		img = (ImageView) findViewById(R.id.img);
	}

	/**
	 * 事件监听
	 */
	private void setListeners() {
		btn_camera.setOnClickListener(this);
		btn_localPic.setOnClickListener(this);
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		case R.id.btn_camera:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String fileName = sdf.format(new Date());
			ContentValues values = new ContentValues();
			values.put(Media.TITLE, fileName);
			photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(intent, CAMERA);
			break;
		case R.id.btn_localPic:
			it = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(it, LOCLPIC);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CAMERA:
			if (resultCode == RESULT_OK) {
				Uri uri = null;
				if (data != null && data.getData() != null) {
					uri = data.getData();
				}
				if (uri == null) {
					if (photoUri != null) {
						uri = photoUri;
					}
				}
				img.setImageURI(uri);
			} else if (resultCode == RESULT_CANCELED) {

			}
			break;
		case LOCLPIC:
			if (resultCode == RESULT_OK) {
				if (data != null) {
					Uri selectedImage = data.getData();
					String[] filePathColumn = { MediaStore.Images.Media.DATA };

					Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
					cursor.moveToFirst();

					int columnIndex = cursor.getColumnIndex(filePathColumn[CAMERA]);
					String picturePath = cursor.getString(columnIndex);
					cursor.close();
					img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
				}
			} else if (resultCode == RESULT_CANCELED) {

			}
			break;
		default:
			break;
		}
	}
}
