package com.casit.hc.fragment;

import com.casit.hc.FragmentReplaceActivity;
import com.casit.hc.R;
import com.casit.hc.mylistener.OnMyListener;
import com.casit.hc.myview.MyEditText;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterFirstFragment extends Fragment implements OnMyListener{

	MyEditText userAccount;
	EditText userPassword;
	private FragmentReplaceActivity fragmentReplaceActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.regist_first_layout, null);
		userAccount = (MyEditText) view.findViewById(R.id.et_regist_username);
		userPassword = (EditText) view.findViewById(R.id.et_regist_password);
		userAccount.setOnMyListener(this);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		fragmentReplaceActivity = (FragmentReplaceActivity) this.getActivity();
	}
	public MyEditText getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(MyEditText userAccount) {
		this.userAccount = userAccount;
	}
	public EditText getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(EditText userPassword) {
		this.userPassword = userPassword;
	}
	public void onText(String str) {
		Log.i("INFO", str);
		// TODO Auto-generated method stub
		if(str.length()!=0&&Integer.valueOf(str)==9){//str!=null&&str=="9"){
			String mystr = "输入9，不合法";
			EditText editText = new EditText(this.getActivity());
			editText.setText(mystr);
		//	Log.i("INFO", "onText");
		//	Toast.makeText(this.getActivity(), mystr, 3000);
			LayoutInflater inflater = LayoutInflater.from( (FragmentReplaceActivity)this.getActivity());
			View view = inflater.inflate(R.layout.main, null);
			LinearLayout ll  = (LinearLayout) view.findViewById(R.id.ll);
			ll.addView(editText);
			this.getActivity().setContentView(view);
			
		}
	}
}
