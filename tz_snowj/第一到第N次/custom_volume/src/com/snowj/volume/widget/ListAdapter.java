package com.snowj.volume.widget;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListAdapter<T>extends BaseAdapter {

	private List<T> list;
	private ListItem listItem;
	
	
	
	public ListAdapter( List<T> list,ListItem listItem) {
		super();
		this.listItem = listItem;
		this.list = list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return listItem.getView(position, convertView, parent);
	}

}
