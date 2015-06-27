package com.chris.fragmentreplacedemo;

import com.chris.fragmentreplacedemo.fragment.FirstFragment;
import com.chris.fragmentreplacedemo.fragment.SecondFragment;
import com.chris.fragmentreplacedemo.listener.OnTypingListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDemoActivity extends Activity
{
	private static final String tag = "FragmentDemoActivity";
	private Button button;
	private LinearLayout main_layout;
	private TextView tv;
	private FirstFragment firstFragment;
	private SecondFragment secondFragment;
	private FragmentManager fm;
	
	private int mFragmentStackIndex;
	private String lastInputString = "";

	protected int getFragmentStackIndex()
	{
		return mFragmentStackIndex;
	}

	protected void setFragmentStackIndex(int index)
	{
		this.mFragmentStackIndex = index;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_demo);
		initViews();
		changeToFragment(0, firstFragment);
	}

	private void initViews()
	{
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(btnClick);
		firstFragment = new FirstFragment();
		firstFragment.setOnTypingListener(typingListener);
		secondFragment = new SecondFragment();
		fm = getFragmentManager();
		fm.addOnBackStackChangedListener(bscListener);
		main_layout = (LinearLayout) findViewById(R.id.main_layout);
		tv = new TextView(this);
		LinearLayout.LayoutParams tvLP = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tvLP.topMargin = 50;
		tv.setLayoutParams(tvLP);
		tv.setText("该条目禁止输入9");
		tv.setTextColor(Color.RED);
//		main_layout.addView(tv);
	}
	
	OnTypingListener typingListener = new OnTypingListener()
	{
		
		@Override
		public void onTyping(String str, CharSequence s, int start, int before, int count)
		{
			Log.d(tag, "lastInputString = "+lastInputString);
			Log.e(tag, "s=" + s + " start=" + start + " before=" + before + " count=" + count);
			Log.d(tag, "view count = "+main_layout.getChildCount());
			if(!s.equals(""))
			{
				//如果输入为9，则在Activity里加一个TextView ，提示用户输入不合法，并取消当前9的输入
				//如果是删除，则count会为0，表示用0个字符串代替从start开始的before长度个字符
				if(s.toString().substring(start, start+count).equals("9"))
				{
					firstFragment.getAcount().setText(lastInputString);
					firstFragment.getAcount().setSelection(start);
					main_layout.addView(tv, 2);
				}
				else
				{
					lastInputString = s.toString();
					if(null != main_layout.getChildAt(2))
					{
						main_layout.removeViewAt(2);
					}
				}
			}
			else
			{
				lastInputString = s.toString();
			}
		}
	};

	OnClickListener btnClick = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			int index = getFragmentStackIndex();
			switch (index)
			{
			case 0:
				changeToFragment(index + 1, secondFragment);
				break;
			case 1:
				showToast();
				break;

			default:
				break;
			}
		}
	};

	OnBackStackChangedListener bscListener = new OnBackStackChangedListener()
	{

		@Override
		public void onBackStackChanged()
		{
			int stackIndex = 0;
			int stackCount = fm.getBackStackEntryCount();
			Log.i(tag, "stack count = " + stackCount);
			if (stackCount > 0)
			{
				//获取最顶端的fragment，也就是当前可见的fragment
				BackStackEntry backStackEntry = fm.getBackStackEntryAt(stackCount - 1);
				stackIndex = Integer.valueOf(backStackEntry.getName()).intValue();
				Log.i(tag, "current stackIndex = " + stackIndex);
			}
			switch (stackIndex)
			{
			case 0:
				button.setText("下一步");
				break;
			case 1:
				button.setText("注册");
				break;
			default:
				button.setText("下一步");
				break;
			}
			//设置当前的Fragment的index，用在button的onClick时候判断是否添加新Fragment
			setFragmentStackIndex(stackIndex);
		}
	};

	protected void changeToFragment(int index, Fragment frg)
	{
		if (frg != null)
		{
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.framelayout, frg);
			if (index > 0)	//第一个Fragment不能入栈，否则最后程序按back退出的时候，会按2下，第1下先出栈Fragment，第二下才是finish
			{
				ft.addToBackStack(String.valueOf(index));
			}
			ft.commit();
		} else
		{
			Log.e(tag, "error: secondFragment is null!");
		}
	}

	protected void showToast()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("userName:" + firstFragment.getAcount().getText().toString().trim() + "\n");
		sb.append("password:" + firstFragment.getPassword().getText().toString().trim() + "\n");
		sb.append("name:" + secondFragment.getName().getText().toString().trim() + "\n");
		sb.append("addr:" + secondFragment.getAddress().getText().toString().trim());
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}

}
