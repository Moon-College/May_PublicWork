package com.rocy.tzclass8;

import java.io.IOException;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CaremaView extends SurfaceView implements SurfaceHolder.Callback{
    private  SurfaceHolder mholder ;
    private Camera camera;
	
	public CaremaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mholder = getHolder();
		mholder.addCallback(this);
		mholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	

	



	/**
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}







	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if(mholder.getSurface()==null){
			//如果预览不存在
			return;
		}
		//停止预览
		camera.stopPreview();
		//在重新打开预览
		camera.startPreview();
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}







	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//释放相机
		camera.release();
	}

}
