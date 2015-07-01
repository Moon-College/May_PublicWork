package cn.ysh.fragment;

import cn.ysh.fragment_viewpager.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_view, null);
		ImageView im = (ImageView) v.findViewById(R.id.fg_view);
		im.setImageResource(getArguments().getInt("position"));
		return v;
	}
}
