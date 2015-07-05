 package com.org.viewpager.fragment;

import com.example.home_viewpgager_frgment.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MyFragment  extends Fragment {
    private int index;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		int path = this.getArguments().getInt("Path"); //»ñÈ¡Âé²¼´ü
		//index = this.getArguments().getInt("Index");
		ImageView images = new  ImageView(this.getActivity());
        images.setImageResource(path);
         return images;
	}
	
	private void onpouse() {
        
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
