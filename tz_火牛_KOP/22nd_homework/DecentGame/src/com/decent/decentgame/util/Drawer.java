package com.decent.decentgame.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import com.decent.decentgame.R;
import com.decent.decentgame.bean.Spirit;

public class Drawer extends Thread {

	/**
	 * 开始状态
	 */
	public static final int READY_STATE = 1;
	/**
	 * 用户正在游戏的状态
	 */
	public static final int RUNNING_STATE = 2;
	/**
	 * 游戏失败的状态
	 */
	public static final int OVER_STATE = 3;
	/**
	 * 管道的向左的速度
	 */
	public static final int PIPE_SPEED = -5;
	/**
	 * 线程是否运行
	 */
	private boolean isStarted;
	/**
	 * 游戏当前的状态
	 */
	private int gameStatus;
	/**
	 * 当前鸟是否已经撞了
	 */
	private boolean isBirdDropped;

	private Context context;
	private Spirit spirit;
	private SurfaceHolder holder;
	private Paint paint;
	private Resources resources;
	private Bitmap bg1Img;
	private Bitmap floorImg;
	private int screenWidth;
	private int screenHeight;
	private Bitmap readyImg;
	private Spirit bg1Spirit;
	private Spirit floorSpirit;
	private Spirit readySpirit;

	private int bird_paths[] = { R.drawable.bird_1, R.drawable.bird_2,
			R.drawable.bird_3 };
	private Spirit birdSpirit;
	private Bitmap[] birdImgs;
	private Bitmap tapImg;
	private Spirit tapSpirit;
	private Bitmap pipeUp1Img;
	private Spirit pipeUp1Spirit;
	private Bitmap pipeDown1Img;
	private Spirit pipeDown1Spirit;
	private Spirit pipeUp2Spirit;
	private Spirit pipeDown2Spirit;
	private int pipeSpan;
	private int pipeTopHeight1;
	private int pipeTopHeight2;
	private float pipe_img_times;
	private Spirit gameoverSpirit;

	private int score = 0;
	private Bitmap gameoverImg;

