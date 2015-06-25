package com.deccent.decentfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class PasswordFragment extends Fragment {

	private EditText username;
	private EditText password;

	public EditText getUsername() {
		return username;
	}

	public void setUsername(EditText username) {
		this.username = username;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * ���һ��������false��ʾ�Ƿ�attachToRoot��������Ҫ��false����Ȼ�ᱨ��
		 * ��Ϊ���view�ᱻattach��R.id.frameLayout�����������ﲻ��attach
		 */
		View view = inflater.inflate(R.layout.password, container, false);
		username = (EditText) view.findViewById(R.id.username);
		password = (EditText) view.findViewById(R.id.password);
		// TODO Auto-generated method stub
		return view;
	}

}
