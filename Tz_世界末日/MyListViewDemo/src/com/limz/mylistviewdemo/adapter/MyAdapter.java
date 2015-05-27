package com.limz.mylistviewdemo.adapter;

import java.util.ArrayList;

import com.limz.mylistviewdemo.activity.R;
import com.limz.mylistviewdemo.bean.Student;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	public ArrayList<Student> mList;
	
	public MyAdapter(Context context, ArrayList<Student> list) {
		mContext = context;
		mList = list;
	}
	
	public int getCount() {
		return mList.size();
	}

	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.listitem, null);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView sex = (TextView) view.findViewById(R.id.sex);
		TextView sorce = (TextView) view.findViewById(R.id.mysorce);
		TextView like = (TextView) view.findViewById(R.id.like);
		ImageView photo = (ImageView) view.findViewById(R.id.image);
		Student student = mList.get(position);
		if(student.getPhoto() > 0 ) {
			photo.setImageResource(student.getPhoto());
		}
		name.setText(student.getName());
		sex.setText(student.getSex());
		sorce.setText("—’÷µ : " + student.getSorce());
		like.setText(student.getLike());
		if(student.getSex().equals("man")) {
			view.setBackgroundResource(R.color.listview_item_blue);
		} else if(student.getSex().equals("woman")) {
			view.setBackgroundResource(R.color.listview_item_red);
		}
		return view;
	}

}
