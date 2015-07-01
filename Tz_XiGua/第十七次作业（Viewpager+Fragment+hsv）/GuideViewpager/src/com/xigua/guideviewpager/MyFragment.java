package com.xigua.guideviewpager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyFragment extends Fragment{
	private int pic;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
		pic = bundle.getInt("pic");
		ImageView img = new ImageView(this.getActivity());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		img.setLayoutParams(params);
		img.setScaleType(ScaleType.CENTER_INSIDE);
		BitmapFactory.Options options = new Options();
		options.inSampleSize = 2;
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pic, options);
		img.setImageBitmap(bitmap);
		return img;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
}
