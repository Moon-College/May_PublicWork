package com.ztt.ch3;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
    private Context mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
                
                LinearLayout linearLayout = new LinearLayout(this);
                LayoutParams lp =
                    new LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.FILL_PARENT);
                linearLayout.setLayoutParams(lp);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                
                EditText editText = new EditText(this);
                LinearLayout.LayoutParams lpText =
                    new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                editText.setLayoutParams(lpText);
                
                Button button = new Button(this);
                button.setText("ËÑË÷");
                button.setTextSize(12);
                LinearLayout.LayoutParams lpButton =
                    new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                button.setLayoutParams(lpButton);
                
                
                linearLayout.addView(editText, 0);
                linearLayout.addView(button, 1);
                setContentView(linearLayout);
        
        
    }
    
}
