package com.tz.camera;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class MainActivity extends Activity implements OnClickListener {
    private static final int RESULT_CAMERA = 1;
	private Button car_camera;
	private Button car_intent;
	private ImageView car_img;
	private File camera_file;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }

	private void initUI() {
		car_camera=(Button)findViewById(R.id.car_btn_camera);
		car_intent=(Button)findViewById(R.id.car_btn_intent);
		car_img=(ImageView)findViewById(R.id.car_btn_img);
		car_camera.setOnClickListener(this);
		car_intent.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_btn_camera:
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
			//获取存放照片的地址
			camera_file = createImageFile();
			i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(camera_file));
			startActivityForResult(i, RESULT_CAMERA);
			break;
		case R.id.car_btn_intent:
//			User user = new User();
//			user.setName("小米");
//			user.setAddress("上海");
			
			Intent intent = new Intent();
//			Bundle mBundle = new Bundle();   
//	        mBundle.putParcelable("date", user);   
//	        intent.putExtras(mBundle);   
			intent.setAction("tz.android.intent.two");            
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_CAMERA:
			if (resultCode == Activity.RESULT_OK) {
				String path=camera_file.getAbsolutePath();
				Bitmap bitmap = FileUtil.getIamge(path);//压缩图片
				car_img.setImageBitmap(bitmap);
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

	private File createImageFile() {
		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd_hhmmss");
		String time = format.format(new Date());
		String fileName = "tz_"+time+".jpg";
		File image = new File(getCameraDir(),fileName);
		return image;
	}
	
	public File getCameraDir(){
		File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Camera");
		if (!dir.exists()){
			dir.mkdirs();
		}
		return dir;
		
		
	}
}