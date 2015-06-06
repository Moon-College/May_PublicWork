package com.rocy.tzclass8;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CameraActivity extends Activity implements OnClickListener{
    private Button btnCommit,btnCarema,btnDismiss;
    private CaremaView caremaView;
    private Camera camera;
    private Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carema_layout);
		FindViewUtril.getView(this);
		camera=Camera.open();
		caremaView.setCamera(camera);
		btnCommit.setOnClickListener(this);
		btnCarema.setOnClickListener(this);
		btnDismiss.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCarema:
			camera.takePicture(null, null, new PictureCallback() {
				
				@Override
				public void onPictureTaken(byte[] data, Camera camera) {
					// TODO Auto-generated method stub
					BitmapFactory.Options opts =new Options();
					opts.inSampleSize=8;
					bitmap=BitmapFactory.decodeByteArray(data, 0, data.length,opts);
				}
			});
			break;
		case R.id.btnCommit:
			Intent mapIntent =new Intent();
			if(bitmap==null){
				setResult(500);
				
			}else{
				mapIntent.putExtra("bitmap", bitmap);
				setResult(200, mapIntent);
			}
			this.finish();
			break;
		case R.id.btnDismiss:
			Intent intent =new Intent();
			setResult(400);
			this.finish();
			break;

		default:
			break;
		}
	}
}
