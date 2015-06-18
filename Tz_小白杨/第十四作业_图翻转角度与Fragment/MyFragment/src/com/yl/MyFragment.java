package com.yl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyFragment extends Fragment implements OnClickListener {
	private Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment, null);
		btn=(Button) view.findViewById(R.id.btn);
		btn.setOnClickListener(this);
		return  view;
	}
	public void onClick(View v) {
		Toast.makeText(this.getActivity(), "this is my button", 1000).show();
	}
}
