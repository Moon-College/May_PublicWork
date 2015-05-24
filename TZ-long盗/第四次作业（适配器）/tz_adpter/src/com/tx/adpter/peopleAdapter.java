package com.tx.adpter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class peopleAdapter extends BaseAdapter {

	private static final String RelativeLayout = null;

	private List<People> list = null;

	private Context context;

	private LayoutInflater inflater;

	public peopleAdapter(Context context, List<People> date) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = date;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public People getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		People item = getItem(position);
		if (convertView ==null) {
			convertView = inflater.inflate(R.layout.item, null);
			 holder = new ViewHolder();
             /**得到各个控件的对象*/      
			 holder.name = (TextView) convertView.findViewById(R.id.item_name);
			 holder.hobbies = (TextView) convertView.findViewById(R.id.item_interest);
			 holder.sex = (TextView) convertView.findViewById(R.id.item_sex);
			 holder.interest = (TextView) convertView.findViewById(R.id.item_colorValue);
			 holder.img = (ImageView) convertView.findViewById(R.id.item_icon);
			 holder.rlayout = (RelativeLayout)convertView.findViewById(R.id.item_rlayout);
			 convertView.setTag(holder);//绑定ViewHolder对象
		} else{
             holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象                  
		}
		
		holder.name.setText(item.getName());
		holder.hobbies.setText(item.getInterest());
		holder.sex.setText(item.getSex());
		holder.img.setImageResource(item.getIcon());
		holder.interest.setText(item.getColorValue());
		holder.rlayout.setBackgroundColor("男" == item.getSex() ? Color.BLUE : Color.RED);

		return convertView;
	}
	 public final class ViewHolder{

		public RelativeLayout rlayout;
		public ImageView img;
		public TextView interest;
		public TextView sex;
		public TextView name;
		public TextView hobbies;
		
	}

}
