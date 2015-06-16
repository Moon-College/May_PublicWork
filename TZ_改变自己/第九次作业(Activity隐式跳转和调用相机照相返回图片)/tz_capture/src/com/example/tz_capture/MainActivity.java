package com.example.tz_capture;

import java.io.File;
import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button btn_capture;
	
	private ImageView image;
	
	private static final int GET_IMAGE_VIA_CAMERA = 1;
	
	private static final String localTempImgDir = "wdj";
	
	private static final String localTempImgFileName = "wdj.jpg";
	
	private Button yinshi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn_capture = (Button) findViewById(R.id.btn_capture);
		image = (ImageView) findViewById(R.id.img_capture);
		yinshi = (Button) findViewById(R.id.yinshi);
		btn_capture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startCamera();
			}
		});
		
		yinshi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.android.twoactivity");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				startActivity(intent);
			}
		});
	}
	
	private void startCamera() {
		String status = Environment.getExternalStorageState();
		
		if(Environment.MEDIA_MOUNTED.equals(status)) {
			File dir = new File(Environment.getExternalStorageDirectory() + "/" + localTempImgDir);
			if(!dir.exists())
				dir.mkdirs();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File file = new File(dir,localTempImgFileName);
			Uri uri = Uri.fromFile(file);
			intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(intent, GET_IMAGE_VIA_CAMERA);
			
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			if(GET_IMAGE_VIA_CAMERA == requestCode) {
				File f=new File(Environment.getExternalStorageDirectory() 
						+"/"+localTempImgDir+"/"+localTempImgFileName); 
				try {
					Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), 
							f.getAbsolutePath(), null, null));
					//uri就是拍摄活得的原图图片的uri
					image.setImageURI(uri); 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
