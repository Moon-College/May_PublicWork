package com.tz.michael.fragment;

import com.tz.michael.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {

	final String TAG="TestFragment";
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "FRAGMENT--onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "FRAGMENT--onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "FRAGMENT--onCreateView");
		View v=inflater.inflate(R.layout.fg_second, container, false);
		
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "FRAGMENT--onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, "FRAGMENT--onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "FRAGMENT--onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, "FRAGMENT--onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, "FRAGMENT--onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, "FRAGMENT--onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "FRAGMENT--onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, "FRAGMENT--onDetach");
	}

	
}
