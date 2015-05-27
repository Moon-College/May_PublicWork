package com.tz.riders.adapter;

import java.util.List;
import com.tz.riders.ContactView;
import com.tz.riders.entity.Persion;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContsAdapter extends BaseAdapter {

	private List<Persion> lisPersions;
	private Context context;

	public ContsAdapter(Context context, List<Persion> lisPersions) {
		this.context = context;
		this.lisPersions = lisPersions;
	}

	@Override
	public int getCount() {
		return lisPersions.size();
	}

	@Override
	public Persion getItem(int position) {
		return lisPersions.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ContactView view;
		if (convertView == null) {
			view = new ContactView(context);
		} else {
			view = (ContactView) convertView;
		}
		Persion item = getItem(position);
		view.showPersion(item);
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

}
