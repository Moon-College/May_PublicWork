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
	//���һ滭��Ҫ�����Ļ��壬���ʣ�ͼ��
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
		//������
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
		//ִ�л滭������
		while(isStart){
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					//�������Կ�ʼִ�л滭��
					canvas.drawText("����һ����Ϸ", 100, 100, paint);
					bird.drawSelf(canvas);//����
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if (canvas!=null) {
					holder.unlockCanvasAndPost(canvas);//��������
				}
			}
		}
	}
}
