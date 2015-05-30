package com.decentsoft.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SdcardFile
{
    private String name;
    private String path;
    private String property;
    private Bitmap icon;
	private SoftReference<Bitmap> cachedIcon;
	public SoftReference<Bitmap> getCachedIcon()
	{
		return cachedIcon;
	}
	public void setCachedIcon(SoftReference<Bitmap> cachedIcon)
	{
		this.cachedIcon = cachedIcon;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getProperty()
	{
		return property;
	}
	public void setProperty(String property)
	{
		this.property = property;
	}
	public Bitmap getIcon()
	{
		return icon;
	}
	public void setIcon(Bitmap icon)
	{
		this.icon = icon;
	}
}
