package com.chris.userdefineadapter;

import java.util.ArrayList;
import java.util.List;

import com.chris.userdefineadapter.adapter.CharaterAdapter;
import com.chris.userdefineadapter.character.Characters;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class UserDefineAdapterActivity extends Activity
{
	private static final String TAG = "UserDefineAdapterActivity";
	private ListView mList;
	private CharaterAdapter mCharAdapter;
	private List<Characters> mPerson;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_define_adapter);

		mList = (ListView) findViewById(R.id.list);

		setupCharaterData();

		setupListView();
	}

	int[] faceImg = new int[]
	{ R.drawable.faceimg01, R.drawable.faceimg02, R.drawable.faceimg03, R.drawable.faceimg04, R.drawable.faceimg05,
			R.drawable.faceimg06, R.drawable.faceimg07, R.drawable.faceimg08, R.drawable.faceimg09,
			R.drawable.faceimg10, };

	private void setupCharaterData()
	{
		mPerson = new ArrayList<Charaters>();
		Resources arryRes = getResources();
		String[] names = arryRes.getStringArray(R.array.names);
		String[] phoneNums = arryRes.getStringArray(R.array.phoneNum);
		String[] sexs = arryRes.getStringArray(R.array.sex);
		Log.i(TAG, "names.length = " + names.length);
		for (int i = 0; i < names.length; i++)
		{
			mPerson.get(i).setFaceImg(faceImg[i]);
			mPerson.get(i).setName(names[i]);
			mPerson.get(i).setPhoneNum(phoneNums[i]);
			mPerson.get(i).setSex(sexs[i]);
		}
	}

	private void setupListView()
	{
		mCharAdapter = new CharaterAdapter(mPerson, this);
		mList.setAdapter(mCharAdapter);;
	}
}
