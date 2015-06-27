package com.tz.viewpagernavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment{
	
	private int path;
	private int position;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
		path = bundle.getInt("paht");
		position = bundle.getInt("position");
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(path);
		return img;
	}

}
