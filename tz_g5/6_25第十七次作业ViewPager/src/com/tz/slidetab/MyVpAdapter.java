package com.tz.slidetab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2015/6/27 0027.
 */
public class MyVpAdapter extends FragmentPagerAdapter {

    private List mList;

    public MyVpAdapter(FragmentManager fm, List mList) {
        super(fm);
        this.mList = mList;
    }



    @Override
    public Fragment getItem(int i) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
