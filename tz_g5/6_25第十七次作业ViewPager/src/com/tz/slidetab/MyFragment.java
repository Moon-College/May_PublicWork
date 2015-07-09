package com.tz.slidetab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/6/27 0027.
 */
public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        Bundle bundle = getArguments();
        int postition = bundle.getInt("position");
        textView.setText(postition + "");
        return textView;
    }
}
