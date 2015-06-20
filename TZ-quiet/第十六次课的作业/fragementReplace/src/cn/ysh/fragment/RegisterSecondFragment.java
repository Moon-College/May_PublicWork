package cn.ysh.fragment;

import cn.ysh.fragementreplace.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterSecondFragment extends Fragment{
	private EditText name , address;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, null);
		name = (EditText) view.findViewById(R.id.name);
		address = (EditText) view.findViewById(R.id.address);
		return view;
	}
	
	public EditText getName() {
		return name;
	}
	public EditText getAddress() {
		return address;
	}
}
