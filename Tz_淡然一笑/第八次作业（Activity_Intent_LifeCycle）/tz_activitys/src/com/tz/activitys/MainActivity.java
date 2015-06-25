package com.tz.activitys;

import com.tz.activitys.R;
import com.tz.activitys.bean.Person;
import com.tz.activitys.bean.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void jump(View view) {
		jumpToOther();
		// jumpToMain();
		// jumpToNormal();
		jumpToBack();
	}

	/**
	 * 显示意图，通过Activity名字来指定要跳转的界面
	 */
	public void jumpToOther() {
		Intent intent = new Intent();
		// intent是一个一个的放数据
		intent.putExtra("num", 100);
		intent.putExtra("name", "Aaron");
		intent.putExtra("flag", true);

		// 数据包，一起把数据放到bunde
		// Bundle bundle = new Bundle();
		// bundle.putString("data1", "How are you");
		// bundle.putString("data2", "How are you doing");
		// intent.putExtras(bundle);

		// 通过对象，传递数据
		Student stu = new Student();
		stu.setName("Licy");
		stu.setAge(18);
		intent.putExtra("stu_data", stu);

		Person person = new Person();
		person.setName("Dave");
		person.setAge(20);
		intent.putExtra("person_data", person);

		intent.setClass(this, OtherActivity.class);
		// Intent intent = new Intent(this, NormalActivity.class); //意图
		this.startActivity(intent);
	}

	/**
	 * 隐式意图启动为Main的界面
	 */
	public void jumpToMain() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN"); // 启动的主界面的Activity【开始界面】
		intent.addCategory("android.intent.category.LAUNCHER"); // 安装到桌面的应用
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
		startActivity(intent);
	}

	/**
	 * 隐式意图启动为Normal的界面
	 */
	public void jumpToNormal() {
		Intent intent = new Intent();
		// action和category两个条件必须要同时具备，特殊情况要指定为相匹配的category
		// action只能有一个，category可以有多个，但是在意图过滤器中要先注册
		intent.setAction("tz.intent.action.normal");
		// intent.addCategory(Intent.CATEGORY_DEFAULT); //默认
		// intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}

	/**
	 * 回传数据
	 */
	public void jumpToBack() {
		Intent intent = new Intent();

		Student stu = new Student();
		stu.setName("Licy");
		stu.setAge(18);
		intent.putExtra("stu_data", stu);

		Person person = new Person();
		person.setName("Dave");
		person.setAge(20);
		intent.putExtra("person_data", person);

		intent.setClass(this, OtherActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Person person = data.getParcelableExtra("data_back");
			Toast.makeText(this, "名字：" + person.getName() + "\t\t" + "年龄：" + person.getAge(), Toast.LENGTH_SHORT).show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
