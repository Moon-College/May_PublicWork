package com.decent.decenttouchevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;

public class DecentTouchEventActivity extends Activity {

	private static final String TAG = "DecentTouchEventActivity";
	/*
	 * 各个控件
	 */
	private ScrollView sl;
	// private com.decent.decenttouchevent.view.MyLinearLayout ll;
	private LinearLayout ll;
	private TextView tv;
	private ListView lv;

	/*
	 * 上一次touch到List的Y坐标
	 */
	private float preTouchYOnLv;

	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	/*
	 * 省长、市长、县长的三个的onTouchListener
	 */
	private LvOntouchListener lvol;
	private LlOntouchListener llol;
	private TvOntouchListener tvol;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentTouchEventActivity.class.getName(),
				com.decent.decenttouchevent.R.class.getName(), this);

		/*
		 * 设置省长、市长、县长的三个 onTouchListener
		 */

		llol = new LlOntouchListener();
		ll.setOnTouchListener(llol);
		tvol = new TvOntouchListener();
		tv.setOnTouchListener(tvol);

		for (int i = 0; i < 20; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item" + (i + 1));
			data.add(map);
		}

		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.lv_item,
				new String[] { "item" }, new int[] { R.id.item });

		lv.setAdapter(sa);
		lvol = new LvOntouchListener();
		lv.setOnTouchListener(lvol);
	}

	/**
	 * 最外层的省长 RelativeLayout的onTouchListener
	 * ListView的
	 * @author yanqiang
	 * 
	 */
	private class LvOntouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			// TODO Auto-generated method stub
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				DecentLogUtil.d(TAG, "ListView ACTION_DOWN");
				preTouchYOnLv = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				DecentLogUtil.d(TAG, "ListView ACTION_MOVE");
				float nowY = event.getY();
				DecentLogUtil.d(
						TAG,
						"lv.getLastVisiblePosition()="
								+ lv.getLastVisiblePosition()
								+ ",lv.getChildCount()=" + lv.getCount());

				/*
				 * 使用requestDisallowInterceptTouchEvent请求上级（scrollView）秘书开启还是拦截事件
				 * 1、如果当前滑到最底部，并且用户还在继续往上滑，交给上级scrollView处理
				 * 2、如果当前在最顶部，并且用户还在往下滑，交给上级scrollView处理
				 * 3、其他时候List自己处理
				 */
				if (lv.getLastVisiblePosition() == (lv.getCount() - 1)
						&& nowY < preTouchYOnLv) {
					DecentLogUtil.d(TAG, "ListView at the bottom");
					sl.requestDisallowInterceptTouchEvent(false);
				} else if (lv.getFirstVisiblePosition() == 0
						&& nowY > preTouchYOnLv) {
					sl.requestDisallowInterceptTouchEvent(false);
					DecentLogUtil.d(TAG, "ListView at the top");
				} else {
					sl.requestDisallowInterceptTouchEvent(true);
				}
				preTouchYOnLv = nowY;
				break;
			case MotionEvent.ACTION_UP:
				DecentLogUtil.d(TAG, "ListView ACTION_UP");
				break;
			default:
				break;
			}

			/*
			 * 需要调用super的onTouchEvent才能够产生滑动的效果
			 */
			return DecentTouchEventActivity.this.onTouchEvent(event);
		}

	}

	/**
	 * 中间层市长LinearLayout的OnTouchlistener
	 * 
	 * @author yanqiang
	 * 
	 */
	private class LlOntouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			// TODO Auto-generated method stub
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				DecentLogUtil.d(TAG, "LinearLayout ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				DecentLogUtil.d(TAG, "LinearLayout ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				DecentLogUtil.d(TAG, "LinearLayout ACTION_UP");
				break;
			default:
				break;
			}
			return false;
		}

	}

	/**
	 * 最里层县长TextView的OnTouchListener
	 * 
	 * @author yanqiang
	 * 
	 */
	private class TvOntouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			// TODO Auto-generated method stub
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				DecentLogUtil.d(TAG, "TextView ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				DecentLogUtil.d(TAG, "TextView ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				DecentLogUtil.d(TAG, "TextView ACTION_UP");
				break;
			default:
				break;
			}
			return false;
		}
	}
}