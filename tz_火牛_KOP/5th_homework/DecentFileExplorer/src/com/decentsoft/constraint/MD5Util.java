package com.decentsoft.constraint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class MD5Util
{
	private final static String[] hexDigits =
	{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 通过输入的String获取MD5摘要算法之后的String
	 * 
	 * @param ori
	 *            想要加密的string
	 * @return MD5摘要算法之后的string
	 */
	public static String getMD5String(String ori)
	{
		String MD5Str = null;
		if (ori == null)
		{
			return null;
		}
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			MD5Str = byteArrayToHexString(messageDigest.digest(ori.getBytes()));
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		Log.d("MD5Util","ori="+ori+",get MD5Str="+MD5Str);
		return MD5Str;

	}

	/**
	 * byte数组转换成string
	 * @param b btye数组
	 * @return 转换之后的string
	 */
	private static String byteArrayToHexString(byte[] b)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * byte转换成对应的String
	 * 
	 * @param b
	 *            需要转换的byte
	 * @return 转换之后的String
	 */
	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
