package com.cn.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cn.test.adapter.MyAdapter;
import com.cn.test.been.Student;

public class MainActivity extends Activity {
	ListView lv;
	List<Student> data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		lv = (ListView) this.findViewById(R.id.list);
		data = new ArrayList<Student>();
		String[] names = new String[] { "binbin", "大西", "Grace", "Dany", "轩儿",
				"杀死凯", "火牛", "蓝天", "瑶瑶", "Sundy" };
		int[] faces = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
				R.drawable.h, R.drawable.j, R.drawable.k };
		String[] handSome = new String[] { "颜值：80", "颜值：90","颜值：100", "颜值：80","颜值：90","颜值：80",
				"颜值：80","颜值：80","颜值：100", "颜值：80"};
		String hobbys[] = new String[] { "爱好：算命", "爱好：吹牛", "爱好：卖萌", "爱好：打码",
				"爱好：逛街", "爱好：打码", "爱好：篮球", "爱好：足球", "爱好：唱歌", "爱好：跑步" };
		String sexs[] = new String[] { "男", "男", "女", "男", "女", "男", "男", "男",
				"女", "男" };
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setStu_face(faces[i]);
			student.setStu_name(names[i]);
			student.setStu_sex(sexs[i]);
			student.setStu_color(handSome[i]);
			student.setStu_hobby(hobbys[i]);
			data.add(student);
		}

		final MyAdapter adapter = new MyAdapter(MainActivity.this, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				data.remove(position);
				adapter.notifyDataSetChanged();

			}
		});
	}

}