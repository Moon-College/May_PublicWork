package com.tz.michael.activity;

import com.tz.michael.custom.TwoWaySeekView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class High_CustomViewActivity extends Activity implements OnClickListener {
	
	private TwoWaySeekView twsv;
	private Button btn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        twsv=(TwoWaySeekView) findViewById(R.id.twsv);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

	public void onClick(View v) {
		twsv.setxDivideY(1/2.0f);
		twsv.setTextXAddscall(1/4.0f);
		twsv.setTextSize(16);
		twsv.setTextColor(Color.RED);
	}
}