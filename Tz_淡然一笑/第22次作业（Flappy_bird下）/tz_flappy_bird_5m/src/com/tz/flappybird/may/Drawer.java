package com.tz.flappybird.may;

import java.util.Random;

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
 * 画家
 * 
 * @author fcc
 * 
 */
public class Drawer extends Thread {

	// 画家画画时需要的画板,画笔,图形
	private Context context;
	private SurfaceHolder holder; //画板SurfaceView的管家
	private int w, h; //屏幕或是画板宽,高
	private Paint paint; //画笔
	private boolean isStart;
//	private Spirit bird; //精灵
	
	//精灵  背景,地板
	private Spirit bg, floor, bird, getReady, tap, pipeTop1, pipeTop2, pipeBottom1, pipeBottom2, gameOver, panel;;

	private int pipeSpan; //管子的间距
	private int pipeTopHeight1, pipeTopHeight2; //上面两根管子的高度

	private int gameStatus; //游戏的状态
	
	public static final int READY = 0; //游戏开始
	public static final int RUNNING = 1; //游戏运行
	public static final int OVER = 2; //游泳结束
	
	private Bitmap pipeTopBm, pipeBottomBm; //上面的管子和下面的管子
	
	private boolean isDrop; //标识鸟是否下坠
	private final int PIPE_SPEED = -5; //管子的速度,管子从右往左为负数
	
	public int score;

	public Drawer(Context context, SurfaceHolder holder, int w, int h) {
		this.context = context;
		this.holder = holder;
		this.w = w;
		this.h = h;
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		isStart = true;
		
		//创建游戏的精灵
		initSpirits();
		//初始化精灵的属性
		initAttrs();
	}

	/**
	 * 创建游戏的精灵
	 */
	public void initSpirits() {
		Bitmap bm_bg = iBitmap(R.drawable.bg1);  //背景图片
		bg = new Spirit(bm_bg);
		Bitmap bm_floor = iBitmap(R.drawable.floor); //地板 1/4
		floor = new Spirit(bm_floor);
		Bitmap bm_ready = iBitmap(R.drawable.getready);
		getReady = new Spirit(bm_ready);
		Bitmap bm_tap = iBitmap(R.drawable.tap);
		tap = new Spirit(bm_tap);
		
		int [] birds = new int []{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap[] bm_birds = new Bitmap[birds.length];
		for (int i = 0; i < birds.length; i++) {
			Bitmap bm_bird = iBitmap(birds[i]);
			bm_birds[i] = bm_bird;
		}
		bird = new Spirit(bm_birds);
		
		pipeTopBm = iBitmap(R.drawable.pipe1);
		pipeTop1 = new Spirit(pipeTopBm);
		pipeBottomBm = iBitmap(R.drawable.pipe2);
		pipeBottom1 = new Spirit(pipeBottomBm);
		
		pipeTop2 = pipeTop1.getCloneObject(); //克隆pipeTop1
		pipeBottom2 = pipeBottom1.getCloneObject();  //克隆pipeBottom1
		
		Bitmap bm_over = iBitmap(R.drawable.gameover);
		gameOver = new Spirit(bm_over);
		
		Bitmap bm_panel = iBitmap(R.drawable.panel);
		panel = new Spirit(bm_panel);

	}
	
	/**
	 * 初始化精灵的坐标速度等属性
	 */
	public void initAttrs() {
		isDrop = false;
		score = 0;
		pipeSpan = h/4;//管子的间距
		pipeTopHeight1 = h/4;
		pipeTopHeight2 = h/3;//开始直接设置上面两根管子的高为固定值
		getReady.setX((w-getReady.getWidth())/2);  //中间
		getReady.setY(h/3);
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		bird.setX(w/4);
		bird.setY(h/2);
		bird.setySpeed(2);
		pipeTop1.setX(w);
		pipeTop1.setY(0);
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1+pipeSpan);
		pipeTop2.setX(3*w/2);
		pipeTop2.setY(0);
		pipeBottom2.setX(3*w/2);
		pipeBottom2.setY(pipeTopHeight2+pipeSpan);
		gameOver.setX((w-gameOver.getWidth())/2);
		gameOver.setY(h/4);
		panel.setX((w-panel.getWidth())/2);
		panel.setY((h-panel.getHeight())/2);
	}
	
