package com.lin.mytzwork.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mytzwork.MainActivity;
import com.lin.mytzwork.R;

/**
 * Created by Administrator on 2015/6/25.
 */
public class MyFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView imageView = new ImageView(this.getActivity());
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setOnClickListener(this);
        return imageView;
    }


    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getActivity();


        TextView tv = activity.getTv();
        tv.setText("我是fragment改变的");
    }
}
