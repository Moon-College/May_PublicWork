package com.example.viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		Bundle bundle = getArguments();
		int path = bundle.getInt("path");

		ImageView image = new ImageView(this.getActivity());
		image.setImageResource(path);

		return image;
	}

}
