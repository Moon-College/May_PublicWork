package com.ccgao.myactivity;

import com.ccgao.myactivity.bean.User;
import com.ccgao.myactivity.bean.MyClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.Intents;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    final int JUMP_CODE=1;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void jump(View v){
    	jumpAndBack();
    }
    
    private void jumpMain(){
    	//隐式意图的方式跳转
    	Intent intent = new Intent();
    	intent.setAction("android.intent.action.MAIN");
//    	intent.addCategory("android.intent.category.LAUNCHER");
    	this.startActivity(intent);
    }
    
    private void jumpTwo(){
    	//隐式意图的方式跳转
    	Intent intent = new Intent();
    	intent.setAction("com.ccgao.action.two");
    	intent.addCategory(intent.CATEGORY_DEFAULT);
//    	intent.addCategory(intent.CATEGORY_BROWSABLE);
    	this.startActivity(intent);
    }

	private void jumpOther() {
		Intent intent = new Intent(this, TwoActivity.class);  //组件之间的桥梁，显式意图调用activity
//    	Bundle bundle = new Bundle();  //数据包
//    	bundle.putString("data", "hello");
//    	bundle.putBoolean("data2", true);
//    	intent.putExtras(bundle);
		intent.putExtra("data1", 1);
    	intent.putExtra("data2",true);
    	User user = new User();
    	user.setUserName("ccgao");
    	user.setAge(18);
    	intent.putExtra("data3",user);
    	MyClass classes = new MyClass();
    	classes.setClassName("VIP五月班");
    	classes.setCount(60);
    	intent.putExtra("data4", classes);
		this.startActivity(intent);
	}
	
	private void jumpAndBack(){
		Intent intent = new Intent();
		User user = new User();
    	user.setUserName("ccgao");
    	user.setAge(18);
    	intent.putExtra("data3",user);
    	MyClass classes = new MyClass();
    	classes.setClassName("VIP五月班");
    	classes.setCount(60);
    	intent.putExtra("data4", classes);
		intent.setClass(this, TwoActivity.class);
		startActivityForResult(intent, JUMP_CODE);
	} 
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK){
			MyClass classes = data.getParcelableExtra("backdata");
			Toast.makeText(this, classes.getClassName(), Toast.LENGTH_SHORT).show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}