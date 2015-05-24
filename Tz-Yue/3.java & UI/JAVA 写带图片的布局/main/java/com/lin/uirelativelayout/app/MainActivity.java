package com.lin.uirelativelayout.app;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout head_layout = new LinearLayout(this);
        head_layout.setOrientation(LinearLayout.HORIZONTAL);
        head_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        Button button = new Button(this);
        button.setText("登陆");
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        head_layout.addView(editText, 0);
        head_layout.addView(button, 1);
        layout.addView(head_layout, 0);


        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams imgLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imgLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(imgLp);
        imageView.setImageResource(R.mipmap.ic_launcher);
        layout.addView(imageView, 1);


        setContentView(layout);
    }


    @Override
    protected void onPause() {
        int a = 1;
        super.onPause();
        a = 2;
    }
}



