package cn.ysh.fragment;

import cn.ysh.callback.myview.MyView;
import cn.ysh.fragementreplace.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterFirstFragment extends Fragment{
	private MyView telnumber;
	private EditText  password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_first, null);
		telnumber = (MyView) view.findViewById(R.id.telnumber);
		password = (EditText) view.findViewById(R.id.password);
		return view;
	}
	
	
	public MyView getTelnumer() {
		return telnumber;
	}

	public EditText getPassword() {
		return password;
	}
}
