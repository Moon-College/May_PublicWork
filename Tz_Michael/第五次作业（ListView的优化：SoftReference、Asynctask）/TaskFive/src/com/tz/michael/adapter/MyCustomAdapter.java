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
	public Context context;
	public List<T> list;
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

	/**
	 * fg:TextView tv_name=holder.getView(R.id.tv_name) or holder.setText(R.id.tv_name, bean.getName())
	 * @param holder 包含View的ViewHolder对象
	 * @param t 模板类对象
	 */
	public abstract void convert(ViewHolder holder,T t);
	
}
