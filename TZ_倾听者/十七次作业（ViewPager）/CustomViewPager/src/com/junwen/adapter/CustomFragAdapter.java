package com.junwen.adapter;

import com.junwen.ui.CustomFragMent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragAdapter extends FragmentPagerAdapter{

	private int [] imgs;
	public CustomFragAdapter(FragmentManager fm,int [] imgs) {
		super(fm);
		this.imgs = imgs;
	}

	@Override
	public Fragment getItem(int position) {
		CustomFragMent frag = new CustomFragMent();
		Bundle bundle = new Bundle();
		bundle.putInt("img", imgs[position]);
		frag.setArguments(bundle);
		return frag;
	}

	@Override
	public int getCount() {
		return imgs.length;
	}

	
}
