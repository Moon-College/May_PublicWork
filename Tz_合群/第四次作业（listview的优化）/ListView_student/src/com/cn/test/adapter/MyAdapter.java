package com.cn.test.adapter;

/**
 * Created on 2015年5月25日上午11:46:40 MyAdapter.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.test.R;
import com.cn.test.been.Student;

public class MyAdapter extends BaseAdapter {
	private List<Student> data;
	private LayoutInflater inflater;
	private Context context;

	public MyAdapter(Context context, List<Student> data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 创建麻布袋
		ViewHolder holder;
		// 第一次没有缓存，找控件
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			// 创建麻布袋，打包控件
			holder = new ViewHolder();
			holder.face = (ImageView) convertView.findViewById(R.id.face);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.colorcount = (TextView) convertView
					.findViewById(R.id.colorcount);
			holder.sex = (TextView) convertView.findViewById(R.id.sex);
			holder.hobby = (TextView) convertView.findViewById(R.id.hobby);
			// 将麻布袋放在convertView里一起缓存
			convertView.setTag(holder);

		} else {
			// 第二次直接从缓存中拿出来
			holder = (ViewHolder) convertView.getTag();
		}
		holder.face.setBackgroundResource(data.get(position).getStu_face());
		holder.name.setText(data.get(position).getStu_name());
		// 千万注意这里要数据类型要转换
		holder.colorcount.setText(data.get(position).getStu_color() + "");
		holder.sex.setText(data.get(position).getStu_sex());
		if (holder.sex.getText().toString().trim().equals("男")) {
			convertView.setBackgroundColor(Color.rgb(135, 206, 250));
		} else {
			convertView.setBackgroundColor(Color.rgb(255, 160, 122));
		}

		holder.hobby.setText(data.get(position).getStu_hobby());
		return convertView;
	}

	private class ViewHolder {

		ImageView face;
		TextView name;
		TextView colorcount;
		TextView sex;
		TextView hobby;

	}

}