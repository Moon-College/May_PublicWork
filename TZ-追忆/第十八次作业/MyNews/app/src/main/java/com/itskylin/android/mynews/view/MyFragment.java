package com.itskylin.android.mynews.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Blue_Sky on 2015/6/27.
 */
public class MyFragment extends Fragment{
    private int path;
    private int position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        path = bundle.getInt("path");
        position = bundle.getInt("position");
        Log.i("INFO", "创建fragment:" + position);
        ImageView img = new ImageView(this.getActivity());
        img.setImageResource(path);
        return img;
    }


}
