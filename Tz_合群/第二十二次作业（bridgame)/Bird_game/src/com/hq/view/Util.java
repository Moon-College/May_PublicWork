package com.hq.view;

import android.content.Context;
import android.util.TypedValue;

/**
 * 工具�?
 * @author zhy
 *
 */
public class Util
{

	/**
	 * dp2px
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, float dp)
	{
		int px = Math.round(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
						.getDisplayMetrics()));
		return px;
	}

}
