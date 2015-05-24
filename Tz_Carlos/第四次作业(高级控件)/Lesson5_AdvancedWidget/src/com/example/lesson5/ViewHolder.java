package com.example.lesson5;

import android.util.SparseArray;
import android.view.View;

/** 
 * @author Carlos
 * @version 1.0
 * @updateTime 2015年5月25日 上午2:43:35
 * Description: 
 */
public class ViewHolder {
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}

}
