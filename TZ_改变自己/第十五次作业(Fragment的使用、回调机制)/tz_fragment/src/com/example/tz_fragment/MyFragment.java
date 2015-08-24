package com.example.tz_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MyFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("wdj","onCreateView");
		ImageView image = new ImageView(getActivity());
		image.setImageResource(R.drawable.aa);
		return image;
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.e("wdj","onAttach");
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("wdj","onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e("wdj","onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onPause() {
		Log.e("wdj","onPause");
		super.onPause();
	}
	
	@Override
	public void onResume() {
		Log.e("wdj","onResume");
		super.onResume();
	}
	
	@Override
	public void onStart() {
		Log.e("wdj","onStart");
		super.onStart();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
