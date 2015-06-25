package com.tz.replace;

import com.tz.fragmentreplace.R;
import com.tz.replace.fragment.RegisterOneFragment;
import com.tz.replace.fragment.RegisterTwoFragment;
import com.tz.replace.listener.OnTextChangListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Android sdkΪ8��2.2����MainActivityҪ�̳�FragmentActivity
public class MainActivity extends Activity implements OnClickListener, OnTextChangListener, OnBackStackChangedListener {

	private Button btn;
	private RegisterOneFragment oneFragment;
	private RegisterTwoFragment twoFragment;
	private FragmentManager fm;
	private int flag;  // flag��0����һ���� flag��1��ע�᡿

	 public static final int REGONE = 0;
	 public static final int REGTWO = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		oneFragment = new RegisterOneFragment();
		
		// ����fragment�Ľ�ջ�ͳ�ջ
		// fm.addOnBackStackChangedListener(new MyBackStackListener()); 
		
		// ������������ֻ���ύһ��,������������Ϊȫ�ֱ�����ÿ�ο����Ķ���һ���µ�����
		FragmentTransaction ft = fm.beginTransaction();
		// ft.add(R.id.frameLayout, regOneFragment); //���
		ft.replace(R.id.frameLayout, oneFragment); // �滻
		// oneFragment����Ҫ��ӵ�fragmentջ�У�����back���ؼ��ͻ����١�oneFragment����ϵͳ����fragment��finish��ֱ���˳���activity�У��򲻻���ʾoneFragment
		// ft.addToBackStack("one"); //one�Ǳ�ʶoneFragment,ͨ��one�����ҵ�oneFragment
		ft.commit(); // �ύ����
		fm.addOnBackStackChangedListener(this); // ����fragment�Ľ�ջ�ͳ�ջ
	}

	private void initView() {
		btn = (Button) findViewById(R.id.btn);
		twoFragment = new RegisterTwoFragment(); // ��regOneFragment��ӽ���

		fm = this.getFragmentManager(); // 2.2��ȡfmΪgetSupportFragmentManager

		btn.setOnClickListener(this);
		twoFragment.setOnTextChangListener(this);
	}

	@Override
	public void onClick(View v) {
		// �����ť,��ת��twoFragment,�滻oneFragment
		if (flag == REGONE) {
			//�滻fragment
			replaceFragment();
		} else if (flag == REGTWO) {
			//��ʾע����Ϣ
			showRegisterInfo();
		}
	}

	/**
	 * ��ʾע����Ϣ
	 */
	private void showRegisterInfo() {
		// ƴ���ַ���
		StringBuffer sb = new StringBuffer();
		sb.append("account:" + oneFragment.getAccount().getText().toString().trim() + "\n");
		sb.append("password:" + oneFragment.getPassword().getText().toString().trim() + "\n");
		sb.append("realName:" + twoFragment.getRealName().getText().toString().trim() + "\n");
		sb.append("phoneNo:" + twoFragment.getphoneNo().getText().toString().trim());
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackStackChanged() {
		// ��ȡ����ջ������
		int count = fm.getBackStackEntryCount();
		// �����ť,��ת��twoFragment,�滻oneFragment
		for (int i = 0; i < count; i++) {
			BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
			backStackEntryAt.getName();
			backStackEntryAt.getClass();
			backStackEntryAt.getId();
			Log.i("INFO", "name:" + backStackEntryAt.getName());
			Log.i("INFO", "class:" + backStackEntryAt.getClass());
			Log.i("INFO", "class:" + backStackEntryAt.getId());
		}
		if (count == 0) {
			btn.setText("��һ��");
			flag = REGONE;
		} else if (count == 1) {
			btn.setText("ע��");
			flag = REGTWO;
		}

	}

	/**
	 * ��ת��twoFragment
	 */
	private void replaceFragment() {
		if (twoFragment == null) {
			twoFragment = new RegisterTwoFragment();
		}
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.frameLayout, twoFragment);
		// ϵͳ�ж��Ƿ���fragment������finish
		// ��twoFragment��ӵ�fragmentջ��
		ft.addToBackStack("two"); // two��ʶtwoFragment��ͨ��two�����ҵ�twoFragment�����ֿ����������
		ft.commit(); //�ύ����
	}

	/**
	private class MyBackStackListener implements OnBackStackChangedListener {

		@Override
		public void onBackStackChanged() {
			// ��ȡ����ջ������
			int backStackEntryCount = fm.getBackStackEntryCount();
			for (int i = 0; i < backStackEntryCount; i++) {
				BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
				backStackEntryAt.getName();
				backStackEntryAt.getClass();
				backStackEntryAt.getId();
				Log.i("INFO", "name:" + backStackEntryAt.getName());
				Log.i("INFO", "class:" + backStackEntryAt.getClass());
				Log.i("INFO", "class:" + backStackEntryAt.getId());
			}
			if (backStackEntryCount == 0) {
				flag = 0;
				btn.setText("��һ��");
			} else {
				flag = 1;
				btn.setText("ע��");
			}
		}
	}
	*/

	@Override
	public void onTextChang(TextView tv) {
		Toast.makeText(MainActivity.this, "��������������9��", Toast.LENGTH_SHORT).show();
	}

}
