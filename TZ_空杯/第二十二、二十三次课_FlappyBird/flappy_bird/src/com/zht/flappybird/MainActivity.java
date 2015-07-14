/**
 * Project Name:flappy_bird
 * File Name:MainActivity.java
 * Package Name:com.zht.flappybird
 * Date:2015-7-6下午3:32:30
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.flappybird;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-6 下午3:32:30 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity implements OnTouchListener {
	private SurfaceView surfaceView;
	private Painter painter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(new MyCallBack());
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);
	}

	private class MyCallBack implements SurfaceHolder.Callback {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			painter = new Painter(MainActivity.this, holder, surfaceView.getWidth(),
					surfaceView.getHeight());
			painter.start();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// 画板改变了

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// 画板销毁了
			painter.isStart = false;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (painter.getGameStatus()) {
		case Painter.READY:
			painter.setGameStatus(Painter.RUNNING);
			break;
		case Painter.RUNNING:
			if(!painter.isDrop){
				painter.getBird().setYSpped(-15);
			}
			break;
		case Painter.OVER:
			painter.setGameStatus(Painter.READY);
			painter.initSpiritsAttrs();
			break;
		default:
			break;
		}
		return false;
	}
	
}
