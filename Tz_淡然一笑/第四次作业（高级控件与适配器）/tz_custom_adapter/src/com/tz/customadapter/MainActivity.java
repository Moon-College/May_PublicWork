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

		// 初始化数据
		initData();
		// 初始化控件和事件监听
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		data = new ArrayList<Student>(); // 初始化数据
		// 图像
		int[] imgs = new int[] { R.drawable.stu1, R.drawable.stu9, R.drawable.stu2, R.drawable.stu3, R.drawable.stu10, R.drawable.stu4,
				R.drawable.stu5, R.drawable.stu6, R.drawable.stu11, R.drawable.stu7, R.drawable.stu8, R.drawable.stu12 };
		// 网名
		String[] netNames = new String[] { "璃茉", "青冢", "淋雪", "矜暮", "凉音", "歆久", "瑾澜", "眸敛", "笑望江山", "荒世独拥", "得之吾幸", "烟不离手" };
		// 性别
		String[] sexs = new String[] { "女", "男", "女", "女", "男", "女", "女", "女", "男", "女", "女", "男" };
		// 爱好
		String[] hobbys = new String[] { "羽毛球,电影", "音乐", "滑冰", "电影", "游泳,篮球", "逛街", "旅游", "玩游戏", "足球,音乐", "篮球", "兵乓球", "滑雪,滑冰" };

		for (int i = 0; i < 12; i++) {
			Student stu = new Student(); // 每循环一次创建一个学生
			stu.setFaceImg(imgs[i]);
			stu.setNetName(netNames[i]);
			stu.setSex(sexs[i]);
			stu.setHobby(hobbys[i]);
			data.add(stu);
		}
	}

	/**
	 * 初始化控件和事件监听
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		adapter = new MyAdapter(this, data); // 适配器
		lv.setAdapter(adapter); // 设置适配器
		lv.setOnItemClickListener(this); // 事件监听
	}

	/**
	 * ListView条目点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 点击ListView，移除选中的条目
		data.remove(position);
		// 刷新
		adapter.notifyDataSetChanged();
	}
}
