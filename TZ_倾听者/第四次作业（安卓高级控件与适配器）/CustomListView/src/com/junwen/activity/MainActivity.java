package com.junwen.activity;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.android.MyMethod;
import com.junwen.adapter.CustomAdapter;
import com.junwen.bean.Student;
import com.junwen.util.JavaUtil;
import com.junwen.view.CustomListView;
import com.junwen.view.CustomListView.OnDeleteListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnDeleteListener {
	// 自定义ListView
	private CustomListView listview;
	// 学生对象集合
	private List<Student> data = new ArrayList<Student>();
	// 所有头像
	private int[] img = { R.drawable.tz1, R.drawable.tz2, R.drawable.tz3,
			R.drawable.tz4, R.drawable.tz5, R.drawable.tz6, R.drawable.tz7,
			R.drawable.tz8, R.drawable.tz9, R.drawable.tz10, R.drawable.tz11, };
	// 网名
	private String[] name = { "Grace姐姐", "David老师", "Danny老师", "Jason老师",
			"小影老师", "Eyre老师", "轩妹妹", "瑶瑶老师", "Evan老师", "子漠老师", "斌大师" };
	// 性别
	private String[] sex = { "女", "男", "男", "男", "女", "男", "女", "女", "男", "男","男" };
	// 颜值
	private int[] faceValue = { 100, 90, 100, 90, 100, 90, 100, 100, 90, 90, 90 };

	// 爱好
	private String[] hobby = { "卖萌", "发表情", "哼哼", "弹吉他", "爱美", "负责", "可爱",
			"声音甜美", "帅气", "发视频", "传教" };
	private MyMethod db ;
	//适配器
	private CustomAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 初始化数据
		initView();
		adapter = new CustomAdapter(this, data);
		// 设置适配器
		listview.setAdapter(adapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		listview = (CustomListView) findViewById(R.id.listview);
		listview.setOndelete(this);
		// 初始化数据
		for (int i = 0; i < name.length; i++) {
			Student student = new Student();
			student.setName(name[i]);
			student.setSex(sex[i]);
			student.setHobby(hobby[i]);
			student.setFaceValue(faceValue[i]);
			student.setHeadImage(new SoftReference<Bitmap>(BitmapFactory
					.decodeResource(getResources(), img[i])));
			if (sex[i].equals("男")) {
				student.setBoy(true);
			}else
			{
				student.setBoy(false);
			}
			data.add(student);
		}
	}

	public void ondeleteitem(final int index) {
		//获取点击的那个条目
		View item = listview.getChildAt(index);
			if(item!=null)
			{
				//获取屏幕宽度
				int screenWidth = JavaUtil.getScreenWidth(MainActivity.this);
				//创建平移动画
				TranslateAnimation animation = new TranslateAnimation(0,screenWidth, 0, 0);
				//动画持续时间
				animation.setDuration(300);
				//动画结束停留不回来
				animation.setFillAfter(true);
				//给控件设置动画
				item.startAnimation(animation);
				//动画监听
				animation.setAnimationListener(new AnimationListener() {
					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					//当动画结束
					public void onAnimationEnd(Animation animation) {
						//删除对象集合里的对象
						data.remove(index);
						//重新设置Adapter
						listview.setAdapter(adapter);
					}
				});
			}
	}
}