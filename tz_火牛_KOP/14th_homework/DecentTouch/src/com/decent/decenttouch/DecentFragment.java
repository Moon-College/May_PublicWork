package com.decent.decenttouch;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class DecentFragment extends Fragment implements OnClickListener {

	private Button btn;
	private DecentTouchActivity dtActivity;
	private static final String TAG = "DecentFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "...into onCreateView...");
		/*
		 * ���R.layout.fragment��view������
		 */
		View v = inflater.inflate(R.layout.fragment, container, false);
		/*
		 * �ҵ�btn
		 */
		btn = (Button) v.findViewById(R.id.btn);
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.d(TAG, "...into onAttach...");		
		/*
		 * ��attach��ʱ��ֱ�Ӽ�¼��Activity������
		 */
		dtActivity = (DecentTouchActivity)activity;
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "...into onActivityCreated...");	
		// TODO Auto-generated method stub
		/*
		 * ��activity created���֮������OnClickListener
		 */
		btn.setOnClickListener(this);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "...into onCreate...");	
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "...into onClick...");	
		// TODO Auto-generated method stub
		/*
		 * ֱ�ӵ��ø�activity��public������reset��Ƭ
		 */
		dtActivity.resetPicture();
	}

	
}
