package com.example.valueanimator.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.valueanimator.R;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout chat; // ��Ҫ������View
	
	private ImageView eyes; // �۾�
	
	private float scrollY; //���µ�Y����
	
	private float roallY; //��ʵ�ƶ��ľ���
	
	private float chatHeight; //View��Y����

	private int screenHeight; //��Ļ�߶�

	private float chatScrollY;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initeView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initeView() {
		
		//��
		chat = (RelativeLayout) findViewById(R.id.rl2);
		eyes = (ImageView) findViewById(R.id.iv_eye);
		
		//���¼�
		chat.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		chatHeight = chat.getY(); //��ȡ��ǰview��Y����
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//����ʱ
			
			scrollY = event.getY(); //��¼���µ�Y����
			
			break;
		case MotionEvent.ACTION_MOVE:
			//�ƶ�ʱ
			
			float distance = event.getY() - scrollY; //������ǵľ���
			
			roallY = (float) (distance * 0.6); //�������
			
			ViewHelper.setTranslationY(chat, roallY); //Ϊ���View���ö���
			
			break;
		case MotionEvent.ACTION_UP:
			//�ɿ�ʱ
			//�ж��ƶ��ľ����Ƿ񳬹����۾��ĸ߶�
			if(roallY > eyes.getHeight()+80){
			screenHeight = getScreenHeight(); //��ȡ��Ļ�߶�
			chatScrollY = screenHeight - chatHeight; //��Ļ�߶� - view��Y���꣬������Ҫ�ƶ��ľ���
			ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(500);
			animator.start();
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator arg0) {
					float value = Float.valueOf(arg0.getAnimatedValue().toString()); //���ÿʱ��ε�
					float y = chatHeight + (chatScrollY * value); //���ÿһ��ʱ����Ҫ�ƶ���Y����
					chat.setY(y); //����Y����
				}
			});
			}else{
				//��view���ص�ԭ����λ��
				ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(500);
				animator.start();
				animator.addUpdateListener(new AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator arg0) {
						float value = Float.valueOf(arg0.getAnimatedValue().toString()); //ȡ��ûһ��ռ�ָ���ֵ
						float y = chatHeight - (roallY * value); //���ÿһ����Ҫ�ƶ�����
						chat.setY(y); //�����view���������ƶ�
					}
				});
			}
			break;
		default:
			break;
		}
		return true;
	}
	/**
	 * ��ȡ��Ļ�߶�
	 * @return
	 */
	public int getScreenHeight(){
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

}
