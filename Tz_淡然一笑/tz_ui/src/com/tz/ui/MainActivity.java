package com.tz.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/**
	 * ����������Ͳ���
	 */
	private LinearLayout ll;
	/**
	 * ����ˮƽ����������
	 */
	private LinearLayout ll_top;
	/**
	 * ���洹ֱ�����Ͳ���
	 */
	private LinearLayout ll_below;
	/**
	 * ȷ����ť
	 */
	private Button btn_confirm;
	/**
	 * ��������0-9
	 */
	private EditText et_inputNumber;
	/**
	 * ���洹ֱ�����Ͳ���	  �༭��
	 */
	private TextView tv_below;
	/**
	 * ���洹ֱ�����Ͳ���	 LayoutParams����
	 */
	private LinearLayout.LayoutParams ll_below_lp;

	private int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// ������ز��� 	  --> ��ҵ1
//		 initViewByCode();

		// ������ز��֣����ҵ����ť����TextView�ؼ�  	--> ��ҵ2
		initViewAndOnClickByCode();

	}
	
	/**
	 * ������ز���
	 */
	private void initViewByCode() {
		// ���������Բ���
		RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// ����ͼƬimgx����ڸ�����rl���з�ʽ����
		rl_lp.addRule(RelativeLayout.CENTER_IN_PARENT); 

		// �������������
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		EditText et = new EditText(this);
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et.setHint("��������������");
		ll.addView(et, 0);

		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText("����");
		ll.addView(btn, 1);

		// �����ͼƬ
		ImageView img = new ImageView(this);
		img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		img.setImageResource(R.drawable.mm);
		// Ҫ��ͼƬimg����ڸ�����rl�����ԣ����ø�img�������ô����ԣ�ͼƬĬ����ʾ�����Ͻ�
		img.setLayoutParams(rl_lp);

		rl.addView(ll, 0);
		rl.addView(img, 1);
		setContentView(rl);
	}

	/**
	 * ������ز��֣����ҵ����ť����TextView�ؼ� 
	 */
	private void initViewAndOnClickByCode() {
		// ����������Ͳ���
		ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.VERTICAL);

		// ����ˮƽ����������
		ll_top = new LinearLayout(this);
		ll_top.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll_top.setOrientation(LinearLayout.HORIZONTAL);

		et_inputNumber = new EditText(this);
		et_inputNumber.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et_inputNumber.setHint("����������");
		// �༭������ֻ����������0-9
		et_inputNumber.setInputType(InputType.TYPE_CLASS_NUMBER); 
		ll_top.addView(et_inputNumber);

		btn_confirm = new Button(this);
		btn_confirm.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn_confirm.setText("ȷ��");
		ll_top.addView(btn_confirm);

		// ��ll_top�ŵ�ll��
		ll.addView(ll_top); 

		// ���洹ֱ�����Ͳ���
		ll_below = new LinearLayout(this);
	    ll_below_lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// ������߾�	left, top, right, bottom
		ll_below_lp.setMargins(10, 5, 10, 5);
		ll_below.setOrientation(LinearLayout.VERTICAL);

		// ��ll_below�ŵ�ll��
		ll.addView(ll_below); 
		// ��һ�μ���ll
		setContentView(ll);

		// ȷ����ť����¼�
		btn_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ���������
				number = Integer.parseInt(et_inputNumber.getText().toString());
				// �������������0-9ʱ����ȥִ��ˢ��ll_below��ͼ�Ĳ���
				if(number>0 && number<10){
					// ���ȷ����ť��ˢ��ll_below��ͼ����̬����TextView�ؼ�
					dynamicViewByCode(number);
				} else {
					Toast.makeText(MainActivity.this, "������0~9֮�����������", Toast.LENGTH_SHORT).show();
					// �༭�������ÿ�
					et_inputNumber.setText("");
					// ��ȡ�༭�򽹵�
					et_inputNumber.requestFocus();
				}
				
			}
		});

	}

	/**
	 * ���ȷ����ť��ˢ��ll_below��ͼ����̬����TextView�ؼ�
	 * 
	 * @param number
	 */
	private void dynamicViewByCode(int number) {
		// �����tv_below�������¼���tv_below
		ll_below.removeAllViews();
		for (int i = 0; i < number; i++) {
			tv_below = new TextView(this);
			tv_below.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// ��������
			tv_below.setText("��" + i + "��TextView�ؼ�");
			// ��������
			tv_below.setTextSize(18);
			// ��߾�
			tv_below.setLayoutParams(ll_below_lp);
			// ����������ж��뷽ʽ
			tv_below.setGravity(Gravity.CENTER);
			// ���ñ߿�
			tv_below.setBackgroundResource(R.drawable.textview_border);
			// ��tv_below�ŵ�ll_below
			ll_below.addView(tv_below); 
		}
		// ˢ��ll_below��ͼ���м�
		this.ll_below.invalidate();
		// ���¼���ll����
		setContentView(ll);
	}

}
