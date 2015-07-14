/**
 * Project Name:lsn13_animation
 * File Name:GifSurfaceView.java
 * Package Name:com.zht.animation.widget
 * Date:2015-6-23下午5:20:31
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.animation.widget;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * ClassName:GifSurfaceView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-23 下午5:20:31 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class GifSurfaceView extends SurfaceView implements Callback {

	private String path;
	private float zoom = 1f;
	private Movie movie;
	private Handler handler = new Handler();
	private Runnable run = new Runnable() {
		
		private SurfaceHolder holder;

		@Override
		public void run() {
			holder = getHolder();
			Canvas canvas = holder.lockCanvas();
			//更改画布的参数（事务）
			canvas.save();
			//缩放图片
			canvas.scale(zoom, zoom);
			movie.draw(canvas, 0, 0);
			
			canvas.restore();
			holder.unlockCanvasAndPost(canvas);
			
			//设置时间
			//总时长5, 1 2 3 4 5;1 2 3 4 5;
			//轮询绘制gif的帧画面
			movie.setTime((int)(System.currentTimeMillis() % movie.duration()));
			handler.removeCallbacks(run);
			handler.postDelayed(run, 30);
			
		}
	};
	
	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		InputStream is;
		try {
			is = getContext().getAssets().open(path);
			//影片对象
			movie = Movie.decodeStream(is);
			//获取gif图片的宽高
			int width = movie.width();
			int height = movie.height();
			
			//如果SurfaceView的宽高要跟gif文件的宽高
			//onMeasure计算SurfaceView的宽高
			//通过setMeasuredDimension应用
			setMeasuredDimension((int)(width * zoom), (int)(height * zoom));
			
			handler.post(run);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GifSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	// SurfaceView初始化完成的回调方法
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//SurfaceView被销毁
		handler.removeCallbacks(run);
	}

}
