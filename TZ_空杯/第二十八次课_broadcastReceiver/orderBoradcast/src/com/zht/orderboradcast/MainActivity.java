/**
 * Project Name:orderBoradcast
 * File Name:MainActivity.java
 * Package Name:com.zht.orderboradcast
 * Date:2015-7-25下午4:52:47
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.orderboradcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-25 下午4:52:47 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = new Intent();
        intent.setAction("com.zht.order.broadcast");
        this.sendOrderedBroadcast(intent, null);
	}
}
