package com.decent.decentgame.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.decent.decentgame.bean.Spirit;

public class Drawer extends Thread {

	private boolean isStarted;
	private Context context;
	private Spirit spirit;
	private SurfaceHolder holder;
	private Paint paint;

	public Drawer(Context context, Spirit spirit, SurfaceHolder holder) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.spirit = spirit;
		this.holder = holder;
		this.paint = new Paint();
		// Ĭ��������isStarted��true
		isStarted = true;
	}

	@Override
	public void run() {
		while (isStarted) {
			Canvas canvas = null;
			try {
				//��ʼ�༭SurfaceView�ϵ����أ����api��Ҫ��SurfaceView�������ɹ�֮����ܵ���
				canvas = holder.lockCanvas();
				if(canvas!=null)
				{
				    spirit.drawSelf(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(canvas!=null)
				{
					//��������SurfaceView�����صı༭����ʾ��SurfaceView��
				    holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Spirit getSpirit() {
		return spirit;
	}

	public void setSpirit(Spirit spirit) {
		this.spirit = spirit;
	}

	public SurfaceHolder getHolder() {
		return holder;
	}

	public void setHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

}
