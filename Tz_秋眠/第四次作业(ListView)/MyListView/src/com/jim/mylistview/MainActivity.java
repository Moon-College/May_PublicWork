package com.jim.mylistview;

import java.util.ArrayList;
import java.util.List;

import com.jim.mylistview.adapter.MyAdapter;
import com.jim.mylistview.beans.Persons;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
	private ListView listView;
	private List<Persons> data = new ArrayList<Persons>();
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		addData();
		initView();
	}

	/**
	 * ��ʼ��ListView
	 */
	private void initView() {
		listView = (ListView) findViewById(R.id.listview);

		adapter = new MyAdapter(data, this);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final int position = arg2;
				final String mName = data.get(arg2).getName();
				AlertDialog.Builder ad = new AlertDialog.Builder(
						MainActivity.this);
				ad.setMessage("�Ƿ�ɾ���ú���");
				ad.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
								adapter.removeItem(position);
								adapter.notifyDataSetChanged();
								Toast.makeText(MainActivity.this,
										"��ɾ��" + mName, Toast.LENGTH_SHORT)
										.show();
							}
						});
				ad.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						});
				ad.create().show();

			}
		});
	}

	/**
	 * �������
	 */
	private void addData() {
		data.add(new Persons("̶��-David��ʦ", "��", "�ô���", 75,
				R.drawable.icon_david));
		data.add(new Persons("̶��-Grace��ʦ", "Ů", "�ԡ�˯������", 85,
				R.drawable.icon_grace));
		data.add(new Persons("̶��-Jason��ʦ", "��", "�����������ô���", 78,
				R.drawable.icon_jason));
		data.add(new Persons("̶��-������ʦ", "��", "�Ķ����ô���", 81,
				R.drawable.icon_hongyu));
		data.add(new Persons("̶��-��ī��ʦ", "��", "�˶����ô���", 78, R.drawable.icon_zimo));
		data.add(new Persons("̶��-Ӱ����ʦ", "Ů", "�ԡ�˯����˧��", 82,
				R.drawable.icon_yingzi));
		data.add(new Persons("̶��-Eyre��ʦ", "��", "�ô���", 76, R.drawable.icon_eyre));
		data.add(new Persons("̶��-������ʦ", "Ů", "�ԡ�˯�����", 86,
				R.drawable.icon_yaoyao));
		data.add(new Persons("̶��-GEMTLEMAN-����", "��", "ߣ��ߣ���ô���", 77,
				R.drawable.icon_gemtleman));
		data.add(new Persons("̶��-����", "Ů", "�ԡ�˯���ô���", 84,
				R.drawable.icon_xuaner));
		data.add(new Persons("̶��-���Х-����", "��", "�ô���", 76,
				R.drawable.icon_qingfengxiao));
		data.add(new Persons("̶��-Rocy-��ɳ", "��", "�ô���", 73, R.drawable.icon_rock));
		data.add(new Persons("̶��-Danny", "��", "�ô��롢�ڿ�", 83,
				R.drawable.icon_danny));
	}

}
