package com.xigua.contentprovider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.provider.ContactsContract;

public class MainActivity extends Activity {

	public static Uri rawContactUri = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
	public static Uri baseUri = ContactsContract.AUTHORITY_URI;
	public static Uri dataUri = Uri.withAppendedPath(baseUri, "data");
	private ContactInfo friend1,friend2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		queryAllContacts();				
//		insertContacts(createContacts(false));
		updateContacts(createContacts(true));
//		boolean isNeed = querySpecifiedContactIsNeedUpdate(friend5);
//		Log.i("INFO", "IsNeedUpdate:"+isNeed);
	}
	
	/**
	 * �����������ϵ������
	 * @author XiGua
	 * @return List<ContactInfo>
	 * @date 2015.07.17
	 */
	private List<ContactInfo> createContacts(boolean isupdate){
		List<ContactInfo> friendsList = new ArrayList<ContactInfo>();		
        friend1 = new ContactInfo("Danny","858030348@qq.com","18888888888");
//		friend2 = new ContactInfo("David","33697367@qq.com","16666666666");
		if(isupdate){
		friend1.setPhone("18888888889");
//		friend2.setPhone("16666666667");
		}
		friendsList.add(friend1);
//		friendsList.add(friend2);
		return friendsList;
	}
	/**
	 * ��ѯ�����ֻ�ͨѶ¼�������ϵ���������绰������
	 * @author XiGua
	 * @return Log the contacts information
	 * @date 2015.07.17
	 */
	private void queryAllContacts(){
		List<ContactInfo> contacts = new ArrayList<ContactInfo>();
		ContentResolver resolver = getContentResolver();
		Cursor raw_contacts_cursor = resolver.query(rawContactUri, new String[]{"_id","display_name"}, null, null, null);
		while(raw_contacts_cursor.moveToNext()){
			ContactInfo contactInfo  = new ContactInfo();
			int r_id = raw_contacts_cursor.getInt(raw_contacts_cursor.getColumnIndex("_id"));
			Cursor data_cursor = resolver.query(dataUri,null,"raw_contact_id=?", new String[]{String.valueOf(r_id)}, null);
			while(data_cursor.moveToNext()){
				String data1 = data_cursor.getString(data_cursor.getColumnIndex("data1"));
				String mimetype = data_cursor.getString(data_cursor.getColumnIndex("mimetype"));
				if(mimetype.equals("vnd.android.cursor.item/email_v2")){
					contactInfo.setEmail(data1);
				}else if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
					contactInfo.setPhone(data1);
				}else if(mimetype.equals("vnd.android.cursor.item/name")){
					contactInfo.setName(data1);
				}
			}
			contacts.add(contactInfo);
		}
		
		for(ContactInfo contactInfo:contacts){
			Log.i("INFO", "name:"+contactInfo.getName());
			Log.i("INFO", "number:"+contactInfo.getPhone());
			Log.i("INFO", "email:"+contactInfo.getEmail());
		}
		
	}
	
    /**
     * ���ֻ�ͨѶ¼���������ϵ������
     * @author XiGua
     * @param contact
     * @date 2015.07.17
     */
	private void insertContacts(List<ContactInfo> insertcontacts){
		ContentResolver resolver = getContentResolver();
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		for(int i=0;i<insertcontacts.size();i++){
		  ops.clear();
		  ContentValues values = new ContentValues();
		  ContentProviderOperation op1 = ContentProviderOperation.newInsert(rawContactUri).withValues(values).build();
		  ops.add(op1);
		  
		  values.clear();
		  values.put("data1", insertcontacts.get(i).getName());
		  values.put("mimetype", "vnd.android.cursor.item/name");
		  ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
		  ops.add(op2);
		  
		  values.clear();
		  values.put("data1",insertcontacts.get(i).getPhone());
		  values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		  ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
		  ops.add(op3);
		  
		  values.clear();
		  values.put("data1",insertcontacts.get(i).getEmail());
		  values.put("mimetype", "vnd.android.cursor.item/email_v2");
		  ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
		  ops.add(op4);
		  
		try {
			resolver.applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException | OperationApplicationException e) {
			e.printStackTrace();
		}
		  		  
	  }
	}
	
	private void updateContacts(List<ContactInfo> updatecontacts){
		
		for(int i=0;i<updatecontacts.size();i++){
			//��ʾ�������ϵ���иı���Ҫ����
			querySpecifiedContactIsNeedUpdate(updatecontacts.get(i));
//            Log.i("INFO", "IsUpdate:"+i+isUpdate);
		}
	}
	
	/**
	 * �жϸ��µ���ϵ���Ƿ������ݿ��Ƿ���ڣ��Ƿ������ݸı䣬�иı������
     * @author XiGua
	 * @param specifiedcontact
	 * @return true if need to update,else false
	 */
	private void querySpecifiedContactIsNeedUpdate(ContactInfo specifiedcontact){
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ContentResolver resolver = getContentResolver();
		Cursor raw_contacts_cursor = resolver.query(rawContactUri, new String[]{"_id","display_name"}, "display_name=?", new String[]{specifiedcontact.getName()}, null);
		//δ�鵽ָ�����ֵ���ϵ�˷���false
		if(raw_contacts_cursor.moveToNext()){
			int r_id = raw_contacts_cursor.getInt(raw_contacts_cursor.getColumnIndex("_id"));
			Cursor data_cursor = resolver.query(dataUri,null,"raw_contact_id=?", new String[]{String.valueOf(r_id)}, null);			
			while(data_cursor.moveToNext()){
				ContentValues values = new ContentValues();
				String data1 = data_cursor.getString(data_cursor.getColumnIndex("data1"));
				String mimetype = data_cursor.getString(data_cursor.getColumnIndex("mimetype"));
				if(mimetype.equals("vnd.android.cursor.item/email_v2")){
					if(!data1.equals(specifiedcontact.getEmail())){
                                                  values.clear();
						  values.put("data1", specifiedcontact.getEmail());
						  values.put("mimetype", "vnd.android.cursor.item/email_v2");
						  ContentProviderOperation op1 = ContentProviderOperation.newUpdate(dataUri).withValues(values).build();
						  ops.add(op1);
//						return true;
						}
				}else if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
					if(!data1.equals(specifiedcontact.getPhone())){
						  values.clear();
						  values.put("data1", specifiedcontact.getPhone());
						  values.put("mimetype", "vnd.android.cursor.item/phone_v2");
						  ContentProviderOperation op2 = ContentProviderOperation.newUpdate(dataUri).withValues(values).build();
						  ops.add(op2);
//						return true;
					}
				}else if(mimetype.equals("vnd.android.cursor.item/name")){
					if(!data1.equals(specifiedcontact.getName())){
						  values.clear();
						  values.put("data1", specifiedcontact.getName());
						  values.put("mimetype", "vnd.android.cursor.item/name");
						  ContentProviderOperation op3 = ContentProviderOperation.newUpdate(dataUri).withValues(values).build();
						  ops.add(op3);
//						return true;
					}
				}
			}
			try {
				resolver.applyBatch(ContactsContract.AUTHORITY, ops);
			} catch (RemoteException | OperationApplicationException e) {
				e.printStackTrace();
			}
			//��ѯraw_contact_id�µ�����data��¼�������û�иı��򷵻�false
//			return false;
		}
//		else
//		    return false;
	}
	

	
	
}
