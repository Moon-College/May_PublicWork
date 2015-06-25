package com.xigua.hideintent;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	private static final String IMAGE_FILE_NAME = "tempimg.jpg";
    private ImageView mImageView;
    private Button mButton;
    private Button mJump;
    private Bitmap mphoto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        init();
        
	}

	private void init(){
		mImageView = (ImageView) findViewById(R.id.image);
		mButton = (Button) findViewById(R.id.photo);
		mJump = (Button) findViewById(R.id.jump);
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentFromCapture = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				// 判断存储卡是否可以用，可用进行存储
				if (hasSdcard()) {
					intentFromCapture.putExtra(
							MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(Environment
									.getExternalStorageDirectory(),
									IMAGE_FILE_NAME)));
				}
				startActivityForResult(intentFromCapture,
						CAMERA_REQUEST_CODE);
			}
		});
		
		mJump.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.xigua.hideintent.Jump");
				startActivity(intent);
			}
		});
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {

			switch (requestCode) {
			case CAMERA_REQUEST_CODE:
                if(data != null){
                	mImageView.setImageURI(data.getData());
                }else{
                	String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+IMAGE_FILE_NAME;
                	BitmapFactory.Options options = new Options();
                	options.inSampleSize = 4;
                	Bitmap bitmap = BitmapFactory.decodeFile(path,options);
                	mImageView.setImageBitmap(bitmap);
                }

				break;
				
			}
		}
		super.onActivityResult(requestCode, resultCode, data);		
	}
	
	/**
	 * 检查是否存在SDCard
	 * @return
	 */
	public boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}

}
