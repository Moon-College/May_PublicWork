package com.yl.reflection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yl.tools.InitView;

public class MationActivity extends Activity {
    private TextView txt_one,txt_two,txt_three,txt_four;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        InitView.init(this);
        
    }
    public void onClick(View v){
    	Toast.makeText(this, txt_one.getText(), Toast.LENGTH_LONG).show();
    }
}