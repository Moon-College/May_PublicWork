package com.junwen.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slidingmenu.R;
import com.junwen.bean.MenuItem;

public class MenuAdapter extends BaseAdapter{

	private Context context;
	private List<MenuItem> data;
	private LayoutInflater inflter;
	
	public MenuAdapter(Context context, List<MenuItem> data) {
		this.context = context;
		this.data = data;
		inflter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if( convertView  == null){
			convertView = inflter.inflate(R.layout.item_menuitem, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.title);
			holder.icon = (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		MenuItem menuItem = data.get(position);
		holder.tv.setText(menuItem.getTitle());
		holder.icon.setImageBitmap(menuItem.getBitmap());
		return convertView;
	}
	class ViewHolder {
		TextView tv;
		ImageView icon;
	}
}