	public Drawer(Context context, SurfaceHolder holder, int screenWidth,
			int screenHeight) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.holder = holder;
		this.paint = new Paint();
		this.resources = this.context.getResources();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		initSpirits();
		initAttrs();
		// 默认是设置isStarted是true
		gameStatus = READY_STATE;
		isStarted = true;
		isBirdDropped = false;
	}

	public void initAttrs() {

		isBirdDropped = false;
		/*
		 * ready
		 */
		readySpirit.setX((screenWidth - readyImg.getWidth()) / 2);
		readySpirit.setY(screenHeight / 3);

		/*
		 * tap
		 */
		tapSpirit.setX((screenWidth - tapImg.getWidth()) / 2);
		tapSpirit.setY(screenHeight / 2);

		/*
		 * bird
		 */
		birdSpirit.setX(screenWidth / 3);
		birdSpirit.setY(screenHeight / 2);
		// bird在不停的下坠
		birdSpirit.setySpeed(2);

		/*
		 * pipe
		 */
		pipeSpan = screenHeight / 4;// 管子的间距
		pipeTopHeight1 = screenHeight / 4;
		pipeTopHeight2 = screenHeight / 4;// 直接设置的高
		pipe_img_times = (float) (((float) screenHeight / 2) / (pipeUp1Img
				.getHeight()));
		Log.d("INFO", "pipeUp1Img.getHeight()-" + pipeUp1Img.getHeight()
				+ ",screenHeight/2=" + screenHeight / 2);
		Log.d("INFO", "pipe_img_times=" + pipe_img_times);
		/*
		 * pipe1的上下两根
		 */
		pipeUp1Spirit.setX(screenWidth);
		pipeUp1Spirit.setY(0);
		pipeUp1Spirit.setScale(pipe_img_times);

		pipeDown1Spirit.setX(screenWidth);
		pipeDown1Spirit.setY(pipeTopHeight1 + pipeSpan);
		pipeDown1Spirit.setScale(pipe_img_times);
		/*
		 * pipe2的上下两根
		 */
		pipeUp2Spirit.setX(3 * screenWidth / 2);
		pipeUp2Spirit.setY(0);
		pipeUp2Spirit.setScale(pipe_img_times);

		pipeDown2Spirit.setX(3 * screenWidth / 2);
		pipeDown2Spirit.setY(pipeTopHeight2 + pipeSpan);
		pipeDown2Spirit.setScale(pipe_img_times);

		/*
		 * gameover
		 */
		gameoverSpirit.setX((screenWidth - gameoverImg.getWidth()) / 2);
		gameoverSpirit.setY(screenHeight / 2);
		
		score = 0;
	}

	private void initSpirits() {
		// TODO Auto-generated method stub
		/*
		 * 背景和floor的spirit初始化
		 */
		bg1Img = loadImg(R.drawable.bg1);
		bg1Spirit = new Spirit(bg1Img);
		floorImg = loadImg(R.drawable.floor);
		floorSpirit = new Spirit(floorImg);

		/*
		 * ready
		 */
		readyImg = loadImg(R.drawable.getready);
		readySpirit = new Spirit(readyImg);

		/*
		 * tap
		 */
		tapImg = loadImg(R.drawable.tap);
		tapSpirit = new Spirit(tapImg);

		/*
		 * birds
		 */
		birdImgs = new Bitmap[bird_paths.length];
		for (int i = 0; i < bird_paths.length; i++) {
			birdImgs[i] = loadImg(bird_paths[i]);
		}
		birdSpirit = new Spirit(birdImgs);

		/*
		 * pipeUp
		 */
		pipeUp1Img = loadImg(R.drawable.pipe1);
		pipeUp1Spirit = new Spirit(pipeUp1Img);
		pipeUp2Spirit = pipeUp1Spirit.getCloneSpirit();
		/*
		 * pipeDown
		 */
		pipeDown1Img = loadImg(R.drawable.pipe2);
		pipeDown1Spirit = new Spirit(pipeDown1Img);

		pipeDown2Spirit = pipeDown1Spirit.getCloneSpirit();

		/*
		 * gameover
		 */
		gameoverImg = loadImg(R.drawable.gameover);
		gameoverSpirit = new Spirit(gameoverImg);
	}

	private Bitmap loadImg(int resourceId) {
		// TODO Auto-generated method stub
		return BitmapFactory.decodeResource(resources, resourceId);
	}

	@Override
	public void run() {
		while (isStarted) {
			Canvas canvas = null;
			try {
				// 开始编辑SurfaceView上的像素，这个api需要在SurfaceView被创建成功之后才能调用
				canvas = holder.lockCanvas();
				if (canvas != null) {
					drawGameSence(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					// 结束本次SurfaceView上像素的编辑，显示在SurfaceView上
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
		Log.d("INFO", "...run out...");
	}

	private void drawGameSence(Canvas canvas) {
		/*
		 * 由于背景图片bg1和floor一直都需要显示 作为三个状态都需要的背景，放在先买显示
		 */
		canvas.drawBitmap(bg1Img, null, new Rect(0, 0, screenWidth,
				screenHeight), paint);
		canvas.drawBitmap(floorImg, null, new Rect(0, 3 * screenHeight / 4,
				screenWidth, screenHeight), paint);

		// TODO Auto-generated method stub
		switch (gameStatus) {
		case READY_STATE:
			drawReadyScence(canvas);
			break;
		case RUNNING_STATE:
			drawRunningScence(canvas);
			break;
		case OVER_STATE:
			drawOverScence(canvas);
			break;
		default:
			break;
		}
	}

	private void drawOverScence(Canvas canvas) {
		String scoreText = "score:" + score;
		// TODO Auto-generated method stub
		gameoverSpirit.drawSelf(canvas);
		canvas.drawText(scoreText,
				gameoverSpirit.getX() + gameoverImg.getWidth()/2 - paint.measureText(scoreText)/2,
				gameoverSpirit.getY() + gameoverImg.getHeight()*2, paint);
	}

	private void drawReadyScence(Canvas canvas) {
		// TODO Auto-generated method stub
		readySpirit.drawSelf(canvas);
		birdSpirit.drawSelf(canvas);
		tapSpirit.drawSelf(canvas);
	}

	private void checkPipeAreaAndUpdateHeight(Spirit pipe, boolean isFirstPipe) {
		/*
		 * 如果这个pipe的x坐标超出坐标，需要
		 */
		if (pipe.getX() + pipe.getWidth() <= 0) {
			pipe.setX(screenWidth);

			if (isFirstPipe) {
				pipeTopHeight1 = (int) (pipeUp1Img.getHeight()
						* (Math.random() * 3 + 1) / 8);
			} else {
				// screenHeight/2 - pipeTopHeight2
				pipeTopHeight2 = (int) (pipeUp1Img.getHeight()
						* (Math.random() * 3 + 1) / 8);
			}
		}
	}

	private void drawRunningScence(Canvas canvas) {

		/*
		 * 在鸟儿还没有坠落的时候不断的调整pipe1和pipe2的位置向左
		 */
		if (!isBirdDropped) {
			/*
			 * 调整pipe1的上下两根的位置和显示的图片
			 */
			pipeUp1Spirit.setX(pipeUp1Spirit.getX() + PIPE_SPEED);
			pipeUp1Spirit.setBitmap(Bitmap.createBitmap(pipeUp1Img, 0,
					pipeUp1Img.getHeight() - pipeTopHeight1,
					pipeUp1Img.getWidth(), pipeTopHeight1));

			pipeDown1Spirit.setX(pipeUp1Spirit.getX());
			pipeDown1Spirit.setY(pipeTopHeight1*pipe_img_times + pipeSpan);
			pipeDown1Spirit.setBitmap(Bitmap.createBitmap(pipeDown1Img, 0, 0,
					pipeDown1Img.getWidth(), pipeDown1Img.getHeight()
							- pipeTopHeight1));
			/*
			 * 调整pipe2的上下两根的位置和显示的图片
			 */
			pipeUp2Spirit.setX(pipeUp2Spirit.getX() + PIPE_SPEED);
			pipeUp2Spirit.setBitmap(Bitmap.createBitmap(pipeUp1Img, 0,
					pipeUp1Img.getHeight() - pipeTopHeight2,
					pipeUp1Img.getWidth(), pipeTopHeight2));

			pipeDown2Spirit.setX(pipeUp2Spirit.getX());
			pipeDown2Spirit.setY(pipeTopHeight2*pipe_img_times + pipeSpan);
			pipeDown2Spirit.setBitmap(Bitmap.createBitmap(pipeDown1Img, 0, 0,
					pipeDown1Img.getWidth(), pipeDown1Img.getHeight()
							- pipeTopHeight2));

			/*
			 * 每次pipe1，pipe2更新位置之后 1、需要检查一下pipe1，pipe2是否已经超出，超出的话x坐标的值需要重新循环
			 * 2、pipeTopHeight1，pipeTopHeight2的高度需要根据随机值和范围更新
			 */
			checkPipeAreaAndUpdateHeight(pipeUp1Spirit, true);
			checkPipeAreaAndUpdateHeight(pipeUp2Spirit, false);
		}

		birdSpirit.setySpeed(birdSpirit.getySpeed() + 1.3f);
		birdSpirit.setY(birdSpirit.getY() + birdSpirit.getySpeed());

		if (birdSpirit.getY() < 0 || checkBirdHit(pipeUp1Spirit)
				|| checkBirdHit(pipeDown1Spirit) || checkBirdHit(pipeUp2Spirit)
				|| checkBirdHit(pipeDown2Spirit)) {
			isBirdDropped = true;
		}

		if (birdSpirit.getY() + birdSpirit.getHeight() >= 3 * screenHeight / 4) {
			gameStatus = OVER_STATE;
		}

		/*
		 * 小鸟穿过pipe就计算分数
		 */
		float pipe_right1 = pipeUp1Spirit.getX() + pipeUp1Img.getWidth();
		float pipe_right2 = pipeUp2Spirit.getX() + pipeUp1Img.getWidth();

		if (pipe_right1 < birdSpirit.getX()
				&& birdSpirit.getX() - pipe_right1 <= (-PIPE_SPEED)) {
			score += 100;
		}

		if (pipe_right2 < birdSpirit.getX()
				&& birdSpirit.getX() - pipe_right2 <= (-PIPE_SPEED)) {
			score += 100;
		}

		/*
		 * 绘制pipe1的上下根
		 */
		pipeUp1Spirit.drawSelf(canvas);
		pipeDown1Spirit.drawSelf(canvas);

		/*
		 * 绘制pipe2的上下根
		 */
		pipeUp2Spirit.drawSelf(canvas);
		pipeDown2Spirit.drawSelf(canvas);

		/*
		 * 绘制鸟儿
		 */
		birdSpirit.drawSelf(canvas);

		paint.setTextSize(20*pipe_img_times);
		String scoreText = "score:" + String.valueOf(score);
		/*
		 * 绘制分数 到鸟的下面
		 */
		canvas.drawText(scoreText, birdSpirit.getX()+birdSpirit.getWidth()/2-paint.measureText(scoreText)/2,
				birdSpirit.getY() + birdSpirit.getHeight() + 10, paint);
	}

	/**
	 * 检测鸟现在是否碰撞到pipe
	 * 
	 * @param pipe
	 *            pipe的spirit
	 * @return 是否碰撞pipe
	 */
	private boolean checkBirdHit(Spirit pipe) {
		// TODO Auto-generated method stub
		if (birdSpirit.getSpiritRect().intersect(pipe.getSpiritRect())) {
			return true;
		}
		return false;
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

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public boolean isBirdDropped() {
		return isBirdDropped;
	}

	public void setBirdDropped(boolean isBirdDropped) {
		this.isBirdDropped = isBirdDropped;
	}

	public Spirit getBirdSpirit() {
		return birdSpirit;
	}

	public void setBirdSpirit(Spirit birdSpirit) {
		this.birdSpirit = birdSpirit;
	}

}
