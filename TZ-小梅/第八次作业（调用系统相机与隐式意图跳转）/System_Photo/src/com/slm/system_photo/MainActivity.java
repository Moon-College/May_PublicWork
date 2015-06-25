package com.slm.system_photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.slm.system_photo.bean.User;
import com.slm.system_photo.bean.UserInfo;

public class MainActivity extends Activity {
	private static final int PICTURE = 0;
	private ImageView iv_images;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_images = (ImageView) findViewById(R.id.iv_images);
	}
	//隐式意图启动另一个Activity
	public void intent(View view){
		hiddenIntent();
		//serializableIntent();
		//parcelableIntetn();
	}
	
	//Parcelable接口传值
	private void parcelableIntetn() {
		Intent intent = new Intent();
		intent.setAction("slm.other");
		User user = new User();
		user.setName("XXXX");
		user.setSex("男");
		intent.putExtra("data1", user);
		startActivity(intent);
	}
	
	
	//Serializable接口传值
	private void serializableIntent() {
		Intent intent = new Intent();
		intent.setAction("slm.other");
		UserInfo info = new UserInfo();
		info.setName("小梅");
		info.setAge(20);
		info.setSex("女");
		intent.putExtra("data", info);
		startActivity(intent);
		
	}
	//隐式意图启动另一个Activity
	private void hiddenIntent() {
		Intent intent = new Intent();
		intent.setAction("slm.other");
		intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}
	//拍照
	public void photo(View view) {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
		//指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, PICTURE);
	}

	// 在onActivityResult方法中再将图片取出，并经过缩小处理再显示在界面上
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case PICTURE:
				System.out.println("==============");
				// 将保存在本地上的图片取出来然后再显示在界面上
				Bitmap bitmap = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/image.jpg");
				// System.out.println("=========1111" + bitmap.toString());
				Bitmap newBitmap = zoomBitmap(bitmap, bitmap.getWidth() / 5,
						bitmap.getHeight() / 5);
				// System.out.println("========="+ newBitmap.toString());
				// 回收内存
				bitmap.recycle();
				// 将处理过的图片显示到界面上，并保存
				iv_images.setImageBitmap(newBitmap);
				System.out.println("=======");
				savePhotoToSDCard(newBitmap, Environment
						.getExternalStorageDirectory().getAbsolutePath(),
						String.valueOf(System.currentTimeMillis()));
				System.out.println("========================");
				break;
			default:
				break;
			}
		}
	}

	// 处理图片缩放的方法
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	// 保存到SD卡的方法
	public static void savePhotoToSDCard(Bitmap photoBitmap, String path,
			String photoName) {
		if (checkSDCardAvailable()) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File photoFile = new File(path, photoName + ".png");
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
							fileOutputStream)) {
						fileOutputStream.flush();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
				e.printStackTrace();
			} catch (IOException e) {
				photoFile.delete();
				e.printStackTrace();
			} finally {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean checkSDCardAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

}
