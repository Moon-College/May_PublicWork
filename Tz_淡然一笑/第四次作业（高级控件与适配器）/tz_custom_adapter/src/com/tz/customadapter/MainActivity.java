package com.tz.customadapter;

import java.util.ArrayList;
import java.util.List;
import com.tz.customadapter.adapter.MyAdapter;
import com.tz.customadapter.bean.Student;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<Student> data;
	private ListView lv;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ʼ������
		initData();
		// ��ʼ���ؼ����¼�����
		initView();
	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		data = new ArrayList<Student>(); // ��ʼ������
		// ͼ��
		int[] imgs = new int[] { R.drawable.stu1, R.drawable.stu9, R.drawable.stu2, R.drawable.stu3, R.drawable.stu10, R.drawable.stu4,
				R.drawable.stu5, R.drawable.stu6, R.drawable.stu11, R.drawable.stu7, R.drawable.stu8, R.drawable.stu12 };
		// ����
		String[] netNames = new String[] { "����", "��ڣ", "��ѩ", "��ĺ", "����", "짾�", "���", "����", "Ц����ɽ", "������ӵ", "��֮����", "�̲�����" };
		// �Ա�
		String[] sexs = new String[] { "Ů", "��", "Ů", "Ů", "��", "Ů", "Ů", "Ů", "��", "Ů", "Ů", "��" };
		// ����
		String[] hobbys = new String[] { "��ë��,��Ӱ", "����", "����", "��Ӱ", "��Ӿ,����", "���", "����", "����Ϸ", "����,����", "����", "������", "��ѩ,����" };

		for (int i = 0; i < 12; i++) {
			Student stu = new Student(); // ÿѭ��һ�δ���һ��ѧ��
			stu.setFaceImg(imgs[i]);
			stu.setNetName(netNames[i]);
			stu.setSex(sexs[i]);
			stu.setHobby(hobbys[i]);
			data.add(stu);
		}
	}

	/**
	 * ��ʼ���ؼ����¼�����
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		adapter = new MyAdapter(this, data); // ������
		lv.setAdapter(adapter); // ����������
		lv.setOnItemClickListener(this); // �¼�����
	}

	/**
	 * ListView��Ŀ����¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// ���ListView���Ƴ�ѡ�е���Ŀ
		data.remove(position);
		// ˢ��
		adapter.notifyDataSetChanged();
	}
}
