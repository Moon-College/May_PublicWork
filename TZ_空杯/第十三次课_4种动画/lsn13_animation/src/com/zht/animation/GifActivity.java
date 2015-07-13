/**
 * Project Name:lsn13_animation
 * File Name:GifActivity.java
 * Package Name:com.zht.animation
 * Date:2015-6-15обнГ5:30:03
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.animation;

import com.zht.animation.widget.GifSurfaceView;

import android.app.Activity;
import android.os.Bundle;

/**
 * ClassName:GifActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-15 обнГ5:30:03 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class GifActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gif);
		GifSurfaceView sv = (GifSurfaceView) findViewById(R.id.sv);
		sv.setPath("20140728194855.gif");
		sv.setZoom(5f);
	}
}
