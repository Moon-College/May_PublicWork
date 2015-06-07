package com.tz.reflection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	@IocAnnotation(R.id.btn)
    private Button btn;
    @IocAnnotation(R.id.tv)
    private TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initUI();
    }

	private void initUI() {
		reflectionUtil.reflectionUI(this);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			tv.setText("恭喜你，中了五百万！");
			break;
		default:
			break;
		}
	}
}