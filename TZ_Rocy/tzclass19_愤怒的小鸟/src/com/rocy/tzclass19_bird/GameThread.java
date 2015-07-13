package com.rocy.tzclass19_bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
/**
 * 游戏运行线程
 * @author Rocy
 *
 */

public class GameThread extends Thread {
	public static final int GAME_START = 0;
	public static final int GAME_RUNNING = 2;
	public static final int GAME_OVER = 4;
	
	private float speed = 1.08f;
	private int width;
	private int height;
	private Context context;
	private SurfaceHolder holder;
	private Spirit bg,floor,gameover,getready,logo,panel,tap;
	
	private Spirit bird;
	private Canvas canvas;
	private boolean isDestory;
	private int status = 0;
	private Spirit upPipe1;
	private Spirit upPipe2;
	private Spirit downPipe1;
	private Spirit downPipe2;
	private Bitmap pipe1;
	private Bitmap pipe2;
	private boolean isbirdDid ;
	private int score = 0;
	private Paint paint ;
	
      
	/**
	 * 
	 * @param context
	 * @param width
	 * @param height
	 * @param holder
	 */
	public GameThread(Context context, int width, int height,
			SurfaceHolder holder) {
		this.width = width;
		this.height = height;
		this.context = context;
		this.holder = holder;
		initSpiritAndLocation();
	}

	
	//初始化精灵和位置
	private void initSpiritAndLocation() {
		paint = new Paint();
		paint.setColor(context.getResources().getColor(android.R.color.holo_red_light));
		paint.setTextSize(40f);
		bg = new Spirit(0, 0, getBitmap(R.drawable.bg1));
		floor = bg.getClone();
		floor.setBitmap(getBitmap(R.drawable.floor));
		floor.setX(0);
		floor.setY(3*height/4);
		bird = bg.getClone();
		Bitmap birdBitmaps[] = new Bitmap[]{getBitmap(R.drawable.bird_1),getBitmap(R.drawable.bird_2),getBitmap(R.drawable.bird_3)};
		bird.setBitmaps(birdBitmaps);
		bird.setX(1*width/6);
		bird.setY(1*height/2);
		bird.setSpeed(speed);
		
		gameover = bg.getClone();
		gameover.setBitmap(getBitmap(R.drawable.gameover));
		gameover.setX((width-gameover.getBitmap().getWidth())/2);
		gameover.setY(1*height/4+100);
		getready = bg.getClone();
		getready.setBitmap(getBitmap(R.drawable.getready));
		getready.setX((width-getready.getBitmap().getWidth())/2);
		getready.setY(1*height/4+100);
		logo = bg.getClone();
		logo.setBitmap(getBitmap(R.drawable.logo));
		logo.setX((width-logo.getBitmap().getWidth())/2);
		logo.setY(1*height/5);
		panel = bg.getClone();
		panel.setBitmap(getBitmap(R.drawable.panel));
		panel.setX((width-panel.getBitmap().getWidth())/2);
		panel.setY(1*height/2);
		pipe1 = getBitmap(R.drawable.pipe1);
		pipe2 = getBitmap(R.drawable.pipe2);
		upPipe1 = bg.getClone();
		upPipe2 = bg.getClone();
		downPipe1 = bg.getClone();
		downPipe2 = bg.getClone();
		initPipe();
		tap = bg.getClone();
		tap.setBitmap(getBitmap(R.drawable.tap));
		tap.setX((width-tap.getBitmap().getWidth())/2);
		tap.setY(1*height/2);
	}


	public void initPipe() {
		upPipe1.setBitmap(Bitmap.createBitmap(pipe1, 0, pipe1.getHeight()-height/4, pipe1.getWidth(), height/4));
		upPipe1.setX((width-pipe1.getWidth())/2);
		upPipe1.setY(0);
		upPipe1.setSpeed(speed);
		upPipe2.setBitmap(Bitmap.createBitmap(pipe1, 0, pipe1.getHeight()-height/4, pipe1.getWidth(), height/4));
		upPipe2.setX(width);
		upPipe2.setY(0);
		upPipe2.setSpeed(speed);
		downPipe1.setBitmap(Bitmap.createBitmap(pipe2, 0, 0, pipe2.getWidth(), height/4));
		downPipe1.setX((width-pipe2.getWidth())/2);
		downPipe1.setY(height/2);
		downPipe1.setSpeed(speed);
		downPipe2.setBitmap(Bitmap.createBitmap(pipe2, 0, 0, pipe2.getWidth(), height/4));
		downPipe2.setX(width);
		downPipe2.setY(height/2);
		downPipe2.setSpeed(speed);
	}

