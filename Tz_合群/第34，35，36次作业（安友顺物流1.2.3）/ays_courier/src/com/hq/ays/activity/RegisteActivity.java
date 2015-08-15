package com.hq.ays.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hq.ays.common.BaseActivity;
import com.hq.ays.fragment.RegistFirstFragment;
import com.hq.ays.fragment.RegistNextFragment;
import com.hq.ays.utils.CheckUtils;
import com.hq.ays.activity.R;

public class RegisteActivity extends BaseActivity {
	private FrameLayout registeFrame;
	private FragmentManager fm;
	private RegistFirstFragment firstFragment;
	private RegistNextFragment  nextFragment;
	private Button next;
	private int step;
	
	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_regist);
	}

	@Override
	public void initViews() {
		//���fragment
		registeFrame = (FrameLayout) findViewById(R.id.regist_frame);
		next = (Button) findViewById(R.id.btn_next);
		step = 1;
		fm = this.getSupportFragmentManager();
		FragmentTransaction beginTransaction = fm.beginTransaction();
		firstFragment = new RegistFirstFragment();
		//fragment�滻
		beginTransaction.replace(R.id.regist_frame, firstFragment);
		beginTransaction.commit();//�ύ����
		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			public void onBackStackChanged() {
				int backStackEntryCount = fm.getBackStackEntryCount();
				if(backStackEntryCount == 0){
					next.setText("��һ��");
					step = 1;
				}else if(backStackEntryCount == 1){
					next.setText("ע��");
					step = 2;
				}
			}
		});
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	public void back(View v){
		this.finish();
	}

	
	public void next(View v){
		if(step == 1){
			//��ע��
			replaceFragment();
		}else if(step == 2){
			//����ע������
			regist();
		}
	}

	private void regist() {
		// TODO Auto-generated method stub
		String cellPhone = firstFragment.getEtRegistMobile().getText().toString().trim();
		String userName = firstFragment.getEtUserName().getText().toString().trim();
		String password = firstFragment.getEtPassword().getText().toString().trim();
		
		String frontPath = nextFragment.getFrontPhotoPath();
		String backPath = nextFragment.getBackPhotoPath();
		String certPath = nextFragment.getCertPhotoPath();
		String age = nextFragment.getEtAge().getText().toString().trim();
		String realName = nextFragment.getEtName().getText().toString().trim();
		
		if(CheckUtils.isMobileCheck(cellPhone, this)){
			if(CheckUtils.isUserNameCheck(userName, this)){
				if(CheckUtils.isPasswordCheck(password, this)){
					if(CheckUtils.isPathCheck(frontPath,this)){
						if(CheckUtils.isPathCheck(backPath,this)){
							if(CheckUtils.isPathCheck(certPath,this)){
								if(CheckUtils.isAgeCheck(age, this)){
									if(CheckUtils.isNameCheck(realName,this)){
										//��֤�ɹ���׼���ύ
										Toast.makeText(this, "��֤�Ϸ�������ע��", 1000).show();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * �滻fragment
	 */
	private void replaceFragment() {
		// TODO Auto-generated method stub
		FragmentTransaction beginTransaction = fm.beginTransaction();
		if(nextFragment == null){
			nextFragment = new RegistNextFragment();
		//fragment�滻
		}
		beginTransaction.replace(R.id.regist_frame, nextFragment);
		beginTransaction.addToBackStack("next");
		beginTransaction.commit();//�ύ����
	}
}
