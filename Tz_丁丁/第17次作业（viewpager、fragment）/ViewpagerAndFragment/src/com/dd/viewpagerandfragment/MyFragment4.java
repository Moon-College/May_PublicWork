package com.dd.viewpagerandfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment4 extends Fragment {
	
	private int id;
	private int position;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("home", "createMyFragment4£º");
//		Bundle bundle = getArguments();
//		id = bundle.getInt("id");
//		position = bundle.getInt("position");
//		ImageView imageView = new ImageView(getActivity());
//		imageView.setImageResource(id);
		TextView textView = new TextView(getActivity());
		textView.setText("MyFragment4");
		return textView;
	}
	@Override
	public void onPause() {
		super.onPause();
		Log.v("home", "ÔÝÍ£MyFragment4£º");
	}
}
