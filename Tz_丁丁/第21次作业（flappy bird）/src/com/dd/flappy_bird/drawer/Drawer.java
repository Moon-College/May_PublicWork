package com.dd.flappy_bird.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.dd.flappy_bird.R;
import com.dd.flappy_bird.spirit.Spirit;

public class Drawer extends Thread {
	//画家绘画需要基本的画板，画笔，图形
	private Context context;
	private SurfaceHolder holder;
	private int w,h;
	private Paint paint;
	private boolean isStart;
	private Spirit bird;
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public Drawer(Context context,SurfaceHolder holder,int w,int h) {
		this.context = context;
		this.holder = holder;
		this.w = w;
		this.h = h;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		isStart = true;
		//创建鸟
		int [] paths = new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap [] bms = new Bitmap[paths.length];
		for(int i = 0;i<paths.length;i++){
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), paths[i]);
			bms[i] = bitmap;
		}
		bird = new Spirit(bms);
		bird.setX(200);
		bird.setY(200);
	}
	@Override
	public void run() {
		super.run();
		//执行绘画的任务
		while(isStart){
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					//真正可以开始执行绘画了
					canvas.drawText("做第一个游戏", 100, 100, paint);
					bird.drawSelf(canvas);//画鸟
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if (canvas!=null) {
					holder.unlockCanvasAndPost(canvas);//解锁画布
				}
			}
		}
	}
}
