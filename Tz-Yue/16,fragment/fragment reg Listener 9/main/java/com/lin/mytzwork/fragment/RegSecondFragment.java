package com.lin.mytzwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lin.mytzwork.R;
import com.lin.mytzwork.util.ReflectUtil;

/**
 * Created by Administrator on 2015/6/27.
 */
public class RegSecondFragment extends Fragment {
    private EditText et1;
    private EditText et2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.res_fragment, null);
        ReflectUtil.initView(this, inflate);

        et1.setHint("请输入真实姓名");
        et2.setHint("地址");

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
}
