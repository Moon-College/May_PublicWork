package com.xiaowei.viewpager_navigation_wp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MyFragment extends Fragment{
	private int path;
	private int position;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		path = this.getArguments().getInt("path");
		position = this.getArguments().getInt("position");
		Log.i("INFO", "创建fragment" + position);
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(path);
		return img;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("INFO", "销毁fragment" + position);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.i("INFO", "销毁fragment" + position);
	}
}
