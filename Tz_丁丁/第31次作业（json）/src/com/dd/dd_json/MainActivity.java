package com.dd.dd_json;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
     * ����ת��json�ַ���
     */
    public String objectToJson(Object student){
    	String jsonString = com.alibaba.fastjson.JSON.toJSONString(student);
    	return jsonString;
    }
    
    /**
     * json�ַ���ת����
     */
    
    public Object stringToObject(String json){
    	Object student = com.alibaba.fastjson.JSONObject.parseObject(json, Object.class);
    	return student;
    }
    
    /**
     * ��������תjson�ַ���
     */
    public String arrayToJson(List<Object> students){
    	String string = com.alibaba.fastjson.JSON.toJSONString(students);
    	return string;
    }
    
    /**
     * json�ַ�������ת��������
     */
    public List<Object> jsonToObjectArray(String json){
    	List<Object> array = com.alibaba.fastjson.JSONArray.parseArray(json, Object.class);
    	return array;
    }

}
