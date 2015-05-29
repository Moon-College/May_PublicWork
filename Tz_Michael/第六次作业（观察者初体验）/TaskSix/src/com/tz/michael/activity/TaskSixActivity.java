package com.tz.michael.activity;

import com.tz.michael.activity.utils.AbstractObServer;
import com.tz.michael.activity.utils.RealObServerable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 观察者模式的测试
 * @author szm
 *
 */
public class TaskSixActivity extends Activity implements OnClickListener, AbstractObServer {
	
	private EditText et;
	private Button btn;
	/**被观察者*/
	private RealObServerable obServerable;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et=(EditText) findViewById(R.id.et);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(et.getText().toString().trim().length()!=0){
			obServerable=new RealObServerable();
			if(Integer.valueOf(et.getText().toString().trim())==2){
				obServerable.registerObServer(this);
				onChanged();
			}else{
				Toast.makeText(this, "输入2试一下", 0).show();
			}
		}else{
			Toast.makeText(this, "请输入数字", 0).show();
		}
	}

	public void onChanged() {
		Log.e("ss--", "监听到了");
		Toast.makeText(this, "Danny is so 2!!! haha", 0).show();
	}
}