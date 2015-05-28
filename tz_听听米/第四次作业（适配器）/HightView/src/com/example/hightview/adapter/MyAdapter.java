package com.example.hightview.adapter;

import java.util.List;

import com.example.hightview.R;
import com.example.hightview.beans.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	private List<Student> data;
	private LayoutInflater inflate;
	
	public MyAdapter(Context context,List<Student> data) {
		this.data = data;
		inflate = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Student getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(null == convertView){
			convertView = inflate.inflate(R.layout.studentlist, parent,false);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.sex = (TextView) convertView.findViewById(R.id.sex);
			viewHolder.faceValue = (TextView) convertView.findViewById(R.id.faceValue);
			viewHolder.hobby = (TextView) convertView.findViewById(R.id.hobby);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Student student = data.get(position);
		LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item);
		if(student.getSex() == 0){
			item.setBackgroundResource(R.color.pink);
		}else{
			item.setBackgroundResource(R.color.lightblue);
		}
		viewHolder.image.setImageResource(student.getImageId());
		viewHolder.name.setText(student.getName());
		viewHolder.sex.setText(student.getSex()==0?"女":"男");
		viewHolder.faceValue.setText(String.valueOf(student.getFaceValue()));
		viewHolder.hobby.setText(student.getHobby());
		
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
		TextView name;
		TextView sex;
		TextView faceValue;
		TextView hobby;
	}

}
