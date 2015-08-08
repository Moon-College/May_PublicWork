package com.junwen.music.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.musicdemo.R;
import com.junwen.music.model.Music;
import com.junwen.music.util.MusicUtil;

public class MusicAdapter extends BaseAdapter {

	private List<Music> data;
	private Context context;
	private LayoutInflater inflater;

	public MusicAdapter(List<Music> data, Context context) {
		this.data = data;
		this.context = context;
		inflater =(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_layout, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.duration = (TextView) convertView.findViewById(R.id.duration);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Music music = data.get(position);
		holder.name.setText(music.getFileName());
		String duration = MusicUtil.formatTime(music.getDuration());
		holder.duration.setText(duration);
		return convertView;
	}
	
	public class ViewHolder{
		TextView name;
		TextView duration;
	}
}
