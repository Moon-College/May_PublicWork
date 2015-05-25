package com.junwen.adapter;

import java.util.List;

import com.junwen.activity.R;
import com.junwen.bean.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	//上下文
	private Context context;
	//对象集合
	private List<Student> data;
	//布局加载器
	private LayoutInflater inflater;
	
	public CustomAdapter(Context context, List<Student> data) {
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			//获取一个Student对象
			Student student = data.get(position);
			//如果是男孩
			if(student.isBoy()==true)
			{
				//载入男生布局
				convertView = inflater.inflate(R.layout.boy_item_layout, null);
			}else
			{
				//载入女生布局
				convertView = inflater.inflate(R.layout.girl_item_layout, null);
			}
		//根据布局获取里面的控件
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView faceValue = (TextView) convertView.findViewById(R.id.faceValue);
		TextView hobby = (TextView) convertView.findViewById(R.id.hobby);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView sex = (TextView) convertView.findViewById(R.id.sex);
		
		//设置数据
		img.setImageBitmap(student.getHeadImage().get());
		faceValue.setText(student.getFaceValue()+"");
		hobby.setText(student.getHobby());
		name.setText(student.getName());
		sex.setText(student.getSex());
		return convertView;
	}
	
}
