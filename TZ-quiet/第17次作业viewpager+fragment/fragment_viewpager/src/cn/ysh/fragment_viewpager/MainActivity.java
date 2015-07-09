package cn.ysh.fragment_viewpager;

import com.nineoldandroids.view.ViewHelper;

import cn.ysh.fragment.MyFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnCheckedChangeListener {
	private ViewPager viewPager;
	private RadioGroup rg;
	private HorizontalScrollView hsv;
	private TextView tx;
	private int[] image = {R.drawable.luffy,
			R.drawable.suolong,
			R.drawable.sanji,
			R.drawable.mamei,
			R.drawable.qiaoba,
			R.drawable.luobin
			};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView) findViewById(R.id.textview);
        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);
    }
    
    private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			MyFragment fg = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("position", image[arg0]);
			fg.setArguments(bundle);
			return fg;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return image.length;
		}
    	
    }

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position + positionOffset) * rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth() - rg.getChildAt(0).getWidth()) / 2; 
		int scrollX = total - left;
		int[] location = new int[2];
		rg.getChildAt(position).getLocationInWindow(location);
		int txScrollX = (int) (location[0] + positionOffset * rg.getChildAt(0).getWidth());
		ViewHelper.setTranslationX(tx, txScrollX);
		hsv.smoothScrollTo(scrollX, 0);
	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.luffy:
			viewPager.setCurrentItem(0);
			break;
		case R.id.suolong:
			viewPager.setCurrentItem(1);
			break;
		case R.id.sanji:
			viewPager.setCurrentItem(2);
			break;
		case R.id.namei:
			viewPager.setCurrentItem(3);
			break;
		case R.id.qiaoba:
			viewPager.setCurrentItem(4);
			break;
		case R.id.luobin:
			viewPager.setCurrentItem(5);
			break;

		default:
			break;
		}
		
	}

}