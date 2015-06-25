package com.example.listview_baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview_baseadapter.adapter.ListViewAdapter;
import com.example.listview_baseadapter.bean.UserInfo;

public class MainActivity extends Activity {
	ListView lv;
	ListViewAdapter adapter;
	List<UserInfo> list;
	String names[] = { "大乔", "貂蝉", "甘宁", "关羽", "郭嘉", "黄盖", "黄月英", "华佗", "司马懿",
			"孙权", "孙尚香" };
	int heads[] = { R.drawable.daqiao, R.drawable.diaochan, R.drawable.ganning,
			R.drawable.guanyu, R.drawable.guojia, R.drawable.huanggai,
			R.drawable.huangyueying, R.drawable.huatuo, R.drawable.simayi,
			R.drawable.sunquan, R.drawable.sunshangxiang };
	String appearance[] = { "80", "98", "88", "86", "94", "81", "83", "88",
			"90", "79", "90" };
	String sex[] = { "男", "女", "男", "男", "女", "男", "女", "男", "男", "男", "女" };
	String hobbys[] = { "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉",
			"吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉", "吃饭,睡觉" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		list = new ArrayList<UserInfo>();
		for (int i = 0; i < names.length; i++) {
			UserInfo info = new UserInfo();
			info.setName(names[i]);
			info.setSex(sex[i]);
			info.setAppearance(appearance[i]);
			info.setHobbys(hobbys[i]);
			info.setHead(heads[i]);
			list.add(info);
		}
		adapter = new ListViewAdapter(this, list);
		lv.setAdapter(adapter);
		// 添加List滑动
		lv.setOnTouchListener(new OnTouchListener() {
			float x, y, upx, upy;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					x = event.getX();
					y = event.getY();
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					upx = event.getX();
					upy = event.getY();
					int position1 = ((ListView) v).pointToPosition((int) x,
							(int) y);
					int position2 = ((ListView) v).pointToPosition((int) upx,
							(int) upy);
					if (position1 == position2 && Math.abs(x - upx) > 10) {
						View view = ((ListView) v).getChildAt(position1);
						removeListItem(view, position1);
					}
				}
				return false;
			}
		});
	}

	/**
	 * 删除item，并播放动画
	 * 
	 * @param rowView
	 *            播放动画的view
	 * @param positon
	 *            要删除的item位置
	 */
	protected void removeListItem(View view, final int position1) {
		final Animation animation = (Animation) AnimationUtils.loadAnimation(
				view.getContext(), R.anim.item_anim);
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}
			public void onAnimationRepeat(Animation animation) {
			}
			public void onAnimationEnd(Animation animation) {
				list.remove(position1);
				adapter.notifyDataSetChanged();
				animation.cancel();
			}
		});
		view.startAnimation(animation);
	}
}
