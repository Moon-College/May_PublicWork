/**
 * Project Name:lsn18_viewpager_navigation
 * File Name:ViewPagerFragment.java
 * Package Name:com.zht.viewpagernavigation
 * Date:2015-6-29обнГ12:19:49
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.viewpagernavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * ClassName:ViewPagerFragment <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-29 обнГ12:19:49 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class ViewPagerFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
		ImageView image = new ImageView(getActivity());
		image.setImageResource(bundle.getInt("path"));
		return image;
	}
}
