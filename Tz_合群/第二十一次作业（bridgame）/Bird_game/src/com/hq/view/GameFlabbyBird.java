package com.hq.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hq.surfaceView.R;

public class GameFlabbyBird extends SurfaceView implements Callback, Runnable
{

	private SurfaceHolder mHolder;
	/**
	 * 与SurfaceHolder绑定的Canvas
	 */
	private Canvas mCanvas;
	/**
	 * 用于绘制的线�?
	 */
	private Thread t;
	/**
	 * 线程的控制开�?
	 */
	private boolean isRunning;

	private Paint mPaint;

	/**
	 * 当前View的尺�?
	 */
	private int mWidth;
	private int mHeight;
	private RectF mGamePanelRect = new RectF();

	/**
	 * 背景
	 */
	private Bitmap mBg;

	/**
	 * *********鸟相�?*********************
	 */
	private Bird mBird;
	private Bitmap mBirdBitmap;
	/**
	 * 地板
	 */
	private Floor mFloor;
	private Bitmap mFloorBg;

	/**
	 * *********管道相关**********************
	 */
	/**
	 * 管道
	 */
	private Bitmap mPipeTop;
	private Bitmap mPipeBottom;
	private RectF mPipeRect;
	private int mPipeWidth;
	/**
	 * 管道的宽�?60dp
	 */
	private static final int PIPE_WIDTH = 60;
	private List<Pipe> mPipes = new ArrayList<Pipe>();

	/**
	 * 分数
	 */
	private final int[] mNums = new int[] { R.drawable.n0, R.drawable.n1,
			R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5,
			R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9 };
	private Bitmap[] mNumBitmap;

	private int mGrade = 0;
	private int mRemovedPipe = 0;

	private static final float RADIO_SINGLE_NUM_HEIGHT = 1 / 15f;
	private int mSingleGradeWidth;
	private int mSingleGradeHeight;
	private RectF mSingleNumRectF;

	private int mSpeed = Util.dp2px(getContext(), 2);

	/***********************************/

	private enum GameStatus
	{
		WAITTING, RUNNING, STOP;
	}

	/**
	 * 记录游戏的状�?
	 */
	private GameStatus mStatus = GameStatus.WAITTING;

	/**
	 * 触摸上升的距离，因为是上升，�?��为负�?
	 */
	private static final int TOUCH_UP_SIZE = -16;
	/**
	 * 将上升的距离转化为px；这里多存储�?��变量，变量在run中计�?
	 * 
	 */
	private final int mBirdUpDis = Util.dp2px(getContext(), TOUCH_UP_SIZE);

	private int mTmpBirdDis;
	/**
	 * 鸟自动下落的距离
	 */
	private final int mAutoDownSpeed = Util.dp2px(getContext(), 2);

	/**
	 * 两个管道间距�?
	 */
	private final int PIPE_DIS_BETWEEN_TWO = Util.dp2px(getContext(), 300);
	/**
	 * 记录移动的距离，达到 PIPE_DIS_BETWEEN_TWO 则生成一个管�?
	 */
	private int mTmpMoveDistance;
	/**
	 * 记录�?��移除的管�?
	 */
	private List<Pipe> mNeedRemovePipe = new ArrayList<Pipe>();

	/**
	 * 处理�?��逻辑上的计算
	 */
	private void logic()
	{
		switch (mStatus)
		{
		case RUNNING:

			mGrade = 0;
			// 更新我们地板绘制的x坐标，地板移�?
			mFloor.setX(mFloor.getX() - mSpeed);

			logicPipe();

			// 默认下落，点击时瞬间上升
			mTmpBirdDis += mAutoDownSpeed;
			mBird.setY(mBird.getY() + mTmpBirdDis);

			// 计算分数
			mGrade += mRemovedPipe;
			for (Pipe pipe : mPipes)
			{
				if (pipe.getX() + mPipeWidth < mBird.getX())
				{
					mGrade++;
				}
			}

			checkGameOver();

			break;

		case STOP: // 鸟落�?
			// 如果鸟还在空中，先让它掉下来
			if (mBird.getY() < mFloor.getY() - mBird.getWidth())
			{
				mTmpBirdDis += mAutoDownSpeed;
				mBird.setY(mBird.getY() + mTmpBirdDis);
			} else
			{
				mStatus = GameStatus.WAITTING;
				initPos();
			}
			break;
		default:
			break;
		}

	}

	/**
	 * 重置鸟的位置等数�?
	 */
	private void initPos()
	{
		mPipes.clear();
		//立即增加�?��
		mPipes.add(new Pipe(getContext(), getWidth(), getHeight(), mPipeTop,
				mPipeBottom));
		mNeedRemovePipe.clear();
		// 重置鸟的位置
		// mBird.setY(mHeight * 2 / 3);
		mBird.resetHeigt();
		// 重置下落速度
		mTmpBirdDis = 0;
		mTmpMoveDistance = 0 ;
		mRemovedPipe = 0;
	}

	private void checkGameOver()
	{

		// 如果触碰地板，gg
		if (mBird.getY() > mFloor.getY() - mBird.getHeight())
		{
			mStatus = GameStatus.STOP;
		}
		// 如果撞到管道
		for (Pipe wall : mPipes)
		{
			// 已经穿过�?
			if (wall.getX() + mPipeWidth < mBird.getX())
			{
				continue;
			}
			if (wall.touchBird(mBird))
			{
				mStatus = GameStatus.STOP;
				break;
			}
		}
	}

