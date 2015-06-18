package com.example.weixin;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout chat;
	private float down_y;
	private float finger_y;
	private float viewRoll;
	private ImageView iv_eye;
	private int limit;
	private LinearLayout bottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ��Ϣ�б���
		chat = (RelativeLayout) findViewById(R.id.rl2);
		chat.setOnTouchListener(this);

		iv_eye = (ImageView) findViewById(R.id.iv_eye);
		bottom = (LinearLayout) findViewById(R.id.bottom);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// ����������޶���Χ
		limit = iv_eye.getHeight() + 100;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ȡ��ָ����ʱ������
			down_y = event.getY();

			break;
		// ��ָ�ƶ������У���Ϣ�б�����ƶ�
		case MotionEvent.ACTION_MOVE:
			// ��ָ�����ľ���
			finger_y = event.getY() - down_y;

			if (finger_y <= 0) {
				break;
			}
			// ������Ϣ�б����ľ��루����Ƶ�ʣ�
			viewRoll = finger_y * 0.6f;
			// chat.setY(viewRoll);
			ViewHelper.setTranslationY(chat, viewRoll);

			break;
		// �ɿ���ָ
		case MotionEvent.ACTION_UP:

			finger_y = event.getY() - down_y;

			if (finger_y <= 0) {
				break;
			}

			viewRoll = finger_y * 0.6f;
			
			//��Ϣ�б����ľ���С���޶�ֵ������ȥ
			if (viewRoll < limit) {
				//���Զ���
				//����500����ִ��һ��������ֵ0�����1
				//ֵ�ķ�Χ��ȡ������������ʲô
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);
				//��ʼ����
				valueAnimator.start();
				//�����仯����
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
							//500�����У�onAnimationUpdate�����ᱻ�����ĵ���
							@Override
							public void onAnimationUpdate(ValueAnimator va) {
								//��ȡ����ִ�еĽ���
								Float progress = Float.parseFloat(va
										.getAnimatedValue().toString());
								//��Ϣ�б��Y����
								float y = viewRoll - (progress * viewRoll);
								chat.setY(y);
							}
						});
			} else {
				//����ֱ�ӻ������ײ�
				
				//��ȡ��Ļ�ĸ߶�
				WindowManager wm = this.getWindowManager();
				int width = wm.getDefaultDisplay().getWidth();
				final int height = wm.getDefaultDisplay().getHeight();

				final float bottom_y = bottom.getY();

				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);

				valueAnimator.start();
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator va) {

								Float progress = Float.parseFloat(va
										.getAnimatedValue().toString());

								float y = viewRoll + (progress * height);
								chat.setY(y);

								bottom.setY(bottom_y + (progress * height));

							}
						});
			}

			break;

		default:
			break;
		}

		return true;
	}
}
