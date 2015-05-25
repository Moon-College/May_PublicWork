package com.qfx.composite.adapter;

import java.util.List;

import com.qfx.composite.R;
import com.qfx.composite.mode.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FriendListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Friend> data;
	private LayoutInflater inflater;

	public FriendListAdapter(Context context, List<Friend> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Friend getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		RelativeLayout containerRL;
		ImageView photoIV;
		TextView nicknameTV;
		TextView sexTV;
		TextView facevalueTV;
		TextView hobbyTV;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.containerRL = (RelativeLayout) convertView.findViewById(R.id.rl_listItem_container);
			holder.photoIV = (ImageView) convertView.findViewById(R.id.iv_listItem_photo);
			holder.nicknameTV = (TextView) convertView.findViewById(R.id.tv_listItem_nickname);
			holder.sexTV = (TextView) convertView.findViewById(R.id.tv_listItem_sex);
			holder.facevalueTV = (TextView) convertView.findViewById(R.id.tv_listItem_facevalue);
			holder.hobbyTV = (TextView) convertView.findViewById(R.id.tv_listItem_hobby);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Friend friend = getItem(position);
		if (friend.getSex().equals("男")) {
			holder.containerRL.setBackgroundColor(0xff222288);
		} else {
			holder.containerRL.setBackgroundColor(0xff882222);
		}
		holder.photoIV.setImageResource(friend.getPhoto());
		holder.nicknameTV.setText(friend.getNickname());
		holder.sexTV.setText(friend.getSex());
		holder.facevalueTV.setText("颜值：" + friend.getFacevalue());
		holder.hobbyTV.setText("爱好：" + friend.getHobby());
		return convertView;
	}

}
