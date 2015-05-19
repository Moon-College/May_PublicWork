package com.chris.logmsgcol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.chris.utils.CLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	public static final String TAG = "LogMsgCollection";
	TextView msgText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CLog.i(TAG, "onCreate");
		Button log2toast = (Button) findViewById(R.id.log2toast);
		Button log2text = (Button) findViewById(R.id.log2text);
		Button clearMsg = (Button) findViewById(R.id.clearMsg);
		msgText = (TextView) findViewById(R.id.logtext);

		log2toast.setOnClickListener(this);
		log2text.setOnClickListener(this);
		clearMsg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		StringBuffer strbuf = new StringBuffer();
		Process result = null;

		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-ds");
		cmdLine.add("*:W");
		try
		{
			//process the command
			result = excuteCmdLine(cmdLine);
			// get the command result
			readCmdResults(result, strbuf);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		Button btn = (Button) v;
		switch (btn.getId())
		{
		case R.id.log2toast:
			Toast.makeText(MainActivity.this, strbuf.toString(), Toast.LENGTH_LONG).show();
			break;
		case R.id.log2text:
			msgText.setText(strbuf);
			break;
		case R.id.clearMsg:
			msgText.setText("");
			break;
		default:
			break;
		}

	}

	private int readCmdResults(Process result, StringBuffer strbuf) throws IOException
	{
		InputStream inStream = result.getInputStream();
		InputStreamReader inReader = new InputStreamReader(inStream);
		BufferedReader bufReader = new BufferedReader(inReader);
		String tempStr;
		while ((tempStr = bufReader.readLine()) != null)
		{
			strbuf.append(tempStr);
			strbuf.append("\n");
		}
		return strbuf.length();
	}

	private Process excuteCmdLine(ArrayList<String> cmdLine) throws IOException
	{
		return Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//return Runtime.getRuntime().exec(cmdLine.toString()); //this will make string to "logcat, -ds, *:W"
	}
}
