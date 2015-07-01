package com.rocy.tzclass14;

import com.rocy.tzclass14.listener.OnMyClickListener;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment {
	private OnMyClickListener mOnMyClickListener;
	private TextView tv;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// 创建视图层
    	 View view = inflater.inflate(R.layout.myfragment, null);
    	 tv =(TextView) view.findViewById(R.id.tv);
    	return view;
    }
      
      @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	  
    	super.onActivityCreated(savedInstanceState);
    }
      @Override
    public void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View vv) {
				// TODO Auto-generated method stub
				mOnMyClickListener.OnMyClick(vv);
			}
		});
    }

	/**
	 * @return the mOnMyClickListener
	 */
	public OnMyClickListener getmOnMyClickListener() {
		return mOnMyClickListener;
	}

	/**
	 * @param mOnMyClickListener the mOnMyClickListener to set
	 */
	public void setmOnMyClickListener(OnMyClickListener mOnMyClickListener) {
		this.mOnMyClickListener = mOnMyClickListener;
	}

	
      
      
}
