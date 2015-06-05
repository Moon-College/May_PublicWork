package com.lzq.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	private ImageView iv;
	private String imageFilePath;
	private String image_path;
	private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button camera = (Button) findViewById(R.id.camera);
		camera.setOnClickListener(this);
		iv = (ImageView) findViewById(R.id.iv);
		path=Environment.getExternalStorageDirectory()+File.separator+Environment.DIRECTORY_DCIM;
	}

	@Override
	public void onClick(View v) {
		
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String temName=new DateFormat().format("yyMMdd_hhmmss",System.currentTimeMillis())+"_"+(Math.random()*100)+".jpg";
		image_path=path+File.separator+temName;
		File file=new File(image_path);
		if(file.exists()){
			file.delete();
		}
		Uri u=Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, u); 
		startActivityForResult(intent, 0);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK){
			File file=new File(image_path);
			try {
				Uri uri = Uri.fromFile(file);
				BitmapFactory.Options options=new BitmapFactory.Options();
				options.inJustDecodeBounds=true;				
				BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
				options.inSampleSize=4;
				options.inJustDecodeBounds=false;
				Bitmap map=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
//				Bitmap map = BitmapFactory.decodeFile(image_path);
//				Bitmap map=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
				// 保存到系统相册
				android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), map, null, null);
				// 扫描指定文件
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
				iv.setImageBitmap(map);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
