package com.tz.michael.utils;

import com.tz.michael.activity.R;
import com.tz.michael.spirit.BaseSpirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

/**
 * 绘制的类
 * @author admin
 *
 */
public class Drawer extends Thread {

	private SurfaceHolder sfh;
	private int w,h;
	private Paint paint;
	private boolean isStart;
	private BaseSpirit bg,floor,logo,getReady,tap,bird,pipeTop1,pipeBottom1,pipeTop2,pipeBottom2,gameOver,panel;
	/**游戏的状态：菜单，play，over*/
	private int status;
	private Bitmap bm_pipeTop,bm_pipeBottom;
	public static final int GAMEMENU=0;
	public static final int GAMEPLAYING=1;
	public static final int GAMEOVER=2;
	/**管子间距*/
	private int pipeSpan;
	/**顶部第两根管子的高度*/
	private int pipeTopHeight1,pipeTopHeight2;
	/**鸟是否已经坠落*/
	private boolean isDrop;
	private float pipeSpeed=-4;
	/**得分*/
	private int score;
	
	public BaseSpirit getBird() {
		return bird;
	}

	public boolean isDrop() {
		return isDrop;
	}

	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public Drawer(Context context,SurfaceHolder surfaceHolder,int width,int height){
		this.sfh=surfaceHolder;
		this.w=width;
		this.h=height;
		paint=new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(14);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		isStart=true;
		initSpirits(context);
		initAttrs();
	}

