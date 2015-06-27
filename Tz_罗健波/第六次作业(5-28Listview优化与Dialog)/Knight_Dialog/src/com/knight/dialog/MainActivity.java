package com.knight.dialog;

import com.knight.dialog.ref.RefInitView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button dialog_one,dialog_two,dialog_three;
	private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RefInitView.findViewById(this);
        inflater = LayoutInflater.from(this);
        dialog_one.setOnClickListener(this);
        dialog_two.setOnClickListener(this);
        dialog_three.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_dialog1:
			ordinary(MainActivity.this);
			break;
		case R.id.btn_dialog2:
			customDialog(MainActivity.this);
			break;
		case R.id.btn_dialog3:
			progress(MainActivity.this);
			break;
		default:
			break;
		}
		
	}
	private void progress(final Context context) {
		ProgressDialog pDialog = new ProgressDialog(context);
		pDialog.setMax(100);
		pDialog.setMessage("loading...");
		pDialog.setTitle("更新");
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.show();
		pDialog.setProgress(40);
		
	}
	private void customDialog(final Context context) {
		AlertDialog.Builder customBuilder = new AlertDialog.Builder(context);
		View view = inflater.inflate(R.layout.custom_dialog, null);
		final EditText eAccount = (EditText) view.findViewById(R.id.edt1);
		final EditText ePassword = (EditText) view.findViewById(R.id.edt2);
		customBuilder.setView(view);
		customBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, eAccount.getText().toString(), 1000).show();
			}
		});
		customBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, ePassword.getText().toString(), 1000).show();
			}
		});
		AlertDialog custom = customBuilder.create();
		custom.show();
		
	}
	private void ordinary(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("更新");
		builder.setMessage("发现新版本是否更新");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "已取消", 1000).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "确定", 1000).show();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
		
	}

}
