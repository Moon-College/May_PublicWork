package com.jim.mylistview.adapter;

import java.util.List;

import com.jim.mylistview.R;
import com.jim.mylistview.beans.Persons;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 好友列表的适配器
 * 
 * @author fb
 * 
 */
public class MyAdapter extends BaseAdapter {
	private List<Persons> list;
	private LayoutInflater inflater;
	private Context context;

	/**
	 * 构造函数
	 * 
	 * @param list
	 * @param context
	 */
	public MyAdapter(List<Persons> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
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

	public void removeItem(int position) {
		list.remove(position);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = new ViewHolder();
		if (view == null) {
			view = inflater.inflate(R.layout.list_item, null);
		}
		holder.img = (ImageView) view.findViewById(R.id.img);
		holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
		holder.tv_appearance = (TextView) view.findViewById(R.id.tv_appearance);
		holder.tv_gender = (TextView) view.findViewById(R.id.tv_gender);
		holder.tv_hobby = (TextView) view.findViewById(R.id.tv_hobby);
		view.setTag(view);

		int mImg = list.get(position).getImg();
		int mAppearance = list.get(position).getAppearance();
		String mName = list.get(position).getName();
		String mHobby = list.get(position).getHobby();
		String mGender = list.get(position).getGender();

		if ("男".equals(mGender)) {
			view.setBackgroundColor(Color.BLUE);
			if (mAppearance > 80) {
				holder.tv_appearance.setBackgroundColor(Color.CYAN);
			} else {
				holder.tv_appearance.setBackgroundColor(Color.GREEN);
			}

		} else if ("女".equals(mGender)) {
			view.setBackgroundColor(Color.RED);
			if (mAppearance > 80) {
				holder.tv_appearance.setBackgroundColor(Color.CYAN);
			} else {
				holder.tv_appearance.setBackgroundColor(Color.GREEN);
			}
		}
		holder.img.setBackgroundResource(mImg);
		holder.tv_appearance.setText("颜值:" + mAppearance);
		holder.tv_gender.setText(mGender);
		holder.tv_hobby.setText("爱好:" + mHobby);
		holder.tv_name.setText(mName);
		return view;
	}

	static class ViewHolder {
		ImageView img;
		TextView tv_name, tv_appearance, tv_gender, tv_hobby;
	}
}
