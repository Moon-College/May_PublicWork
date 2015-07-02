package com.lx.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.lx.listener.MyListener;
import com.lx.refragment.RegistFirstFragment;
import com.lx.refragment.RegistSecondFragment;

public class MainActivity extends FragmentActivity implements OnClickListener, OnBackStackChangedListener,MyListener {
    private Button button;
    private RegistFirstFragment firstFragment; 
    private RegistSecondFragment secondFragment;
    private FragmentManager fragmentManager;// 通过FragmentManager来执行Fragment的事务
    int sep = 0; // 
	private StringBuffer bufer;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         initViews();
       
    }
     /**
      * @autho  Sunday
      * 初始化数据
      * */
	private void initViews() {
        button = (Button) findViewById(R.id.button);
        firstFragment = new RegistFirstFragment();
        fragmentManager = this.getSupportFragmentManager();
        //为Fragment添加
        fragmentManager.addOnBackStackChangedListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, firstFragment);
        transaction.commit();//提交事务
        
        
        button.setOnClickListener(this);        
	}
	//button的点击事件
	public void onClick(View v) {
       if(sep == 0){
    	   replaceSecondFragment();
       }else if(sep  ==1){
    	    shoToast();   
       }	
	}
//   private class MyBackChangeListener implements OnBackStackChangedListener{
//	   
//   } 
     private void shoToast() {
        bufer = new StringBuffer();
       bufer.append("username"+firstFragment.getEdt1().getText().toString().trim()+"\n");
       bufer.append("userpad:"+firstFragment.getEdt2().getText().toString().trim()+"\n");
       bufer.append("user-nuber"+secondFragment.getEdt1().toString().trim()+"\n");
       bufer.append("user-address"+secondFragment.getEdt2().toString().trim()+"\n");
       //打印出消息
      Toast.makeText(this, bufer.toString(), 1000);
		
	
     }
	private void replaceSecondFragment() {
		//判断secondFragment是否为空  则从新创建实例
	   	  if(secondFragment == null){
	   		  secondFragment = new RegistSecondFragment();
	      }
	    FragmentTransaction tracsaction =fragmentManager.beginTransaction();
	    tracsaction.replace(R.id.framelayout, secondFragment);
	    //添加该Fragment到栈当中 这样的话 当用户每次点击的时候将返回到上一个fragment当中
	    //Fragment当中的事务科Sqlserver中的存储过程本质是一样的
	    tracsaction.addToBackStack("Twee");
	    tracsaction.commit(); //提交事务
	    
	   	  
	}
	public void onBackStackChanged() {
        //首先获取栈里面的数量
		 int backStacCount = fragmentManager.getBackStackEntryCount();
		 if(backStacCount == 0){
			 sep = 0;
			 button.setText("下一步");
		 }else {
			 sep = 1;
			 button.setText("注册");
		 }
	}
	public void show(View v) {
	    //动态添加一个TextView　来提示用户
	 TextView txt = new TextView(this); 
	 String ho = "你输入的{0}"+secondFragment.getEdt1().toString().trim()+"有误 请重新输入";
	 txt.setText(ho); 
	 LinearLayout.LayoutParams  parms= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
     txt.setLayoutParams(parms);
     setContentView(txt);
     
     
	}
}