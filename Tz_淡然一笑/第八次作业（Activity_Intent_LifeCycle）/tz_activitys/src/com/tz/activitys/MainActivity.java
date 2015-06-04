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
	 * ��ʾ��ͼ��ͨ��Activity������ָ��Ҫ��ת�Ľ���
	 */
	public void jumpToOther() {
		Intent intent = new Intent();
		// intent��һ��һ���ķ�����
		intent.putExtra("num", 100);
		intent.putExtra("name", "Aaron");
		intent.putExtra("flag", true);

		// ���ݰ���һ������ݷŵ�bunde
		// Bundle bundle = new Bundle();
		// bundle.putString("data1", "How are you");
		// bundle.putString("data2", "How are you doing");
		// intent.putExtras(bundle);

		// ͨ�����󣬴�������
		Student stu = new Student();
		stu.setName("Licy");
		stu.setAge(18);
		intent.putExtra("stu_data", stu);

		Person person = new Person();
		person.setName("Dave");
		person.setAge(20);
		intent.putExtra("person_data", person);

		intent.setClass(this, OtherActivity.class);
		// Intent intent = new Intent(this, NormalActivity.class); //��ͼ
		this.startActivity(intent);
	}

	/**
	 * ��ʽ��ͼ����ΪMain�Ľ���
	 */
	public void jumpToMain() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN"); // �������������Activity����ʼ���桿
		intent.addCategory("android.intent.category.LAUNCHER"); // ��װ�������Ӧ��
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
		startActivity(intent);
	}

	/**
	 * ��ʽ��ͼ����ΪNormal�Ľ���
	 */
	public void jumpToNormal() {
		Intent intent = new Intent();
		// action��category������������Ҫͬʱ�߱����������Ҫָ��Ϊ��ƥ���category
		// actionֻ����һ����category�����ж������������ͼ��������Ҫ��ע��
		intent.setAction("tz.intent.action.normal");
		// intent.addCategory(Intent.CATEGORY_DEFAULT); //Ĭ��
		// intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}

	/**
	 * �ش�����
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
			Toast.makeText(this, "���֣�" + person.getName() + "\t\t" + "���䣺" + person.getAge(), Toast.LENGTH_SHORT).show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
