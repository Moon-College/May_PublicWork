package com.yl.activity;

import java.io.File;

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
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
    private ImageView iv;
    private String imgName;
    private static String path=Environment.getExternalStorageDirectory().getAbsolutePath().toString();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        iv=(ImageView) this.findViewById(R.id.img);
        iv.setOnClickListener(this);
    }
	public void onClick(View v) {
		getCameraData();
	}
	private void getCameraData(){
//		Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		this.startActivityForResult(it, 20);
		setData();
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK ){	
			if(requestCode==20){
		     Bundle extras = data.getExtras();
		     Bitmap b = (Bitmap) extras.get("data");
			 iv.setImageBitmap(b);
			}else if(requestCode==30){
				String picPath = path + "/" + imgName;
				Log.i("INFO", picPath);
				iv.setImageBitmap(BitmapFactory.decodeFile(picPath));
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void setData(){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		imgName = System.currentTimeMillis() + ".jpg";
		File file = new File(path + "/" + imgName);
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, 30);
	}
}