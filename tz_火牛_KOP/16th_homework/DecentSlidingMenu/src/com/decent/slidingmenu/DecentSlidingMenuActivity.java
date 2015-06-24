package com.decent.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.decent.slidingmenu.view.DecentSlidingBarView;

public class DecentSlidingMenuActivity extends Activity implements
		OnTouchListener {
	private static final String TAG = "DecentSlidingMenuActivity";
	private DecentSlidingBarView dsv;
	private Handler mHandler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dsv = (DecentSlidingBarView) findViewById(R.id.hsv);
		dsv.setOnTouchListener(this);
		/*
		 * ʹ��mHandler��ʾһ�������Ϊ�˼���horizontalScrollView���Ե�Ӱ��
		 */
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				int x = (Integer) msg.obj;
				/*
				 * ʹ��smoothScrollToҲ��Ϊ�˼���horizontalScrollView���Ե�Ӱ��
				 */
				dsv.smoothScrollTo(x, 0);
			}

		};
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		dsv.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			/*
			 * ͨ��getScrollX��ȡ���ڻ����˶��� ��ACTION_UP��̧������ʱ���ж�Ӧ��scrollTo
			 * menu����content
			 */

			int nowScrollX = dsv.getScrollX();
			/*
			 * ���ݵ�ǰ�����ľ�����������Ҫsroll�ľ���
			 */
			if (nowScrollX < dsv.getMenuWidth() / 2) {
				Message msg = mHandler.obtainMessage(0, 0);
				mHandler.sendMessage(msg);
			} else if (dsv.getMenuWidth() > nowScrollX
					&& nowScrollX > dsv.getMenuWidth() / 2) {
				Message msg = mHandler.obtainMessage(0,
						(int) dsv.getMenuWidth());
				mHandler.sendMessage(msg);
				// dsv.scrollTo((int) dsv.getMenuWidth(), 0);
			} else {
				// dsv.scrollTo((int) dsv.getMenuWidth(), 0);
			}
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub
		return true;
	}
}