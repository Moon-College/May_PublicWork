package com.limz.mylistviewdemo.activity;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Test;

import com.limz.mylistviewdemo.adapter.MyAdapter;
import com.limz.mylistviewdemo.bean.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyListViewDemoActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
	
	private ArrayList<Student> mList;
	private ListView listView;
	private MyAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.mylistview);
        mList = new ArrayList<Student>();
        testData();
        adapter = new MyAdapter(this, mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    /**
     * Data for test
     */
	private void testData() {
		Student s1 = new Student();
		s1.setName("Test name 1");
		s1.setSex("man");
		s1.setSorce(100);
		s1.setLike("Test like 1");
		s1.setPhoto(R.drawable.p1);
		mList.add(s1);
		
		Student s2 = new Student();
		s2.setName("Test name 2");
		s2.setSex("woman");
		s2.setSorce(100);
		s2.setLike("Test like 2");
		mList.add(s2);
		
		Student s3 = new Student();
		s3.setName("Test name 3");
		s3.setSex("man");
		s3.setSorce(77);
		s3.setLike("Test like 3");
		s3.setPhoto(R.drawable.p2);
		mList.add(s3);
		
		Student s4 = new Student();
		s4.setName("Test name 4");
		s4.setSex("man");
		s4.setSorce(90);
		s4.setLike("Test like 4");
		s4.setPhoto(R.drawable.p3);
		mList.add(s4);
		
		Student s5 = new Student();
		s5.setName("Test name 5");
		s5.setSex("woman");
		s5.setSorce(66);
		s5.setLike("Test like 5");
		s5.setPhoto(R.drawable.p4);
		mList.add(s5);
		
		Student s6 = new Student();
		s6.setName("Test name 6");
		s6.setSex("man");
		s6.setSorce(44);
		s6.setLike("Test like 6");
		s6.setPhoto(R.drawable.p5);
		mList.add(s6);
		
		Student s7 = new Student();
		s7.setName("Test name 7");
		s7.setSex("woman");
		s7.setSorce(100);
		s7.setLike("Test like 7");
		s7.setPhoto(R.drawable.p7);
		mList.add(s7);
		
		Student s8 = new Student();
		s8.setName("Test name 8");
		s8.setSex("man");
		s8.setSorce(100);
		s8.setLike("Test like 8");
		mList.add(s8);
		
		Student s9 = new Student();
		s9.setName("Test name 9");
		s9.setSex("man");
		s9.setSorce(20);
		s9.setLike("Test like 9");
		mList.add(s9);
		
		Student s10 = new Student();
		s10.setName("Test name 10");
		s10.setSex("man");
		s10.setSorce(0);
		s10.setLike("Test like 10");
		s10.setPhoto(R.drawable.p6);
		mList.add(s10);
		
		Student s11 = new Student();
		s11.setName("Test name 11");
		s11.setSex("woman");
		s11.setSorce(0);
		s11.setLike("Test like 11");
		mList.add(s11);
	}

	public void onItemClick(AdapterView<?> arg0, View view, int id, long pos) {
		Toast.makeText(this, "I click the item : " + mList.get(id).getName(), 
				Toast.LENGTH_LONG).show();
		mList.remove(id);
		adapter.mList = mList;
		adapter.notifyDataSetChanged();
	}
}