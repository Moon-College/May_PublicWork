package com.tz.callback;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by qinhan on 15/6/23.
 */
public class LoginFragment extends Fragment implements TextWatcher {
    private MyActivity activity;

    public static final String TAG = "LoginFragment";

    private EditText et_account;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_login, null);
        et_account = (EditText) rootView.findViewById(R.id.et_account);
        et_account.addTextChangedListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MyActivity) getActivity();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        activity.call(s);
    }
}
