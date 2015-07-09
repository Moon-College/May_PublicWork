package com.vincen.customeradapter;

import java.util.LinkedList;
import java.util.List;

import com.vincen.customeradapter.adapter.MyAdapter;
import com.vincen.customeradapter.bean.Student;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lvStudents;
	private List<Student> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
		
		final MyAdapter adapter = new MyAdapter(data, this);
		lvStudents.setAdapter(adapter);
		
		lvStudents.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				data.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	private void initData() {
		int[] images = new int[]{
				R.drawable.danny,
				R.drawable.grace,
				R.drawable.jason,
				R.drawable.snowj,
				R.drawable.vincen
		};
		String[] names = new String[]{
				"danny","grace","jason","snowj","vince"
		};
		String[] sexs = new String[]{
				"ÄÐ","Å®","ÄÐ","ÄÐ","ÄÐ"
		};
		String[] hobbies = new String[]{
				"³ª¸è","ÌøÎè","ÓÎÓ¾","Áï±ù","³ª¸è"
		};
		String[] colorValues = new String[]{
				"90","89","90","88","90"
		}; 
		
		for(int i=0;i<names.length;i++){
			Student stu = new Student();
			stu.setImage(images[i]);
			stu.setName(names[i]);
			stu.setSex(sexs[i]);
			stu.setHobbies(hobbies[i]);
			stu.setColorValue(colorValues[i]);
			
			data.add(stu);
		}
		
		
	}

	private void initView() {
		lvStudents = (ListView) findViewById(R.id.lv_students);
		data = new LinkedList<Student>();
	}

}
