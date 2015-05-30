package com.jzlg.until;

import java.util.List;

import com.jzlg.cn.R;
import com.jzlg.entity.MyFile;

import android.R.anim;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	Context context;
	List<MyFile> list;
	public MyBaseAdapter(Context con,List<MyFile> li){
		context=con;
		list=li;
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

	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyFile myFile=list.get(position);
		Item item=new Item();
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.item, null);
			item.imageView=(ImageView) view.findViewById(R.id.image);
			item.textView=(TextView) view.findViewById(R.id.textview);
			view.setTag(item);
		}else{
			item=(Item) view.getTag();
		}
	
		item.imageView.setImageBitmap(myFile.getBitmap());
	    item.textView.setText(myFile.getName());
	    //item.imageView.setImageBitmap(myFile.getBitmap());
	    
		return view;
	}
	
	/**
	 * @author cxc 用户 看到的 条目  样子
	 * */
	class Item{
		ImageView imageView;
		TextView textView;
	}
	
	
}
