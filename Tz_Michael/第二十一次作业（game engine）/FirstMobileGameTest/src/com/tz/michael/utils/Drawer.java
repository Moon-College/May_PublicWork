package com.tz.michael.utils;

import com.tz.michael.activity.R;
import com.tz.michael.spirit.BaseSpirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * 绘制的类
 * @author admin
 *
 */
public class Drawer extends Thread {

	private SurfaceHolder sfh;
	private int w,h;
	private Paint paint;
	private boolean isStart;
	private BaseSpirit bSpirit;
	
	
	public boolean isStart() {
		return isStart;
	}


	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}


	public Drawer(Context context,SurfaceHolder surfaceHolder,int width,int height){
		this.sfh=surfaceHolder;
		this.w=width;
		this.h=height;
		paint=new Paint();
		paint.setColor(Color.BLUE);
		isStart=true;
		int[] drawableIds=new int[]{R.drawable.bird_1,R.drawable.bird_2,R.drawable.bird_3};
		Bitmap[] bitmaps=new Bitmap[drawableIds.length];
		for(int i=0;i<drawableIds.length;i++){
			bitmaps[i]=BitmapFactory.decodeResource(context.getResources(), drawableIds[i]);
		}
		bSpirit=new BaseSpirit(bitmaps);
		bSpirit.setX(250);
		bSpirit.setY(400);
	}
	
	
	@Override
	public void run() {
		super.run();
		while(isStart){
			Canvas canvas=null;
			try {
				canvas=sfh.lockCanvas();
				if(canvas!=null){
					canvas.drawText("你好game", 100, 200, paint);
					bSpirit.drawSelf(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(canvas!=null){
					sfh.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	
}
