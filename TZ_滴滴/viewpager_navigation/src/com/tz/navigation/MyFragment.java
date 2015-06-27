package com.tz.navigation;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Bundle bundle = this.getArguments();
		path = bundle.getInt("path");
		position = bundle.getInt("position");
		
		Log.i("INFO", "´´½¨fragment:"+position);
		
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(path);
		return img;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("INFO", "Ïú»Ùfragment:"+position);
	}
}
