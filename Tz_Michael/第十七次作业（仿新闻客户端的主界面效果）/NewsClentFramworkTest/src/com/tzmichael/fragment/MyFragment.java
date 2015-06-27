package com.tzmichael.fragment;

import com.tzmichael.activity.R;
import com.tzmichael.activity.TestCombinViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyFragment extends Fragment {

	private int position;
	private int[] drawables=new int[]{R.drawable.fg1_drawable,R.drawable.fg2_drawable,R.drawable.fg3_drawable,R.drawable.fg4_drawable,R.drawable.fg5_drawable,R.drawable.fg6_drawable,R.drawable.fg7_drawable};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle=this.getArguments();
		position=bundle.getInt("position");
		Log.i("pp--", "--====---"+position);
		LinearLayout lay=(LinearLayout) inflater.inflate(R.layout.fg_1_layout, container, false);
		lay.setBackgroundResource(drawables[position]);
		TextView tv=(TextView) lay.findViewById(R.id.tv_fg);
		tv.setBackgroundResource(drawables[position]);
		tv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),TestCombinViewActivity.class));
			}
		});
		return lay;
	}
	
	
	
}
