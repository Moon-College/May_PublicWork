package com.tz.asy.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tz.asy.R;
import com.tz.asy.http.common.CommentItem;

public class CommentAdapter extends BaseAdapter{
	private List<CommentItem> data ;
	private Context context;
	private LayoutInflater inflater;
	public CommentAdapter(Context context,List<CommentItem> data){
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
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
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.comment_item, null);
			holder.commentTv = (TextView) convertView.findViewById(R.id.comment_content);
			holder.commentTime = (TextView) convertView.findViewById(R.id.comment_time);
			holder.commentUser = (TextView) convertView.findViewById(R.id.comment_user);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		CommentItem item = data.get(position);
		holder.commentTv.setText(item.getComment());
		holder.commentTime.setText(item.getCreateTime());
		holder.commentUser.setText(item.getUserName());
		return convertView;
	}

	class ViewHolder {
		TextView commentTv;
		TextView commentTime;
		TextView commentUser;
	}
}
