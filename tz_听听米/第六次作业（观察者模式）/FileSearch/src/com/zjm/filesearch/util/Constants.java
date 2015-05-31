package com.zjm.filesearch.util;

import android.os.Environment;
import android.util.Log;

public class Constants {
	public static final String TAG = Constants.class.getSimpleName().toString();

	public static final String root = Environment.getExternalStorageDirectory()
			.getAbsolutePath().toString();

	public static final String[] PIC_SUFFIX = { ".png", ".bmp", ".jpg", ".gif", };

	private static final String[][] MIME_MapTable = {
			// {后缀名，MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

	/**
	 * @author 听听米
	 * @data 2015-5-31下午4:18:37
	 * @belong com.zjm.filesearch.util.Constants
	 * @describe：是否是图片文件名
	 * @param fileName
	 * @return boolean
	 */
	public static boolean isPicFileName(String fileName) {
		if (fileName == null) {
			return false;
		}
		for (int i = 0; i < PIC_SUFFIX.length; i++) {
			if (fileName.toLowerCase().endsWith(PIC_SUFFIX[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @author 听听米
	 * @data 2015-5-31下午4:20:29
	 * @belong com.zjm.filesearch.util.Constants
	 * @describe：通过文件名获取MIME类型字符串
	 * @param fileNname
	 * @return String
	 */
	public static String getFileMIMEType(String fileNname) {
    	if(fileNname==null){
    		return null;
    	}
    	String type = "*/*";
    	int lastIndex = fileNname.lastIndexOf(".");
    	if(lastIndex < 0){
    		Log.d(TAG, "lastIndex="+lastIndex);
    		return type;
    	}
    	String suffix = fileNname.substring(lastIndex, fileNname.length()).toLowerCase();
    	Log.d(TAG, "suffix="+suffix);
    	if("".equals(suffix)){
    		return type;
    	}
    	for(int i=0; i<MIME_MapTable.length; i++){
    	    if(suffix.equals(MIME_MapTable[i][0]))
    	    {
    	    	return MIME_MapTable[i][1];
    	    }
    	}
		return "";
	}

}
