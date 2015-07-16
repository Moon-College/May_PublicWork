package com.dd.dd_sqlite;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.dd.dd_sqlite.bean.NbaStar;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void parseXml() throws Exception{
		List<NbaStar> stars = new ArrayList<NbaStar>();
		XmlPullParser pull = Xml.newPullParser();//创建pull解析的核心类
		InputStream inputStream = this.getAssets().open("nba_stars.xml");
		pull.setInput(inputStream, "utf-8");
		int type = pull.getEventType();//解析第一行的类型
		NbaStar nbaStar = null;
		while(type!=XmlPullParser.END_DOCUMENT){
			//说明可以继续往下解析
			switch (type) {
			case XmlPullParser.START_TAG:
				//主要解析<star>
				String startTagName = pull.getName();
				if(startTagName.equals("star")){
					nbaStar = new NbaStar();
					int count = pull.getAttributeCount();
					for(int i = 0;i<count;i++){
						String value = pull.getAttributeValue(i);
						nbaStar.setId(Integer.valueOf(value));
					}
				}else if(startTagName.equals("name")){
					String nextText = pull.nextText();
					nbaStar.setName(nextText);
				}else if(startTagName.equals("age")){
					String age = pull.nextText();
					nbaStar.setAge(Integer.valueOf(age));
				}else if(startTagName.equals("number")){
					String number = pull.nextText();
					nbaStar.setNumber(Integer.valueOf(number));
				}
				break;
			case XmlPullParser.END_TAG:
				String endTagName = pull.getName();
				if(endTagName.equals("star")){
					stars.add(nbaStar);
				}
				break;
			default:
				break;
			}
			type = pull.next();
		}
		for(NbaStar star : stars){
			Log.i("home", star.getName());
		}
	}
}
