package com.tz.riders;

import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tz.riders.entity.Persion;

public class ContactView extends LinearLayout implements OnClickListener {
	public static final int IMGID = 12;
	public static final int NAMEID = 22;
	private Persion persion;
	private ImageView imgHeader;
	private TextView tvName, tvGender, tvNumber, tvHobby;
	private LinearLayout linView, rightView, consLinear;
	private List<Persion> lisPersions;
	private int position;

	public ContactView(Context context) {
		super(context);
		init(context);
	}

	public ContactView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ContactView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		consLinear = new LinearLayout(context);
		consLinear.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		consLinear.setOrientation(HORIZONTAL);
		// 控件实例化
		imgHeader = new ImageView(context);
		linView = new LinearLayout(context);
		rightView = new LinearLayout(context);
		tvName = new TextView(context);
		tvGender = new TextView(context);
		tvHobby = new TextView(context);
		tvNumber = new TextView(context);
		// 控件属性
		imgHeader.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		linView.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		linView.setOrientation(VERTICAL);
		rightView.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		rightView.setOrientation(VERTICAL);
		
		tvGender.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		tvName.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		tvNumber.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		tvHobby.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		// 添加子控件
		consLinear.addView(imgHeader);
		consLinear.addView(linView);
		consLinear.addView(rightView);
		linView.addView(tvName);
		linView.addView(tvNumber);
		rightView.addView(tvGender);
		rightView.addView(tvHobby);
		ContactView.this.addView(consLinear);
	}

	public void showPersion(Persion persion) {
		this.persion = persion;

		Log.i("INFO", persion.getPicture_id() + ":Picid");
		if (persion.getPicture_id() != 0) {
			imgHeader.setImageResource(persion.getPicture_id());
		}
		if (persion.getpName() != null) {
			tvName.setText(persion.getpName());
		}
		if (persion.getNumber() != 0) {
			tvNumber.setText(String.valueOf(persion.getNumber()));
		}
		if (persion.getpGender() == 0) {
			consLinear.setBackgroundColor(Color.BLUE);
			tvGender.setText("男神");
		} else {
			consLinear.setBackgroundColor(Color.RED);
			tvGender.setText("女神");
		}
		if (persion.getHobby() != null) {
			tvHobby.setText(persion.getHobby());
		}
		// if (sex != null) {
		// tvSex.setText(sex);
		// }
		// if (sex.equals("男")) {
		// ContactView.this.setBackgroundColor(Color.BLUE);
		// }else {
		// ContactView.this.setBackgroundColor(Color.RED);
		// }

	}

	@Override
	public void onClick(View v) {
		lisPersions.remove(position);
	}
}
