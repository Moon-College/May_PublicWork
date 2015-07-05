package com.org.viewpager.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.home_viewpgager_frgment.R;
import com.org.viewpager.fragment.MyFragment;

public class MainActivity extends FragmentActivity implements OnTouchListener {
    private HorizontalScrollView  hzScrollView;
    private RadioGroup radioGroup;
    private TextView text_line;
    private ViewPager viewPager;
    private int fromX;
    private  int [] imgs; 
    private ImageView img1,img2;
    
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVies();
    }

	private void initVies() {
       hzScrollView = (HorizontalScrollView) findViewById(R.id.hzoview);
       radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
       text_line = (TextView) findViewById(R.id.txt_line);
       viewPager = (ViewPager) findViewById(R.id.viewpager);
       viewPager.setOnTouchListener(this);
       img1 = (ImageView) findViewById(R.id.img_left);
       img2 = (ImageView) findViewById(R.id.img_right);
       imgs = new int []{
    	    	R.drawable.one,
    	    	R.drawable.japan,
    	    	R.drawable.india,
    	    	R.drawable.tail,
    	    	R.drawable.usa,
    	    	R.drawable.korea,
    	    	R.drawable.nkorea,
    	    	R.drawable.ic_launcher,
    	    };
       radioGroup.setOnCheckedChangeListener(new MyCheckChangeListener());
       MyViewPagerAdapter dapter = new MyViewPagerAdapter(getSupportFragmentManager());
       viewPager.setAdapter(dapter);
       viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    		   
		
	}
	
	private class MyViewPagerAdapter extends FragmentPagerAdapter{
             
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

	    //����ÿһҳViewPager����ͼ����
		 public Fragment getItem(int i) {
	       //Fragment�������������Լ�����
		    MyFragment fragment = new MyFragment();
		    Bundle  bundle = new Bundle();
		    bundle.putInt("Path", imgs[i]);
		    if(i == 0 || i== radioGroup.getChildCount()){
		    	img1.setImageResource(imgs[i]);
		    }else{
		    	img1.setImageResource(imgs[i -1 ]);
		    }
		    img2.setImageResource( imgs [i + 1]);
		    fragment.setArguments(bundle);
			 return  fragment;
			
		}

		//����ViewPager������
		public int getCount() {
			// TODO Auto-generated method stub
			return radioGroup.getChildCount();
		} 
		
		
	}
	private  class MyCheckChangeListener  implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
                   switch (checkedId) {
				case R.id.one:
					viewPager.setCurrentItem(0);
					break;
	              case R.id.twee:
	            	  viewPager.setCurrentItem(1);
					break;
	              case R.id.three:
	            	viewPager.setCurrentItem(4);
					break;
	               case R.id.four:
	            	 viewPager.setCurrentItem(3);
	           	      break;
	              case R.id.five:
	        	    viewPager.setCurrentItem(2);
		            break;
	              case R.id.sex:
	        	  viewPager.setCurrentItem(5);
		          break;
	              case R.id.seven:
	    	         viewPager.setCurrentItem(5);
	    	         break;
	              case R.id.eight:
	    	     viewPager.setCurrentItem(7);
	    	      break;
		         default:
		        break;
				}		
		} 
		
	}
	
	private class MyOnPageChangeListener implements OnPageChangeListener{

		@Override//ҳ��Ļ���״̬�ĸı�  ��Ϊ  ��ʼ���� 0  �������� 1 ֹͣ  2 ����һ��ҳ�� 3
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override//arg2 �ǽ� �����İٷֱ� ת��������      arg1 ҳ���İٷֱ� ��0 -1 
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		 int sum = (int) ((arg0 + arg1)*radioGroup.getChildAt(0).getWidth());
	     int  lef = (viewPager.getWidth() - radioGroup.getChildAt(0).getWidth())/2;	
	     int scrollX = sum - lef;
	     hzScrollView.scrollTo(scrollX, 0);
	        moveLine(arg0, arg1);
		}

		private void moveLine(int arg0, float arg1) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(arg0); 
            int [] location = new int[2];
            button.getLocationInWindow(location);
            //Button  b = new Button(MainActivity.this);
            TranslateAnimation animation = new TranslateAnimation(
    		fromX,
    		location[0] + arg1*button.getWidth(),
    		0, 
   		    0);
          //����������ʱ��
           animation.setDuration(40);
           //�֮��ֹͣ�ڵ�ǰ��λ��
           animation.setFillAfter(true);
           //�����fromX���൱������ͼƬ�ķŴ���ƶ���ʱ�� ����ָ��ɵ�ǰ��λ��
           fromX = (int) (location[0] + arg1*button.getWidth());
           text_line.setAnimation(animation);
         }
		

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}

		
	    //��ǰ�ǵڼ�ҳ
		
		
	}
	 /**
	  * List<fragment> fragmets = ArrayList<Fragmetn>();
	  * ����������list ���ڿ���ͨ��lis������
	  *  
	  *  ��ò�Ҫͨ��Fragment�Ĺ��캯������ֵ �����Ļ�
	  * */
	public boolean onTouch(View v, MotionEvent event) {
      
		return false;
	}
}
