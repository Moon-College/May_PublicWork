package com.decent.courier.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.decent.courier.activity.R;
import com.decent.courier.common.OrderItem;

public class OrderListAdapter extends BaseAdapter {

	private List<OrderItem> mOrderList;
	private Context mContext;
	LayoutInflater mInflater;
	
	public OrderListAdapter(List<OrderItem> mOrderList, Context context) {
		super();
		this.mOrderList = mOrderList;
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mOrderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		OrderItem item = mOrderList.get(position);
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.order_item, null);
			TextView good_tv = (TextView) convertView.findViewById(R.id.good_iv);
			TextView price_tv = (TextView) convertView.findViewById(R.id.price_tv);
			TextView startTime = (TextView)convertView.findViewById(R.id.starttime);
			holder = new ViewHolder(good_tv, price_tv, startTime);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.good_tv.setText(item.getGoods());
		holder.price_tv.setText(String.valueOf(item.getPrice()));
		holder.startTime.setText(mContext.getString(R.string.create_time)+" "+item.getCreateTime());
		return convertView;
	}
	
	private class ViewHolder {
		TextView good_tv;
		TextView price_tv;
		TextView startTime;
		public ViewHolder(TextView good_tv, TextView price_tv,
				TextView startTime) {
			super();
			this.good_tv = good_tv;
			this.price_tv = price_tv;
			this.startTime = startTime;
		}
	}
}
