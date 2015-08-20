package com.tz.asy.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tz.asy.R;
import com.tz.asy.http.common.OrdersItem;


public class OrderAdapter extends BaseAdapter{
	List<OrdersItem> data;
	Context context;
	LayoutInflater inflater;
	public OrderAdapter(List<OrdersItem> data,Context context){
		this.data = data;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.order_item, null);
			holder.goodTv = (TextView) convertView.findViewById(R.id.good_tv);
			holder.priceTv = (TextView) convertView.findViewById(R.id.price_tv);
			holder.startTime = (TextView) convertView.findViewById(R.id.starttime);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		OrdersItem item = data.get(position);
		holder.goodTv.setText(item.getGoods());
		holder.priceTv.setText(String.valueOf(item.getPrice()));
		holder.startTime.setText(context.getString(R.string.create_time)+" "+item.getCreateTime());
		return convertView;
	}

	class ViewHolder {
		TextView goodTv;
		TextView priceTv;
		TextView startTime;
	}
}
