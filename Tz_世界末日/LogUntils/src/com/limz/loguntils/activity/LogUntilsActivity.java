package com.limz.loguntils.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.limz.loguntils.untils.Untils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LogUntilsActivity extends Activity implements OnClickListener {
	private Button button;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(this);
        Untils.isLogTrunOn = true;
    }
    
	public void onClick(View v) {
		Untils.w("LogUntilsActivity", "Log start");
		ArrayList<String> list = new ArrayList<String>();
		list.add("logcat");
		list.add("-d");
		list.add("*:W");
		try {
			Process process = Runtime.getRuntime().exec((String[]) list.toArray(new String[list.size()]));
			InputStream inputStream = process.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String string = null;
			StringBuffer myStringBuffer = new StringBuffer();
			while( (string = bufferedReader.readLine()) != null) {
				myStringBuffer.append(string);
				myStringBuffer.append("\n");
			}
			Untils.e("LogUntilsActivity", "Log end");
			Toast.makeText(this, myStringBuffer.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}