package com.chris.fragmentreplacedemo.fragment;

import com.chris.fragmentreplacedemo.R;
import com.chris.fragmentreplacedemo.listener.OnTypingListener;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FirstFragment extends Fragment
{
	protected static final String tag = "FirstFragment";
	private EditText acount, password;
	private OnTypingListener typingListener;

	public OnTypingListener getOnTypingListener()
	{
		return typingListener;
	}

	public void setOnTypingListener(OnTypingListener typingListener)
	{
		this.typingListener = typingListener;
	}

	public EditText getAcount()
	{
		return acount;
	}

	public void setAcount(EditText acount)
	{
		this.acount = acount;
	}

	public EditText getPassword()
	{
		return password;
	}

	public void setPassword(EditText password)
	{
		this.password = password;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.regist_first_layout, null);
		acount = (EditText) view.findViewById(R.id.et_regist_username);
		password = (EditText) view.findViewById(R.id.et_regist_password);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		acount.addTextChangedListener(watcher);
		super.onActivityCreated(savedInstanceState);
	}

	TextWatcher watcher = new TextWatcher()
	{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			// TODO Auto-generated method stub
			Log.i(tag, "beforeTextChanged: s=" + s + " start=" + start + " count=" + count);
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			Log.i(tag, "onTextChanged: s=" + s + " start=" + start + " before=" + before + " count=" + count);

			if (typingListener != null)
			{
				typingListener.onTyping(acount.getText().toString(), s, start, before, count);
			}

		}

		@Override
		public void afterTextChanged(Editable s)
		{
			// TODO Auto-generated method stub
			Log.i(tag, "afterTextChanged: s=" + s);
		}
	};

}
