package com.chris.uilayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class UiLayoutDemoActivity extends Activity
{

	public static final String TAG = "UiLayoutDemoActivity";
	private final int BTN_ID = 0x1512;
	private final int ET_ID = 0x1513;
	private final int IMG_ID = 0x1514;

	Button btn;
	Button btnExtra;
	EditText edtxt;
	EditText edtxtExtra;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* set relative button */
		btn = (Button) getRelaytiveButtonLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, -1);

		/* set relative edit text */
		edtxt = (EditText) getRelaytiveEdtxtLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, btn.getId());

		/* set relative image view */
		img = (ImageView) getRelaytiveImageLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, -1);

		/* embed Linear layout */
		LinearLayout linearLayout = embedLnearLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		/* set main layout */
		RelativeLayout layoutMain = new RelativeLayout(this);
		RelativeLayout.LayoutParams mainLayoutParm = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutMain.setLayoutParams(mainLayoutParm);
		layoutMain.addView(btn);
		layoutMain.addView(edtxt);
		layoutMain.addView(img);
		layoutMain.addView(linearLayout);

		/* create main layout */
		setContentView(layoutMain);
	}

	/**
	 * embed a linear layout in the bottom of the main relative layout.
	 * 
	 * @param width
	 * @param height
	 * @param btnExtra
	 * @param edtxtExtra
	 * @return
	 */
	private LinearLayout embedLnearLayout(int width, int height)
	{
		LinearLayout linearLayout = new LinearLayout(this);
		RelativeLayout.LayoutParams linearLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		linearLayout.setLayoutParams(linearLayoutParams);

		/* set linear button */
		btnExtra = getLinarButtonLayout(0, LayoutParams.WRAP_CONTENT, 1);

		/* set relative edit text */
		edtxtExtra = getLinarEditText(0, LayoutParams.WRAP_CONTENT, 2);

		linearLayout.addView(btnExtra);
		linearLayout.addView(edtxtExtra);

		return linearLayout;
	}

	/**
	 * create a button inside the linear layout, which is in the bottom of the
	 * main relative layout
	 * 
	 * @param width
	 * @param height
	 * @param weight
	 * @return
	 */
	private Button getLinarButtonLayout(int width, int height, int weight)
	{
		Button btnExtra = new Button(this);
		btnExtra.setText(R.string.button_2);
		LinearLayout.LayoutParams btnExtraLayoutParams = new LinearLayout.LayoutParams(width, height);
		btnExtraLayoutParams.weight = weight;
		btnExtra.setLayoutParams(btnExtraLayoutParams);
		btnExtra.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				edtxtExtra.setText("");
			}
		});

		return btnExtra;
	}

	/**
	 * create a EditText inside the linear layout, which is in the bottom of the
	 * main relative layout, click it to clear the msg
	 * 
	 * @param width
	 * @param height
	 * @param weight
	 * @return
	 */
	private EditText getLinarEditText(int width, int height, int weight)
	{
		EditText edtxtExtra = new EditText(this);
		edtxtExtra.setHint(R.string.hello_world);
		LinearLayout.LayoutParams edtxtExtaLayoutParams = new LinearLayout.LayoutParams(width, height);
		edtxtExtaLayoutParams.weight = weight;
		edtxtExtra.setLayoutParams(edtxtExtaLayoutParams);

		return edtxtExtra;
	}

	/**
	 * create a button inside the main relative layout, click it to clear msg of
	 * the edit text
	 * 
	 * @param width
	 * @param height
	 * @param anchor
	 *            The id of another view to use as an anchor, or a boolean value
	 *            (represented as TRUE for true or 0 for false). For verbs that
	 *            don't refer to another sibling (for example,
	 *            ALIGN_WITH_PARENT_BOTTOM) just use -1.
	 * @return
	 */
	private Button getRelaytiveButtonLayout(int width, int height, int anchor)
	{
		Button btn = new Button(this);
		btn.setText(R.string.button_1);
		btn.setId(BTN_ID);
		RelativeLayout.LayoutParams btnLayoutParm = new RelativeLayout.LayoutParams(width, height);
		btnLayoutParm.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, anchor);
		btnLayoutParm.addRule(RelativeLayout.ALIGN_PARENT_TOP, anchor);
		btn.setLayoutParams(btnLayoutParm);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				edtxt.setText("");
			}
		});

		return btn;
	}

	/**
	 * create a edit text of the main relative layout.
	 * 
	 * @param width
	 * @param height
	 * @param anchor
	 *            The id of another view to use as an anchor, or a boolean value
	 *            (represented as TRUE for true or 0 for false). For verbs that
	 *            don't refer to another sibling (for example,
	 *            ALIGN_WITH_PARENT_BOTTOM) just use -1.
	 * @return
	 */
	private EditText getRelaytiveEdtxtLayout(int width, int height, int anchor)
	{
		EditText edtxt = new EditText(this);
		edtxt.setId(ET_ID);
		edtxt.setHint(R.string.hello_world);
		edtxt.setSingleLine(false);
		RelativeLayout.LayoutParams edTxtLayoutParm = new RelativeLayout.LayoutParams(width, height);
		edTxtLayoutParm.addRule(RelativeLayout.ALIGN_PARENT_LEFT, anchor);
		edTxtLayoutParm.addRule(RelativeLayout.ALIGN_BOTTOM, anchor);
		edTxtLayoutParm.addRule(RelativeLayout.LEFT_OF, anchor);
		edtxt.setLayoutParams(edTxtLayoutParm);

		return edtxt;
	}

	/**
	 * create a image view in the center of the main relative layout
	 * 
	 * @param width
	 * @param height
	 * @param anchor
	 *            The id of another view to use as an anchor, or a boolean value
	 *            (represented as TRUE for true or 0 for false). For verbs that
	 *            don't refer to another sibling (for example,
	 *            ALIGN_WITH_PARENT_BOTTOM) just use -1.
	 * @return
	 */
	private ImageView getRelaytiveImageLayout(int width, int height, int anchor)
	{
		ImageView img = new ImageView(this);
		img.setId(IMG_ID);
		img.setImageResource(R.drawable.ic_launcher);
		RelativeLayout.LayoutParams imgLayoutParm = new RelativeLayout.LayoutParams(width, height);
		imgLayoutParm.addRule(RelativeLayout.CENTER_IN_PARENT, anchor);
		img.setLayoutParams(imgLayoutParm);

		return img;
	}

}
