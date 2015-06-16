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

		// 消息列表布局
		chat = (RelativeLayout) findViewById(R.id.rl2);
		chat.setOnTouchListener(this);

		iv_eye = (ImageView) findViewById(R.id.iv_eye);
		bottom = (LinearLayout) findViewById(R.id.bottom);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 滑动距离的限定范围
		limit = iv_eye.getHeight() + 100;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 获取手指按下时的坐标
			down_y = event.getY();

			break;
		// 手指移动过程中，消息列表跟随移动
		case MotionEvent.ACTION_MOVE:
			// 手指滑动的距离
			finger_y = event.getY() - down_y;

			if (finger_y <= 0) {
				break;
			}
			// 计算消息列表滑动的距离（减低频率）
			viewRoll = finger_y * 0.6f;
			// chat.setY(viewRoll);
			ViewHelper.setTranslationY(chat, viewRoll);

			break;
		// 松开手指
		case MotionEvent.ACTION_UP:

			finger_y = event.getY() - down_y;

			if (finger_y <= 0) {
				break;
			}

			viewRoll = finger_y * 0.6f;
			
			//消息列表滑动的距离小于限定值，滑回去
			if (viewRoll < limit) {
				//属性动画
				//花了500毫秒执行一个动画，值0变成了1
				//值的范围，取决于你用它做什么
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);
				//开始动画
				valueAnimator.start();
				//监听变化过程
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
							//500毫秒中，onAnimationUpdate方法会被反复的调用
							@Override
							public void onAnimationUpdate(ValueAnimator va) {
								//获取动画执行的进度
								Float progress = Float.parseFloat(va
										.getAnimatedValue().toString());
								//消息列表的Y坐标
								float y = viewRoll - (progress * viewRoll);
								chat.setY(y);
							}
						});
			} else {
				//否则，直接滑动到底部
				
				//获取屏幕的高度
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
