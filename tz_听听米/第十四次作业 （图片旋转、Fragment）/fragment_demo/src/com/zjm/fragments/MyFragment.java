package com.zjm.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MyFragment extends Fragment {
	
	@Override
	public void onAttach(Activity activity) {
		Log.i("INFO", "Fragment onAttach");
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i("INFO", "Fragment onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("INFO", "Fragment onCreateView");
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(R.drawable.ic_launcher);
		return img;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i("INFO", "Fragment onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Log.i("INFO", "Fragment onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.i("INFO", "Fragment onResume");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		Log.i("INFO", "Fragment onPause");
		super.onPause();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i("INFO", "Fragment onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onStop() {
		Log.i("INFO", "Fragment onStop");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Log.i("INFO", "Fragment onDestroyView");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.i("INFO", "Fragment onDestroy");
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		Log.i("INFO", "Fragment onDetach");
		super.onDetach();
	}

}
