package com.example.listview_baseadapter.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listview_baseadapter.MainActivity;
import com.example.listview_baseadapter.R;
import com.example.listview_baseadapter.bean.UserInfo;

public class ListViewAdapter extends BaseAdapter {
	List<UserInfo> list;
	Context context;
	LayoutInflater mInflater;

	public ListViewAdapter(Context context, List<UserInfo> list) {
		this.context = context;
		this.list = list;
		mInflater = LayoutInflater.from(context);
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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = mInflater.inflate(R.layout.list_item, null);

		ImageView iv_head = (ImageView) view.findViewById(R.id.iv_head);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_sex = (TextView) view.findViewById(R.id.tv_sex);
		TextView tv_appearance = (TextView) view
				.findViewById(R.id.tv_appearance);
		TextView tv_hobbys = (TextView) view.findViewById(R.id.tv_bobbys);

		UserInfo info = list.get(position);

		iv_head.setImageResource(info.getHead());
		tv_name.setText(info.getName());
		tv_sex.setText(info.getSex());
		tv_appearance.setText(info.getAppearance());
		tv_hobbys.setText(info.getHobbys());

		return view;
	}

}
