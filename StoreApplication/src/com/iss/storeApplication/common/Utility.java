package com.iss.storeApplication.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author milan
 * 
 */
public class Utility {

	private static Properties prop = new Properties();

	static {
		InputStream input;
		try {
			File file = new File(Constants.PROPERTY_FILE_DIR,
					Constants.PROPERTY_FILE_NAME);
			if (!file.exists()) {
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

	public static String getPropertyValue(String key) {
		return prop.get(key).toString();
	}

}
