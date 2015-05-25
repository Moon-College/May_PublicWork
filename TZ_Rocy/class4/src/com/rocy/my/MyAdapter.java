package com.rocy.my;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private Context context;
	private List<Student> list=new ArrayList<Student>();
	public MyAdapter(Context context, List<Student> list) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.list=list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Student student = list.get(position);
		LinearLayout ll =new LinearLayout(context);
		AbsListView.LayoutParams lp =new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT);
		//让里面控件都居中
		ll.setGravity(Gravity.CENTER_VERTICAL);
		ll.setLayoutParams(lp);
		//图片
		ImageView face =new ImageView(context);
		LinearLayout.LayoutParams orgPl =new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
		face.setLayoutParams(orgPl);
		face.setBackgroundResource(student.getFace());
		ll.addView(face);
		//名字
		TextView name =new TextView(context);
		name.setLayoutParams(orgPl);
		name.setGravity(Gravity.CENTER);
		name.setText(student.getName());
		ll.addView(name);
		//性别
		TextView sex =new TextView(context);
		sex.setLayoutParams(orgPl);
		sex.setGravity(Gravity.CENTER);
		if(student.sex==0){
			//男
			sex.setText("男");
			ll.setBackgroundResource(android.R.color.holo_orange_dark);
			
		}else{
			sex.setText("女");
			ll.setBackgroundResource(android.R.color.holo_green_dark);
		}
		ll.addView(sex);
		//年龄
				TextView age =new TextView(context);
				age.setLayoutParams(orgPl);
				age.setGravity(Gravity.CENTER);
				age.setText(student.getAge());
				ll.addView(age);
		return ll;
	}

}
