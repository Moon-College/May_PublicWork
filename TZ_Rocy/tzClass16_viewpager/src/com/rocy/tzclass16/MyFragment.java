package com.rocy.tzclass16;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {
    
	private int  rId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle bundle = getArguments();
		if(bundle != null){
		rId	= bundle.getInt("rId");
		}
		ImageView image =new ImageView(getActivity());
		image.setImageResource(rId);
		return image;
	}
}
