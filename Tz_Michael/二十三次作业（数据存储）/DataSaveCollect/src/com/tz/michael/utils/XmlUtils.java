package com.tz.michael.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Xml;
import com.tz.michael.bean.Car;
/**
 * xml解析的练习
 * @author admin
 *
 */
public class XmlUtils {

	public static List<Car> getCarList(Context context){
		List<Car> ll=new ArrayList<Car>();
		try {
			XmlPullParser parser=(XmlPullParser) Xml.newPullParser();
			InputStream inputStream=context.getAssets().open("car.xml");
			parser.setInput(inputStream, "utf-8");
			int state=parser.getEventType();
			Car car=null;
			while(state!=XmlPullParser.END_DOCUMENT){
				switch (state) {
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("example")){
						car=new Car();
						for(int i=0;i<parser.getAttributeCount();i++){
							String name=parser.getAttributeValue(i);
							car.setName(name);
						}
					}else if(parser.getName().equals("color")){
						String color=parser.nextText();
						car.setColor(color);
					}else if(parser.getName().equals("price")){
						String price=parser.nextText();
						car.setPrice(Integer.valueOf(price));
					}
					break;
				case XmlPullParser.END_TAG:
					ll.add(car);
					break;
				default:
					break;
				}
				state=parser.next();
			}
			return ll;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
}
