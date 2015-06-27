package com.junwen.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomFragMent extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageView img = new ImageView(getActivity());
		Bundle bundle = this.getArguments();
		img.setImageResource(bundle.getInt("img"));
		return img;
	}
}
