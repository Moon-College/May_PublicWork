package com.cn.test.fragments;

import com.cn.test.R;
import com.cn.test.fragments.*;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MyFragment extends Fragment implements OnTouchListener{
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment attach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment create");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment destroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment detach");
		super.onDetach();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment pause");
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment resume");
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment start");
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment stop");
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("INFO", "fragment createView");
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(R.drawable.liutao);
		img.setOnTouchListener(this);
		return img;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "刘涛美女", 1000).show();
		return false;
	}
}
