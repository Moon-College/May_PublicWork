package com.tz.michael.adapter;

import java.util.List;

import com.tz.michael.utils.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 自己封装的adapter
 * @author admin
 *
 */
public abstract class MyCustomAdapter <T> extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<T> list;
	private int layoutId;
	
	public MyCustomAdapter(Context mContext,List<T> list,int layoutId){
		this.context=mContext;
		this.list=list;
		this.layoutId=layoutId;
		inflater=LayoutInflater.from(mContext);
	}
	
	public int getCount() {
		return list.size();
	}

	public T getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.get(context, position, convertView, parent, layoutId);
		convert(holder,getItem(position));
		return holder.getConvertView();
	}

	public abstract void convert(ViewHolder holder,T t);
	
}
