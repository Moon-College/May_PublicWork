package com.rocy.tz_class_contentprivoder;

import java.util.ArrayList;

import com.rocy.tz_class_contentprivoder.bean.PhoneContact;
import com.rocy.tz_class_contentprivoder.util.ContactsUtil;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContacts.Entity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    ArrayList<PhoneContact> array = new ArrayList<PhoneContact>();
	private ContentResolver resolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver resolver = this.getContentResolver();
		//query();
		ContactsUtil.newInsert("啊啊", resolver).insertPhone("1233454654").builder();
	}
	
	
	
	private void query() {
		resolver = this.getContentResolver();
		Cursor cursor = resolver.query(RawContacts.CONTENT_URI,null,null, null, null);
		while (cursor.moveToNext()) {
			PhoneContact contact = new PhoneContact();
			long _id =cursor.getInt(cursor.getColumnIndex(RawContacts.Entity._ID));
			String name = cursor.getString(cursor.getColumnIndex(RawContacts.DISPLAY_NAME_PRIMARY));
			contact.setAccount_name(name);
			Cursor query = resolver.query(Data.CONTENT_URI, null,Data.RAW_CONTACT_ID+"=?" , new String[]{_id+""}, null);
			while (query.moveToNext()){
				String data =query.getString(query.getColumnIndex(Entity.DATA1));
				String mimeType = query.getString(query.getColumnIndex(Entity.MIMETYPE));
				if(Phone.CONTENT_ITEM_TYPE.equals(mimeType)){
					contact.setPhone(data);
				}
				
			}
			query.close();
			array.add(contact);
			
		}
		cursor.close();
		for (PhoneContact i : array) {
			Log.i("info", "rocy:"+i.getAccount_name()+","+i.getPhone());
		}
	}
}
