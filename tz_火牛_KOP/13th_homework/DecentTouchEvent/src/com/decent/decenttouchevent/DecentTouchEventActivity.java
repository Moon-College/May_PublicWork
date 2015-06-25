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
	 * �����ؼ�
	 */
	private ScrollView sl;
	// private com.decent.decenttouchevent.view.MyLinearLayout ll;
	private LinearLayout ll;
	private TextView tv;
	private ListView lv;

	/*
	 * ��һ��touch��List��Y����
	 */
	private float preTouchYOnLv;

	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	/*
	 * ʡ�����г����س���������onTouchListener
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
		 * ����ʡ�����г����س������� onTouchListener
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
	 * ������ʡ�� RelativeLayout��onTouchListener
	 * ListView��
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
				 * ʹ��requestDisallowInterceptTouchEvent�����ϼ���scrollView�����鿪�����������¼�
				 * 1�������ǰ������ײ��������û����ڼ������ϻ��������ϼ�scrollView����
				 * 2�������ǰ������������û��������»��������ϼ�scrollView����
				 * 3������ʱ��List�Լ�����
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
			 * ��Ҫ����super��onTouchEvent���ܹ�����������Ч��
			 */
			return DecentTouchEventActivity.this.onTouchEvent(event);
		}

	}

	/**
	 * �м���г�LinearLayout��OnTouchlistener
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
	 * ������س�TextView��OnTouchListener
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