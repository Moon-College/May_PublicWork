package com.dd.viewpagerandfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {
	
	private int id;
	private int position;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		id = bundle.getInt("id");
		position = bundle.getInt("position");
		Log.v("home", "createMyFragment£º"+position);
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(id);
		return imageView;
	}
	@Override
	public void onPause() {
		super.onPause();
		Log.v("home", "ÔÝÍ£Fragment£º" + position);
	}
}
