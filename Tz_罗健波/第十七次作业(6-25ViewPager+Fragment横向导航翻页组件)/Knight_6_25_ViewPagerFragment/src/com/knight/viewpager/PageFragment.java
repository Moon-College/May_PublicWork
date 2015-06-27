package com.knight.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageFragment extends Fragment {

	
	int page;
	int position;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageView img = new ImageView(getActivity());
		Bundle bundle = this.getArguments();
		page = bundle.getInt("page");
		position = bundle.getInt("position");
		img.setImageResource(page);
		Log.e("INFO", "新建pager:"+position);
		return img;
	}
	
	@Override
	public void onPause() {
		Log.e("Info", "销毁pager："+position);
		super.onPause();
	}
}
