package com.tz.asy.activity;


import java.io.File;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.tz.asy.R;
import com.tz.asy.bean.request.RequestCourierRegister;
import com.tz.asy.bean.result.HttpBaseResult;
import com.tz.asy.common.BaseActivity;
import com.tz.asy.common.MyConstants;
import com.tz.asy.fragment.RegistFragmentFirst;
import com.tz.asy.fragment.RegistFragmentSecond;
import com.tz.asy.listener.HttpCallback;
import com.tz.asy.utils.MD5;
import com.tz.asy.utils.RegistCheck;


public class RegistActivity extends BaseActivity implements OnClickListener {
	private Button next;
	private RegistFragmentFirst first;//第一个fragment
	private RegistFragmentSecond second;//第二个fragment
	private FragmentManager fragmentManager;
	private int step;
	private RegistCheck check;
	private ProgressDialog dialog;
	@Override
	public void initContentView() {
		setContentView(R.layout.activity_regist);
	}

	@Override
	public void initView() {
		next = (Button) findViewById(R.id.btn_next);
	}

	@Override
	public void initListener() {
		next.setOnClickListener(this);
		
	}

	@Override
	public void initData() {
		fragmentManager = this.getSupportFragmentManager();
		fragmentManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			public void onBackStackChanged() {
				//退栈
				int count = fragmentManager.getBackStackEntryCount();
				if(count == 0){
					//表示stop为0
					next.setText("下一步");
					step = 0;
				}else if(count == 1){
					next.setText("注册");
					step = 1;
				}
			}
		});
		first = new RegistFragmentFirst();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		beginTransaction.replace(R.id.regist_frame, first);
		beginTransaction.commit();
		check = new RegistCheck();
	}

	/**
	 * 作业：完成注册的数据的正则以及实现注册
	 */
	public void onClick(View v) {
		//获取两个fragment里面用户数输入的数据，进行注册提交
		if(step == 0){
			//跳到下一步
			replaceFragment();
		}else if(step == 1){
			//执行注册
			//1开头的11位数字
			String phone = first.getPhoneNum().getText().toString().trim();
			//2-10
			String userName = first.getUserName().getText().toString().trim();
			//>6
			String password = first.getPassword().getText().toString().trim();
		
			//获取第二个fragment里面的年龄和真实姓名
			//1-100
			String age = second.getEt_age().getText().toString().trim();
			//2-10
			String name = second.getEt_name().getText().toString().trim();
			//获取选中证件的图片路径
			String faceImage = second.getFaceImage();
			String backImage = second.getBackImage();
			String licenseImage = second.getLicenseImage();
			//正则通过则开始注册
			if(check.checkAge(age)&&check.checkName(name)&&check.checkUserName(userName)&&check.isMobileNO(phone)
					&&check.isPasswordNo(password)&&check.checkEmpty(faceImage, "正面证件照")&&check.checkEmpty(backImage, "背面证件照")
					&&check.checkEmpty(licenseImage, "证件照")){
				//开始注册了
				RequestCourierRegister register = new RequestCourierRegister(userName, MD5.GetMD5Code(password), name, phone, Integer.valueOf(age),
						new File(faceImage),
						new File(backImage), 
						new File(licenseImage));
				//多文件传输注册
				/*request.showDialog(this,true, "", "正在注册");*/
				dialog = new ProgressDialog(this);
				dialog.setMessage("正在注册");
				dialog.show();
				request.doQuestByMultiPostMethod(MyConstants.REGISTER, register,false, new HttpCallback() {
					
					public void success(String result) {
						if(dialog!=null&&dialog.isShowing()){
							dialog.dismiss();
						}
						HttpBaseResult baseResult = request.formatResult(result, HttpBaseResult.class);
						Toast.makeText(RegistActivity.this, baseResult.getMsg(), Toast.LENGTH_LONG).show();
						if(baseResult.getRet() == 0){
							//注册成功
						}else{
							
						}
					}
					
					public void fail(String result) {
						if(dialog!=null&&dialog.isShowing()){
							dialog.dismiss();
						}
						Toast.makeText(RegistActivity.this, result, Toast.LENGTH_SHORT).show();
					}
				});
			}else{
				Toast.makeText(this, check.getCheckText(), Toast.LENGTH_SHORT).show();
			}
			}else{
				//未通过正则
				Toast.makeText(RegistActivity.this, check.getCheckText(), Toast.LENGTH_LONG).show();
			}
				
//		}
	}

	
	/**
	 * 讲第二个fragment替换第一个
	 */
	private void replaceFragment() {
		// TODO Auto-generated method stub
		if(second == null){
			second = new RegistFragmentSecond();
		}
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		beginTransaction.replace(R.id.regist_frame, second);
		beginTransaction.addToBackStack("second");
		beginTransaction.commit();
	}
	
}
