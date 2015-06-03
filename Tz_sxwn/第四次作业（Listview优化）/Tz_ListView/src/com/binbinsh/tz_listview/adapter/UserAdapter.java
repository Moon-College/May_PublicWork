package com.binbinsh.tz_listview.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binbinsh.tz_listview.entity.User;
import com.wp.tz_listview.R;

public class UserAdapter extends BaseAdapter{

	private List<User> _list=new ArrayList<User>();
	private Context context;
	private LayoutInflater mInflater;
	
	public UserAdapter(Context context,List<User> _list) {
		super();
		this._list = _list;
		this.context=context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return _list.size();
	}

	@Override
	public Object getItem(int position) {
		return _list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.list_user_item, parent, false);
			holder.ivImage=(ImageView) convertView.findViewById(R.id.iv);
			holder.tvName=(TextView) convertView.findViewById(R.id.tvName);
			holder.tvSex=(TextView) convertView.findViewById(R.id.tvSex);
			holder.tvFaceValue=(TextView) convertView.findViewById(R.id.tvFaceValue);
			holder.tvHobbies=(TextView) convertView.findViewById(R.id.tvHobbies);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		if(null!=_list){
			User u=_list.get(position);
			holder.ivImage.setBackgroundResource(context.getResources().getIdentifier(u.getThumbnail(), "drawable", "com.binbinsh.tz_listview"));
			holder.tvSex.setText(u.getSex());
			holder.tvFaceValue.setText("颜值："+u.getFaceValue());
			holder.tvHobbies.setText(u.getHobbies());
			holder.tvName.setText(u.getName());
			if(u.getSex().equals("男")){
				convertView.setBackgroundColor(Color.parseColor("#0099ff"));
			}else{
				convertView.setBackgroundColor(Color.parseColor("#ff3333"));
			}
			
		}
		
		
		return convertView;
	}
	
	

	class ViewHolder {
		TextView tvName;
		TextView tvSex;
		TextView tvFaceValue;
		TextView tvHobbies;
		ImageView ivImage;
	}
	

}
