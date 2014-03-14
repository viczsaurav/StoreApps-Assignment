package com.iss.storeApplication.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {

	private static Properties prop = new Properties();
	
	static{
		InputStream input;
		try {
			//URL url = Utility.class.getResource(Constansts.PROPERTY_FILE_NAME);
			File file=new File(Constansts.PROPERTY_FILE_DIR,Constansts.PROPERTY_FILE_NAME);
			file.getAbsoluteFile();
			if(!file.exists())
			{
				file.createNewFile();
			}
			input = new FileInputStream(file);
			prop.load(input);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
		catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	
	public static String getPropertyValue(String key)
	{
		return prop.get(key).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Utility.getPropertyValue(Constansts.PRODUCT_NAME));
	}
}
