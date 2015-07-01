package com.lin.mytzwork.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/6/29.
 */
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        int position = bundle.getInt("position");
        int image = bundle.getInt("image");


        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(image);


        return imageView;
    }
}
