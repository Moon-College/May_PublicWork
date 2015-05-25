package com.mytz_listadapter;

import java.util.List;

import com.example.listadapter.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	public List<UserBean> datalist;
	private Context context;

	public MyAdapter(List<UserBean> datalist, Context context) {
		super();
		this.datalist = datalist;
		this.context = context;
	}

	@Override
	public int getCount() {
		return datalist.size();
	}

	@Override
	public Object getItem(int position) {
		return datalist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null);
			holder.root = (LinearLayout) convertView.findViewById(R.id.root);
			holder.header = (ImageView) convertView.findViewById(R.id.header);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.sex = (TextView) convertView.findViewById(R.id.sex);
			holder.face = (TextView) convertView.findViewById(R.id.face);
			holder.interest = (TextView) convertView
					.findViewById(R.id.interest);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.header.setBackgroundDrawable(convertView.getResources()
				.getDrawable(datalist.get(position).getHeader()));
		holder.name.setText(datalist.get(position).getName());
		holder.sex.setText(datalist.get(position).getSex());
		holder.face.setText(String.valueOf(datalist.get(position).getFace()));
		holder.interest.setText(datalist.get(position).getInterest());
		if (datalist.get(position).getSex().equals("女")) {
			holder.root.setBackgroundColor(Color.RED);
		} else {
			holder.root.setBackgroundColor(Color.BLUE);
		}
		return convertView;
	}

	class Holder {
		LinearLayout root;
		ImageView header;
		TextView name;
		TextView sex;
		TextView face;
		TextView interest;
	}

}
