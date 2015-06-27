package com.tzmichael.adapter;

import com.tzmichael.fragment.MyFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyVPAdapter extends FragmentStatePagerAdapter {

	public MyVPAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		MyFragment fg=new MyFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("position", position);
		fg.setArguments(bundle);
		return fg;
	}

	@Override
	public int getCount() {
		return 7;
	}

	
}
