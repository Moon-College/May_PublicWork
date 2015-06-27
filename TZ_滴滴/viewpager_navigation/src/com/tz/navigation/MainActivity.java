package com.tz.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
    HorizontalScrollView hsv;
    RadioGroup rg;
    ViewPager viewPager;
    TextView tvLine;
    
    int [] flags; //����ͼƬ��ԴID����
    int fromX;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        rg = (RadioGroup) findViewById(R.id.rg);
        tvLine = (TextView) findViewById(R.id.tvline);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        
        flags = new int[]{
        		R.drawable.china,
        		R.drawable.korea,
        		R.drawable.nkorea,
        		R.drawable.japan,
        		R.drawable.usa,
        		R.drawable.india,
        		R.drawable.tail
        };
        
        viewPager.setAdapter(adapter);
        
        rg.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
    }
    
    private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		//����ÿһҳ��ʲô��
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			MyFragment fragment = new MyFragment();
			
			Bundle bundle = new Bundle();
			bundle.putInt("path", flags[arg0]);//�鲼��������ͼƬ��ԴID
			bundle.putInt("position", arg0); //�ڼ���ͼƬ
			
			fragment.setArguments(bundle);
			
			return fragment;
		}

		//�����ж���ҳ
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return rg.getChildCount();
		}
    	
    }

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.china:
			//�й���ѡ��
			viewPager.setCurrentItem(0);
			break;
		case R.id.korea:
			//������ѡ��
			viewPager.setCurrentItem(1);
			break;
		case R.id.nkorea:
			//���ʱ�ѡ��
			viewPager.setCurrentItem(2);
			break;
		case R.id.japan:
			//�ձ���ѡ��
			viewPager.setCurrentItem(3);
			break;
		case R.id.america:
			viewPager.setCurrentItem(4);
			break;
		case R.id.india:
			viewPager.setCurrentItem(5);
			break;
		case R.id.tail:
			viewPager.setCurrentItem(6);
			//�й���ѡ��
			break;
		default:
			break;
		}
	}

	//position��ʾ�����ڼ���ѡ�ѡ�У������й���һҳ��position=0��
	//positionOffset��ʾҳ���Ѿ�����ȥ���ٱ�����0��1��
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position+positionOffset)*rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth()-rg.getChildAt(0).getWidth())/2; //��ֵΪ�̶�ֵ������仯
		int scrollX = total - left;
		hsv.scrollTo(scrollX, 0); //����hsv���
		
		moveLine(position,positionOffset); //����TextView���
	}

	
	private void moveLine(int position, float positionOffset) {
		// TODO Auto-generated method stub
		//position��ʾ�����ڼ���ѡ�ѡ�У������й���һҳ��position=0��
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		//����
		int [] location = new int [2];
		rb.getLocationInWindow(location);
		//����λ�ƶ���������Ӧ�İ�ť��λ��
		TranslateAnimation animation = new TranslateAnimation(
				fromX
				,location[0]+positionOffset*rb.getWidth(), 
				0, 
				0);
		animation.setDuration(50);//����ʱ�䣬�Զ�һ��
		animation.setFillAfter(true);//������ͣ����
		fromX = (int) (location[0]+positionOffset*rb.getWidth());
		tvLine.startAnimation(animation);
	}

	//��һ����ҳ��ѡ��ʱ
	public void onPageSelected(int position) {
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		//rb.setTextColor(R.drawable.blue);
		rb.setTextSize(40.0f);
		//rb.setBackgroundColor(android.R.color.background_light);
		for (int i = 0; i < rg.getChildCount(); i++) {
			if (i != position) {
				RadioButton rbutton = (RadioButton) rg.getChildAt(i);
				//rbutton.setTextColor(R.drawable.white);
				rbutton.setTextSize(20.0f);
			}
		}
	}

	//ҳ�滬����״��ı�ʱ������ȥ���������ɿ���ҳ��ͣ������
	public void onPageScrollStateChanged(int state) {
		
	}
}