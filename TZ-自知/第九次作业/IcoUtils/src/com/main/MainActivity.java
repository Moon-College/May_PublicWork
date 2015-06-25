package com.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.icoutils.R;

public class MainActivity extends Activity {
	@InjectionName private EditText et_txt;
	@InjectionName(values="show") private Button btn;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			IcoUtilsCommon.Doinit(this);
		}
		
		public void show() {
			System.out.println("QWeqweqw爱上大声大声道e"+et_txt.getText());
		}
	
}