	private void logicPipe()
	{
		// 管道移动
		for (Pipe pipe : mPipes)
		{
			if (pipe.getX() < -mPipeWidth)
			{
				mNeedRemovePipe.add(pipe);
				mRemovedPipe++;
				continue;
			}
			pipe.setX(pipe.getX() - mSpeed);
		}
		// 移除管道
		mPipes.removeAll(mNeedRemovePipe);
		mNeedRemovePipe.clear();

		// Log.e("TAG", "现存管道数量�? + mPipes.size());

		// 管道
		mTmpMoveDistance += mSpeed;
		// 生成�?��管道
		if (mTmpMoveDistance >= PIPE_DIS_BETWEEN_TWO)
		{
			Pipe pipe = new Pipe(getContext(), getWidth(), getHeight(),
					mPipeTop, mPipeBottom);
			mPipes.add(pipe);
			mTmpMoveDistance = 0;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{

		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN)
		{
			switch (mStatus)
			{
			case WAITTING:
				mStatus = GameStatus.RUNNING;
				break;
			case RUNNING:
				mTmpBirdDis = mBirdUpDis;
				break;
			}

		}

		return true;

	}

	public GameFlabbyBird(Context context)
	{
		this(context, null);
	}

	public GameFlabbyBird(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		mHolder = getHolder();
		mHolder.addCallback(this);

		setZOrderOnTop(true);// 设置画布 背景透明
		mHolder.setFormat(PixelFormat.TRANSLUCENT);

		// 设置可获得焦�?
		setFocusable(true);
		setFocusableInTouchMode(true);
		// 设置常亮
		this.setKeepScreenOn(true);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);

		initBitmaps();

		// 初始化�?�?
		// mSpeed = Util.dp2px(getContext(), 2);
		mPipeWidth = Util.dp2px(getContext(), PIPE_WIDTH);

	}

	/**
	 * 初始化图�?
	 */
	private void initBitmaps()
	{
		mBg = loadImageByResId(R.drawable.bg1);
		mBirdBitmap = loadImageByResId(R.drawable.b1);
		mFloorBg = loadImageByResId(R.drawable.floor_bg2);
		mPipeTop = loadImageByResId(R.drawable.g2);
		mPipeBottom = loadImageByResId(R.drawable.g1);

		mNumBitmap = new Bitmap[mNums.length];
		for (int i = 0; i < mNumBitmap.length; i++)
		{
			mNumBitmap[i] = loadImageByResId(mNums[i]);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.e("TAG", "surfaceCreated");

		// �?��线程
		isRunning = true;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		Log.e("TAG", "surfaceChanged");
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		Log.e("TAG", "surfaceDestroyed");
		// 通知关闭线程
		isRunning = false;
	}

	@Override
	public void run()
	{
		while (isRunning)
		{
			long start = System.currentTimeMillis();
			logic();
			draw();
			long end = System.currentTimeMillis();

			try
			{
				if (end - start < 50)
				{
					Thread.sleep(50 - (end - start));
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}

	}

	private void draw()
	{
		try
		{
			// 获得canvas
			mCanvas = mHolder.lockCanvas();
			if (mCanvas != null)
			{
				// drawSomething..

				drawBg();
				drawBird();

				drawPipes();

				drawFloor();

				drawGrades();

			}
		} catch (Exception e)
		{
		} finally
		{
			if (mCanvas != null)
				mHolder.unlockCanvasAndPost(mCanvas);
		}
	}

	private void drawFloor()
	{
		mFloor.draw(mCanvas, mPaint);
	}

	/**
	 * 绘制背景
	 */
	private void drawBg()
	{
		mCanvas.drawBitmap(mBg, null, mGamePanelRect, null);
	}

	private void drawBird()
	{
		mBird.draw(mCanvas);
	}

	/**
	 * 绘制管道
	 */
	private void drawPipes()
	{
		for (Pipe pipe : mPipes)
		{
			// pipe.setX(pipe.getX() - mSpeed);
			pipe.draw(mCanvas, mPipeRect);
		}
	}

	/**
	 * 初始化尺寸相�?
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{

		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = w;
		mHeight = h;
		mGamePanelRect.set(0, 0, w, h);

		// 初始化mBird
		mBird = new Bird(getContext(), mWidth, mHeight, mBirdBitmap);
		// 初始化地�?
		mFloor = new Floor(mWidth, mHeight, mFloorBg);
		// 初始化管道范�?
		mPipeRect = new RectF(0, 0, mPipeWidth, mHeight);

		Pipe pipe = new Pipe(getContext(), w, h, mPipeTop, mPipeBottom);
		mPipes.add(pipe);

		// 初始化分�?
		mSingleGradeHeight = (int) (h * RADIO_SINGLE_NUM_HEIGHT);
		mSingleGradeWidth = (int) (mSingleGradeHeight * 1.0f
				/ mNumBitmap[0].getHeight() * mNumBitmap[0].getWidth());
		mSingleNumRectF = new RectF(0, 0, mSingleGradeWidth, mSingleGradeHeight);

	}

	/**
	 * 绘制分数
	 */
	private void drawGrades()
	{
		String grade = mGrade + "";
		mCanvas.save(Canvas.MATRIX_SAVE_FLAG);
		mCanvas.translate(mWidth / 2 - grade.length() * mSingleGradeWidth / 2,
				1f / 8 * mHeight);
		// draw single num one by one
		for (int i = 0; i < grade.length(); i++)
		{
			String numStr = grade.substring(i, i + 1);
			int num = Integer.valueOf(numStr);
			mCanvas.drawBitmap(mNumBitmap[num], null, mSingleNumRectF, null);
			mCanvas.translate(mSingleGradeWidth, 0);
		}
		mCanvas.restore();

	}

	/**
	 * 根据resId加载图片
	 * 
	 * @param resId
	 * @return
	 */
	private Bitmap loadImageByResId(int resId)
	{
		return BitmapFactory.decodeResource(getResources(), resId);
	}
}
