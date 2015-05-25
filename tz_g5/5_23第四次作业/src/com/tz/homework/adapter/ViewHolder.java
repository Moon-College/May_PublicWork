package com.tz.homework.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by guanzhe on 14-9-28.
 */
public class ViewHolder {
    public static <T extends View> T get(View view,int id){
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if(viewHolder == null){
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if(childView == null){
            childView = view.findViewById(id);
            viewHolder.put(id,childView);
        }
        return (T) childView;
    }
}
