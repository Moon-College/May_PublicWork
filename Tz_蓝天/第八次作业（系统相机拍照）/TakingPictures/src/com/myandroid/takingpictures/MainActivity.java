package com.myandroid.takingpictures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.myandroid.takingpictures.util.ReflectionUtil;

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

	private static final int CAMEAR_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;

	private Button bnt;
	private ImageView img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtil.initViews(this);
		bnt.setOnClickListener(this);

	}

	// 调用系统相机进行拍照
	private void Camear() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getPictureUri();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAMEAR_ACTIVITY_REQUEST_CODE);
	}

	// 创建图片存放的目录
	private Uri getPictureUri() {
		String time = new SimpleDateFormat("yyyy-MM-dd_HHmmss")
				.format(new Date());
		File path = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
				"MyCamera"+time);

//		File picUri = new File(path.getPath() + time + ".jpg");
		Log.i("INFO", path + "-------");
		return Uri.fromFile(path);
	}

	//用输入流把图片显示在imageview控件中
	private Bitmap getImage(Uri uri) {
		try {
			InputStream inputIamge = getContentResolver().openInputStream(uri);
			Bitmap bitmap = BitmapFactory.decodeStream(inputIamge);
			return bitmap;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 系统相机拍照后回调
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CAMEAR_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				Bitmap picBitmap = getImage(fileUri);
				img.setImageBitmap(picBitmap);
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	//点击按钮打开系统相机拍照
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Camear();
	}

}