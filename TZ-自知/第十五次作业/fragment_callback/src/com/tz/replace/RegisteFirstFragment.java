package com.tz.replace;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tz.replace.R;

public class RegisteFirstFragment extends Fragment {
	private EditText userName,password;
	private YanZhengPhone yzp;
	private MainActivity mainActivity;
	
	public EditText getUserName() {
		return userName;
	}

	public void setUserName(EditText userName) {
		this.userName = userName;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}
	
	public RegisteFirstFragment(YanZhengPhone yzp) {
		this.yzp = yzp;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivity = (MainActivity) getActivity();
	}

	private Handler handler = new Handler();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	} 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.regist_first_layout, null);
		userName = (EditText) view.findViewById(R.id.et_username);
		password = (EditText) view.findViewById(R.id.et_password);
		initListener();
		return view;
	}

	private void initListener() {
		userName.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.toString().startsWith("9")) {
					yzp.tixing(s.toString().substring(0, 1));
					handler.postDelayed(new Runnable() {
						public void run() {
							userName.setText("");
							userName.setEnabled(true);
						}
					}, 1000);
					userName.setEnabled(false);
				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			public void afterTextChanged(Editable s) {
			}
		});
	}

}
