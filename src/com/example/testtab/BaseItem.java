package com.example.testtab;

public class BaseItem {
	public String image;
	public String title;
	public String description;
	BaseItem(String s, String t, String d)
	{
		image = s;
		title = t;
		description = d;
	}
	public void set(BaseItem b) {
		// TODO Auto-generated constructor stub
		image = b.image;
		title = b.title;
		description = b.description;
	}
}
