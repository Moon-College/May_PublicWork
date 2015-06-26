package com.riders.browser.constans;

import android.os.Environment;

/**
 * 
 * @ClassName: RidersConstans
 * @Description: TODO
 * @author: Riders
 * @date: 2015-5-27 下午3:18:14
 */
public class RidersConstans {
	//得到系统的挂载 /mnt
	public static final String MNTROOT = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
}
