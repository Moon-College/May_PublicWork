package com.chris.flapybird;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class FlapyBirdActivity extends Activity implements SurfaceHolder.Callback, OnClickListener
{
	private static final String tag = "FlapyBirdActivity";
	private static final int BIRD_BOUNCE_SPEED = 7;	//小鸟被点击的弹跳速度
	private SurfaceView sv;
	private FlapyDrawer drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flapy_bird);

		//初始化相关控件
		initViews();

		//配置画家对象
		drawer = new FlapyDrawer(this, sv);
	}

	private void initViews()
	{
		sv = (SurfaceView) findViewById(R.id.sv);
		sv.getHolder().addCallback(this);
		sv.setOnClickListener(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.d(tag, "surfaceCreated");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		Log.d(tag, "surfaceChanged");
		drawer.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		Log.d(tag, "surfaceDestroyed");
		drawer.stopDraw();
	}

	@Override
	public void onClick(View v)
	{
		switch (drawer.getGameStatus())
		{
			case FlapyDrawer.GAME_READY:
				drawer.setGameStatus(FlapyDrawer.GAME_RUNNING);
				break;
			case FlapyDrawer.GAME_RUNNING:
				if (!drawer.isBirdDead())
				{
					drawer.getBird().setySpeed(-BIRD_BOUNCE_SPEED);
				}

				break;
			case FlapyDrawer.GAME_OVER:
				drawer.setAttrsForSpirits(FlapyDrawer.GAME_READY);
				break;

			default:
				break;
		}
	}

}
