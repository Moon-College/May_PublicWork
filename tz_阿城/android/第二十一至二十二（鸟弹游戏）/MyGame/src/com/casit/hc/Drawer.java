package com.casit.hc;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;
import android.view.SurfaceHolder;

public class Drawer extends Thread {
	//画家绘画基本画笔，，画笔，图形
	private Context context;
	private SurfaceHolder holder;
	private int w , h ;
	private Paint paint;
	private boolean isStart;
	private int pipeSpan;//管子的间距
	private int pipeTopHeight1,pipeTopHeight2; //上面两根管子的高度
	private Spirit bg,floor,bird,getReady,tap,pipeTop1,pipeTop2,pipeBottom1,pipeBottom2,gameOver,panel;
	private Bitmap pipeBottomBm,pipeTopBm;	
	public int gameStatu;
	public final static int READY = 0;
	public final static int RUNNING = 1;
	public final static int OVER = 2;
	private boolean isDrop;//鸟是否已经坠落
	private final static int PIPE_SPEED = -8;
	private int score;

	public Drawer(Context context , SurfaceHolder surfaceholder,int w, int h){
		this.context = context;
		this.holder = surfaceholder;
		this.w = w;
		this.h = h;
		paint =new Paint();
		paint.setColor(Color.BLUE);
		isStart = true;
		//初始化精灵
		initSpirits();
		//初始化精灵属性
		initAttrs();
		//创建鸟
	/*	int [] paths = new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap [] bms = new Bitmap[paths.length];
		for(int i = 0;i<paths.length;i++){
			bms[i] = BitmapFactory.decodeResource(context.getResources(), paths[i]);
		}
		bird = new Spirit(bms);*/
		
	}
	public void initAttrs() {
		// TODO Auto-generated method stub
		isDrop = false;
		score = 0;
	//	score = 0;
		pipeSpan = h/4;//管子的间距
		pipeTopHeight1 = h/4;
		pipeTopHeight2 = h/3;//直接设置的高
		getReady.setX((w-getReady.getWidth())/2);
		getReady.setY(h/3);
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		bird.setX(w/4);
		bird.setY(h/2);
		bird.setSpeedY(2);
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
	public void initSpirits() {
		Bitmap bm_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
		bg = new Spirit(bm_bg);
		Bitmap bm_floor = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
		floor = new Spirit(bm_floor);
		Bitmap bm_ready = BitmapFactory.decodeResource(context.getResources(), R.drawable.getready);
		getReady = new Spirit(bm_ready);
		Bitmap bm_tap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tap);
		tap = new Spirit(bm_tap);
		int [] birds = new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap[] bm_birds = new Bitmap [birds.length];
		for(int i = 0;i < birds.length ;i++){
			Bitmap bm_bird = BitmapFactory.decodeResource(context.getResources(), birds[i]);
			bm_birds[i] = bm_bird;
		}
		bird = new Spirit(bm_birds);
		pipeTopBm = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
		pipeTop1 = new Spirit(pipeTopBm);
		pipeBottomBm = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
		pipeBottom1 = new Spirit(pipeBottomBm);
		pipeTop2 = pipeTop1.getCloneObject();
		pipeBottom2 = pipeBottom1.getCloneObject();
		Bitmap bm_over = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
		gameOver = new Spirit(bm_over);
		Bitmap bm_panel = BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
		panel = new Spirit(bm_panel);
	    
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(isStart){
			Canvas canvas = null;
			try{
			    canvas = holder.lockCanvas();
			    if(canvas!=null){
			    	canvas.drawText("今天准备了一个游戏", 100, 100, paint);
			    	drawGameView(canvas);
			    //	bird.drawSelf(canvas);
			    }
			    
			}catch(Exception e){
				
			}finally{
				if(canvas!=null)
				holder.unlockCanvasAndPost(canvas);
			}
			
			
		}
	}
	/**
	 * 
	 * @author hucheng
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas){
		//现绘制各个状态都需要的精灵
		
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0,0,w,h), paint);
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0,3*h/4,w,h), paint);
		switch(gameStatu){
		case READY:
			drawGameReadyView(canvas);
			break;
		case RUNNING:
		//	Log.i("INFO", "Running");
			drawGameRunningView(canvas);
			break;
		case OVER:
			drawGameOverView(canvas);
			break;
		}
	}
	public boolean isDrop() {
		return isDrop;
	}
	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}
	private void drawGameOverView(Canvas canvas) {
		// TODO Auto-generated method stub
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
	}
	private void drawGameRunningView(Canvas canvas) {
		// TODO Auto-generated method stub
		if(!isDrop){
		pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);	
		pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
	
		pipeBottom1.setX(pipeTop1.getX());
		pipeBottom1.setY(pipeTopHeight1+h/4);
		pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0,0, pipeBottomBm.getWidth(), h/2-pipeTopHeight1));
	
		pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);	
		pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
	
		pipeBottom2.setX(pipeTop2.getX());
		pipeBottom2.setY(pipeTopHeight2+h/4);
		pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight2));
		}
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		checkOutBound(pipeTop1,true);
		checkOutBound(pipeTop2,false);		
	    if(pipeTop1.getX()+pipeTop1.getWidth()<bird.getX()&&bird.getX()<=pipeTop1.getX()+pipeTop1.getWidth()-pipeTop1.getSpeedX());
	    {
	    	score++;
	    }
	    Log.i("INFO", score+"");
	    bird.setSpeedY(bird.getSpeedY()+1.3f);
		bird.setY(bird.getY()+bird.getSpeedY());
		if(bird.getY()<=0||birdHitPipe(pipeTop1)||birdHitPipe(pipeTop2)||birdHitPipe(pipeBottom1)||birdHitPipe(pipeBottom2)){
			isDrop = true;
		}
		if(bird.getY()+bird.getHeight()>=3*h/4){
			gameStatu = OVER;
			bird.setY(3*h/4-bird.getHeight());
		}
		bird.drawSelf(canvas);
	}
	private boolean birdHitPipe(Spirit pipe) {
		// TODO Auto-generated method stub
	    if(bird.getSpiritRect().intersect(pipe.getSpiritRect())){
		    return true;
	    }
	    return false;
	}
	private void checkOutBound(Spirit pipeTop12, boolean isFirst) {
		// TODO Auto-generated method stub
	
			if(pipeTop12.getX()+pipeTop1.getWidth()<=0){
				pipeTop12.setX(w);
				if(isFirst){
					pipeTopHeight1 = (int) (h*(Math.random()*2+1)/8);
				}else{
					pipeTopHeight2 = (int) (h*(new Random().nextFloat()*2+1)/8);
				}
			}
		
	}
	private void drawGameReadyView(Canvas canvas) {
		// TODO Auto-generated method stub
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);
	}
	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public Spirit getBird() {
		return bird;
	}
	public void setBird(Spirit bird) {
		this.bird = bird;
	}
	public int getGameStatu() {
		return gameStatu;
	}
	public void setGameStatu(int gameStatu) {
		this.gameStatu = gameStatu;
	}
}
