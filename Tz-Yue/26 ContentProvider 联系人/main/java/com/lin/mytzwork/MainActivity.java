package com.lin.mytzwork;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) this.findViewById(R.id.bt);
        bt.setOnClickListener(this);

    }

    private void read() {
        List<ContactInfo> list = new ArrayList<>();
        ContentResolver cr = this.getContentResolver();

        Uri raw_contacts_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contacts");
        Cursor rawC = cr.query(raw_contacts_uri, new String[]{"_id", "display_name"}, null, null, null);
        while (rawC.moveToNext()) {
            ContactInfo info = new ContactInfo();
            String display_name = rawC.getString(rawC.getColumnIndex("display_name"));
            info.setName(display_name);

            int _id = rawC.getInt(rawC.getColumnIndex("_id"));
            Uri data_Url = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contacts/" + _id + "/data");
            Cursor dataC = cr.query(data_Url, null, null, null, null);
            while (dataC.moveToNext()) {
                String data1 = dataC.getString(dataC.getColumnIndex("data1"));
                String mimetype = dataC.getString(dataC.getColumnIndex("mimetype"));

                if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                    info.setPhone(data1);
                } else if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                    info.setEmail(data1);
                }
            }
            list.add(info);
        }


        for (ContactInfo info : list) {
            Log.i("info", info.toString());
        }
    }


    @Override
    public void onClick(View v) {
        read();
        ContactInfo info = new ContactInfo();
        info.setName("打哈哈").setEmail("123@qq.com").setPhone("438438");
        transactionCommit(info);
        read();
    }

    private void transactionCommit(ContactInfo info) {
        ContentResolver cr = this.getContentResolver();
        ArrayList<ContentProviderOperation> list = new ArrayList<>();

        Uri raw_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contacts");
        ContentValues values = new ContentValues();
        ContentProviderOperation cpo0 = ContentProviderOperation.newInsert(raw_uri).withValues(values).build();
        list.add(cpo0);


        Uri data_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "data");

        values.clear();
        values.put("data1", info.getPhone());
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        ContentProviderOperation cpo1 = ContentProviderOperation.newInsert(data_uri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
        list.add(cpo1);

        values.clear();
        values.put("data1", info.getEmail());
        values.put("mimetype", "vnd.android.cursor.item/email_v2");
        ContentProviderOperation cpo2 = ContentProviderOperation.newInsert(data_uri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
        list.add(cpo2);

        values.clear();
        values.put("data1", "娃哈哈");
        values.put("mimetype", "vnd.android.cursor.item/name");
        ContentProviderOperation cpo3 = ContentProviderOperation.newInsert(data_uri).withValueBackReference("raw_contact_id", 0).withValues(values).build();
        list.add(cpo3);


        try {
            cr.applyBatch(ContactsContract.AUTHORITY, list);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

    }
}
