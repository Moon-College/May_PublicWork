package com.example.parabola;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.ValueAnimator;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView iv = (ImageView) findViewById(R.id.iv);
		// rotateyAnimRun(iv);
		// parabolaAnimRun(iv,"x",0,100);
		parabolaAnimRun(iv, 0, 1);

	}

	/**
	 * ��ת����
	 * 
	 * @param view
	 */
	public void rotateyAnimRun(final View view) {
		ObjectAnimator.ofFloat(view, "rotationX", 0f, 280f).setDuration(5000)
				.start();
	}

	/**
	 * ���Ŷ���
	 * 
	 * @param view
	 */
	public void scaleAnimRun(final View view) {
		ObjectAnimator anim = ObjectAnimator.ofFloat(view, "lzq", 1f, 0f, 0.5f)
				.setDuration(5000);
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(
					android.animation.ValueAnimator animation) {
				float cVal = (Float) animation.getAnimatedValue();
				view.setAlpha(cVal);
				view.setScaleX(cVal);
				view.setScaleY(cVal);

			}
		});
	}

	/**
	 * ����view��ƽ�ƶ�����������X��Y�����ƽ��
	 * 
	 * @param view
	 */
	public void translateAnimRun(final View view, final String orientation,
			float from, float to) {

		ValueAnimator anim = ValueAnimator.ofFloat(from, to);
		anim.setTarget(view);
		anim.setDuration(2000);
		// AccelerateInterpolator һ�����������ٶȿ�ʼ�����ظı�Ȼ����١�
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();

		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (orientation.equals("y")) {
					view.setTranslationY((Float) animation.getAnimatedValue());
				} else {
					view.setTranslationX((Float) animation.getAnimatedValue());
				}

			}
		});
	}

	/**
	 * ������
	 * 
	 * @param view
	 * @param orientation
	 * @param from
	 * @param to
	 */
	public void parabolaAnimRun(final View view,
			float from, float to) {

		ValueAnimator anim = ValueAnimator.ofFloat(from, to);
		anim.setTarget(view);
		anim.setDuration(2000);
		// AccelerateInterpolator һ�����������ٶȿ�ʼ�����ظı�Ȼ����١�
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();

		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();// value��ֵ��Χ��0-1
				view.setTranslationX(40 * value * 3);// ���������þ��ᣬ��������ʽ�������������ߵ�x��yλ�Ƽ��㹫ʽ��
				view.setTranslationY(0.5f * 40 * value * 3 * value * 3);

			}
		});
	}

}