	/**
	 * 根据图片id加载图片
	 * @param resId
	 * @return
	 */
	public Bitmap iBitmap(int resId){
		return BitmapFactory.decodeResource(context.getResources(), resId);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// 执行绘画的任务
		while (isStart) {
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas(); // 锁定画板
				if (canvas != null) {
					// 真正开始执行绘画
					// canvas.drawText("今天开始绘制愤怒的小鸟的游戏", 100, 100, paint);
					// bird.drawSelf(canvas); //画鸟
					drawGameView(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas); // 解锁画板
				}
			}

		}
	}

	/**
	 * 通过游戏状态绘制不同的界面
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//先绘画游戏各个状态都需要的背景地图和地板
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, w, h), paint);
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, 3*h/4, w, h), paint);
		switch (gameStatus) {
		case READY:
			drawGameReadyView(canvas);
			break;
		case RUNNING:
			drawGameRunningView(canvas);
			break;
		case OVER:
			drawGameOverView(canvas);
//			canvas.drawText("计分:"+score, 200, 200, paint);
			break;

		default:
			break;
		}
	}

	private void drawGameReadyView(Canvas canvas) {
		//绘制游戏开始的视图
		getReady.drawSelf(canvas);  //绘制ready
		tap.drawSelf(canvas);  //绘制tap
		bird.drawSelf(canvas); //绘制鸟
	}
	
	private void drawGameRunningView(Canvas canvas) {
		//绘制游戏运行的视图
		//绘制管子,先让管子运动起来
		if(!isDrop){   
			pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);
	//		pipeTop1.setY(0);
			//上面裁剪管子上面一部分,裁剪管子的部分用Bitmap.createBitmap()方法
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
			
	//		pipeBottom1.setX(pipeBottom1.getX()+PIPE_SPEED);
			pipeBottom1.setX(pipeTop1.getX());  //和pipeTop1坐标一样
			pipeBottom1.setY(pipeTopHeight1+pipeSpan);
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight1));
			
			pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);
	//		pipeTop2.setY(0);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
		
			pipeBottom2.setX(pipeTop2.getX());  //和pipeTop2坐标一样
			pipeBottom2.setY(pipeTopHeight2+pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight2));
		}
		
		//鸟,设置鸟的位置
		bird.setySpeed(bird.getySpeed()+1.3f); //加速度
		bird.setY(bird.getY()+bird.getySpeed());
		if(bird.getY()<=0||birdHitPipe(pipeTop1)||birdHitPipe(pipeBottom1)||birdHitPipe(pipeTop2)||birdHitPipe(pipeBottom2)){
			//鸟撞晕了
			isDrop = true;
		}
		if(bird.getY()+bird.getHeight()>=3*h/4){
			gameStatus = OVER;
			bird.setY(3*h/4 - bird.getHeight());
		}
		
		//管子出界了以后再让管子回到右边
		checkOutBound(pipeTop1,true);
		checkOutBound(pipeTop2, false);
		
		/**
		 * 判断鸟是否穿过了管子,穿过计一分
		 */
		int pipe_right1 = (int) (pipeTop1.getX()+pipeTop1.getWidth());
		int pipe_right2 = (int) (pipeTop2.getX()+pipeTop2.getWidth());
		if(pipe_right1<bird.getX()&&(bird.getX()-PIPE_SPEED)<=(-PIPE_SPEED)){
			score++;
			Log.i("INFO", "score:"+score);
		}
		if(pipe_right2<bird.getX()&&(bird.getX()-PIPE_SPEED)<=(-PIPE_SPEED)){
			score++;
			Log.i("INFO", "score:"+score);
		}
		
		//绘制管子
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		
		bird.drawSelf(canvas); //画鸟
	}
	
	/**
	 * 碰撞检测
	 * @param pipe
	 * @return
	 */
	private boolean birdHitPipe(Spirit pipe) {
		if(bird.getSpiritRect().intersect(pipe.getSpiritRect())){
			return true;
		}
		return false;
	}

	private void checkOutBound(Spirit pipe, boolean isFirst) {
		if(pipe.getX()+pipe.getWidth()<=0){
			//管子出去了
			pipe.setX(w); //回到右边
			if(isFirst){
				pipeTopHeight1 = (int) (h*(Math.random()*2+1)/8); // 1/8-3/8
			}else{
				pipeTopHeight2 = (int) (h*(new Random().nextFloat()*2+1)/8); // 1/8-3/8
			}
		}
	}

	private void drawGameOverView(Canvas canvas) {
		//绘制游戏结束的视图
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
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
	
	public boolean isDrop() {
		return isDrop;
	}

	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}

}
