package com.decentsoft.hellouigroup;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloUIGroupActivity extends Activity implements OnClickListener {
	private static final String TAG = "HelloUIGroupActivity";
	private RelativeLayout rl;
	private LinearLayout ll;
	private EditText et;
	private Button bt;
	private ImageView iv;
	private ScrollView sv;
	private ArrayList<TextView> etAl= null;
	private LinearLayout sLl;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        rl = new RelativeLayout(this);
        
        ll =new LinearLayout(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
        rlp.setMargins(1, 1, 0, 0);
        ll.setLayoutParams(rlp);
        ll.setId(0);
        
        et =new EditText(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(llp);
        et.setHint("input search word");
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        //et.setId(1);
        //InputFilter[1] filters=new HelloUIGroupActivity();
		//et.setFilters(filters);
        
        ll.addView(et,0);
        
        bt =new Button(this);
        LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
        bt.setLayoutParams(llp2);
        bt.setText("+");
        ll.addView(bt,1);
        bt.setOnClickListener(this);
        
        rl.addView(ll, 0);
        
        //Õº∆¨œ‘ æ
        iv = new ImageView(this);
        
        RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        //rlp2.addRule(RelativeLayout.CENTER_HORIZONTAL,1);
        //rlp2.addRule(RelativeLayout.CENTER_VERTICAL,1);
        rlp2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        iv.setLayoutParams(rlp2);
        iv.setId(1);
        
        iv.setBackgroundResource(R.drawable.ic_launcher);
        
        rl.addView(iv, 1);
        
        sv = new ScrollView(this);
        RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(100,200);
        rlp3.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        rlp3.addRule(RelativeLayout.BELOW, 1); 
        sv.setLayoutParams(rlp3);
        sv.setId(3);
        
        rl.addView(sv,2);
        
        sLl = new LinearLayout(this);
        sLl.setOrientation(LinearLayout.VERTICAL);
        sv.addView(sLl, 0);
        
        setContentView(rl);
    }

	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		String text = et.getText().toString().trim();
		if("".equals(text))
		{
			Toast.makeText(this, "1~10!!!", Toast.LENGTH_LONG).show();
			return;
		}
		int value = (int)Integer.valueOf(text);
		if( value < 1  || value > 10)
		{
			//Toast t =new Toast(this);
			Toast.makeText(this, "value "+value+" is invalid,1~10!!!", Toast.LENGTH_LONG).show();
			return;
		}
		addEditTextToRl(value);
	}

	/**
	 * 
	 * @param value
	 */
	private void addEditTextToRl(int value)
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "value = "+value);
		for(int i = 0;i<value;i++)
		{
			if(etAl == null)
			{
				etAl = new ArrayList<TextView>();
			}
	        int num = sLl.getChildCount();
	        
	        TextView localEt =new TextView(this);
			RelativeLayout.LayoutParams localRlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			//localRlp.addRule(RelativeLayout.BELOW, num-1);
	        localEt.setLayoutParams(localRlp);  	

	      
	        localEt.setText(""+(num+1));
	        localEt.setId(num);
			etAl.add(localEt);
			sLl.addView(localEt,num);
		}
	}
}