	private Bitmap getBitmap(int id) {
		// 获取素材
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	@Override
	public void run() {
		// 获取画笔
		super.run();
		while (!isDestory) {
			try {
			canvas = holder.lockCanvas();
			canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, width, height), null);
			canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, 3*height/4, width, height), null);
			//控制帧的刷新频率			
			gamePlay();
			sleep(100);
			holder.unlockCanvasAndPost(canvas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		}
	}

	private void gamePlay() {
		// TODO Auto-generated method stub
		switch (status) {
		case GAME_START:
			//位置复位
			isbirdDid = false;
			getready.drawAnimationSpirit(canvas);
			logo.drawAnimationSpirit(canvas);
			tap.drawAnimationSpirit(canvas);
			bird.drawAnimationSpirit2(canvas);
			break;
		case GAME_RUNNING:
			//进入游戏
			//绘制管子
			upPipe1.drawAnimationSpirit(canvas);
			upPipe2.drawAnimationSpirit(canvas);
			downPipe1.drawAnimationSpirit(canvas);
			downPipe2.drawAnimationSpirit(canvas);
			canvas.drawText(score+"", 30, 30, paint);
			//鸟
			bird.drawAnimationSpirit2(canvas);
			//鸟移动
			bird.setY((int)(bird.getY()*bird.getSpeed()));
			//判断管子是不是需要重新开始绘制
			beginMove(upPipe1,downPipe1);
			beginMove(upPipe2,downPipe2);
			isScore();
			//鸟跟管子碰撞为死亡
			isImpack();
			birdDie(bird);
			break;
		case GAME_OVER:
			//结束画面
			gameover.drawAnimationSpirit(canvas);
			panel.drawAnimationSpirit(canvas);
			
			break;

		default:
			break;
		}
	}

	


	private void isScore() {
		// 穿过并且小于最小间隔
		int x1= upPipe1.getX()+upPipe1.getBitmap().getWidth();
		int x2= upPipe2.getX()+upPipe2.getBitmap().getWidth();
		
		if((bird.getX() >= x1 && bird.getX()-x1 <= speed*15)||
				(bird.getX() >= x2 && bird.getX()-x2 <= speed*15)){
			score ++;
		}
		
	}


	private void isImpack() {
		// 碰撞
		if(isbirdDid){
			stopPipe();
		}else{
		if(bird.getRect().intersect(upPipe1.getRect())||
				bird.getRect().intersect(upPipe2.getRect())||
				bird.getRect().intersect(downPipe1.getRect())||
				bird.getRect().intersect(downPipe2.getRect())){
			//不能操作
			isbirdDid = true ;
		}else if(bird.getY()<=0){
			//不能操作
			isbirdDid = true ;
		}else{
			//移动
			upPipe1.setX((int) (upPipe1.getX()-speed*15));
			upPipe2.setX((int) (upPipe2.getX()-speed*15));
			downPipe1.setX((int) (downPipe1.getX()-speed*15));
			downPipe2.setX((int) (downPipe2.getX()-speed*15));
		}
		}
		
	}


	private void stopPipe() {
		///管子停止运动
		upPipe1.setX(upPipe1.getX());
		upPipe2.setX(upPipe2.getX());
		downPipe1.setX(downPipe1.getX());
		downPipe2.setX(downPipe2.getX());
		
	}


	private void birdDie(Spirit bird) {
		// 鸟死亡
		if(bird.getY() >= 3*height/4){
			status = GAME_OVER;
			bird.setY(1*height/2);
			initPipe();
			score = 0;
		};
	}


	private void beginMove(Spirit upSpirit ,Spirit downSpirit) {
		// 判断是不是需要重新绘制
		int upX= upSpirit.getX()+upSpirit.getBitmap().getWidth();
		int downX= downSpirit.getX()+downSpirit.getBitmap().getWidth();
		if(upX <= 0 && downX <= 0){
			//返回
			upSpirit.setX(width);
			downSpirit.setX(width);
			//并且高度随机变化；
			float rand=(float) (Math.random()+0.3);
			int rand2= Math.round(rand*(height/4-pipe1.getWidth()));
			upSpirit.setBitmap(Bitmap.createBitmap(pipe1, 0,Math.round((rand*(height/4-pipe1.getWidth()))),  pipe1.getWidth(),pipe1.getHeight()-rand2));
			int y=height/4+upSpirit.getBitmap().getHeight();
			downSpirit.setY(y);
			downSpirit.setBitmap(Bitmap.createBitmap(pipe2, 0, 0, pipe2.getWidth(), 3*height/4-y));
		
		}
	}


	/**
	 * @param isDestory the isDestory to set
	 */
	public void setDestory(boolean isDestory) {
		this.isDestory = isDestory;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the bird
	 */
	public Spirit getBird() {
		return bird;
	}


	/**
	 * @param bird the bird to set
	 */
	public void setBird(Spirit bird) {
		this.bird = bird;
	}


	/**
	 * @return the isbirdDid
	 */
	public boolean isIsbirdDid() {
		return isbirdDid;
	}


	/**
	 * @param isbirdDid the isbirdDid to set
	 */
	public void setIsbirdDid(boolean isbirdDid) {
		this.isbirdDid = isbirdDid;
	}
    
	
}
