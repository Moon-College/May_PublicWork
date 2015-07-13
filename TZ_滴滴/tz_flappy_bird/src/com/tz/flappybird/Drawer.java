package com.tz.flappybird;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;

public class Drawer extends Thread {
	private Context context;
	private SurfaceHolder holder;
	
	private int w,h;              //����Ŀ�ߣ�Ҳ���ֻ���Ļ�Ŀ�ߣ�
	private Paint paint;
	private boolean isStart;
	
	private Bitmap pipeTopBm;      //�Ϲ���ͼƬ
	private Bitmap pipeBottomBm;   //�¹���ͼƬ
	
	private int pipeSpan;                      //���¹��ӵĿ�϶���
	private int pipeTopHeight1,pipeTopHeight2; //�����������ӵĸ߶�
	//����
	private Spirit bg,floor,bird,getReady,tap,pipeTop1,pipeTop2,pipeBottom1,pipeBottom2,gameOver,panel;
	
	public Spirit getBird() {
		return bird;
	}

	public void setBird(Spirit bird) {
		this.bird = bird;
	}

	//��Ϸ�Ľ׶�
	private int gameStatu;
	public void setGameStatu(int gameStatu) {
		this.gameStatu = gameStatu;
	}

	public int getGameStatu() {
		return gameStatu;
	}

	public static final int READY = 0;
	public static final int RUNNING = 1;
	public static final int OVER = 2;

	private boolean isDrop; //���Ƿ�׹��
	private final int PIPE_SPEED = -20; //������X�����ƶ����ٶ�
	private int score;
	public boolean isDrop() {
		return isDrop;
	}

	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
	public Drawer(Context context,SurfaceHolder holder,int w,int h){
		this.context = context;
		this.holder = holder;
		this.w = w;
		this.h = h;
		paint = new Paint();  //����
		paint.setColor(Color.BLACK);
		paint.setTextSize(50);
		
		isStart = true;
		
		//������Ϸ�ľ���
		initSpirits();
		//��ʼ����������
		initAttrs();
	}


	/**
	 * ��ʼ������������ٶȵ�����
	 */
	public void initAttrs() {
		
		isDrop = false;
		score = 0;
		
		pipeSpan = h/4;  //���¹��ӵĿ�϶���
		pipeTopHeight1 = h/4;  //�����һ�����ӵĸ߶�
		pipeTopHeight2 = h/3;  //����ڶ������ӵĸ߶�  ��Ҳ������Ϊ����ģ�
		
		getReady.setX((w - getReady.getWidth())/2);
		getReady.setY(h/3);
		
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		
		bird.setX(w/4);
		bird.setY(h/2);
		bird.setySpeed(2);
		
		pipeTop1.setX(w);
		pipeTop1.setY(0);
		
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
		
		pipeTop2.setX(3*w/2);
		pipeTop2.setY(0);
		
		pipeBottom2.setX(3*w/2);
		pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
		
		gameOver.setX((w-gameOver.getWidth())/2);
		gameOver.setY(h/4);
		
		panel.setX((w-panel.getWidth())/2);
		panel.setY((h-panel.getHeight())/2);		
	}

