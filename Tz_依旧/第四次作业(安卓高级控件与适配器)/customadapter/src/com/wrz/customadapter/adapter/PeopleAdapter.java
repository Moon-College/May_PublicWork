package com.wrz.customadapter.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrz.customadapter.R;
import com.wrz.customadapter.bean.People;

public class PeopleAdapter extends BaseAdapter {

	List<People> data;
	Context context;
	LayoutInflater inflater;// 布局加载器
	
	public PeopleAdapter(Context context, List<People> data){
		this.data = data;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// 获取人物信息
		People people = data.get(position);
		// 根据布局加载视图
		View view = inflater.inflate(R.layout.people_listview, null);
		// 头像
		ImageView img = (ImageView) view.findViewById(R.id.l_img);
		// 网名
		TextView l_name = (TextView) view.findViewById(R.id.l_name);
		// 性别
		TextView l_sex = (TextView) view.findViewById(R.id.l_sex);
		// 颜值
		TextView r_number = (TextView) view.findViewById(R.id.r_number);
		// 喜好
		TextView l_like = (TextView) view.findViewById(R.id.r_like);
		// 控件绑定
		img.setImageResource(people.getHeadImg());
		l_name.setText(people.getName());
		l_name.setTextColor(Color.parseColor("#000000"));
		l_sex.setTextColor(Color.parseColor("#000000"));
		r_number.setTextColor(Color.parseColor("#000000"));
		l_like.setTextColor(Color.parseColor("#000000"));
		// 性别男为蓝色，女为红色
		if("男".equals(people.getSex())){
			view.setBackgroundColor(Color.BLUE);
		}else if("女".equals(people.getSex())){
			view.setBackgroundColor(Color.RED);
		}
		l_sex.setText(people.getSex());
		r_number.setText(people.getFaceNumber());
		l_like.setText(people.getLike());
		return view;
	}

}
