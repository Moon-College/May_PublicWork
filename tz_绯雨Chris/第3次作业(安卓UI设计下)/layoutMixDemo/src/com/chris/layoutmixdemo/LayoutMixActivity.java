package com.chris.layoutmixdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LayoutMixActivity extends Activity implements OnClickListener
{
	private static final String TAG = "LayoutMixActivity";
	private static final int MAX_TEXT_LINE_NUM = 18;

	private static final int BTN_SEARCH_ID = 1512;
	private static final int BTN_CREATE_ID = BTN_SEARCH_ID + 1;
	private static final int BTN_CLEAR_ID = BTN_CREATE_ID + 1;
	private static final int TEXT_SEARCH_ID = BTN_CLEAR_ID + 1;
	private static final int TEXT_CREATE_ID = TEXT_SEARCH_ID + 1;
	private static final int DEMO_IMAGE_ID = TEXT_CREATE_ID + 1;
	private static final int DOT_IMAGE_ID = DEMO_IMAGE_ID + 1;
	private static final int BTN_EXCHANGE_FST = DOT_IMAGE_ID + 1;
	private static final int BTN_EXCHANGE_SEC = BTN_EXCHANGE_FST + 1;
	private static final int MAIN_LAYOUT = BTN_EXCHANGE_SEC + 1;
	private static final int SEC_LAYOUT = MAIN_LAYOUT + 1;
	private static final int CHILD_SEARCH_LAYOUT = SEC_LAYOUT + 1;
	private static final int CHILD_CREATE_LAYOUT = CHILD_SEARCH_LAYOUT + 1;

	private RelativeLayout mainLayout = null;
	private RelativeLayout.LayoutParams mainLP = null;
	private RelativeLayout secLayout = null;
	private RelativeLayout.LayoutParams secLP = null;

	private EditText globalTextCreate = null;
	private LinearLayout globalNexTextLayout = null;
	private int gLineCnt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* create first main layout */
		mainLayout = new RelativeLayout(this);
		mainLayout.setId(MAIN_LAYOUT);
		mainLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(mainLP);

		/* create second layout */
		secLayout = new RelativeLayout(this);
		secLayout.setId(SEC_LAYOUT);
		secLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		secLayout.setLayoutParams(secLP);

		/* start first main layout */
		layoutExchange(mainLayout);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case BTN_EXCHANGE_FST:
			layoutExchange(mainLayout);
			break;
		case BTN_EXCHANGE_SEC:
			layoutExchange(secLayout);
			break;
		case BTN_CREATE_ID:
			String strNum = globalTextCreate.getText().toString();
			int cnt = 0;
			if(!strNum.equals(""))
			{
				cnt = Integer.parseInt(strNum);
			}
			
			if(cnt>MAX_TEXT_LINE_NUM)
				cnt = MAX_TEXT_LINE_NUM;
			
			for (int i = 0; i < cnt; i++)
			{
				gLineCnt++;
				makeNewTextContent(gLineCnt);
			}
			globalTextCreate.setText("");
			break;
		case BTN_CLEAR_ID:
			globalNexTextLayout.removeAllViews();
			globalTextCreate.setText("");
			gLineCnt = 0;
			break;
		default:
			break;
		}
	}

	private void makeNewTextContent(int num)
	{
		TextView txt = new TextView(LayoutMixActivity.this);
		txt.setBackgroundColor(Color.LTGRAY);
		txt.setText(""+num);
		txt.setGravity(Gravity.CENTER);
		globalNexTextLayout.addView(txt);
		Log.i(TAG, "globalNexTextLayout child count = " + globalNexTextLayout.getChildCount());
	}

	private void layoutExchange(RelativeLayout layout)
	{
		if (MAIN_LAYOUT == layout.getId())
		{
			if (0 != secLayout.getChildCount())
			{
				secLayout.removeAllViews(); //clean last layout if necessary
			}
			/*
			 * Create child for search bar within a LinearLayout
			 */
			LinearLayout childSearchLayout = new LinearLayout(this);
			childSearchLayout.setId(CHILD_SEARCH_LAYOUT);
			RelativeLayout.LayoutParams childSearchBarLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			childSearchBarLP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			childSearchBarLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			childSearchLayout.setLayoutParams(childSearchBarLP);
			/* create button */
			Button btnSearch = createButtonLayout(BTN_SEARCH_ID, getString(R.string.btnSearch),
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
			/* create edit text */
			EditText textSearch = createTextViewLayout(TEXT_SEARCH_ID, getString(R.string.textSearch),
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
			childSearchLayout.addView(textSearch);
			childSearchLayout.addView(btnSearch);

			/*
			 * Create child for image bar within a FrameLayout
			 */
			FrameLayout imageLayout = new FrameLayout(this);
			RelativeLayout.LayoutParams imgBarLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			imgBarLP.addRule(RelativeLayout.CENTER_IN_PARENT);
			imageLayout.setLayoutParams(imgBarLP);
			ImageView picDemo = createDemoLayout(R.drawable.demo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			ImageView picDot = createDotLayout(R.drawable.dot, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			imageLayout.addView(picDemo);
			imageLayout.addView(picDot);

			/*
			 * Create button for changing to second layout
			 */
			Button btnExchangeSec = createbtnExLayout(BTN_EXCHANGE_SEC, "点击切换作业2", LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			btnExchangeSec.setOnClickListener(this);

			/* set layout content view */
			mainLayout.addView(childSearchLayout);
			mainLayout.addView(imageLayout);
			mainLayout.addView(btnExchangeSec);
			Log.i(TAG, "main layout has child count = " + mainLayout.getChildCount());
			
			setContentView(mainLayout);
		} else if (SEC_LAYOUT == layout.getId())
		{
			if (0 != mainLayout.getChildCount())
			{
				mainLayout.removeAllViews(); //clean last layout if necessary
			}
			/*
			 * Create child for search bar within a LinearLayout
			 */
			LinearLayout childCreateLayout = new LinearLayout(this);
			childCreateLayout.setId(CHILD_CREATE_LAYOUT);
			RelativeLayout.LayoutParams childCreateBarLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			childCreateBarLP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			childCreateBarLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			childCreateLayout.setLayoutParams(childCreateBarLP);
			/* create button */
			Button btnCreate = createButtonLayout(BTN_CREATE_ID, getString(R.string.btnCreate),
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
			btnCreate.setId(BTN_CREATE_ID);
			btnCreate.setOnClickListener(LayoutMixActivity.this);
			/* clear button */
			Button btnClear = createButtonLayout(BTN_CREATE_ID, getString(R.string.btnClear),
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
			btnClear.setId(BTN_CLEAR_ID);
			btnClear.setOnClickListener(LayoutMixActivity.this);
			/* create edit text */
			EditText textCreate = createTextViewLayout(TEXT_CREATE_ID, getString(R.string.textCreate),
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
			textCreate.setInputType(InputType.TYPE_CLASS_NUMBER);	// restricted to input numbers
			globalTextCreate = textCreate;
			childCreateLayout.addView(textCreate);
			childCreateLayout.addView(btnCreate);
			childCreateLayout.addView(btnClear);

			/*
			 * create text views
			 */
			LinearLayout newTextLayout = new LinearLayout(LayoutMixActivity.this);
			newTextLayout.setBackgroundColor(Color.GRAY);
			newTextLayout.setOrientation(LinearLayout.VERTICAL);
			RelativeLayout.LayoutParams newTextLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			newTextLP.addRule(RelativeLayout.BELOW, CHILD_CREATE_LAYOUT);
			newTextLP.addRule(RelativeLayout.ABOVE, BTN_EXCHANGE_FST);
			newTextLayout.setLayoutParams(newTextLP);
			globalNexTextLayout = newTextLayout;

			/*
			 * Create button for changing to first layout
			 */
			Button btnExchangeFst = createbtnExLayout(BTN_EXCHANGE_FST, "点击切换作业1", LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			btnExchangeFst.setOnClickListener(this);

			secLayout.addView(childCreateLayout);
			secLayout.addView(newTextLayout);
			secLayout.addView(btnExchangeFst);
			Log.i(TAG, "second layout has child count = " + secLayout.getChildCount());
			
			setContentView(secLayout);
		}
	}

	private Button createbtnExLayout(int btnId, String string, int width, int height)
	{
		Button btnExt = new Button(this);
		btnExt.setText(string);
		btnExt.setId(btnId);
		RelativeLayout.LayoutParams btnExtLP = new RelativeLayout.LayoutParams(width, height);
		btnExtLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		btnExtLP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		btnExt.setLayoutParams(btnExtLP);
		return btnExt;
	}

	private ImageView createDemoLayout(int id, int width, int height)
	{
		ImageView demoImg = new ImageView(this);
		demoImg.setImageResource(id);
		FrameLayout.LayoutParams demoImgLP = new FrameLayout.LayoutParams(width, height);
		demoImgLP.gravity = Gravity.CENTER_VERTICAL;
		demoImg.setLayoutParams(demoImgLP);
		return demoImg;
	}

	private ImageView createDotLayout(int id, int width, int height)
	{
		ImageView dotImg = new ImageView(this);
		dotImg.setImageResource(id);
		FrameLayout.LayoutParams dotImgLP = new FrameLayout.LayoutParams(width, height);
		dotImgLP.gravity = Gravity.CENTER;
		dotImg.setLayoutParams(dotImgLP);
		return dotImg;
	}

	private Button createButtonLayout(int id, String str, int width, int height, int weight)
	{
		Button btn = new Button(this);
		btn.setId(id);
		btn.setText(str);
		LinearLayout.LayoutParams btnLP = new LinearLayout.LayoutParams(width, height, weight);
		btn.setLayoutParams(btnLP);
		return btn;
	}

	private EditText createTextViewLayout(int id, String str, int width, int height, int weight)
	{
		EditText et = new EditText(this);
		et.setId(id);
		et.setHint(str);
		LinearLayout.LayoutParams etLP = new LinearLayout.LayoutParams(width, height, weight);
		etLP.gravity = Gravity.BOTTOM;
		et.setLayoutParams(etLP);
		return et;
	}

}
