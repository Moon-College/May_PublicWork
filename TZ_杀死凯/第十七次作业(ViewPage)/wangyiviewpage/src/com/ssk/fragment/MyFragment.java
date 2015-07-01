package com.ssk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment{
	String[] contries = new String[]{
			"头条","娱乐","热点","体育","财经","科技","图片","汽车","军事"
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle=getArguments();
		TextView tv=new TextView(getActivity());
		tv.setText(contries[bundle.getInt("position")]);
		return tv;
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

}
