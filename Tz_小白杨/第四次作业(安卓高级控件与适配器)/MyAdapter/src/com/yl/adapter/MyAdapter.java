package com.yl.adapter;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yl.bean.Person;
import com.yl.listview.R;

public class MyAdapter extends BaseAdapter {
	private Context context;
	public List<Person> data;
	private LayoutInflater inflater;
	public MyAdapter(Context context,List<Person> data){
		this.context=context;
		this.data=data;
		inflater=LayoutInflater.from(context);
		}
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Person person=data.get(position);
		View view=inflater.inflate(R.layout.listview_item,null);
		ImageView img=(ImageView) view.findViewById(R.id.iv);
		TextView name=(TextView) view.findViewById(R.id.name);
		TextView sex=(TextView) view.findViewById(R.id.sex);
		TextView num=(TextView) view.findViewById(R.id.number);
		TextView hobby=(TextView) view.findViewById(R.id.hobby);
		
		img.setImageResource(person.getImg());
		name.setText(person.getName());
		sex.setText(person.getSex());
		num.setText(person.getNum());
		hobby.setText(person.getHobby());
		
		if( "ÄÐ".equals( person.getSex() )) {
			view.setBackgroundColor(Color.rgb(0, 0, 255));
		} else {
			view.setBackgroundColor(Color.rgb(255, 0, 0));
		}
		return view;
	}

}
