/**
 * Project Name:lsn16_fragment
 * File Name:RegisterFragment.java
 * Package Name:com.zht.fragment
 * Date:2015-6-19ÏÂÎç5:44:36
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * ClassName:RegisterFragment <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-19 ÏÂÎç5:44:36 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class RegisterFragment extends Fragment {
	private EditText et_name;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register_fragment, null);
		et_name = (EditText) view.findViewById(R.id.et_username);
		et_name.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("INFO", "notice:   "+s);
				if("9".equals(String.valueOf(s))){
					MyNoticeListener listener = (MyNoticeListener) getActivity();
				    listener.notice(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}

}
