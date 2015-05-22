package com.yanqiangfire.helloui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class HelloUIActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        LinearLayout ll =new LinearLayout(this);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(lp);
        
        EditText et =new EditText(this);
        LinearLayout.LayoutParams etLlp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1);
        ll.addView(et,0);
        

        Button bt =new Button(this);
        LinearLayout.LayoutParams bLlp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
        bt.setLayoutParams(bLlp);
        bt.setText("go home");
        ll.addView(bt, 1);
        
        et.setLayoutParams(etLlp);
        
        setContentView(ll);
    }
}