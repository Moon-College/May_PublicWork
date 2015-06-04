package com.junwen.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.intentdemo.R;

public class PhoneAdapter extends BaseAdapter{
	private String [] data;
	private Context context;
	private LayoutInflater inlInflater;
	
	
	public PhoneAdapter(String [] data, Context context) {
		this.data = data;
		this.context = context;
		inlInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		convertView = inlInflater.inflate(R.layout.phoneitem_layout, null);
		TextView text = (TextView) convertView.findViewById(R.id.num);
		text.setText(data[position]);
		return convertView;
	}
	
}
