package com.view.viewpagees;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPager extends Fragment{
	private int imgId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		imgId = bundle.getInt("img");
		ImageView view = new ImageView(this.getActivity());
		view.setImageResource(imgId);
		return view;
	}

}
