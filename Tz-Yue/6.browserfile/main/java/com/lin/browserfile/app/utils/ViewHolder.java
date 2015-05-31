package com.lin.browserfile.app.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Administrator on 2015/5/31.
 *
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
        }
        return (T) childView;
    }
}