	/**
	 * 初始化精灵的一些属性，主要是坐标位置
	 */
	public void initAttrs() {
		score=0;
		isDrop=false;
		pipeSpan=h/4;
		//第一次直接设置顶部管子的高
		pipeTopHeight1 = h/4;
		pipeTopHeight2 = h/3;
		//设置logo的位置
		logo.setX((w-logo.getWidth())/2);
		logo.setY(h/8);
		//设置getReady的坐标
		getReady.setX((w-getReady.getWidth())/2);
		getReady.setY(h/3);
		//设置tap图片的位置
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		//设置鸟的初始位置
		bird.setX(w/4);
		bird.setY(h/2);
		//设置鸟的初始速度Y
		bird.setSpeedY(2);
		//设置管子的初始位置
		pipeTop1.setX(w);
		pipeTop1.setY(0);
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1+pipeSpan);
		pipeTop2.setX(3*w/2);
		pipeTop2.setY(0);
		pipeBottom2.setX(3*w/2);
		pipeBottom2.setY(pipeTopHeight2+pipeSpan);
		//设置管子的速度
		pipeTop1.setSpeedX(pipeSpeed);
		//设置gameOver图片的初始位置
		gameOver.setX((w-gameOver.getWidth())/2);
		gameOver.setY(h/4);
		//设置panel得分板初始位置
		panel.setX((w-panel.getWidth())/2);
		panel.setY((h-panel.getHeight())/2);
	}

	/**
	 * 初始化精灵
	 * @param context
	 */
	private void initSpirits(Context context) {
		//初始化背景
		Bitmap bm_bg=BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
		bg=new BaseSpirit(bm_bg);
		//初始化地板
		Bitmap bm_floor=BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
		floor=new BaseSpirit(bm_floor);
		//gameTitle
		Bitmap bm_gameTitle=BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);
		logo=new BaseSpirit(bm_gameTitle);
		//开始菜单的文字图片
		Bitmap bm_menuTextIcon=BitmapFactory.decodeResource(context.getResources(), R.drawable.getready);
		getReady=new BaseSpirit(bm_menuTextIcon);
		//开始菜单的图片
		Bitmap bm_menuStartIcon=BitmapFactory.decodeResource(context.getResources(), R.drawable.tap);
		tap=new BaseSpirit(bm_menuStartIcon);
		//初始化鸟
		int[] drawableIds=new int[]{R.drawable.bird_1,R.drawable.bird_2,R.drawable.bird_3};
		Bitmap[] bitmaps=new Bitmap[drawableIds.length];
		for(int i=0;i<drawableIds.length;i++){
			bitmaps[i]=BitmapFactory.decodeResource(context.getResources(), drawableIds[i]);
		}
		bird=new BaseSpirit(bitmaps);
		bm_pipeTop = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
		pipeTop1 = new BaseSpirit(bm_pipeTop);
		bm_pipeBottom = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
		pipeBottom1 = new BaseSpirit(bm_pipeBottom);
		pipeTop2 = pipeTop1.cloneSelf();
		pipeBottom2 = pipeBottom1.cloneSelf();
		Bitmap bm_over = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
		gameOver = new BaseSpirit(bm_over);
		Bitmap bm_panel = BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
		panel = new BaseSpirit(bm_panel);
	}
	
	
	@Override
	public void run() {
		super.run();
		while(isStart){
			Canvas canvas=null;
			try {
				canvas=sfh.lockCanvas();
				if(canvas!=null){
					drawDeals(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(canvas!=null){
					sfh.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	/**
	 * 处理要画的内容
	 * @param status
	 */
	private void drawDeals(Canvas canvas) {
		//画几个状态共同的内容
		canvas.drawBitmap(bg.getCurBitmap(), null, new Rect(0, 0, w, h), null);
		//画地板
		canvas.drawBitmap(floor.getCurBitmap(), null, new Rect(0, h*3/4, w, h), null);
		switch (status) {
		case GAMEMENU:
			logo.drawSelf(canvas);
			bird.drawSelf(canvas);
			getReady.drawSelf(canvas);
			tap.drawSelf(canvas);
			break;
		case GAMEPLAYING:
			drawGamePlayingView(canvas);
			break;
		case GAMEOVER:
			gameOver.drawSelf(canvas);
			panel.drawSelf(canvas);
			canvas.drawText("您的得分："+score, (w-panel.getWidth())/2+panel.getWidth()/8, (h-panel.getHeight())/2+panel.getHeight()*7/12, paint);
			break;
		default:
			break;
		}
	}

	/**
	 * 画游戏中的图片
	 * @param canvas
	 */
	private void drawGamePlayingView(Canvas canvas) {
		if (!isDrop) {
			//这里让管子动起来，故需要加上速度
			pipeTop1.setX(pipeTop1.getX() + pipeTop1.getSpeedX());
			pipeTop2.setX(pipeTop2.getX() + pipeTop1.getSpeedX());
		}
			pipeTop1.setCurBitmap(Bitmap.createBitmap(bm_pipeTop, 0,
					bm_pipeTop.getHeight() - pipeTopHeight1,
					bm_pipeTop.getWidth(), pipeTopHeight1));
			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
			pipeBottom1.setCurBitmap(Bitmap.createBitmap(bm_pipeBottom, 0, 0,
					bm_pipeBottom.getWidth(), h / 2 - pipeTopHeight1));
			
			pipeTop2.setCurBitmap(Bitmap.createBitmap(bm_pipeTop, 0,
					bm_pipeTop.getHeight() - pipeTopHeight2,
					bm_pipeTop.getWidth(), pipeTopHeight2));
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
			pipeBottom2.setCurBitmap(Bitmap.createBitmap(bm_pipeBottom, 0, 0,
					bm_pipeBottom.getWidth(), h / 2 - pipeTopHeight2));
			pipeTop1.drawSelf(canvas);
			pipeBottom1.drawSelf(canvas);
			pipeTop2.drawSelf(canvas);
			pipeBottom2.drawSelf(canvas);
			//判断管子是否出界，这里是循环利用管子
			checkPipeOut(pipeTop1,true);
			checkPipeOut(pipeTop2,false);
		//画鸟
		bird.setSpeedY(bird.getSpeedY()+0.98f);
		bird.setY(bird.getY()+bird.getSpeedY());
		bird.drawSelf(canvas);
		//判断鸟是否撞死
		if(bird.getY()<=0||isBirdCrashPipe(pipeTop1.getRectf())||isBirdCrashPipe(pipeTop2.getRectf())||isBirdCrashPipe(pipeBottom1.getRectf())||isBirdCrashPipe(pipeBottom2.getRectf())){
			isDrop=true;
		}
		//判断鸟事碰到地板
		if(bird.getY()+bird.getHeight()>=3*h/4){
			bird.setY(floor.getY());
			setStatus(GAMEOVER);
		}
		//判断鸟是否穿过第一对管子
		if(pipeTop1.getX()+pipeTop1.getWidth()<bird.getX()&&bird.getX()-(pipeTop1.getX()+pipeTop1.getWidth())<=-pipeTop1.getSpeedX()){
			score++;
		}
		//判断鸟是否穿过第一对管子
		if(pipeTop2.getX()+pipeTop2.getWidth()<bird.getX()&&bird.getX()-(pipeTop2.getX()+pipeTop2.getWidth())<=-pipeTop1.getSpeedX()){
			score++;
		}
		//如果超过20分，管子移动速度加快，这里给个加速度
		if(score>=20){
			pipeTop1.setSpeedX(pipeTop1.getSpeedX()-0.8f);
		}
	}

	/**
	 * 判断如果管子出界，让管子再次回到
	 * @param pipeTop12
	 */
	private void checkPipeOut(BaseSpirit pipe,boolean isFirst) {
		if(pipe.getX()<=-pipe.getWidth()){
			pipe.setX(w);
			if(isFirst){
				pipeTopHeight1=(int) (h*(Math.random()*2+1)/8);
			}else{
				pipeTopHeight2=(int) (h*(Math.random()*2+1)/8);
			}
		}
	}

	/**
	 * 碰撞检测
	 * @param rectf
	 * @return
	 */
	private boolean isBirdCrashPipe(RectF rectf) {
		if(bird.getRectf().intersect(rectf)){
			return true;
		}
		return false;
	}

}
