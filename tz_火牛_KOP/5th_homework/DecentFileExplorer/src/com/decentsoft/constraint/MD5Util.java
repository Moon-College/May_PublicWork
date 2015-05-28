package com.decentsoft.constraint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
	private final static String[] hexDigits =
	{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * ͨ�������String��ȡMD5ժҪ�㷨֮���String
	 * 
	 * @param ori
	 *            ��Ҫ���ܵ�string
	 * @return MD5ժҪ�㷨֮���string
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

		return MD5Str;

	}

	/**
	 * byte����ת����string
	 * @param b btye����
	 * @return ת��֮���string
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
	 * byteת���ɶ�Ӧ��String
	 * 
	 * @param b
	 *            ��Ҫת����byte
	 * @return ת��֮���String
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
