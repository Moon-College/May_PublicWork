package com.dd.rotate_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment extends android.support.v4.app.Fragment{

	private DhhFragmentActivity activity;
	private TextView textView;
	int index =0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (DhhFragmentActivity) getActivity();
		textView = new TextView(getActivity());
		textView.setText("点我变");
		return textView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final TextView textViewDhh = activity.getTextView();
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				textViewDhh.setText("变了"+index);
				index ++;
			}
		});
	}
}
