package com.junwen.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asyntaskdownloadimage.R;
import com.junwen.bean.App;

public class AppAdapter extends BaseAdapter{

	private Context context;
	private List<App> data ;
	private LayoutInflater inflater;
	
	
	public AppAdapter(Context context, List<App> data) {
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder holder = null;
		if(arg1 == null) {
			arg1 = inflater.inflate(R.layout.item_layout, null);
			holder = new ViewHolder();
			holder.img = (ImageView) arg1.findViewById(R.id.img);
			holder.tv = (TextView) arg1.findViewById(R.id.name);
			arg1.setTag(holder);
		}else
		{
			holder = (ViewHolder) arg1.getTag();
		}
		App app = data.get(arg0);
		holder.img.setImageDrawable(app.getIcon());
		holder.tv.setText(app.getName());
		return arg1;
	}
	class ViewHolder {
		ImageView img;
		TextView tv;
	}
}
