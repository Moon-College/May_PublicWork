package com.chris.logmsgcol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.chris.logmsgcol.reflectutils.ReflectUtils;
import com.chris.utils.CLog;

import android.app.Activity;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private static final String TAG = "LogMsgCollection";
	private static final boolean isUseReflect = true; 
	private Button log2toast, log2text, clearMsg, constructors;
	private Button methods;
	private TextView msgText;
	private InputManager im;
	private long mDownTime;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CLog.setDebug(false);
		CLog.i(TAG, "onCreate");
		if (isUseReflect)
		{
			try
			{
				ReflectUtils.initViewsLayout(this);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			log2toast.setOnClickListener(this);
//			log2text.setOnClickListener(this);
//			clearMsg.setOnClickListener(this);
//			constructors.setOnClickListener(this);
//			methods.setOnClickListener(this);
		} else
		{
			log2toast = (Button) findViewById(R.id.log2toast);
			log2text = (Button) findViewById(R.id.log2text);
			clearMsg = (Button) findViewById(R.id.clearMsg);
			constructors = (Button) findViewById(R.id.constructors);
			methods = (Button) findViewById(R.id.methods);
			msgText = (TextView) findViewById(R.id.msgText);

			log2toast.setOnClickListener(this);
			log2text.setOnClickListener(this);
			clearMsg.setOnClickListener(this);
			constructors.setOnClickListener(this);
			methods.setOnClickListener(this);
		}
		im = (InputManager) this.getSystemService(Context.INPUT_SERVICE);
	}

	public static void get_Reflection_Constructors(InputManager im)
	{
		Class temp = im.getClass();
		String className = temp.getName(); // 获取指定类的类名
		Log.i(TAG, "className = " + className);

		try
		{
			Constructor<InputManager>[] theConstructors = temp.getDeclaredConstructors(); // 获取指定类的公有构造方法
			Log.i(TAG, "theConstructors.length = " + theConstructors.length);
			for (int i = 0; i < theConstructors.length; i++)
			{
				Log.i(TAG, "(" + i + ")" + "Constructors: " + theConstructors[i].toString());
				int mod = theConstructors[i].getModifiers(); // 输出修饰域和方法名称
				Log.i(TAG, "(" + i + ")" + "Modifier: " + Modifier.toString(mod) + " " + className + "(");

				Class[] parameterTypes = theConstructors[i].getParameterTypes(); // 获取指定构造方法的参数的集合
				for (int j = 0; j < parameterTypes.length; j++)
				{ // 输出打印参数列表
					Log.i(TAG, "(" + i + ")" + "parameterTypes: " + parameterTypes[j].getName());
					if (parameterTypes.length > j + 1)
					{
						Log.i(TAG, ", ");
					}
				}
				Log.i(TAG, ")");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Method[] get_Reflection_Method(InputManager im)
	{

		Class temp = im.getClass();
		String className = temp.getName();
		Log.i(TAG, "className = " + className);

		/*
		 * Note: 方法getDeclaredMethods()只能获取到由当前类定义的所有方法，不能获取从父类继承的方法
		 * 方法getMethods() 不仅能获取到当前类定义的public方法，也能得到从父类继承和已经实现接口的public方法
		 * 请查阅开发文档对这两个方法的详细描述。
		 */
		//Method[] methods = temp.getDeclaredMethods();
		Method[] methods = temp.getMethods();

		for (int i = 0; i < methods.length; i++)
		{
			// 打印输出方法的修饰域
			int mod = methods[i].getModifiers();
			Log.i(TAG, "(" + i + ")" + "Modifier: " + Modifier.toString(mod) + " ");

			// 输出方法的返回类型
			Log.i(TAG, "(" + i + ")" + "ReturnType: " + methods[i].getReturnType().getName());

			// 获取输出的方法名
			Log.i(TAG, "(" + i + ")" + "Methods: " + methods[i].getName() + "(");

			// 打印输出方法的参数列表
			Class[] parameterTypes = methods[i].getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++)
			{
				Log.i(TAG, "(" + i + ")" + "ParameterTypes: " + parameterTypes[j].getName());
				if (parameterTypes.length > j + 1)
				{
					Log.i(TAG, ", ");
				}
			}
			Log.i(TAG, ")");
		}
		return methods;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		CLog.d(TAG, "onKeyDown");
		CLog.d(TAG, "key = " + keyCode + ", action = " + event.getAction());
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v)
	{
		StringBuffer strbuf = new StringBuffer();
		Process result = null;
		try
		{
			result = excuteCmdLine("logcat", "-d", "*:W"); //process the command
			readCmdResults(result, strbuf); // get the command result
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		switch (v.getId())
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
		case R.id.constructors:
			get_Reflection_Constructors(im);
			break;
		case R.id.methods:
			//get_Reflection_Method(im);
			Log.d(TAG, "Truely Shutdown, send long power event: ");
			mDownTime = SystemClock.uptimeMillis();
			int truelySleepTime = ViewConfiguration.getLongPressTimeout();
			sendEvent(KeyEvent.ACTION_DOWN, 0, mDownTime);
			try
			{
				//Log.d(TAG,"Truely Shutdown, Sleep "+truelySleepTime+"ms");
				Thread.currentThread().sleep(truelySleepTime);
				sendEvent(KeyEvent.ACTION_DOWN, KeyEvent.FLAG_LONG_PRESS);
				finish();
			} catch (Exception e)
			{
			}
			break;
		default:
			Toast.makeText(this, "不要点我，我是TextView", Toast.LENGTH_LONG).show();
			break;
		}
	}

	void sendEvent(int action, int flags)
	{
		long when = 0;
		switch (flags)
		{
		case KeyEvent.FLAG_LONG_PRESS:
			when = mDownTime + ViewConfiguration.getLongPressTimeout();
		case 0:
		default:
			when = mDownTime + 200;
			break;
		}
		sendEvent(action, flags, when);
	}

	void sendEvent(int action, int flags, long when)
	{
		final int repeatCount = (flags & KeyEvent.FLAG_LONG_PRESS) != 0 ? 1 : 0;
		final KeyEvent ev = new KeyEvent(mDownTime, when, action, KeyEvent.KEYCODE_POWER, repeatCount, 0,
				KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
				flags | KeyEvent.FLAG_FROM_SYSTEM | KeyEvent.FLAG_VIRTUAL_HARD_KEY, InputDevice.SOURCE_KEYBOARD);

		//		InputManager.getInstance().injectInputEvent(ev, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
		Method[] meth = get_Reflection_Method(im);
		try
		{
			meth[15].invoke(im, new Object[]
			{ ev, 0 });
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * execute the command line
	 * 
	 * @param cmd
	 * @param options
	 * @param args
	 * @return cmd exec result, as Process type
	 * @throws IOException
	 */
	private Process excuteCmdLine(String cmd, String options, String args) throws IOException
	{
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add(cmd);
		cmdLine.add(options);
		cmdLine.add(args);
		return Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//return Runtime.getRuntime().exec(cmdLine.toString()); //this will make string to "logcat, -ds, *:W"
	}

	/**
	 * read command line execution result to string buffer
	 * 
	 * @param result
	 *            cmd exec result
	 * @param strbuf
	 *            string buffer to store log
	 * @return string buffer length
	 * @throws IOException
	 */
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
}
