package com.tz.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment 碎片【子Activity】
 */
public class MyFragment extends Fragment implements OnClickListener {

	private MainActivity activity;
	private TextView tv;

	// 创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Fragment", "create");
	}

	// 绑定，Fragment它是Activity的一部分，附着在Activity上面的
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.i("Fragment", "attach");
	}
	
	//局部Fragment创建完成了之后，再创建Activity
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activity = (MainActivity) this.getActivity();
		ImageView img = new ImageView(this.getActivity());
		img.setImageResource(R.drawable.mm);
		img.setOnClickListener(this);
		Log.i("Fragment", "createView");
		return img;
		// return super.onCreateView(inflater, container, savedInstanceState);
	}

	// Activity创建完成了之后调用
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("Fragment", "activityCreated");
		//此处要注意，如果写在onCreateView或是onCreate中，会报空指针
		tv = activity.getTv();
		tv.setText("How are you");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Fragment", "start");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("Fragment", "resume");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Fragment", "pause");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("Fragment", "stop");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Fragment", "destroy");
	}

	// 解绑
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("Fragment", "detach");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(this.getActivity(), "hello world", Toast.LENGTH_SHORT).show();
	}

}
