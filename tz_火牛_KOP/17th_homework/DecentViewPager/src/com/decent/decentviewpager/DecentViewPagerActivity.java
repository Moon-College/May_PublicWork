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
	 * fl�Ǹ��������ڵ�λ��
	 */
	private int previousX = 0;
	/*
	 * ͼƬ��resources�б�
	 */
	private int[] img_array = { R.drawable.tieguaili, R.drawable.hanzhongli,
			R.drawable.zhangguolao, R.drawable.lvdongbin, R.drawable.hexiangu,
			R.drawable.lancaihe, R.drawable.hanxiangzi, R.drawable.caoguojiu };
	/*
	 * ����string��resources�б�
	 */
	private int[] des_array = { R.string.tieguaili_des,
			R.string.hanzhongli_des, R.string.zhangguolao_des,
			R.string.lvdongbin_des, R.string.hexiangu_des,
			R.string.lancaihe_des, R.string.hanxiangzi_des,
			R.string.caoguojiu_des };
	/*
	 * ÿһ��title�Ŀ��
	 */
	public int title_size;
	/*
	 * ���㴰�ڵĿ�ȣ��������൱��vp�Ŀ��
	 */
	private int windowsWidth;
	/*
	 * ���� (���ڿ��-һ��title�Ŀ��)/2
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
		 * ����viewPager��FragmentPagerAdapter
		 */
		ImmortalsFragmentAdaptor ifa = new ImmortalsFragmentAdaptor(
				getSupportFragmentManager());
		vp.setAdapter(ifa);
		/*
		 * ����viewPager��OnPageChangeListener
		 */
		ImmortalOnPageChangeListener iocListener = new ImmortalOnPageChangeListener();
		vp.setOnPageChangeListener(iocListener);
		/*
		 * ����ÿһ��radioButton��onClickListener
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
		 * ��ȡÿһ��title�Ŀ��
		 */
		title_size = getResources().getDimensionPixelSize(R.dimen.title_size);
		/*
		 * ���㴰�ڵĿ�ȣ��������൱��vp�Ŀ��,����onResume���涼��ȡ������
		 * ���Է���onWindowFocusChanged�����ȡ
		 */
		windowsWidth = vp.getWidth();

		/*
		 * ���� (���ڿ��-һ��title�Ŀ��)/2
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
			 * �½�ImmortalsFragment����ʼ�������Ϣ��bundle���ŵ�setArguments����
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
			 * ֱ�ӷ���rg���ӿؼ�����
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
		 * Called when the scroll state changes. viewpager�Ļ���״̬�Ļص�����
		 * SCROLL_STATE_IDLE/SCROLL_STATE_DRAGGING/SCROLL_STATE_SETTLING
		 */
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}

		/**
		 * position----��ǰ��������λ��page��index Position index of the first page
		 * currently being displayed. Page position+1 will be visible if
		 * positionOffset is nonzero. positionOffset----����ڵ�ǰ��ǰ�����˶��ٵİٷֱ� Value
		 * from [0, 1) indicating the offset from the page at position.
		 * positionOffsetPixels----��ǰҳ��ƫ�Ƶ�����λ�� Value in pixels indicating the
		 * offset from position.
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			/*
			 * Ϊ�˱�֤�����������Ǹ�title�ܹ����У��㷨���� position*ÿ��title�Ŀ�� -
			 * middleOffset(���Ǵ���Ļ��ʼ����title������Ļ�м����Ҫ��offset)
			 */
			int currentX = position * title_size - middleOffset;
			Log.d(TAG, "onPageScrolled currentX=" + currentX + ",position="
					+ position + ",title_size" + title_size + ",middleOffset="
					+ middleOffset + ",windowsWidth=" + windowsWidth);
			hsv.smoothScrollTo(currentX, 0);
		}

		/**
		 * ѡ�е�ǰҳ��ʱ�򱻵��� This method will be invoked when a new page becomes
		 * selected. Position index of the new selected page.
		 */
		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			//���title֮ǰtitle�ĳ����ܺ�
			int preSpace = position * title_size;
			//���title֮��title�ĳ����ܺ�
			int leftSpace = (rg.getChildCount() - (position + 1)) * title_size;
			int currentX;
			if (preSpace < middleOffset) {
				//��Ϊǰ���title̫�ٲ��ܰڷ����м�������currentX����position * title_size
				currentX = position * title_size;
			} else if (middleOffset > leftSpace) {
				/*
				 * ��Ϊ�����title̫�ٲ��ܰݷ����м�������currentX����
				 * �������ڵĿ��  - �����title�Ŀ�ȣ������Լ�������position���ü�1��
				 * �����8��title��ʱ��position��7
				 */
				currentX = windowsWidth - (rg.getChildCount() - position)
						* title_size;
			} else {
				/*
				 * Ҳ����preSpace>=deltaWidth && deltaWidth<=leftSpace,
				 * Ҳ����title�������м��ʱ�����ʱ��currentX����middleOffset
				 */
				currentX = middleOffset;
			}
			//�½�����previousX~currentX��250ms
			TranslateAnimation ta = new TranslateAnimation(previousX, currentX,
					0, 0);
			fl.setAnimation(ta);
			//�ڶ���֮�󱣳�״̬
			ta.setFillAfter(true);
			ta.setDuration(250);
			ta.start();
			//��¼��ǰ������
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