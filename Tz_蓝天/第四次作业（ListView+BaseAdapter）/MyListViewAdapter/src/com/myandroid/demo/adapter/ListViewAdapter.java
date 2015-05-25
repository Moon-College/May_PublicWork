package com.myandroid.demo.adapter;

import java.util.List;

import org.w3c.dom.Text;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myandroid.demo.MainrActivity;
import com.myandroid.demo.R;
import com.myandroid.demo.bean.Contacts;

public class ListViewAdapter extends BaseAdapter {

	private Context context = null;
	private List<Contacts> list = null;
	private LayoutInflater inflater;

	public ListViewAdapter(Context context, List<Contacts> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private class HolderView {
		public ImageView img;
		public TextView tv_name;
		public TextView tv_sex;
		public TextView tv_handSome;
		public TextView tv_interest;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView;

		if (convertView == null) {
			holderView = new HolderView();
			convertView = inflater.inflate(R.layout.list_item, null);
			holderView.img = (ImageView) convertView.findViewById(R.id.img);
			holderView.tv_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			holderView.tv_sex = (TextView) convertView
					.findViewById(R.id.tv_sex);
			holderView.tv_handSome = (TextView) convertView
					.findViewById(R.id.tv_handsome);
			holderView.tv_interest = (TextView) convertView
					.findViewById(R.id.tv_interest);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}
		Contacts contacts = list.get(position);
		holderView.img.setImageResource(contacts.getPicture());
		holderView.tv_name.setText(contacts.getName());
		holderView.tv_sex.setText(contacts.getSex());
		
		if (holderView.tv_sex.getText().toString().trim().equals("男")) {	
			convertView.setBackgroundColor(Color.rgb(135, 206, 250));
		} else {
			convertView.setBackgroundColor(Color.rgb(255, 160, 122));
		}

		holderView.tv_handSome.setText(contacts.getHandsome());
		holderView.tv_interest.setText(contacts.getInterest());

		return convertView;
	}

	// @Override
	// public void notifyDataSetChanged() {
	//
	// super.notifyDataSetChanged();
	// }

}
