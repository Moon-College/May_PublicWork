package com.tz.michael.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 通用的ViewHolder类
 * 可以实现一些简化操作和代码的重用性
 * @author szm
 *
 */
public class ViewHolder {

	/**新建一个SparseArray（本质是个map）,用来存放控件的id以及控件，key viewId  value view对象*/
	private SparseArray<View> mViews;
	/**对应getView()中的convertView*/
	private View convertView;
	/**对应getView()中的position*/
	private int position;
	
	public ViewHolder(Context context,int position,ViewGroup parent,int layoutId){
		this.position=position;
		this.mViews=new SparseArray<View>();
		this.convertView=LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.convertView.setTag(this);
	}
	
	/**
	 * 这个是个入口方法，主要考虑到ViewHolder要实现复用
	 * @param context
	 * @param position 
	 * @param convertView
	 * @param parent 
	 * @param layoutId 
	 * @return
	 */
	public static ViewHolder get(Context context,int position,View convertView,ViewGroup parent,int layoutId){
		if(convertView==null){
			return new ViewHolder(context,position, parent, layoutId);
		}else{
			ViewHolder holder=(ViewHolder) convertView.getTag();
			holder.position=position;
			return holder;
		}
	}
	
	public View getConvertView() {
		return convertView;
	}
	
	/**
	 * 通过viewId返回view
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId){
		//先从map中拿取view
		View view=mViews.get(viewId);
		if(view==null){
			view=convertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}
	
	/**
	 * 专门为TextView 提供的方法、
	 * 采用链式编程
	 * @param viewId
	 * @param str
	 * @return
	 */
	public ViewHolder setText(int viewId,String str){
		TextView tv=getView(viewId);
		tv.setText(str);
		return this;
	}
	
}
