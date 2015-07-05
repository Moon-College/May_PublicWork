package com.lin.mytzwork.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/6/27.
 */
public class UiUtil {

    public static Display getDisplay(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        return defaultDisplay;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Display displayDisplay = getDisplay(context);
        DisplayMetrics outMetrics = new DisplayMetrics();
        displayDisplay.getMetrics(outMetrics);
        return outMetrics;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, int dipValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (pxValue / scale + 0.5f);
    }


}
