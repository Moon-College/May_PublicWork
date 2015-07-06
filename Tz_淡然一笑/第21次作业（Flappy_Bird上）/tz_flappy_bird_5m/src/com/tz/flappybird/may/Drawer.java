package com.tz.flappybird.may;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * ����
 * @author fcc
 *
 */
public class Drawer extends Thread{
	
	//���һ���ʱ��Ҫ�Ļ���,����,ͼ��
	private Context context;
	private SurfaceHolder holder; //����SurfaceView�Ĺܼ�
	private int w, h; //�����,��
	private Paint paint; //����
	private boolean isStart;
	
	private Spirit bird;  //����

	public Drawer(Context context, SurfaceHolder holder, int w, int h){
		this.context = context;
		this.holder = holder;
		this.w = w;
		this.h = h;
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		isStart = true;
		//������
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
		//ִ�л滭������
		while(isStart){
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas(); //��������
				if(canvas != null){
					//������ʼִ�л滭
					canvas.drawText("���쿪ʼ���Ʒ�ŭ��С�����Ϸ", 100, 100, paint);
					bird.drawSelf(canvas); //����
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if(canvas != null){
					holder.unlockCanvasAndPost(canvas); //��������
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
