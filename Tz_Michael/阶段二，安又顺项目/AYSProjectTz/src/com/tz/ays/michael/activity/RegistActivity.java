package com.tz.ays.michael.activity;

import java.io.File;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tz.ays.michael.R;
import com.tz.ays.michael.bean.request.RegisterRequestBean;
import com.tz.ays.michael.bean.response.HttpBaseResult;
import com.tz.ays.michael.callback.IHttpCallBack;
import com.tz.ays.michael.common.BaseActivity;
import com.tz.ays.michael.common.Constrant;
import com.tz.ays.michael.fragments.RegistFragmentFirst;
import com.tz.ays.michael.fragments.RegistFragmentSecond;
import com.tz.ays.michael.http.MyParser;
/**
 * 注册界面
 * @author szm
 *
 */
public class RegistActivity extends BaseActivity implements OnClickListener {

	private Button next;
	private RegistFragmentFirst first;//第一个fragment
	private RegistFragmentSecond second;//第二个fragment
	private FragmentManager fragmentManager;
	private int step;
	
	@Override
	public void setContentLayout() {
		setContentView(R.layout.regist_lay);
	}

	@Override
	public void initViews() {
		next = (Button) findViewById(R.id.btn_next);
	}

	@Override
	public void setListenners() {
		next.setOnClickListener(this);
	}

	@Override
	public void getData() {
		fragmentManager=this.getSupportFragmentManager();
		first=new RegistFragmentFirst();
		fragmentManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			public void onBackStackChanged() {
				int count=fragmentManager.getBackStackEntryCount();
				if(count==1){
					next.setText("注册");
					step=1;
				}else if(count==0){
					next.setText("下一步");
					step=0;
				}
			}
		});
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		transaction.replace(R.id.regist_frame, first);
		transaction.commit();
	}

	public void onClick(View v) {
		if(step==0){
			//跳到下一个
			replaceFragment();
		}else{
			//注册提交
			//执行注册
			//1开头的11位数字
			String phone = first.getPhoneNum().getText().toString().trim();
			//2-10
			String userName = first.getUserName().getText().toString().trim();
			if(!check.pHoneCheck(userName)){
				return;
			}
			//>6
			String password = first.getPassword().getText().toString().trim();
			if(!check.passWordCheck(password)){
				return;
			}
			//获取第二个fragment里面的年龄和真实姓名
			//1-100
			String age = second.getEt_age().getText().toString().trim();
			if(!check.ageCheck(age)){
				return;
			}
			//2-10
			String name = second.getEt_name().getText().toString().trim();
			if(!check.nameCheck(name)){
				return;
			}
			//获取选中证件的图片路径
			String faceImage = second.getFaceImage();
			String backImage = second.getBackImage();
			String licenseImage = second.getLicenseImage();
			//正则通过则开始注册
			//开始注册了
			RegisterRequestBean registRequest=new RegisterRequestBean(userName, password, name, phone, Integer.valueOf(age),
					new File(faceImage),
					new File(backImage),
					new File(licenseImage));
			hRequest.doMultiQuestByPostMethod(Constrant.REGISTER, registRequest, false, new IHttpCallBack() {
				
				public void success(String result) {
					HttpBaseResult rBaseResult=MyParser.parseToObject(result, HttpBaseResult.class);
					if(rBaseResult.getRet()==0){
						//注册成功
						Toast.makeText(mContext, "注册成功", 0).show();
					}else{
						Toast.makeText(mContext, "注册失败", 0).show();
					}
				}
				
				public void failed(String result) {
					Toast.makeText(mContext, result, 0).show();
				}
			});
		}
	}

	private void replaceFragment() {
		if(second==null){
			second=new RegistFragmentSecond();
		}
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		transaction.replace(R.id.regist_frame, second);
		transaction.addToBackStack("second");
		transaction.commit();
	}

	public void back(View v){
		if(step==1){
			//出栈一个fragment
			fragmentManager.popBackStack();
			step=0;
		}else if(step==0){
			finish();
		}
	}
	
	
}
