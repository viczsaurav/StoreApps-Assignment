package com.iss.storeApplication.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtility {

	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	public static boolean isEmpty(String s) {
		if (s == null)
			return true;
		return s.trim().equals("");
	}
	
	public static String getStringFromDate(Date date)
	{
		return dateFormat.format(date);
	}
	
	public static Date getDateFromString(String s)
	{
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
		
		if(isEmpty(str))
		{
			return true;
		}
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}
