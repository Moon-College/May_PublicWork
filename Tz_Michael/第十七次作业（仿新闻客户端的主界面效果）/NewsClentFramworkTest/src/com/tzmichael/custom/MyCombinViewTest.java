package com.tzmichael.custom;

import java.util.ArrayList;
import java.util.List;

import com.nineoldandroids.view.ViewHelper;
import com.tzmichael.activity.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
/**
 * 自己组装的一个容器，就是几个控件的简单拼凑
 * 不足之处，未能通过setTitleData（）方法以List<String>的形式把title数据传进来，未能通过设置adapter的形式给内部的ViewPager设置适配器（故这里只能以一个参数的构造函数来初始化），这属于基础没打好吧
 * 能力之外的：未能去掉RadioButton的按钮图片
 * @author admin
 *
 */
public class MyCombinViewTest extends LinearLayout implements OnCheckedChangeListener, OnPageChangeListener {

	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private TextView tv_item;
	private ViewPager vp;
	
	private List<String> ll_tt;
	
	public ViewPager getVp() {
		return vp;
	}

	public MyCombinViewTest(Context context,List<String> ll_t,PagerAdapter adapter) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.combinview_lay, this, true);
		hsv=(HorizontalScrollView) findViewById(R.id.hsv);
		rg=(RadioGroup) findViewById(R.id.rg);
		tv_item=(TextView) findViewById(R.id.tv_item);
		vp=(ViewPager) findViewById(R.id.vp);
		ll_tt=ll_t;
//		ll_tt=new ArrayList<String>();
//		for(int i=0;i<7;i++){
//			ll_tt.add("栏目"+(i+1));
//		}
		for(int i=0;i<ll_t.size();i++){
			RadioButton rb=new RadioButton(context);
			rb.setId(i);
			rb.setText(ll_t.get(i));
			rb.setTextSize(14);
			rb.setTextColor(getResources().getColor(R.color.rb_tv_color));
			rb.setButtonDrawable(null);//这里想动态的把RadioButton的按钮图片去掉，尝试了很多方法，始终去不掉
			rb.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));//这里想动态的把RadioButton的按钮图片去掉，我帮你试试
			rb.setGravity(Gravity.CENTER);
			rb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rb_bg_shap));
			rg.addView(rb, i);
		}
		rg.setOnCheckedChangeListener(this);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(this);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		vp.setCurrentItem(checkedId);
//		switch (checkedId) {
//		case R.id.rb1:
//			vp.setCurrentItem(0, true);
//			break;
//		case R.id.rb2:
//			vp.setCurrentItem(1, true);
//			break;
//		case R.id.rb3:
//			vp.setCurrentItem(2, true);
//			break;
//		case R.id.rb4:
//			vp.setCurrentItem(3, true);
//			break;
//		case R.id.rb5:
//			vp.setCurrentItem(4, true);
//			break;
//		case R.id.rb6:
//			vp.setCurrentItem(5, true);
//			break;
//		case R.id.rb7:
//			vp.setCurrentItem(6, true);
//			break;
//		default:
//			break;
//		}
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total=(int) ((positionOffset+position)*rg.getChildAt(0).getWidth());
		int leftX=(vp.getWidth()-rg.getChildAt(0).getWidth())/2;
		hsv.smoothScrollTo((int) (total-leftX), 0);
		scrollItemByAttributAnimation(position,positionOffset);
	}

	private void scrollItemByAttributAnimation(int position,
			float positionOffset) {
		int[] loc=new int[2];
		rg.getChildAt(position).getLocationInWindow(loc);
		ViewHelper.setTranslationX(tv_item, loc[0]+positionOffset*rg.getChildAt(0).getWidth());
		if(position<rg.getChildCount()-1){
			float scall=rg.getChildAt(position+1).getWidth()/rg.getChildAt(position).getWidth()*1.0f;
			ViewHelper.setScaleX(tv_item, scall);
		}
	}

	public void onPageSelected(int position) {
		
	}

	public void onPageScrollStateChanged(int state) {
		
	}

}
