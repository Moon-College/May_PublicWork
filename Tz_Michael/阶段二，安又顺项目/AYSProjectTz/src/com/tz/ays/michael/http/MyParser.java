package com.tz.ays.michael.http;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.tz.ays.michael.bean.response.HttpBaseResult;

/**
 * 数据解析工具类
 * @author szm
 *
 */
public class MyParser {

	/**
	 * 
	 * @param json
	 * @param clazz
	 * @return 单个对象
	 */
	public static <T extends HttpBaseResult> T parseToObject(String json,Class clazz){
		T t=(T) JSON.parseObject(json, clazz);
		return t;
	}
	
	/**
	 * 
	 * @param json
	 * @param clazz
	 * @return 返回多个对象
	 */
	public static <T extends HttpBaseResult> List<T> parseToArray(String json,Class clazz){
		List<T> ll=JSON.parseArray(json, clazz);
		return ll;
	}
	
}
