package com.decent.decentviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.decent.decentutil.ReflictionUtil;
import com.decent.decentviewpager.view.ImmortalsFragment;

public class DecentViewPagerActivity extends FragmentActivity implements
		OnClickListener {

	public static final String TAG = "DecentViewPagerActivity";
	private HorizontalScrollView hsv;
	private FrameLayout fl;
	private ViewPager vp;
	private RadioGroup rg;
	/*
	 * fl那个横条所在的位置
	 */
	private int previousX = 0;
	/*
	 * 图片的resources列表
	 */
	private int[] img_array = { R.drawable.tieguaili, R.drawable.hanzhongli,
			R.drawable.zhangguolao, R.drawable.lvdongbin, R.drawable.hexiangu,
			R.drawable.lancaihe, R.drawable.hanxiangzi, R.drawable.caoguojiu };
	/*
	 * 描述string的resources列表
	 */
	private int[] des_array = { R.string.tieguaili_des,
			R.string.hanzhongli_des, R.string.zhangguolao_des,
			R.string.lvdongbin_des, R.string.hexiangu_des,
			R.string.lancaihe_des, R.string.hanxiangzi_des,
			R.string.caoguojiu_des };
	/*
	 * 每一个title的宽度
	 */
	public int title_size;
	/*
	 * 计算窗口的宽度，在这里相当于vp的宽度
	 */
	private int windowsWidth;
	/*
	 * 计算 (窗口宽度-一个title的宽度)/2
	 */
	private int middleOffset;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentViewPagerActivity.class.getName(),
				R.class.getName(), this);
		/*
		 * 设置viewPager的FragmentPagerAdapter
		 */
		ImmortalsFragmentAdaptor ifa = new ImmortalsFragmentAdaptor(
				getSupportFragmentManager());
		vp.setAdapter(ifa);
		/*
		 * 设置viewPager的OnPageChangeListener
		 */
		ImmortalOnPageChangeListener iocListener = new ImmortalOnPageChangeListener();
		vp.setOnPageChangeListener(iocListener);
		/*
		 * 设置每一个radioButton的onClickListener
		 */
		for (int i = 0; i < rg.getChildCount(); i++) {
			rg.getChildAt(i).setOnClickListener(this);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		/*
		 * 获取每一个title的宽度
		 */
		title_size = getResources().getDimensionPixelSize(R.dimen.title_size);
		/*
		 * 计算窗口的宽度，在这里相当于vp的宽度,放在onResume里面都获取不到，
		 * 所以放在onWindowFocusChanged里面获取
		 */
		windowsWidth = vp.getWidth();

		/*
		 * 计算 (窗口宽度-一个title的宽度)/2
		 */
		middleOffset = (windowsWidth - title_size) / 2;				
	}
	
	private class ImmortalsFragmentAdaptor extends FragmentPagerAdapter {

		public ImmortalsFragmentAdaptor(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			/*
			 * 新建ImmortalsFragment，初始化相关信息的bundle，放到setArguments里面
			 */
			ImmortalsFragment iFragment = new ImmortalsFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(ImmortalsFragment.IMG_RES, img_array[position]);
			bundle.putInt(ImmortalsFragment.DES_RES, des_array[position]);
			bundle.putInt(ImmortalsFragment.IMMORTAL_INDEX, position);
			iFragment.setArguments(bundle);
			return iFragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			/*
			 * 直接返回rg的子控件个数
			 */
			return rg.getChildCount();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			Log.d(TAG,"into destroyItem position="+position);
			super.destroyItem(container, position, object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Log.d(TAG,"into instantiateItem position="+position);			
			return super.instantiateItem(container, position);
		}

	}

	private class ImmortalOnPageChangeListener implements OnPageChangeListener {

		/**
		 * Called when the scroll state changes. viewpager的滑动状态的回调函数
		 * SCROLL_STATE_IDLE/SCROLL_STATE_DRAGGING/SCROLL_STATE_SETTLING
		 */
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}

		/**
		 * position----当前滑动到的位置page的index Position index of the first page
		 * currently being displayed. Page position+1 will be visible if
		 * positionOffset is nonzero. positionOffset----相对于当前当前滑动了多少的百分比 Value
		 * from [0, 1) indicating the offset from the page at position.
		 * positionOffsetPixels----当前页面偏移的像素位置 Value in pixels indicating the
		 * offset from position.
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			/*
			 * 为了保证滑动过程中那个title能够居中，算法就是 position*每个title的宽度 -
			 * middleOffset(就是从屏幕开始到把title放在屏幕中间的需要的offset)
			 */
			int currentX = position * title_size - middleOffset;
			Log.d(TAG, "onPageScrolled currentX=" + currentX + ",position="
					+ position + ",title_size" + title_size + ",middleOffset="
					+ middleOffset + ",windowsWidth=" + windowsWidth);
			hsv.smoothScrollTo(currentX, 0);
		}

		/**
		 * 选中当前页的时候被调用 This method will be invoked when a new page becomes
		 * selected. Position index of the new selected page.
		 */
		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			//这个title之前title的长度总和
			int preSpace = position * title_size;
			//这个title之后title的长度总和
			int leftSpace = (rg.getChildCount() - (position + 1)) * title_size;
			int currentX;
			if (preSpace < middleOffset) {
				//因为前面的title太少不能摆放在中间的情况，currentX就是position * title_size
				currentX = position * title_size;
			} else if (middleOffset > leftSpace) {
				/*
				 * 因为后面的title太少不能拜访在中间的情况，currentX就是
				 * 整个窗口的宽度  - 后面的title的宽度（包括自己，所以position不用减1）
				 * 比如第8个title的时候position是7
				 */
				currentX = windowsWidth - (rg.getChildCount() - position)
						* title_size;
			} else {
				/*
				 * 也就是preSpace>=deltaWidth && deltaWidth<=leftSpace,
				 * 也就是title摆在正中间的时候，这个时候currentX就是middleOffset
				 */
				currentX = middleOffset;
			}
			//新建动画previousX~currentX，250ms
			TranslateAnimation ta = new TranslateAnimation(previousX, currentX,
					0, 0);
			fl.setAnimation(ta);
			//在动画之后保持状态
			ta.setFillAfter(true);
			ta.setDuration(250);
			ta.start();
			//记录当前的坐标
			previousX = currentX;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rb_tieguaili:
			vp.setCurrentItem(0);
			break;
		case R.id.rb_hanzhongli:
			vp.setCurrentItem(1);
			break;
		case R.id.rb_zhangguolao:
			vp.setCurrentItem(2);
			break;
		case R.id.rb_lvdongbin:
			vp.setCurrentItem(3);
			break;
		case R.id.rb_hexiangu:
			vp.setCurrentItem(4);
			break;
		case R.id.rb_lancaihe:
			vp.setCurrentItem(5);
			break;
		case R.id.rb_hanxiangzi:
			vp.setCurrentItem(6);
			break;
		case R.id.rb_caoguojiu:
			vp.setCurrentItem(7);
			break;
		default:
			break;
		}
	}
}