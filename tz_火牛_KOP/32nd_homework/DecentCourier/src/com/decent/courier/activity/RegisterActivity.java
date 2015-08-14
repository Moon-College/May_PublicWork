package com.decent.courier.activity;

import java.io.File;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.decent.courier.bean.request.HttpRequestRegister;
import com.decent.courier.bean.result.HttpBaseResult;
import com.decent.courier.common.BaseActivity;
import com.decent.courier.fragment.Register1stFragment;
import com.decent.courier.fragment.Register2ndFragment;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.DecentToast;
import com.decent.courier.utils.RegExUtil;

public class RegisterActivity extends BaseActivity implements OnClickListener,
		OnBackStackChangedListener, IHttpRequestCallback {

	private Button btn_next;
	private ImageView btn_back;
	private int mStep = 0;
	private Register1stFragment m1stFragment = null;
	private Register2ndFragment m2ndFragment = null;
	private FragmentManager mFragmentManager;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_register);
	}

	@Override
	public void initView() {
		btn_next = (Button) findViewById(R.id.btn_next);
		btn_back = (ImageView) findViewById(R.id.btn_back);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		btn_next.setOnClickListener(this);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager.addOnBackStackChangedListener(this);

		btn_next.setText("��һ��");
		mStep = 0;
		m1stFragment = new Register1stFragment();
		// handleReplaceFragment(m1stFragment);
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.replace(R.id.register_frame, m1stFragment);
		// transaction.addToBackStack(null);
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			handleClickBack();
			break;
		case R.id.btn_next:
			handleClickNext();
			break;
		default:
			break;
		}
	}

	private void handleClickBack() {
		// TODO Auto-generated method stub
		if (mStep == 0) {
			DecentLogUtil.d("into handleClickBack mStep = 0");
			// ֱ��finish
			finish();
		} else if (mStep == 1) {
			// ��ʾm1stFragment
			DecentLogUtil.d("into handleClickBack mStep = 1");
			if (m1stFragment == null) {
				m1stFragment = new Register1stFragment();
			}
			FragmentTransaction transaction = mFragmentManager
					.beginTransaction();
			mFragmentManager.popBackStack();
			transaction.replace(R.id.register_frame, m1stFragment);
			transaction.commit();
		}
	}

	private void handleClickNext() {
		// TODO Auto-generated method stub
		switch (mStep) {
		case 0:
			if (m2ndFragment == null) {
				m2ndFragment = new Register2ndFragment();
			}
			FragmentTransaction transaction = mFragmentManager
					.beginTransaction();
			transaction.replace(R.id.register_frame, m2ndFragment);
			transaction.addToBackStack("second");
			transaction.commit();
			break;
		case 1:
			// �������Ķ����Ƿ�ok
			// �ύhttpmultipost��ȥע��
			doCheckRegisterAndSubmit();
			break;
		default:
			break;
		}
	}

	/**
	 * ���ע����Ϣ�Ƿ�ok�����ok�ύ����ok����ʾ����
	 */
	private void doCheckRegisterAndSubmit() {
		// TODO Auto-generated method stub
		// ��һҳ�������ؼ�ֵ
		// 1��ͷ��11λ����
		String strMobile = m1stFragment.getEt_regist_mobile().getText()
				.toString().trim();

		if (!RegExUtil.checkMobile(strMobile)) {
			DecentToast.showToastLong(this, "�ֻ��ű�����1��ͷ��11λ����");
			return;
		}
		// 2-10
		String strUserName = m1stFragment.getEt_regist_name().getText()
				.toString().trim();
		if (!RegExUtil.chekUserName(strUserName)) {
			DecentToast.showToastLong(this, "��¼��������2-10��Ӣ���ַ������»���");
			return;
		}
		// >6 <20
		String strPwd = m1stFragment.getEt_pwd().getText().toString().trim();
		if (!RegExUtil.checkPassword(strPwd)) {
			DecentToast.showToastLong(this, "��¼��������6-20��Ӣ���ַ������»���");
			return;
		}
		// �ڶ�ҳ���������ֿؼ�
		// 1-100
		String strAge = m2ndFragment.getEt_age().getText().toString().trim();
		if (!RegExUtil.checkAge(strAge)) {
			DecentToast.showToastLong(this, "���������0-100");
			return;
		}
		// 2-10,����������
		String strName = m2ndFragment.getEt_name().getText().toString().trim();
		if (!RegExUtil.checkName(strName)) {
			DecentToast.showToastLong(this, "����������2-10���ַ�������������");
			return;
		}

		// �ڶ�ҳ��3��ͼƬ�ļ�·��,�жϲ�Ϊ��
		String front_photo_path = m2ndFragment.getFront_photo_path();
		String rear_photo_path = m2ndFragment.getRear_photo_path();
		String cert_photo_path = m2ndFragment.getCert_photo_path();

		if (RegExUtil.isStringEmpty(front_photo_path)
				|| RegExUtil.isStringEmpty(rear_photo_path)
				|| RegExUtil.isStringEmpty(cert_photo_path)) {
			DecentToast.showToastLong(this, "�ϴ�ͼƬ����Ϊ��");
			DecentLogUtil.d("�ļ�·��Ϊ��");
			return;
		}

	    //��ʼ��request
		HttpRequestRegister httpRequestRegister = new HttpRequestRegister(strUserName,
				strPwd, strName, strMobile, Integer.valueOf(strAge), new File(
						front_photo_path), new File(rear_photo_path), new File(
						cert_photo_path));
		//����
		mHttpRequest.doMultiQuestByPostMethod(DecentConstants.REGISTER_URL,
				httpRequestRegister, false, this);
	}

	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		int count = mFragmentManager.getBackStackEntryCount();
		DecentLogUtil.d("into onBackStackChanged count = " + count);
		if (count == 0) {
			btn_next.setText("��һ��");
			mStep = 0;
		} else if (count == 1) {
			btn_next.setText("ע��");
			mStep = 1;
		}
	}

	@Override
	public void onRequestFail(String result) {
		// TODO Auto-generated method stub
		DecentToast.showToastLong(this, "ע��ʧ��:"+result);
	}

	@Override
	public void onRequestSuccess(String result) {
		// TODO Auto-generated method stub
		HttpBaseResult httpBaseResult = JSON.parseObject(result, HttpBaseResult.class);
		DecentToast.showToastLong(this, "������:"+httpBaseResult.getRet()+","+httpBaseResult.getMsg());
	}
}
