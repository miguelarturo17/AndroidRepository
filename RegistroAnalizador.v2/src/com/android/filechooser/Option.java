package com.android.filechooser;

public class Option implements Comparable<Option>{
	private String name;
	private String data;
	private String path;
	
	public Option(String valueName, String valueLabel, String valuePath)
	{
		name = valueName;
		data = valueLabel;
		path = valuePath;
	}
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	public String getPath()
	{
		return path;
	}
	@Override
	public int compareTo(Option o) {
		if(this.name != null)
			return this.name.toLowerCase().compareTo(o.getName().toLowerCase()); 
		else 
			throw new IllegalArgumentException();
	}
}
