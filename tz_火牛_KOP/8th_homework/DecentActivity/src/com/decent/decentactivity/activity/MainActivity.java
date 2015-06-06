package com.decent.decentactivity.activity;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.decent.decentactivity.R;
import com.decent.decentactivity.bean.User;
import com.decent.decentactivity.util.CustomLog;

public class MainActivity extends Activity implements OnClickListener
{
	private static final String TAG = "MainActivity";
	private static final String DECENT_OTHER_ACTIVITY = "com.decent.decentactivity.activity.OTHER";
	private static final String CAMERA_PATH = "//DCIM//Camera//temp.jpg";
	private Button mCameraButton;
	private Button mOtherButton;
	private ImageView mShowImg;
	private TextView mUserName;
	/*
	 * 打开照相机拍一张照片的requestCode
	 */
	public static final int OPEN_CAMERA_TAKE_A_PHOTO = 1;
	/*
	 * 隐式意图打开一个activity
	 */
	public static final int OPEN_ACTIVITY_IMPLICITLY = 2;
	private String mTempImgPath = Environment.getExternalStorageDirectory()
			+ CAMERA_PATH;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		mCameraButton = (Button) findViewById(R.id.open_camera);
		mCameraButton.setOnClickListener(this);

		mShowImg = (ImageView) findViewById(R.id.show_img);

		mOtherButton = (Button) findViewById(R.id.open_other);
		mOtherButton.setOnClickListener(this);

		mUserName = (TextView) findViewById(R.id.user_name);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.open_camera:
			/*
			 * Standard Intent action that can be sent to have the camera
			 * application capture an image and return it.
			 * 标准的intent打开照相机程序捕获图片，并且返回 类似的还有ACTION_VIDEO_CAPTURE
			 */
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			/*
			 * The caller may pass an extra EXTRA_OUTPUT to control where this
			 * image will be written. EXTRA_OUTPUT参数是用于控制照相机程序在哪里存放照片
			 */
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(mTempImgPath)));

			/*
			 * requestCode必须>=0
			 */
			startActivityForResult(intent, OPEN_CAMERA_TAKE_A_PHOTO);
			break;

		case R.id.open_other:
			Intent otherIntent = new Intent();
			otherIntent.setAction(DECENT_OTHER_ACTIVITY);
			/*
			 * 在启动activity的这边也是需要加这个CATEGORY_DEFAULT的
			 */
			otherIntent.addCategory(Intent.CATEGORY_DEFAULT);
			startActivityForResult(otherIntent, OPEN_ACTIVITY_IMPLICITLY);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		CustomLog.d(TAG, "into onActivityResult requestCode=" + requestCode
				+ ",resultCode=" + resultCode);
		switch (requestCode)
		{
		case OPEN_CAMERA_TAKE_A_PHOTO:
			String path = null;
			/*
			 * 如果data里面有数据
			 */
			if (data != null && data.getData() != null)
			{
				path = data.getData().getPath();
			} else
			{
				/*
				 * 如果data里面没有数据，则读取tempImgPath文件
				 */
				path = mTempImgPath;
			}

			mShowImg.setImageBitmap(rotationPic(path));
			break;

		case OPEN_ACTIVITY_IMPLICITLY:
			if (resultCode == Activity.RESULT_OK)
			{
				User user = data.getParcelableExtra("user");
				mUserName.setText(user.getName());
			}
			break;

		default:
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/*
	 * 根据图片路径获取
	 */
	private int getPicRotationInfo(String path)
	{
		int degree = -1;
		try
		{
			/*
			 * 读取图片Exif信息里面的旋转信息出来
			 */
			ExifInterface exifInterface = new ExifInterface(path);
			degree = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (degree)
			{
			case ExifInterface.ORIENTATION_NORMAL:
				degree = 0;
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			default:
				break;
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return degree;
	}

	private Bitmap rotationPic(String path)
	{
		int degree = getPicRotationInfo(path);
		Bitmap bitmap = BitmapFactory.decodeFile(path);

		CustomLog.d(TAG, "getPicRotationInfo return degree=" + degree);
		/*
		 *  degree不是0，表示图片被旋转过，需要旋转回来
		 */
		if (degree != -1 && degree != 0)
		{
			/*
			 * 图片放入暂存Bitmap，接着再利用Bitmap.
			 * createBitmap来创建新的Bitmap对象
			 * 在创建新的Bitmap对象的同时，搭配Matrix对象里的setRotate()方法动态旋转新创建的Bitmap。
			 */
			Matrix matrix = new Matrix();
			matrix.postRotate(degree);
			// matrix.preRotate(degree);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
					bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			return resizedBitmap;
		}
		return bitmap;
	}

}
