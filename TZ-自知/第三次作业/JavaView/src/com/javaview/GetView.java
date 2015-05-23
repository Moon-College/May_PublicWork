package com.javaview;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GetView {
	/**
	 * 获取搜索界面
	 * @param context
	 * @param Str
	 * @param ocl
	 * @return
	 */
	public static LinearLayout getTop(Context context,String Str,final OnClickListener ocl)
	{
		LinearLayout ll=new LinearLayout(context);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		
		EditText et=new EditText(context);
		LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT );
		lp.weight=1;
		et.setLayoutParams(lp);
		
		Button bt=new Button(context);
		bt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		bt.setText(Str);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ocl!=null)
				{
					ocl.onClick(v);
				}
			}
		});
		ll.addView(et);
		ll.addView(bt);		
		return ll;
	}

	/**
	 * 获取父控价
	 * @param context
	 * @return
	 */
	public static LinearLayout getParent(Context context) {
		LinearLayout ll_parent=new LinearLayout(context);
		ll_parent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ll_parent.setOrientation(LinearLayout.VERTICAL);
		return ll_parent;
	}
}
