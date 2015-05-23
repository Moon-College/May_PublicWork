package com.limz.layoutoneproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class LayoutOneProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams reLayoutParams = new RelativeLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(reLayoutParams);
        //²¼¾ÖËÑË÷±à¼­¿òºÍËÑË÷°´Å¥
        LinearLayout layout = new LinearLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.setLayoutParams(params);
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        editText.setHint("ÇëÊäÈëÍøÖ·");
        editText.setLayoutParams(layoutParams);
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(layoutParams2);
        button.setText("ËÑË÷");
        button.setTextSize(30);
        layout.addView(editText);
        layout.addView(button);
        
        //²¼¾ÖËÑË÷±à¼­¿òºÍËÑË÷°´Å¥
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setImageResource(R.drawable.image);
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(imageParams);
        
        relativeLayout.addView(layout);
        relativeLayout.addView(imageView);
        setContentView(relativeLayout);
//        setContentView(R.layout.main);
    }
}