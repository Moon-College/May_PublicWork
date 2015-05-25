package com.tz.customadapter.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.customadapter.R;
import com.tz.customadapter.consts.Sex;
import com.tz.customadapter.vo.People;

public class CustomAdapter extends BaseAdapter {
	private List<People> data;
	private LayoutInflater inflater;

	public CustomAdapter(Context context, List<People> data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	public void addItems(List<People> data) {
		if (data != null) {
			this.data.addAll(data);
		}
	}
	
	public void addItem(People people) {
		if (people != null) {
			data.add(people);
		}
	}
	

	public void clear() {
		if (data != null) {
			data.clear();
		}		
	}
	
	public void addItem(List<People> data) {
		if (data != null) {
			data.addAll(data);
		}
	}
	
	public void remove(int position) {
		if (position < 0 || position > data.size()) {
			return;
		}
		data.remove(position);
	}

	@Override
	public int getCount() {
		// 数据为null，则返回0
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		if (data != null) {
			return data.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		// 初始化view
		if (convertView == null) {
			// inflate item view
			convertView = inflater.inflate(R.layout.item_customadapter, null);

			holder = new ViewHolder();
			holder.avatarView = (ImageView) convertView
					.findViewById(R.id.customadapter_avatar);
			holder.nameView = (TextView) convertView
					.findViewById(R.id.customadapter_name);
			holder.sexView = (TextView) convertView
					.findViewById(R.id.customadapter_sex);
			holder.hobbiesView = (TextView) convertView
					.findViewById(R.id.customadapter_hobbies);
			holder.faceScoreView = (TextView) convertView
					.findViewById(R.id.customadapter_facescore);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		People people = data.get(position);

		// 设置数据
		holder.avatarView.setImageResource(people.avatar);
		holder.nameView.setText(people.name);
		holder.faceScoreView.setText(String.format("颜值：%d", people.faceScore));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; people.hobbies != null && i < people.hobbies.length; i++) {
			if (i == 0) {
				sb.append(people.hobbies[i]);
			} else {
				sb.append("，" + people.hobbies[i]);
			}
		}
		holder.hobbiesView.setText(sb.toString());
		holder.sexView.setText(people.sex.getName());
		// 男的背景为蓝色，女的为红色
		if (Sex.MAN == people.sex) {
			convertView.setBackgroundColor(Color.BLUE);
		} else if (Sex.WOMEN==people.sex) {
			convertView.setBackgroundColor(Color.RED);
		}
		return convertView;
	}

	private class ViewHolder {
		ImageView avatarView;
		TextView nameView;
		TextView sexView;
		TextView hobbiesView;
		TextView faceScoreView;
	}
}
