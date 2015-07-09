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
 * ����
 * 
 * @author fcc
 * 
 */
public class Drawer extends Thread {

	// ���һ���ʱ��Ҫ�Ļ���,����,ͼ��
	private Context context;
	private SurfaceHolder holder; //����SurfaceView�Ĺܼ�
	private int w, h; //��Ļ���ǻ����,��
	private Paint paint; //����
	private boolean isStart;
//	private Spirit bird; //����
	
	//����  ����,�ذ�
	private Spirit bg, floor, bird, getReady, tap, pipeTop1, pipeTop2, pipeBottom1, pipeBottom2, gameOver, panel;;

	private int pipeSpan; //���ӵļ��
	private int pipeTopHeight1, pipeTopHeight2; //�����������ӵĸ߶�

	private int gameStatus; //��Ϸ��״̬
	
	public static final int READY = 0; //��Ϸ��ʼ
	public static final int RUNNING = 1; //��Ϸ����
	public static final int OVER = 2; //��Ӿ����
	
	private Bitmap pipeTopBm, pipeBottomBm; //����Ĺ��Ӻ�����Ĺ���
	
	private boolean isDrop; //��ʶ���Ƿ���׹
	private final int PIPE_SPEED = -5; //���ӵ��ٶ�,���Ӵ�������Ϊ����
	
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
		
		//������Ϸ�ľ���
		initSpirits();
		//��ʼ�����������
		initAttrs();
	}

	/**
	 * ������Ϸ�ľ���
	 */
	public void initSpirits() {
		Bitmap bm_bg = iBitmap(R.drawable.bg1);  //����ͼƬ
		bg = new Spirit(bm_bg);
		Bitmap bm_floor = iBitmap(R.drawable.floor); //�ذ� 1/4
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
		
		pipeTop2 = pipeTop1.getCloneObject(); //��¡pipeTop1
		pipeBottom2 = pipeBottom1.getCloneObject();  //��¡pipeBottom1
		
		Bitmap bm_over = iBitmap(R.drawable.gameover);
		gameOver = new Spirit(bm_over);
		
		Bitmap bm_panel = iBitmap(R.drawable.panel);
		panel = new Spirit(bm_panel);

	}
	
	/**
	 * ��ʼ������������ٶȵ�����
	 */
	public void initAttrs() {
		isDrop = false;
		score = 0;
		pipeSpan = h/4;//���ӵļ��
		pipeTopHeight1 = h/4;
		pipeTopHeight2 = h/3;//��ʼֱ�����������������ӵĸ�Ϊ�̶�ֵ
		getReady.setX((w-getReady.getWidth())/2);  //�м�
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
	 * ����ͼƬid����ͼƬ
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
		// ִ�л滭������
		while (isStart) {
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas(); // ��������
				if (canvas != null) {
					// ������ʼִ�л滭
					// canvas.drawText("���쿪ʼ���Ʒ�ŭ��С�����Ϸ", 100, 100, paint);
					// bird.drawSelf(canvas); //����
					drawGameView(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas); // ��������
				}
			}

		}
	}

	/**
	 * ͨ����Ϸ״̬���Ʋ�ͬ�Ľ���
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//�Ȼ滭��Ϸ����״̬����Ҫ�ı�����ͼ�͵ذ�
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
//			canvas.drawText("�Ʒ�:"+score, 200, 200, paint);
			break;

		default:
			break;
		}
	}

	private void drawGameReadyView(Canvas canvas) {
		//������Ϸ��ʼ����ͼ
		getReady.drawSelf(canvas);  //����ready
		tap.drawSelf(canvas);  //����tap
		bird.drawSelf(canvas); //������
	}
	
	private void drawGameRunningView(Canvas canvas) {
		//������Ϸ���е���ͼ
		//���ƹ���,���ù����˶�����
		if(!isDrop){   
			pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);
	//		pipeTop1.setY(0);
			//����ü���������һ����,�ü����ӵĲ�����Bitmap.createBitmap()����
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
			
	//		pipeBottom1.setX(pipeBottom1.getX()+PIPE_SPEED);
			pipeBottom1.setX(pipeTop1.getX());  //��pipeTop1����һ��
			pipeBottom1.setY(pipeTopHeight1+pipeSpan);
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight1));
			
			pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);
	//		pipeTop2.setY(0);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
		
			pipeBottom2.setX(pipeTop2.getX());  //��pipeTop2����һ��
			pipeBottom2.setY(pipeTopHeight2+pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight2));
		}
		
		//��,�������λ��
		bird.setySpeed(bird.getySpeed()+1.3f); //���ٶ�
		bird.setY(bird.getY()+bird.getySpeed());
		if(bird.getY()<=0||birdHitPipe(pipeTop1)||birdHitPipe(pipeBottom1)||birdHitPipe(pipeTop2)||birdHitPipe(pipeBottom2)){
			//��ײ����
			isDrop = true;
		}
		if(bird.getY()+bird.getHeight()>=3*h/4){
			gameStatus = OVER;
			bird.setY(3*h/4 - bird.getHeight());
		}
		
		//���ӳ������Ժ����ù��ӻص��ұ�
		checkOutBound(pipeTop1,true);
		checkOutBound(pipeTop2, false);
		
		/**
		 * �ж����Ƿ񴩹��˹���,������һ��
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
		
		//���ƹ���
		pipeTop1.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		
		bird.drawSelf(canvas); //����
	}
	
	/**
	 * ��ײ���
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
			//���ӳ�ȥ��
			pipe.setX(w); //�ص��ұ�
			if(isFirst){
				pipeTopHeight1 = (int) (h*(Math.random()*2+1)/8); // 1/8-3/8
			}else{
				pipeTopHeight2 = (int) (h*(new Random().nextFloat()*2+1)/8); // 1/8-3/8
			}
		}
	}

	private void drawGameOverView(Canvas canvas) {
		//������Ϸ��������ͼ
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
