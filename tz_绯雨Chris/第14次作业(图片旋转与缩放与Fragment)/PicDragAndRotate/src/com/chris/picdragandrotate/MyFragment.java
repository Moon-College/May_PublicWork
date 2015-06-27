package com.chris.picdragandrotate;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment
{

	private static final String tag = "MyFragment";
	private TextView tv;

	@Override
	public void onAttach(Activity activity)
	{
		Log.e(tag, "onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.e(tag, "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.e(tag, "onCreateView");
		tv = (TextView) inflater.inflate(R.id.tv, null);
		container.addView(tv);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		Log.e(tag, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart()
	{
		Log.e(tag, "onStart");
		super.onStart();
	}

	@Override
	public void onResume()
	{
		Log.e(tag, "onResume");
		super.onResume();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		Log.e(tag, "onConfigurationChanged");
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onPause()
	{
		Log.e(tag, "onPause");
		super.onPause();
	}

	@Override
	public void onStop()
	{
		Log.e(tag, "onPause");
		super.onStop();
	}

	@Override
	public void onDestroyView()
	{
		Log.e(tag, "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy()
	{
		Log.e(tag, "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach()
	{
		Log.e(tag, "onDetach");
		super.onDetach();
	}

}
