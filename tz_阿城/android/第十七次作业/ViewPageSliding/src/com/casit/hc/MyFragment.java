package com.casit.hc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment  extends Fragment{

	private int path;
	private int position;
	public int getPath() {
		return path;
	}
	public void setPath(int path) {
		this.path = path;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		path = this.getArguments().getInt("path");
		position = this.getArguments().getInt("position");
		Log.i("INFO", "增加index"+position);
		ImageView imageview = new ImageView(this.getActivity());
		imageview.setImageResource(path);
		return imageview;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("INFO", "销毁index"+position);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("INFO", "销毁index"+position);
	}
	
	


	
	
	
}
