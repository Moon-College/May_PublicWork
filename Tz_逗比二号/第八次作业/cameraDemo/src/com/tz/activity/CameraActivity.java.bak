package com.tz.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.camerademo.R;

public class CameraActivity extends Activity implements OnClickListener
{
	private static final int REQUEST_CODE_CAMERA 	= 10000;
	
	private static final String ACTION_SECOND_ACTIVITY	= "com.tz.activity.SECOND_ACTIVITY";
	
	private ImageView mCameraView;
	private Button mCameraBtn;
	private Button mStartBtn;
	
	private Uri mPhotoUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_camera);
		
		initView();
	}
	
	private void initView()
	{
		mCameraView = (ImageView) this.findViewById(R.id.camera_layout_camera_view);
		mCameraBtn = (Button) this.findViewById(R.id.camera_layout_camera_btn);
		mStartBtn = (Button) this.findViewById(R.id.camera_layout_start_btn);
		
		mCameraBtn.setOnClickListener(this);
		mStartBtn.setOnClickListener(this);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
			case REQUEST_CODE_CAMERA:
			{
				if (resultCode == RESULT_OK)
				{
					Bitmap photo = findBitmapByUri(mPhotoUri);
					if (photo != null)
					{
						mCameraView.setImageBitmap(photo);
					}
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 获取Bitmap并进行压缩
	 * @param uri
	 * @return
	 */
	private Bitmap findBitmapByUri(Uri uri)
	{
		if (uri == null) return null;
		
		InputStream input = null;
		Bitmap bitmap = null;
		try
		{
			input = getContentResolver().openInputStream(mPhotoUri);
			
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeStream(input, null, opts);
			
			float outWidth = opts.outWidth;
			float outHeight = opts.outHeight;
			float imageWidth = 400;
			float imageHeight = 400;
			
            int xRatio = (int) Math.ceil(outWidth / imageWidth);
            int yRatio = (int) Math.ceil(outHeight / imageHeight);
			int inSampleSize = 1;
            if (xRatio > 1 || yRatio > 1)
            {
            	if (xRatio > yRatio)
            	{
            		inSampleSize = xRatio;
            	}
            	else
            	{
            		inSampleSize = yRatio;
            	}
            }
			input.close();
			
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = inSampleSize;

			input = getContentResolver().openInputStream(mPhotoUri);
			bitmap = BitmapFactory.decodeStream(input, null, opts);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.camera_layout_camera_btn:
			{
				go2Camera();
				break;
			}
			case R.id.camera_layout_start_btn:
			{
				go2SecondActivity();
				break;
			}
		}
	}

	private void go2SecondActivity()
	{
		Intent intent = new Intent(ACTION_SECOND_ACTIVITY);
		startActivity(intent);
	}

	private void go2Camera()
	{
		String sdCard = Environment.getExternalStorageDirectory().getPath();
		String photoPath = sdCard + "/" + "tz";
		String photoName = "photo_" + System.currentTimeMillis() + ".jpg";
		File file = new File(photoPath, photoName);
		
		mPhotoUri = Uri.fromFile(file);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
		this.startActivityForResult(intent, REQUEST_CODE_CAMERA);
	}
}
