package com.chris.activityintentdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.chris.utils.CLog;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class IntentDemoActivity extends Activity implements OnClickListener
{

	private static final String TAG = "IntentDemoActivity";
	private static final int CAPTURE_IMG_REQUEST_CODE = 0x1512;
	private static int PIC_WIDTH_LIMIT = 800;
	private static int PIC_HEIGHT_LIMIT = 480;
	private Button btnExtraDemo, btnCamera;
	private ImageView pic;
	private Uri picStoreUri;
	

	public Uri getPicStoreUri()
	{
		return picStoreUri;
	}

	public void setPicStoreUri(Uri picStoreUri)
	{
		this.picStoreUri = picStoreUri;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_demo);
		pic = (ImageView) findViewById(R.id.pic);
		btnExtraDemo = (Button) findViewById(R.id.btnExtraDemo);
		btnCamera = (Button) findViewById(R.id.btnCamera);
		btnExtraDemo.setOnClickListener(this);
		btnCamera.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btnExtraDemo:
			Intent intent = new Intent();
			intent.setAction("chris.action.jumpother");
			startActivity(intent);
			break;
		case R.id.btnCamera:
			String picStorePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/tzPic";
			String picName = "tzTrainning_"+System.currentTimeMillis()+".jpg";
			File file = new File(picStorePath, picName);
			setPicStoreUri(Uri.fromFile(file));	//更新照片存储地址的Uri
			//启动相机拍照
			Intent itCamera = new Intent();
			itCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			itCamera.putExtra(MediaStore.EXTRA_OUTPUT, picStoreUri);	//设置照片外部存储路径
			this.startActivityForResult(itCamera, CAPTURE_IMG_REQUEST_CODE);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (CAPTURE_IMG_REQUEST_CODE == requestCode)
		{
			if (Activity.RESULT_OK == resultCode)
			{
				CLog.i(TAG, "requestCode = " + requestCode);
				CLog.i(TAG, "resultCode = " + resultCode);
				CLog.i(TAG, "data = " + data);

				Bitmap bm;
				//如果使用了 intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)设置照片存储路径，则data会为空
				if(data != null)
				{
					setPicStoreUri(data.getData());	//更新照片存储地址的Uri
				}
				try
				{
					//从URI获取Bitmap图片，并缩放
					bm = scaleBitmapFromUri(picStoreUri);
					if (bm != null)
					{
						pic.setImageBitmap(bm);
						pic.setVisibility(ImageView.VISIBLE);
					}
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (Activity.RESULT_CANCELED == resultCode)
			{
				//image capture canceled.
			} else
			{
				//image capture failed.
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private Bitmap scaleBitmapFromUri(Uri data) throws IOException
	{
		if(data == null)
		{
			return null;
		}
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		InputStream is = getContentResolver().openInputStream(data);
		Bitmap bitmap = BitmapFactory.decodeStream(is, null, opts);
		int width = opts.outWidth;
		int height = opts.outHeight;
		if (width > height)
		{
			opts.inSampleSize = width / PIC_WIDTH_LIMIT;
//			opts.inSampleSize = (width/PIC_HEIGHT_LIMIT + height/PIC_WIDTH_LIMIT)/2;
		} else
		{
			opts.inSampleSize = height / PIC_HEIGHT_LIMIT;
//			opts.inSampleSize = (width/PIC_WIDTH_LIMIT + height/PIC_HEIGHT_LIMIT)/2;
		}
		CLog.i(TAG, "get opts.outWidth = " + opts.outWidth);
		CLog.i(TAG, "get opts.outHeight = " + opts.outHeight);
		CLog.i(TAG, "get opts.inSampleSize = " + opts.inSampleSize);
		opts.inJustDecodeBounds = false;
		
		is = getContentResolver().openInputStream(data);
		bitmap = BitmapFactory.decodeStream(is, null, opts);

		CLog.i(TAG,"bitmap = "+bitmap);
		CLog.i(TAG,"bitmap width = "+bitmap.getWidth());
		CLog.i(TAG,"bitmap height = "+bitmap.getHeight());
		
		is.close();
		
		return bitmap;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intent_demo, menu);
		CLog.i(TAG, "onCreateOptionsMenu");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		CLog.i(TAG, "onOptionsItemSelected");
		CLog.i(TAG, "id=" + item.getItemId());
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			Toast.makeText(this, "No function", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
