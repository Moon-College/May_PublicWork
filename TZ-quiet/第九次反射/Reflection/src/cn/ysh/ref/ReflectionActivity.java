package cn.ysh.ref;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReflectionActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView tv_one,tv_two,tv_three;
	private String str; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ReflectionUtils.initView(this , "findViewById");
    }
    
    public void show(View v){
    	Toast.makeText(this, tv_two.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}