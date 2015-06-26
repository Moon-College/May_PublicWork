package com.casit.hc;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Lesson3Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
       LinearLayout ll = new LinearLayout(this);
       LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
       ll.setLayoutParams(lp);
      // ll.setOrientation(orientation);
       EditText ed = new EditText(this);
       LinearLayout.LayoutParams llp =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,1);
       ed.setLayoutParams(llp);
       
       Button bt =  new Button(this);
       llp =  new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
      // bt.setLayoutParams(llp);
       bt.setTextColor(255);
       bt.setText("¿Ø¼þ");
       
       ll.addView(ed, 0);
       ll.addView(bt, 1);
       setContentView(ll);
       
       
       
      
       
        
    }
}