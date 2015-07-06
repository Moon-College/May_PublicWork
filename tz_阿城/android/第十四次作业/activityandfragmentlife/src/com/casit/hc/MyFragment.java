package com.casit.hc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
//里面想怎么加载就怎么加载
public class MyFragment extends Fragment implements OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(R.drawable.girl2);
		img.setOnClickListener(this);
		Log.i("INFO", "FragmentoncreateView");
		// TODO Auto-generated method stub
		
		return img;
	}

	public void onClick(View v) {
		Toast.makeText(this.getActivity(), "MyFragment", 3000);
		// TODO Auto-generated method stub
		this.getActivity().finish();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		Log.i("INFO", "Fragmentoncreate");	
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub

		super.onAttach(activity);
		Log.i("INFO", "Fragmentonattach");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("INFO", "FragmentoActivityncreate");

	}
	
	@Override
	public void onPause() {

		// TODO Auto-generated method stub
		super.onPause();
		Log.i("INFO", "Fragmentonpause");
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		Log.i("INFO", "FragmentonStop");
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub

		super.onStart();
		Log.i("INFO", "Fragmentonstart");
	}
	@Override
	public void onResume() {

		// TODO Auto-generated method stub
		super.onResume();
		Log.i("INFO", "FragmentonResume");
	}
	@Override
	public void onDestroy() {

		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("INFO", "FragmentonDestroy");
	}
	@Override
	public void onDetach() {

		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("INFO", "FragmentonDetach");
	}
	
	
	
}
