package com.zjm.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int resid = getArguments().getInt("resid");
		ImageView imgView = new ImageView(getActivity());
		imgView.setBackgroundResource(resid);
		return imgView;
	}
	
}
