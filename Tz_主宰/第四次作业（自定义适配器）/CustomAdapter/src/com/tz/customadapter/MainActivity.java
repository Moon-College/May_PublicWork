package com.tz.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.tz.customadapter.adapter.CustomAdapter;
import com.tz.customadapter.consts.Sex;
import com.tz.customadapter.vo.People;

public class MainActivity extends Activity {
	private static int[] avatars;
	private static String[] names;
	private static Sex[] sexes;
	private static String[][] hobbies;

	static {
		// ����ͷ������
		avatars = new int[] { R.drawable.avatar1, R.drawable.avatar2,
				R.drawable.avatar3, R.drawable.avatar4, R.drawable.avatar5,
				R.drawable.avatar6, R.drawable.avatar7, R.drawable.avatar8,
				R.drawable.avatar9, R.drawable.avatar10 };
		names = new String[] { "Grace", "David", "Danny", "Jone", "Luce",
				"Lilei", "Hanmei", "James", "Allen", "Pake" };

		// �����Ա�
		sexes = new Sex[] { Sex.MAN, Sex.MAN, Sex.WOMEN, Sex.MAN, Sex.MAN,
				Sex.WOMEN, Sex.WOMEN, Sex.MAN, Sex.MAN, Sex.WOMEN };

		// ��Ȥ
		hobbies = new String[][] { { "����", "����" }, { "����" }, { "����", "��Ϸ" },
				{ "���" }, { "����", "����" } };
	}

	private ListView listView;
	private CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// �����Զ���adapter
		adapter = new CustomAdapter(this, new ArrayList<People>());
		
		// ��ʼ��listview
		listView = (ListView) findViewById(R.id.main_listview);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
		listView.setAdapter(adapter);
		
		// ��̬��������
		adapter.addItems(requestData());
		adapter.notifyDataSetChanged();
		
		// ���»�ȡ���ݰ�ť
		Button resetData = (Button) findViewById(R.id.main_resetdate);
		resetData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��̬��������
				adapter.clear();
				adapter.addItems(requestData());
				adapter.notifyDataSetChanged();
			}
		});
	}

	// ��ȡ����
	public List<People> requestData() {
		List<People> peoples = new ArrayList<People>();
		for (int i = 0; i < names.length; i++) {
			peoples.add(new People(avatars[i], names[i], sexes[i],
					hobbies[i % 2], 90 + i));
		}
		return peoples;
	}
}
