package com.qfx.composite;

import java.util.ArrayList;
import java.util.List;

import com.qfx.composite.adapter.FriendListAdapter;
import com.qfx.composite.mode.Friend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView lv;
	private FriendListAdapter adapter;
	private List<Friend> data;
	private static final int[] drawables = new int[] {
		R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
		R.drawable.photo4, R.drawable.photo5, R.drawable.photo6,
		R.drawable.photo7, R.drawable.photo8, R.drawable.photo9,
		R.drawable.photo10, R.drawable.photo11, R.drawable.photo12,
		R.drawable.photo13, R.drawable.photo14
	};
	
	private static final String[] nicknames = new String[] {
		"Grace", "Danny", "David", "Jason", "影子MM", "Eyre",
		"子漠老师", "瑶瑶MM", "轩儿MM", "学渣", "蓝天", "宁采臣",
		"binbin", "Rocy"
	};
	
	private static final String[] sexes = new String[] {
		"女", "男", "男", "男", "女", "男",
		"男", "女", "女", "男", "男", "男",
		"男", "男"
	};
	
	//颜值，满值100
	private static final String[] faceValues = new String[] {
		"98", "98", "96", "95", "97", "96",
		"92", "96", "93", "65.5", "67.5", "66",
		"72", "70"
	};
	
	private static final String[] hobbys = new String[] {
		"自拍", "臭美", "飙车", "弹吉他", "打扮", "反唱小品",
		"刷微信", "跳舞", "谈恋爱", "偷窥", "幻想", "打游戏",
		"装大师", "打游戏"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView() {
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		data = new ArrayList<Friend>();
		Friend friend = null;
		for (int i = 0; i < drawables.length; i++) {
			friend = new Friend();
			friend.setPhoto(drawables[i]);
			friend.setNickname(nicknames[i]);
			friend.setSex(sexes[i]);
			friend.setFacevalue(faceValues[i]);
			friend.setHobby(hobbys[i]);
			data.add(friend);
		}
		adapter = new FriendListAdapter(this, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				data.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
	}

}
