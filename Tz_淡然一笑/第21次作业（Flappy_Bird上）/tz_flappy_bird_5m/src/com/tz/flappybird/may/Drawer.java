package com.tz.flappybird.may;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * 画家
 * @author fcc
 *
 */
public class Drawer extends Thread{
	
	//画家画画时需要的画板,画笔,图形
	private Context context;
	private SurfaceHolder holder; //画板SurfaceView的管家
	private int w, h; //画板宽,高
	private Paint paint; //画笔
	private boolean isStart;
	
	private Spirit bird;  //精灵

	public Drawer(Context context, SurfaceHolder holder, int w, int h){
		this.context = context;
		this.holder = holder;
		this.w = w;
		this.h = h;
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		isStart = true;
		//创建鸟
		int [] paths = new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap[] bitmaps = new Bitmap[paths.length];
		for (int i = 0; i < paths.length; i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), paths[i]);
			bitmaps[i] = bitmap;
		}
		bird = new Spirit(bitmaps);
		bird.setX(300);
		bird.setY(300);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//执行绘画的任务
		while(isStart){
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas(); //锁定画板
				if(canvas != null){
					//真正开始执行绘画
					canvas.drawText("今天开始绘制愤怒的小鸟的游戏", 100, 100, paint);
					bird.drawSelf(canvas); //画鸟
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if(canvas != null){
					holder.unlockCanvasAndPost(canvas); //解锁画板
				}
			}
			
		}
	}
	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

}
