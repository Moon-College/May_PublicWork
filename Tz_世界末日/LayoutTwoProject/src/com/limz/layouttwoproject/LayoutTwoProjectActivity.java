package com.limz.layouttwoproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LayoutTwoProjectActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */

	RelativeLayout relativeLayout;
	Button button;
	EditText editText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        layoutParams.topMargin = 10;
        relativeLayout.setLayoutParams(layoutParams);
        
        //布局Button
        button = new Button(this);
        button.setOnClickListener(this);
        button.setId(2);
        button.setText("搜索");
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        button.setLayoutParams(buttonParams);
        
        //布局EditText
        editText = new EditText(this);
        editText.setId(1);
        editText.setHint("请输入一个数字");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        RelativeLayout.LayoutParams editTextParams = new RelativeLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        editTextParams.addRule(RelativeLayout.LEFT_OF, 2);
        editTextParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        editText.setLayoutParams(editTextParams);
        
        relativeLayout.addView(button);
        relativeLayout.addView(editText);
        setContentView(relativeLayout);
//        setContentView(R.layout.main);
    }

	public void onClick(View v) {
		switch (v.getId()) {
			case 2:
				String num = editText.getText().toString();
				if(num == null || num.equals("")) {
					Toast.makeText(this, "你需要输入一个数字 ", Toast.LENGTH_LONG).show();
					return;
				}
				editText.setText("");
				Toast.makeText(this, "你输入的输入是 : " + num, Toast.LENGTH_LONG).show();
				int number = Integer.valueOf(num);
				for(int i=1; i<=number; i++) {
					TextView textView = new TextView(this);
					RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
			        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					textView.setText(String.valueOf(i));
					textView.setId(i+2);//为了避免和Button和EditText的ID冲突
					textParams.addRule(RelativeLayout.BELOW, i+1);
					textView.setLayoutParams(textParams);
					relativeLayout.addView(textView);
				}
				editText.setVisibility(View.VISIBLE);
				setContentView(relativeLayout);
				break;
	
			default:
				break;
		}
	}
}