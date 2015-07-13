package com.chris.flapybird;

import java.util.Random;

import com.chris.flapybird.spirit.MySpirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class FlapyDrawer extends Thread
{
	private static final String tag = "FlapyDrawer";
	public static final int COMMON_FLUSH_FREQ = 200;
	public static final int GAME_READY = 0x10000;
	public static final int GAME_RUNNING = 0x10001;
	public static final int GAME_OVER = 0x10002;
	private static final float TEXT_SIZE = 30;
	private Context mContext;
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	private MySpirit bird, bg, floor, gameOver, getReady, logo, panel, tap, pipeTop1, pipeBottom1, pipeTop2, pipeBottom2;
	private Bitmap[] bmps_bg, bmps_bird, bmps_floor, bmps_gameOver, bmps_getReady, bmps_logo, bmps_panel, bmps_tap, bmps_pipe1, bmps_pipe2;
	private int dispWidth, dispHeight;
	private int mFloorHeight;
	private int mFlushFreq;
	private int mGameStatus;
	private boolean isBirdDead;
	private Random mRandom;
	private Paint mPaint;
	private int mScore;

	public MySpirit getBird()
	{
		return bird;
	}

	public int getGameStatus()
	{
		return mGameStatus;
	}

	public void setGameStatus(int gameStatus)
	{
		this.mGameStatus = gameStatus;
	}

	public boolean isBirdDead()
	{
		return isBirdDead;
	}

	public void setBirdDead(boolean isBirdDead)
	{
		this.isBirdDead = isBirdDead;
	}

	public int getScore()
	{
		return mScore;
	}

	public void setScore(int mScore)
	{
		this.mScore = mScore;
	}

	public int getDispWidth()
	{
		return dispWidth;
	}

	public void setDispWidth(int dispWidth)
	{
		this.dispWidth = dispWidth;
	}

	public int getDispHeight()
	{
		return dispHeight;
	}

	public void setDispHeight(int dispHeight)
	{
		this.dispHeight = dispHeight;
	}

	/**
	 * Handler和Runnable，循环主体
	 */
	private Handler mHandler;
	private Runnable mRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			mHandler.removeCallbacks(mRunnable);
			if (null != (mCanvas = mHolder.lockCanvas()))
			{
				try
				{
					startDrawFrame(mCanvas);
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
				finally
				{
					mHolder.unlockCanvasAndPost(mCanvas);
				}
			}
			mHandler.postDelayed(mRunnable, mFlushFreq);
		}
	};

	private void createAllBitmaps()
	{
		int[] birdResId = new int[]
		{ R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3 };
		bmps_bird = new Bitmap[birdResId.length];
		for (int i = 0; i < birdResId.length; i++)
		{
			bmps_bird[i] = BitmapFactory.decodeResource(mContext.getResources(), birdResId[i]);
		}

		bmps_bg = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg1) };
		bmps_floor = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.floor) };
		bmps_gameOver = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.gameover) };
		bmps_getReady = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.getready) };
		bmps_logo = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo) };
		bmps_panel = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.panel) };
		bmps_tap = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tap) };
		bmps_pipe1 = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pipe1) };
		bmps_pipe2 = new Bitmap[]
		{ BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pipe2) };
	}

	private void createAllSpirits()
	{
		bg = new MySpirit(mContext, bmps_bg);
		floor = new MySpirit(mContext, bmps_floor);
		bird = new MySpirit(mContext, bmps_bird);
		gameOver = new MySpirit(mContext, bmps_gameOver);
		getReady = new MySpirit(mContext, bmps_getReady);
		logo = new MySpirit(mContext, bmps_logo);
		panel = new MySpirit(mContext, bmps_panel);
		tap = new MySpirit(mContext, bmps_tap);
		pipeTop1 = new MySpirit(mContext, bmps_pipe1);
		pipeBottom1 = new MySpirit(mContext, bmps_pipe2);
		pipeTop2 = new MySpirit(mContext, bmps_pipe1);
		pipeBottom2 = new MySpirit(mContext, bmps_pipe2);
	}

	/**
	 * 设置所有Spirit的位置
	 */
	public void setAttrsForSpirits(int gameStatus)
	{
		setBirdDead(false);
		setGameStatus(gameStatus);
		setScore(0);
		
		bird.setLocation(new Point(getDispWidth() / 4, getDispHeight() / 2));
		getReady.setLocation(new Point((getDispWidth() - getReady.getWidth()) / 2, getDispHeight() / 3));
		tap.setLocation(new Point((getDispWidth() - getReady.getWidth()) / 2, getDispHeight() / 2));
		logo.setLocation(new Point((getDispWidth() - logo.getWidth()) / 2, getDispHeight() / 4));
		gameOver.setLocation(new Point((getDispWidth() - gameOver.getWidth()) / 2, getDispHeight() / 3));
		panel.setLocation(new Point((getDispWidth() - panel.getWidth()) / 2, (getDispHeight() - panel.getHeight()) / 2));

		bird.setySpeed(0);
		updatePipeAttrs(true, pipeTop1, bmps_pipe1[0]);
		updatePipeAttrs(true, pipeTop2, bmps_pipe1[0]);
		updatePipeAttrs(false, pipeBottom1, bmps_pipe2[0]);
		updatePipeAttrs(false, pipeBottom2, bmps_pipe2[0]);
	}

	/**
	 * 初始话所有Spirit的属性
	 */
	public void initFlapyDrawer()
	{
		//获取屏幕的宽和高
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics mainMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(mainMetrics);
		Log.d(tag, "widthPixels 2 ="+mainMetrics.widthPixels+" heightPixels 2="+mainMetrics.heightPixels);
		setDispWidth(mainMetrics.widthPixels);
		setDispHeight(mainMetrics.heightPixels);
		mFloorHeight = getDispHeight() / 4; //地板的高度，固定值

		mPaint.setColor(Color.RED);
		mPaint.setTextSize(TEXT_SIZE);

		createAllBitmaps();
		createAllSpirits();
		setAttrsForSpirits(GAME_READY);
	}

	public FlapyDrawer(Context context, SurfaceView surView)
	{
		this.mHandler = new Handler();
		this.mContext = context;
		this.mHolder = surView.getHolder();
		this.mGameStatus = GAME_READY;
		this.isBirdDead = false;
		this.mRandom = new Random();
		this.mPaint = new Paint();

		initFlapyDrawer();
	}

	/**
	 * 开始在循环中绘图
	 */
	@Override
	public void run()
	{
		mHandler.post(mRunnable);
		super.run();
	}

	/**
	 * 绘制一帧游戏画面，具体场景取决于游戏不同阶段，包括GAME_READY, GAME_RUNNING和GAME_OVER
	 * 
	 * @param canvas
	 */
	public void startDrawFrame(Canvas canvas)
	{
		bg.drawSelfFromRect(canvas, null, new Rect(0, 0, getDispWidth(), getDispHeight()));
		floor.drawSelfFromRect(canvas, null, new Rect(0, getDispHeight() * 3 / 4, getDispWidth(), getDispHeight()));

		switch (getGameStatus())
		{
			case GAME_READY:
				drawGameReadyFrame(canvas);
				break;
			case GAME_RUNNING:
				drawGameRunningFrame(canvas);
				break;
			case GAME_OVER:
				drawGameOverFrame(canvas);
				break;

			default:
				break;
		}

	}

	/**
	 * 绘制游戏准备场景
	 * 
	 * @param canvas
	 */
	private void drawGameReadyFrame(Canvas canvas)
	{
		setAttrsForSpirits(GAME_READY);
		setBirdDead(false);
		setScore(0);
		logo.drawSelf(canvas);
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);
	}

	/**
	 * 绘制游戏运行场景
	 * 
	 * @param canvas
	 */
	private void drawGameRunningFrame(Canvas canvas)
	{
		bird.drawSelf(canvas);
		bird.setySpeed(bird.getySpeed() + 1);

		//如果小鸟没有死亡，则继续移动柱子，否则停止
		if (isBirdDead())
		{
			int xSpeed = 0;
			pipeTop1.setxSpeed(xSpeed);
			pipeBottom1.setxSpeed(xSpeed);
			pipeTop2.setxSpeed(xSpeed);
			pipeBottom2.setxSpeed(xSpeed);
		}

		//判断是否得分，柱子是否超过屏幕左边
		checkToAdjustPipeLeftOut(true, pipeTop1, bmps_pipe1[0]);
		pipeTop1.drawSelf(canvas);
		updateScore(pipeTop1, bird);

		checkToAdjustPipeLeftOut(true, pipeTop2, bmps_pipe1[0]);
		pipeTop2.drawSelf(canvas);
		updateScore(pipeTop2, bird);

		checkToAdjustPipeLeftOut(false, pipeBottom1, bmps_pipe2[0]);
		pipeBottom1.drawSelf(canvas);
		updateScore(pipeBottom1, bird);

		checkToAdjustPipeLeftOut(false, pipeBottom2, bmps_pipe2[0]);
		pipeBottom2.drawSelf(canvas);
		updateScore(pipeBottom2, bird);

		//判断小鸟是否 撞顶、撞地、撞柱子，撞了就挂了
		if (bird.getLocation().y < 0 || bird.getLocation().y + bird.getHeight() > (getDispHeight() - mFloorHeight) || birdHitPipe(pipeTop1) || birdHitPipe(pipeBottom1) || birdHitPipe(pipeTop2) || birdHitPipe(pipeBottom2))
		{
			setBirdDead(true);
		}
		//判断小鸟屁股着地，游戏结束
		if ((bird.getLocation().y + bird.getHeight()) >= (getDispHeight() - mFloorHeight))
		{
			setGameStatus(GAME_OVER);
		}
	}

	/**
	 * 绘制游戏结束场景
	 * 
	 * @param canvas
	 */
	private void drawGameOverFrame(Canvas canvas)
	{
		gameOver.drawSelf(canvas);
		logo.drawSelf(canvas);
		panel.drawSelf(canvas);

		canvas.drawText(String.valueOf(getScore()), panel.getLocation().x + panel.getWidth() / 6, panel.getLocation().y + panel.getHeight() / 2 + mPaint.descent(), mPaint);
	}

	/**
	 * 检测小鸟是否成功穿越柱子得分
	 * 
	 * @param pipe 柱子Spirit
	 * @param bird 小鸟Spirit
	 */
	private void updateScore(MySpirit pipe, MySpirit bird)
	{
		int birdLeft = bird.getLocation().x;
		int pipeRight = pipe.getLocation().x + pipe.getWidth();
		int pipeSpeed = pipe.getxSpeed();
		//speed是负数，所以要减掉
		if (pipeRight <= birdLeft && (pipeRight - pipeSpeed) >= birdLeft)
		{
			setScore(getScore() + 1);
		}
	}

	/**
	 * 更新柱子的属性
	 * 
	 * @param isTop true:顶部的柱子, false:底部的柱子
	 * @param pipe 柱子Spirit
	 * @param bitmap 柱子图片
	 */
	private void updatePipeAttrs(boolean isTop, MySpirit pipe, Bitmap bitmap)
	{
		//如果随机产生的图片高度大于素材图片的高度，那么取最小值素材图片高度
		int randomHeight = (int) (getDispHeight() * (mRandom.nextFloat() * 9 + 4) / 32);
		int randomOffset = (int) (getDispWidth() * (mRandom.nextFloat() * 3 + 1) / 4);
		int randomSpeed = (int) (mRandom.nextFloat() * 5 + 5);
		if (randomHeight > bitmap.getHeight())
		{
			randomHeight = bitmap.getHeight();
		}

		if (isTop)
		{
			pipe.setBitmaps(new Bitmap[]
			{ Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() - randomHeight, bitmap.getWidth(), randomHeight) });
			pipe.setLocation(new Point(getDispWidth() + randomOffset, 0));
			pipe.setxSpeed(-randomSpeed);
		}
		else
		{
			pipe.setBitmaps(new Bitmap[]
			{ Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), randomHeight) });
			pipe.setLocation(new Point(getDispWidth() + randomOffset, getDispHeight() - mFloorHeight - randomHeight));
			pipe.setxSpeed(-randomSpeed);
		}
	}

	/**
	 * 检测柱子是否移动超出屏幕左侧，如果超出，则要重新布置柱子的位置和属性
	 * 
	 * @param isTopPipe true:顶部的柱子, false:底部的柱子
	 * @param pipe 柱子Spirit
	 * @param bitmap 柱子的图片
	 */
	private void checkToAdjustPipeLeftOut(boolean isTopPipe, MySpirit pipe, Bitmap bitmap)
	{
		if ((pipe.getLocation().x + pipe.getWidth()) < 0)
		{
			updatePipeAttrs(isTopPipe, pipe, bitmap);
		}
	}

	/**
	 * 小鸟和柱子的矩形碰撞检测
	 * 
	 * @param pipe 柱子Spirit
	 * @return true:撞上了, false:没撞上
	 */
	private boolean birdHitPipe(MySpirit pipe)
	{
		return bird.getSpiritRect().intersect(pipe.getSpiritRect());
	}

	/**
	 * 停止绘图，退出Runnable循环
	 */
	public void stopDraw()
	{
		mHandler.removeCallbacks(mRunnable);
	}

}
