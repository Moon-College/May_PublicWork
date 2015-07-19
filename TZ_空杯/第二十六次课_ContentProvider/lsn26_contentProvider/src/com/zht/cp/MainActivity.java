/**
 * Project Name:activityTest
 * File Name:MainActivity.java
 * Package Name:com.zht.activitytest
 * Date:2015-3-27����4:34:09
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */
package com.zht.cp;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zht.cp.bean.ContactsInfo;
import com.zht.cp.utils.ContactsService;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        ContactsService service = new ContactsService(this);
        
        List<ContactsInfo> contacts2 = service.getAllContacts();
        //List<ContactsInfo> contacts2 = service.readContacts();
        for (int i = 0; i < contacts2.size(); i++) {
        	ContactsInfo contactsInfo = contacts2.get(i);
			Log.i("INFO", "姓名："+contactsInfo.getName()+",电话:"+contactsInfo.getPhone());
		}
        /*ContactsInfo contact = new ContactsInfo("王八", "wangba@wangba.com", "13838383838");
        service.insertContact(contact);*/
       /* ContactsInfo contact = new ContactsInfo("陈九", "wangba@wangba.com", "13838383838");
        service.insertContact2(contact);*/
	}
}
