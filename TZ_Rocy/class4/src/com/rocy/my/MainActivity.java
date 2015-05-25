package com.rocy.my;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class MainActivity extends Activity  {

	private ListView lv;
	private MyAdapter myAdapter;
	private FrameLayout ll;
	private ArrayList<Student> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onMyLayout();
		onInit();
		
	}
	//初始化控件和数据
	private void onInit() {
		// TODO Auto-generated method stub
//		lv.setScrollBarStyle(ListView.SCROLLBAR_POSITION_DEFAULT);
		//获取数据
		list =new ArrayList<Student>();
		for (int i = 1; i < 11; i++) {
			//
			int sex;
			//偶为女
			if(i%2==0){
				sex=1;
			}else{
				sex=0;
			}
			Student student =new Student("明明"+i,R.drawable.ic_launcher,sex,i+20+"");
			list.add(student);
		}
		myAdapter=new MyAdapter(this , list);
		lv.setAdapter(myAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				list.remove(position);
				myAdapter.notifyDataSetChanged();
			}
		});
	}
	//布局文件
	public void onMyLayout(){
	    setTitle(null);
	    LinearLayout ll =new LinearLayout(this);
	    LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	    ll.setLayoutParams(lp);
	    lv =new ListView(this);
	    ll.addView(lv,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	    setContentView(ll);
	}

	
	

}
