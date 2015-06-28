package com.vincen.customeradapter.adapter;

import java.util.List;

import com.vincen.customeradapter.R;
import com.vincen.customeradapter.bean.Student;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	private List<Student> data;
	private Context context;
	private LayoutInflater inflater;
	
	public MyAdapter(List<Student> data, Context context) {
		super();
		this.data = data;
		this.context = context;
		
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if(convertView == null){
			 convertView = inflater.inflate(R.layout.list_student_item, null);
			 holder.imgStu = (ImageView) convertView.findViewById(R.id.img_stu);
			 holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			 holder.tvSex = (TextView) convertView.findViewById(R.id.tv_sex);
			 holder.tvHabbies = (TextView) convertView.findViewById(R.id.tv_habbies);
			 holder.tvColorVal = (TextView) convertView.findViewById(R.id.tv_colorValue);
			 convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		 holder.imgStu.setBackgroundResource(data.get(position).getImage());
		 holder.tvName.setText(data.get(position).getName());
		 holder.tvSex.setText(data.get(position).getSex());
		 holder.tvHabbies.setText(data.get(position).getHobbies());
		 holder.tvColorVal.setText(data.get(position).getColorValue());
		 
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView imgStu;
		TextView tvName;
		TextView tvSex;
		TextView tvHabbies;
		TextView tvColorVal;
		
	}

}
