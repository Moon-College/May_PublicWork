package com.lin.mytzwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lin.mytzwork.R;
import com.lin.mytzwork.util.ReflectUtil;

/**
 * Created by Administrator on 2015/6/27.
 */
public class RegFistFragment extends Fragment implements TextWatcher {

    private EditText et1;
    private EditText et2;
    private OnInput9Listener input9Listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.res_fragment, null);

        ReflectUtil.initView(this, inflate);

        et1.setHint("请输入用户名");
        et1.addTextChangedListener(this);
        et2.setHint("请输入密码");
        et2.setSingleLine();
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        return inflate;
    }


    public EditText getEt1() {
        return et1;
    }

    public void setEt1(EditText et1) {
        this.et1 = et1;
    }

    public EditText getEt2() {
        return et2;
    }

    public void setEt2(EditText et2) {
        this.et2 = et2;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().indexOf("9") != -1 && input9Listener != null) {
            input9Listener.is9(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface OnInput9Listener {
        void is9(String s);
    }


    public void setInput9Listener(OnInput9Listener input9Listener) {
        this.input9Listener = input9Listener;
    }
}
