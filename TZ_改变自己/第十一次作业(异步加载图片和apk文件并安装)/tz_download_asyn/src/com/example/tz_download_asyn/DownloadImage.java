package com.example.tz_download_asyn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage {
	public static InputStream getImageStream(String imageUrl) {
		
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			return urlConnection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
}
