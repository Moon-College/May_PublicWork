package cn.ysh.flappy_bird;

import java.util.Random;

import cn.ysh.flappy_bird1.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * 绘制类，用来绘制各种组件
 * @author Administrator
 *
 */
public class Drawer extends Thread{
	//surfaceView的宽高
	private int width;
	private int height;
	private int span;
	private int pipeTopHeight1;
	private int pipeTopHeight2;
	//管子移动的速度
	private final int PIPE_SPEED = -10;
	//还要给到SurfaceHolder
	private SurfaceHolder holder;
	private Context context;
	//给个开关控制run方法里面的while循环
	private boolean isStart;
	//一般游戏体现出状态机的机制，这里我们分为三个状态，准备，运行，结束
	public final static int READY = 0;
	public final static int RUNNING = 1;
	public final static int OVER = 2;
	private int gameStatus;
	public boolean isDrop;

	//所有的精灵
	private Spirit bg,floor,bird,getReady,tap,pipeTop1,pipeTop2,pipeBottom1,pipeBottom2,gameOver,panel;
	private Bitmap bm_pipeTop;
	private Bitmap bm_pipeBottom;
	public Drawer(Context context, SurfaceHolder holder,int width, int height){
		this.context = context;
		this.holder = holder;
		this.width = width;
		this.height = height;
		isStart = true;
		//初始化所有的精灵（游戏内的图片）
		initSpirit();
		//初始化所有精灵的属性
		initSpiritAttrs();
	}
	/**
	 * 初始化精灵
	 */
	private void initSpirit() {
		Bitmap bm_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
		bg = new Spirit(bm_bg);
		Bitmap bm_floor = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
		floor = new Spirit(bm_floor);
		Bitmap bm_getReady = BitmapFactory.decodeResource(context.getResources(), R.drawable.getready);
		getReady = new Spirit(bm_getReady);
		Bitmap bm_tap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tap);
		tap = new Spirit(bm_tap);
		int[] image = new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3};
		Bitmap[] bitmaps = new Bitmap[image.length];
		for(int i = 0; i < bitmaps.length; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), image[i]);
			bitmaps[i] = bitmap;
		}
		bird = new Spirit(bitmaps);
		bm_pipeTop = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
		pipeTop1 =  new Spirit(bm_pipeTop);
		pipeTop2 = pipeTop1.getCloneObject();
		bm_pipeBottom = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
		pipeBottom1 =  new Spirit(bm_pipeBottom);
		pipeBottom2 = pipeBottom1.getCloneObject();
		Bitmap bm_gameOver =  BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
		gameOver = new Spirit(bm_gameOver);
		Bitmap bm_panel =  BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
		panel = new Spirit(bm_panel);
	}
	/**
	 * 初始化精灵的属性
	 */
	public void initSpiritAttrs() {
		isDrop = false;
		span = height / 4;
		pipeTopHeight1 = height / 4;
		pipeTopHeight2 = height / 3;
		getReady.setX((width - getReady.getWidth()) / 2);
		getReady.setY(height / 3);
		tap.setX((width-tap.getWidth())/2);
		tap.setY(height/2);
		bird.setX(width/4);
		bird.setY(height/2);
		bird.setySpeed(0);
		pipeTop1.setX(width);
		pipeTop1.setY(0);
		pipeBottom1.setX(width);
		pipeBottom1.setY(pipeTopHeight1 + span);
		pipeTop2.setX(width * 3 / 2);
		pipeTop2.setY(0);
		pipeBottom2.setX(width * 3 / 2);
		pipeBottom2.setY(pipeTopHeight2 + span);
		gameOver.setX((width-gameOver.getWidth())/2);
		gameOver.setY(height/4);
		panel.setX((width-panel.getWidth())/2);
		panel.setY((height-panel.getHeight())/2);
	}
	@Override
	public void run() {
		while(isStart){
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if(canvas != null){
					drawGameView(canvas);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(canvas != null){
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	/**
	 * 通过游戏状态去绘制不同的页面
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//为了保证能全屏，画在矩形里
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, width, height), null);
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, height * 3 / 4, width, height), null);
		switch (gameStatus) {
		case READY:
			drawReadyView(canvas);
			break;
		
		case RUNNING:
			drawRunningView(canvas);
			break;
		
		case OVER:
			drawOverView(canvas);
			break;

		default:
			break;
		}
		
	}
	
	private void drawOverView(Canvas canvas) {
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
		
	}
	private void drawRunningView(Canvas canvas) {
		if(!isDrop){
			//绘制游戏视图，得先让管子动起来
			pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);
			pipeTop1.setBitmap(Bitmap.createBitmap(bm_pipeTop, 0, bm_pipeTop.getHeight()-pipeTopHeight1, bm_pipeTop.getWidth(), pipeTopHeight1));
			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1+span);
			pipeBottom1.setBitmap(Bitmap.createBitmap(bm_pipeBottom, 0, 0, bm_pipeBottom.getWidth(), height/2-pipeTopHeight1));
			pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);
			pipeTop2.setBitmap(Bitmap.createBitmap(bm_pipeTop, 0, bm_pipeTop.getHeight()-pipeTopHeight2, bm_pipeTop.getWidth(), pipeTopHeight2));
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2+span);
			pipeBottom2.setBitmap(Bitmap.createBitmap(bm_pipeBottom, 0, 0, bm_pipeBottom.getWidth(), height/2-pipeTopHeight2));
			//检查管道是否出了屏幕，出了屏幕后回到右边
			checkPipeOut(pipeTop1,true);
			checkPipeOut(pipeTop2, false);
		}
		bird.setySpeed(bird.getySpeed()+1);//给小鸟一个加速度
		bird.setY(bird.getY()+bird.getySpeed());
		//判断小鸟与矩形碰撞
		if(bird.getY()<=0 || hitPipeCheck(pipeTop1) || hitPipeCheck(pipeTop2) || hitPipeCheck(pipeBottom1) || hitPipeCheck(pipeBottom2)){
			isDrop = true;
		}
		//当碰撞后需要判断小鸟的高度，直到落地后，游戏切换到over状态
		if(bird.getY() + bird.getHeight() >= height * 3 / 4){
			gameStatus = OVER;
			bird.setY(height * 3 / 4 - bird.getHeight());
		}
		
		
		
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		bird.drawSelf(canvas);
	}
	
	private void drawReadyView(Canvas canvas) {
		getReady.drawSelf(canvas);
		bird.drawSelf(canvas);
		tap.drawSelf(canvas);
	}
	
	/**
	 * 判断管子是否出了屏幕
	 * @param pipe
	 * @param isFirst 是否为第一对管子
	 */
	private void checkPipeOut(Spirit pipe, boolean isFirst) {
		if(pipe.getX() + pipe.getWidth() <= 0){
			pipe.setX(width);
			if(isFirst){
				pipeTopHeight1 = (int) (height * (new Random().nextFloat() * 2 + 1) / 8);
			}else{
				pipeTopHeight2 = (int) (height * (new Random().nextFloat() * 2 + 1) / 8);
			}
		}
	}
	/**
	 * 碰撞检测
	 * @param pipe
	 * @return
	 */
	public boolean hitPipeCheck(Spirit pipe){
		return bird.getRect().intersect(pipe.getRect());
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
}
