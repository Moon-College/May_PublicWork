package com.rocy.tz_class_contentprivoder.util;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import com.rocy.tz_class_contentprivoder.bean.BaseBean;
import com.rocy.tz_class_contentprivoder.bean.PhoneContact;

/**
 * 系统联系人增删改查
 * @author Rocy
 *
 */
public class ContactsUtil {

	 
     
     /**'
      * 获得添加的基类 然后在自己想添加什么添加什么
      * 增加 必须添加名字 才能成功
      * @param name 
      */
     public static InsertBuilder newInsert(String name ,ContentResolver cr) {
				return new InsertBuilder(name,cr);
				
	}
	
	 
	 /**
	  * 
	  * @author Administrator
	  *
	  */
	 public  static  class  InsertBuilder{
		private ContentResolver cr;
		private ArrayList<ContentProviderOperation> operration;
		private ContentValues values;
		private int index;
		private InsertBuilder(String name,ContentResolver cr) {
			this.cr = cr ;
			values = new ContentValues();
			operration = new ArrayList<ContentProviderOperation>();
			index =operration.size();
			requiredFild(name);
		}
		private void requiredFild(String name ) {
			// 添加必须添加名字
			Uri uri = RawContacts.CONTENT_URI;
			operration.add(ContentProviderOperation.newInsert(uri).withValues(values).build());
			values.clear();
			values.put(RawContacts.Data.MIMETYPE, "vnd.android.cursor.item/name");
			values.put(Data.DATA1, name);
			operration.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, 0).withValues(values).build());
		}
		 
		/**
		 * 添加电话号码
		 * @param phone
		 * @return
		 */
		public  InsertBuilder insertPhone(String phone){
				
				 values.clear();
				 values.put(RawContacts.Data.DATA1, phone);
				 values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE); 
				 operration.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, 0).withValues(values).build());
			return this;
			
		}
		
		
		
		/**
		 * 添加邮箱
		 * @param email
		 * @return
		 */
		public InsertBuilder insertEmail(String email){
			if(email != null){
				 int index =operration.size();
				 values.clear();
				 values.put(RawContacts.Data.DATA1, email);
				 values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE); 
				 operration.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, 0).withValues(values).build());
			}
			
			
			return this;
			
		}
		
		
		public ContactsUtil builder(){
			try {
				//远程事务批次处理
				cr.applyBatch(ContactsContract.AUTHORITY, operration);
				return new ContactsUtil();
			} catch (Exception e) {
				// 没成功返回null
				e.printStackTrace();
				return null;
			}
		}
		
	 } 
}
