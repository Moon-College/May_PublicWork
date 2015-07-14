/**
 * Project Name:flappy_bird
 * File Name:Painter.java
 * Package Name:com.zht.flappybird
 * Date:2015-7-6下午3:37:15
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.flappybird;

import java.util.Random;

import com.zht.flappybird.bean.Spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * ClassName:Painter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-6 下午3:37:15 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class Painter extends Thread {
	private Context context;
	private SurfaceHolder surfaceHolder;
	private int width;
	private int height;
	private Paint paint;
	public boolean isStart;
	private Spirit getReady;
	private Spirit tap;
	private Spirit bg;
	private Spirit floor;
	private Spirit pipeTop1;
	private Spirit pipeTop2;
	private Spirit pipeBottom1;
	private Spirit pipeBottom2;
	private Spirit bird;
	private Spirit gameOver;
	private Spirit panel;
	public boolean isDrop;
	private int score;
	private int pipeSpan;
	private int pipeTopHeight1;
	private int pipeTopHeight2;
	public static final int READY = 0;
	public static final int RUNNING = 1;
	public static final int OVER = 2;
	private int gameStatus;
	public static int pipe_speed = -5;
	private Bitmap bm_pipe_top;
	private Bitmap bm_pipe_bottom;

	public Painter(Context context, SurfaceHolder surfaceHolder, int width,
			int height) {
		this.context = context;
		this.surfaceHolder = surfaceHolder;
		this.width = width;
		this.height = height;
		paint = new Paint();
		paint.setColor(Color.WHITE);
		isStart = true;
		// 创建游戏精灵
		createSpirits();
		// 初始化精灵属性
		initSpiritsAttrs();
	}

	private void createSpirits() {
		// 准备页面
		Bitmap bm_ready = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.getready);
		getReady = new Spirit(bm_ready);
		// 准备页面点击处
		Bitmap bm_tap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.tap);
		tap = new Spirit(bm_tap);
		// 游戏背景
		Bitmap bm_bg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bg1);
		bg = new Spirit(bm_bg);
		;
		// 游戏背景地板
		Bitmap bm_floor = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.floor);
		floor = new Spirit(bm_floor);
		bm_pipe_top = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pipe1);
		bm_pipe_bottom = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pipe2);
		pipeTop1 = new Spirit(bm_pipe_top);
		pipeTop2 = pipeTop1.getCloneObject();
		pipeBottom1 = new Spirit(bm_pipe_bottom);
		pipeBottom2 = pipeBottom1.getCloneObject();
		// 鸟
		int[] birds = new int[] { R.drawable.bird_1, R.drawable.bird_2,
				R.drawable.bird_3 };
		Bitmap[] bm_birds = new Bitmap[birds.length];
		for (int i = 0; i < bm_birds.length; i++) {
			bm_birds[i] = BitmapFactory.decodeResource(context.getResources(),
					birds[i]);
		}

		bird = new Spirit(bm_birds);
		// 游戏结束
		Bitmap bm_gameover = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.gameover);
		gameOver = new Spirit(bm_gameover);
		// 计分板
		Bitmap bm_panel = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.panel);
		panel = new Spirit(bm_panel);
	}

	public void initSpiritsAttrs() {
		isDrop = false;
		score = 0;
		pipeSpan = height / 4;
		pipeTopHeight1 = height / 4;
		pipeTopHeight2 = height / 3;
		getReady.setX((width - getReady.getWidth()) / 2);
		getReady.setY(height / 3);
		tap.setX((width - tap.getWidth()) / 2);
		tap.setY(height / 2);
		bird.setX(width / 4);
		bird.setY(height / 2);
		bird.setYSpped(2);
		pipeTop1.setX(width);
		pipeTop1.setY(0);
		pipeBottom1.setX(width);
		pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
		pipeTop2.setX(3 * width / 2);
		pipeTop2.setY(0);
		pipeBottom2.setX(3 * width / 2);
		pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
		gameOver.setX((width - gameOver.getWidth()) / 2);
		gameOver.setY(height / 4);
		panel.setX((width - panel.getWidth()) / 2);
		panel.setY((height - panel.getHeight()) / 2);
	}

	@Override
	public void run() {
		super.run();
		while (isStart) {
			Canvas canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas();
				if (canvas != null) {
					// 开始绘制游戏画面
					drawGameView(canvas);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}

		}
	}

	// 通过游戏状态绘制不同的界面
	private void drawGameView(Canvas canvas) {
		// 先绘制各个状态都需要的精灵
		// 背景
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, width, height),
				paint);
		// 地板
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, 3 * height / 4,
				width, height), paint);
		// 在绘制不同界面中不同的精灵
		switch (gameStatus) {
		case READY:
			drawGameReadyView(canvas);
			break;
		case RUNNING:
			drawGamenRunningView(canvas);
			break;
		case OVER:
			drawGameOverView(canvas);
			break;
		default:
			break;
		}
	}

	private void drawGameReadyView(Canvas canvas) {
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);

	}

	// 重点是绘制游戏中的画面
	private void drawGamenRunningView(Canvas canvas) {
		// 绘制管子，让上下两根管子运动起来
		if (!isDrop) {// 在鸟没有掉下来的情况下才运动
			// 前面两对管子
			pipeTop1.setX(pipeTop1.getX() + pipe_speed);
			pipeTop1.setBitmap(Bitmap.createBitmap(bm_pipe_top, 0,
					bm_pipe_top.getHeight() - pipeTopHeight1,
					bm_pipe_top.getWidth(), pipeTopHeight1));

			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
			pipeBottom1.setBitmap(Bitmap.createBitmap(bm_pipe_bottom, 0, 0,
					bm_pipe_bottom.getWidth(), height / 2 - pipeTopHeight1));
			// 后面两对管子
			pipeTop2.setX(pipeTop2.getX() + pipe_speed);
			pipeTop2.setBitmap(Bitmap.createBitmap(bm_pipe_top, 0,
					bm_pipe_top.getHeight() - pipeTopHeight2,
					bm_pipe_top.getWidth(), pipeTopHeight2));

			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(bm_pipe_bottom, 0, 0,
					bm_pipe_bottom.getWidth(), height / 2 - pipeTopHeight2));
		}

		// 设置鸟的位置
		bird.setYSpped(bird.getYSpped() + 1f);
		bird.setY(bird.getY() + bird.getYSpped());
		if (bird.getY() <= 0 || birdHitPipe(pipeTop1) || birdHitPipe(pipeTop2)
				|| birdHitPipe(pipeBottom1) || birdHitPipe(pipeBottom2)) {
            isDrop = true;
		}
		if(bird.getY()+bird.getHeight()>=3*height/4){
			gameStatus = OVER;
			bird.setY(3*height/4 - bird.getHeight());
		}
		//管子循环出现
		pipeCycleShow(pipeTop1,true);
		pipeCycleShow(pipeTop2,false);
		
		/**
		 * 判断鸟是否穿过了管子
		 */
		int pipe_right1 = (int) (pipeTop1.getX()+pipeTop1.getWidth());
		int pipe_right2 = (int) (pipeTop2.getX()+pipeTop2.getWidth());
	    if(pipe_right1<bird.getX()&&bird.getX()-pipe_right1<=(-pipe_speed)){
	    	score++;
	    	Log.i("INFO", "score:"+score);
	    }
	    if(pipe_right2<bird.getX()&&bird.getX()-pipe_right2<=(-pipe_speed)){
	    	score++;
	    	Log.i("INFO", "score:"+score);
	    }
		//绘制管子
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		//画鸟
		bird.drawSelf(canvas);
	}

	private void pipeCycleShow(Spirit pipe, boolean isFirst) {
		if(pipe.getX()+pipe.getWidth()<=0){
			//管子出去了
			pipe.setX(width);
			if(isFirst){
				pipeTopHeight1 = (int)(height*(Math.random()*2+1)/8);
			}else{
				pipeTopHeight2 = (int) (height*(new Random().nextFloat()*2+1)/8);
			}
		}
		
	}
     //碰撞检测
	private boolean birdHitPipe(Spirit pipe) {
		if(bird.getSpiritRect().intersect(pipe.getSpiritRect())){
			Log.i("INFO","hitPipe");
			return true;
		}
		return false;
	}

	private void drawGameOverView(Canvas canvas) {
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);

	}

	// ------------------------------------------------------------------------------------
	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Spirit getBird() {
		return bird;
	}

	public void setBird(Spirit bird) {
		this.bird = bird;
	}
    
}
