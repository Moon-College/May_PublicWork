package com.chris.contactsrw;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContactsRWActivity extends Activity implements OnClickListener
{

	private static final String tag = "ContactsRWActivity";
	private Button queryBtn, insertBtn, deleteBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_rw);
		queryBtn = (Button) findViewById(R.id.qureyBtn);
		insertBtn = (Button) findViewById(R.id.insertBtn);
		deleteBtn = (Button) findViewById(R.id.deleteBtn);
		queryBtn.setOnClickListener(this);
		insertBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
	}

	private void queryData()
	{
		ContentResolver cr = this.getContentResolver();
		Cursor rawContactsCursor = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, null, null, null);
		int count = rawContactsCursor.getCount();
		Log.d(tag, "there are " + count + " rows in the table");

		while (rawContactsCursor.moveToNext())
		{
			int _id = rawContactsCursor.getInt(rawContactsCursor.getColumnIndex(ContactsContract.RawContacts._ID));
			Log.d(tag, "id = " + _id);
			Log.d(tag, "name = " + rawContactsCursor.getString(rawContactsCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)));
			Cursor dataCursor = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.RAW_CONTACT_ID+"=?", new String[]{String.valueOf(_id)}, null);
			while (dataCursor.moveToNext())
			{
				String mimeType = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
				if(mimeType.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE))
				{
					Log.d(tag, "data name = "+dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1)));
				}
				else if(mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE))
				{
					Log.d(tag, "data phone = "+dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1)));
				}
				else if(mimeType.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE))
				{
					Log.d(tag, "data email = "+dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1)));
				}
			}
		}

	}
	
	private void insertPerson(MyPerson person)
	{
		ContentResolver cr = this.getContentResolver();
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ContentValues values = new ContentValues();
		values.clear();
		values.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, person.getName());
		ContentProviderOperation op1 = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValues(values).build();
		ops.add(op1);
		
		values.clear();
		values.put(ContactsContract.Data.DATA1, person.getName());
		values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValues(values).build();
		ops.add(op2);
		
		values.clear();
		values.put(ContactsContract.Data.DATA1, person.getPhone());
		values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		ContentProviderOperation op3 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValues(values).build();
		ops.add(op3);
		
		values.clear();
		values.put(ContactsContract.Data.DATA1, person.getEmail());
		values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
		ContentProviderOperation op4 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValues(values).build();
		ops.add(op4);
		
		try
		{
			cr.applyBatch(ContactsContract.AUTHORITY, ops);
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (OperationApplicationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deletePerson(MyPerson person)
	{
		ContentResolver cr = this.getContentResolver();
		Cursor rawContactsCursor = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY+"=?", new String[]{person.getName()}, null);
		while (rawContactsCursor.moveToNext())
		{
			int _id = rawContactsCursor.getInt(rawContactsCursor.getColumnIndex(ContactsContract.RawContacts._ID));
			cr.delete(ContactsContract.Data.CONTENT_URI, ContactsContract.Data.RAW_CONTACT_ID+"=?", new String[]{String.valueOf(_id)});
			cr.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY+"=?", new String[]{person.getName()});
		}
	}

	@Override
	public void onClick(View v)
	{
		MyPerson person = new MyPerson();
		person.setName("小艳");
		person.setEmail("babyyr00x@163.com");
		person.setPhone("19999999999");
		
		switch (v.getId())
		{
			case R.id.qureyBtn:
				queryData();
				break;
			case R.id.insertBtn:
				insertPerson(person);
				break;
			case R.id.deleteBtn:
				deletePerson(person);
				break;

			default:
				break;
		}
	}

}
