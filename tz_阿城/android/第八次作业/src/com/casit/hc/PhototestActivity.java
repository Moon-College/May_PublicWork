package com.casit.hc;



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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PhototestActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	Button toActivityBt, toPhotoBt;
	ImageView myImageView;
	private static final String CAMERA_PATH = "//DCIM//Camera//temp.jpg";
	public final static int TO_PHOTO_BACK  =1; //发送与返回requestCode代码 
	public final static int TO_OTHER_BACK  =2; //发送与返回requestCode代码 
	private String mTempImgPath = Environment.getExternalStorageDirectory()
			+ CAMERA_PATH;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toActivityBt = (Button) findViewById(R.id.other);
        toPhotoBt = (Button) findViewById(R.id.photo);
        myImageView = (ImageView) findViewById(R.id.myiv);
        toActivityBt.setOnClickListener(this);
        toPhotoBt.setOnClickListener(this);
        
    }
	public void onClick(View v) {
		// TODO Auto-generated method stubs
		     
		switch(v.getId()){
		case R.id.other:
			jumpToOther();
		    break;
		case R.id.photo:
			jumpToPhoto();
			break;
		default:
			break;
		}
		
	}
    
	public void jumpToOther(){
		Intent intent = new Intent();
		intent.setAction("casit.intent.action.TwoActivity");
	    intent.addCategory("android.intent.category.DEFAULT");
	    MyClass myclass = new MyClass();
	    myclass.setClassName("软工2班");
	    myclass.setStudentsCount(25);	    
	    intent.putExtra("data1", myclass);
	    this.startActivityForResult(intent,TO_OTHER_BACK);
	}
	public void jumpToPhoto(){
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(mTempImgPath)));
		Log.i("INFO", mTempImgPath);
		startActivityForResult(intent,TO_PHOTO_BACK);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("INFO", "test");
		Log.i("INFO", String.valueOf(requestCode));
		switch(requestCode){
		case TO_OTHER_BACK:
				MyClass myclasses = data.getParcelableExtra("backdata");			
			    Toast.makeText(this, myclasses.getClassName(), 2000).show();		   		    
		    break;
		case TO_PHOTO_BACK:
			String path = null;
			/*
			 * 如果data里面有数据
			 */
			if (data != null && data.getData() != null)
			{
				path = data.getData().getPath();

			} else
			{
				Log.i("INFO", "view1");				
				/*
				 * 如果data里面没有数据，则读取tempImgPath文件
				 */
				path = mTempImgPath;
			}

			//myImageView.setImageBitmap(rotationPic(path));
			myImageView.setImageBitmap(BitmapFactory.decodeFile(path));  
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

}