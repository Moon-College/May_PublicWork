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
    private FragmentManager fragmentManager;// ͨ��FragmentManager��ִ��Fragment������
    int sep = 0; // 
	private StringBuffer bufer;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         initViews();
       
    }
     /**
      * @autho  Sunday
      * ��ʼ������
      * */
	private void initViews() {
        button = (Button) findViewById(R.id.button);
        firstFragment = new RegistFirstFragment();
        fragmentManager = this.getSupportFragmentManager();
        //ΪFragment���
        fragmentManager.addOnBackStackChangedListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, firstFragment);
        transaction.commit();//�ύ����
        
        
        button.setOnClickListener(this);        
	}
	//button�ĵ���¼�
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
       //��ӡ����Ϣ
      Toast.makeText(this, bufer.toString(), 1000);
		
	
     }
	private void replaceSecondFragment() {
		//�ж�secondFragment�Ƿ�Ϊ��  ����´���ʵ��
	   	  if(secondFragment == null){
	   		  secondFragment = new RegistSecondFragment();
	      }
	    FragmentTransaction tracsaction =fragmentManager.beginTransaction();
	    tracsaction.replace(R.id.framelayout, secondFragment);
	    //��Ӹ�Fragment��ջ���� �����Ļ� ���û�ÿ�ε����ʱ�򽫷��ص���һ��fragment����
	    //Fragment���е������Sqlserver�еĴ洢���̱�����һ����
	    tracsaction.addToBackStack("Twee");
	    tracsaction.commit(); //�ύ����
	    
	   	  
	}
	public void onBackStackChanged() {
        //���Ȼ�ȡջ���������
		 int backStacCount = fragmentManager.getBackStackEntryCount();
		 if(backStacCount == 0){
			 sep = 0;
			 button.setText("��һ��");
		 }else {
			 sep = 1;
			 button.setText("ע��");
		 }
	}
	public void show(View v) {
	    //��̬���һ��TextView������ʾ�û�
	 TextView txt = new TextView(this); 
	 String ho = "�������{0}"+secondFragment.getEdt1().toString().trim()+"���� ����������";
	 txt.setText(ho); 
	 LinearLayout.LayoutParams  parms= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
     txt.setLayoutParams(parms);
     setContentView(txt);
     
     
	}
}