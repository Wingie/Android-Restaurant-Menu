package com.example.testtab;

/**
 * This class is used specifically to pass these 3 strings.
 * passing the XMLresource started giving wierd errors with the garbage collector..
 * 
 * @author WINDAdmin
 *
 */
public class BaseItem {
	public String image;
	public String title;
	public String description;
	public String price;
	public int 	num;
	BaseItem(String s, String t, String d,String p)
	{
		image = s;
		title = t;
		description = d;
		price = p;
		num = 1;
	}
}
