package com.jim.mybird.view;

import com.jim.mybird.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.WindowManager;

/**
 * 新建一个画家类继承与线程进行不断绘画的操作
 * 
 * @author Jim
 * 
 */
public class Drawer extends Thread {

	private Context context;
	private Paint paint;// 画笔
	private SurfaceHolder holder;// surfaceView的管家
	private int W, H;// 画板的宽高
	private boolean isStart;// 开关
	private Bird bird;// 鸟
	private WindowManager wm;

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public Drawer(Context context, SurfaceHolder holder, int w, int h) {
		this.context = context;
		this.holder = holder;
		this.W = w;
		this.H = h;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		isStart = true;
		int[] picS = new int[] { R.drawable.bird_1, R.drawable.bird_2,
				R.drawable.bird_3, };
		Bitmap[] bmps = new Bitmap[picS.length];
		for (int i = 0; i < picS.length; i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), picS[i]);
			bmps[i] = bitmap;
		}
		bird = new Bird(bmps);
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenW = wm.getDefaultDisplay().getWidth();
		int screenH = wm.getDefaultDisplay().getHeight();
		bird.setX(screenW / 4);
		bird.setY(screenH / 3);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// 开始绘画
		while (isStart) {
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					bird.drawSelf(canvas);// 画这只鸟
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);// 解除画布
				}
			}
		}
	}
}