	/**
	 * ������Ϸ����
	 */
	private void initSpirits() {
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
		
		//ִ�л滭����
		while (isStart) {
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas !=null) {
//					canvas.drawText("���ӷ�����", 100, 110, paint);
//					bird.drawSelf(canvas);
					drawGameView(canvas);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas); //��������
				}
			}
			
		}
	}

	/**
	 * ͨ����Ϸ״̬���Ʋ�ͬ����
	 * @author wangyanfei
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//���Ƹ����׶ξ��еĻ��棨��ͼ���ذ壩
		paint.setTextSize(50);
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, w, h), paint);
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, 3*h/4, w, h), paint);
		canvas.drawText("�÷֣�"+String.valueOf(score), 130, 100, paint);
		paint.setTextSize(30);
		//paint.setColor(Color.GREEN);
		canvas.drawText("���ӷ�����", 165, 40, paint);
		switch (gameStatu) {
		case READY:
			drawGameReadyView(canvas);
			break;
		case RUNNING:
			drawGameRunningView(canvas);
			break;
		case OVER:
			drawGameOverView(canvas);
			break;
		default:
			break;
		}
	}

	/**
	 * ��ϷOver�׶λ������
	 * @param canvas
	 */
	private void drawGameOverView(Canvas canvas) {
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
	}

	/**
	 * ��ϷRunning�׶λ������
	 * @param canvas
	 */
	private void drawGameRunningView(Canvas canvas) {
		//���ù��ӵ�λ��
		if (!isDrop) {
			//��1�Թ���
			pipeTop1.setX(pipeTop1.getX() + PIPE_SPEED);  //����ÿ������һ�Σ������ƶ�PIPE_SPEED��ô��ľ���
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight() - pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
			
			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2 - pipeTopHeight1));
			//��2�Թ���
			pipeTop2.setX(pipeTop2.getX() + PIPE_SPEED);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight() - pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
			
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2 - pipeTopHeight2));
		}
		
		//�������λ��
		bird.setySpeed(bird.getySpeed()+ 1.5f); //���ٶ�
		bird.setY(bird.getY() + bird.getySpeed());
		if (bird.getY() <= 0 || birdHitPipe(pipeTop1) ||birdHitPipe(pipeTop2)||birdHitPipe(pipeBottom1)||birdHitPipe(pipeBottom2)) { 
			//��ײ�ˣ��컨�����ӣ�
			isDrop = true;
		}
		
		if (bird.getY() + bird.getHeight() >= 3*h/4) {
			gameStatu = OVER;
			bird.setY(3*h/4 - bird.getHeight());
		}
		
		//���ƹ���
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		//������
		bird.drawSelf(canvas);
		
		//���ӳ������Ժ����ù��ӻص��ұ�
		checkOutBound(pipeTop1,true);  //true��ʾ��1�Թ��ӳ��磬false��ʾ��2�Թ��ӳ���
		checkOutBound(pipeTop2, false);
		
		//����÷֣��ж����Ƿ񴩹��˹��ӣ�
		int pipe1_right = (int)(pipeTop1.getX()+ pipeTop1.getWidth());
		int pipe2_right = (int)(pipeTop2.getX()+ pipeTop2.getWidth());
		if (pipe1_right <bird.getX() && bird.getX() - pipe1_right <= (-PIPE_SPEED)) {
			score ++;

			
		}
		if (pipe2_right <bird.getX() && bird.getX() - pipe2_right <= (-PIPE_SPEED)) {
			score ++;
			
		}
	}

	/**
	 * ��ײ���
	 * @return
	 */
	private boolean birdHitPipe(Spirit pipe) {
		if (bird.getSpiritRect().intersect(pipe.getSpiritRect())) {
			return true;
		}
		return false;
	}

	/**
	 * �жϹ����Ƿ����
	 * @param pipeTop
	 * @param isFirst  ��true��ʾ��1�Թ��ӳ��磬false��ʾ��2�Թ��ӳ��磩
	 */
	private void checkOutBound(Spirit pipe, boolean isFirst) {
		if (pipe.getX() + pipe.getWidth() <= 0) { 
			//���ӳ���
			pipe.setX(w); //�ص��ұ�
			if (isFirst) {  
				//����ǵ�1�Թ��ӳ���
				pipeTopHeight1 = (int)(h*(Math.random()*2+1)/8);
			}else{
				pipeTopHeight2 = (int)(h * (new Random().nextFloat()*2 +1)/8);
			}
		}
	}

	/**
	 * ��ϷReady�׶λ������
	 * @param canvas
	 */
	private void drawGameReadyView(Canvas canvas) {
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);
	}
	
	
}
