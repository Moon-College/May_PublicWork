package com.deccent.decentfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DecentFragmentActivity extends FragmentActivity implements
		OnClickListener, OnBackStackChangedListener {
	private static final String TAG = "DecentFragmentActivity";
	private Button registerBtn;
	private TextView warning;

	FragmentManager mFragmentManager;
	/*
	 * ��¼ע��Ĳ���
	 */
	private int mStep = 0;
	private PasswordFragment pf = null;
	private MobileFagment mf = null;
	private boolean mIsWarning;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		 * ע�ᰴť�Ĵ���
		 */
		registerBtn = (Button) findViewById(R.id.registerBtn);
		mStep = 0;
		registerBtn.setOnClickListener(this);

		// ��support�汾�Ļ�����getFragmentManager
		mFragmentManager = getSupportFragmentManager();
		// ����Fragment��stackchange
		mFragmentManager.addOnBackStackChangedListener(this);

		warning = (TextView) findViewById(R.id.warning);
	}

	public void setWarningState(boolean isWarning, String warningText) {
		mIsWarning = isWarning;
		warning.setText(warningText);
	}

	@Override
	public void onClick(View arg0) {
		Log.d(TAG, "into onClick");
		// TODO Auto-generated method stub
		replaceFragment();
	}

	/**
	 * �����滻fragment
	 */
	private void replaceFragment() {
		Log.d(TAG, "into replaceFragment mStep=" + mStep);
		/*
		 * ����mStep����Ӧ���滻 mStep
		 */
		switch (mStep) {
		case 0:
			/*
			 * �������û��������fragment�����滻R.id.frameLayout ���Ҽ���fragmentջ
			 */
			if (pf == null) {
				pf = new PasswordFragment();
			}
			FragmentTransaction transaction = mFragmentManager
					.beginTransaction();
			transaction.replace(R.id.frameLayout, pf);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		case 1:
			/*
			 * ���������ֺ͵�ַ��fragment�����滻R.id.frameLayout ���Ҽ���fragmentջ
			 */
			if (mf == null) {
				mf = new MobileFagment();
				/*
				 * ����mobile���Զ���listnerΪthis������������治����9
				 */
			}

			FragmentTransaction secondTransaction = mFragmentManager
					.beginTransaction();
			secondTransaction.replace(R.id.frameLayout, mf);
			secondTransaction.addToBackStack(null);
			secondTransaction.commit();
			break;
		case 2:
			StringBuffer sb = new StringBuffer();
			/*
			 * ��ӡע��Ľ��
			 */
			if (!mIsWarning) {
				sb.append("�û���:" + pf.getUsername().getText().toString().trim());
				sb.append("\n");
				sb.append("��    ��:"
						+ pf.getPassword().getText().toString().trim());
				sb.append("\n");
				sb.append("�ֻ���:" + mf.getMobile().getText().toString().trim());
				sb.append("\n");
				sb.append("��    ַ:"
						+ mf.getAddress().getText().toString().trim());
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			} else {
				sb.append("���ȴ���澯�������ύ!!!");
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}

	/*
	 * ����getBackStackEntryCount�ı仯������registerBtn��ʾ�����ֺ�mStep
	 * 
	 * @see android.support.v4.app.FragmentManager.OnBackStackChangedListener#
	 * onBackStackChanged()
	 */
	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		int count = mFragmentManager.getBackStackEntryCount();
		if (count == 0) {
			registerBtn.setText("ע��");
			mStep = 0;
		} else if (count == 1) {
			registerBtn.setText("��һ��");
			mStep = 1;
		} else if (count == 2) {
			registerBtn.setText("�ύ");
			mStep = 2;
		}
	}
}