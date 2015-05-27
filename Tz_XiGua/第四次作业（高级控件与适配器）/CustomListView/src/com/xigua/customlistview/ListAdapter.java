package com.xigua.customlistview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Context context;
	private List<TzStudent> list;
	private LayoutInflater inflater;
	
	public ListAdapter(Context context,List<TzStudent> list) {
		super();
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.favorite = (TextView) convertView.findViewById(R.id.favorite);
			viewHolder.sex = (TextView) convertView.findViewById(R.id.sex);
			viewHolder.appearance = (TextView) convertView.findViewById(R.id.appearance);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setImageResource(list.get(position).getImg());
		viewHolder.name.setText(list.get(position).getName());
		viewHolder.favorite.setText(list.get(position).getFavorite());
		viewHolder.sex.setText(list.get(position).getSex());
		viewHolder.appearance.setText(list.get(position).getAppearance());
		return convertView;
	}

	class ViewHolder{
		private ImageView img;
		private TextView name,favorite,sex,appearance;
		
	}
	
}
