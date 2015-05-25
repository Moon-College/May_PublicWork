package com.example.hightview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hightview.adapter.MyAdapter;
import com.example.hightview.beans.Student;

public class MainActivity extends Activity implements OnItemClickListener {


    List<Student> data;
    MyAdapter adapter;
    ListView lv;
	
    int [] images = new int[]{
    		R.drawable.image_01,
    		R.drawable.image_02,
    		R.drawable.image_03,
    		R.drawable.image_04,
    		R.drawable.image_05,
    		R.drawable.image_06,
    		R.drawable.image_07,
    		R.drawable.image_08,
    		R.drawable.image_09,
    		R.drawable.image_10,
    		R.drawable.image_11,
    		R.drawable.image_12
    };
    
    String [] names = new String[]{
    		"瑶瑶老师",
    		"耶世华",
    		"徐半仙",
    		"小路子",
    		"小高",
    		"小丑鱼",
    		"Grace美眉",
    		"清风啸",
    		"火牛",
    		"子漠老师",
    		"西瓜",
    		"秋棉"
    };
    
    int [] sexs = new int[]{
    		0,
    		1,
    		0,
    		0,
    		1,
    		1,
    		0,
    		0,
    		1,
    		0,
    		1,
    		0
    };
    
    int [] faceValues = new int[]{
    		100,
    		85,
    		90,
    		70,
    		65,
    		86,
    		73,
    		91,
    		23,
    		69,
    		88,
    		70
    };
    
    String [] hobbys = new String[]{
    		"爱运动",
    		"爱LOL",
    		"爱魔兽",
    		"爱篮球",
    		"爱吉他",
    		"爱美女",
    		"爱泡吧",
    		"爱跑步",
    		"爱生活",
    		"爱可乐",
    		"爱动漫",
    		"爱八卦",
    };
	private Student student;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initData();
		initView(data);
	}

	
	/**
	 * @author Json
	 * */
	private void initView(List<Student> data) {
		if(null == adapter){
			adapter = new MyAdapter(this, data);
			lv = (ListView) findViewById(R.id.studentlist);
			lv.setOnItemClickListener(this);
			lv.setAdapter(adapter);
		}else{
			adapter.notifyDataSetChanged();
		}
	}


	/**
	 * 初始化数据
	 * @author Json
	 * 
	 * */
	private void initData() {
		data = new ArrayList<Student>();
		for (int i = 0; i < names.length; i++) {
			student = new Student();
			student.setImageId(images[i]);
			student.setName(names[i]);
			student.setSex(sexs[i]);
			student.setFaceValue(faceValues[i]);
			student.setHobby(hobbys[i]);
			data.add(student);
		}
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		Toast.makeText(MainActivity.this, position+"", 1000).show();
		data.remove(position);
		initView(data);
	}

}
